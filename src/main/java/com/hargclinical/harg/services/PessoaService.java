package com.hargclinical.harg.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.utils.CPF;
import com.hargclinical.harg.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PessoaService {
    public Paciente createPersonFromJson(String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        Paciente newPaciente;

        try {
            JsonNode node = mapper.readTree(jsonData);
            JsonNode comorbidadesNode = node.get("comorbidades");

            LocalDate date = parseDate(node);
            String cpf = extractCpf(node);
            char sexo = extractSexo(node);
            int planoId = extractPlanoId(node);

            String nome = node.get("nome").asText();
            Plano plano = Plano.valueOf(planoId);

            Comorbidades comorbidadesJson = processComorbidade(comorbidadesNode, date);

            validateGestante(sexo, comorbidadesJson.isGestante());

            newPaciente = new Paciente(nome, cpf, date, sexo, plano);

            newPaciente.setProntuario();
            newPaciente.getProntuario().setComorbidades(comorbidadesJson);
            comorbidadesJson.setProntuario(newPaciente.getProntuario());

            return newPaciente;
        }catch (IllegalArgument e){
            throw new IllegalArgument(e.getMessage());
        }catch(Exception e) {
            throw new IllegalArgument("Erro ao cadastrar! Verifique os dados!");
        }
    }

    public String extractString(String jsonData, String campo){
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode node = mapper.readTree(jsonData);
            return node.get(campo).asText();
        }catch(Exception e) {
            throw new IllegalArgument("Erro ao cadastrar! Verifique os dados!");
        }
    }

    private LocalDate parseDate(JsonNode node) {
        return LocalDate.parse(node.get("date").asText());
    }

    private String extractCpf(JsonNode node) {
        String cpf = node.get("cpf").asText();

        if (!CPF.isCPFValid(cpf)) {
            throw new IllegalArgumentException("CPF Inválido!");
        }
        return cpf;
    }

    private char extractSexo(JsonNode node) {
        String sexoString = node.get("sexo").asText();
        char sexo = sexoString.charAt(0);

        if (sexo != 'M' && sexo != 'F') {
            throw new IllegalArgumentException("Sexo inválido!");
        }
        return sexo;
    }

    private int extractPlanoId(JsonNode node) {
        String planoIdStr = node.get("plano").asText();

        if (!StringUtils.containsOnlyDigits(planoIdStr)) {
            throw new IllegalArgumentException("Plano inválido! Somente dígitos [0-4]!");
        }
        return Integer.parseInt(planoIdStr);
    }

    private Comorbidades processComorbidade(JsonNode comorbidadesNode, LocalDate date) {
        boolean tabagismo = false;
        boolean hipertensao = false;
        boolean gestante = false;
        boolean diabetes = false;
        boolean obesidade = false;

        for (JsonNode jNode : comorbidadesNode) {
            String idString = jNode.get("id").asText();
            boolean valor = jNode.get("value").asBoolean();
            switch (idString) {
                case "tabagismo" -> tabagismo = valor;
                case "obesidade" -> obesidade = valor;
                case "hipertensao" -> hipertensao = valor;
                case "gestante" -> gestante = valor;
                case "diabetes" -> diabetes = valor;
                default -> throw new IllegalArgument("Comorbidade inválida!!");
            }
        }

        return new Comorbidades(tabagismo, obesidade, hipertensao, gestante, diabetes, date);
    }

    private void validateGestante(char sexo, boolean gestante) {
        if (sexo == 'M' && gestante) {
            throw new IllegalArgumentException("Falha ao cadastrar!");
        }
    }
}
