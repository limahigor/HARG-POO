package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.entities.ServConsulta;
import com.hargclinical.harg.entities.ServExame;
import com.hargclinical.harg.entities.ServProcedimento;
import com.hargclinical.harg.entities.Services;
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.ServicesService;
import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping("/services")
public class ServicesResource {

    @Autowired
    private ServicesService service;

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<Services>> getServicos() {
        List<Services> services = service.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Services> findById(@PathVariable Long id) {
		Services obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

    @PostMapping("/cadastrar")
    public ResponseEntity<String> adicionarServico(@RequestBody String servicoJson) {
        ObjectMapper mapper = new ObjectMapper();

        Services newServices;

        Agenda novaAgenda = new Agenda();
        List<Dias> dias = new ArrayList<>();

        try {
            JsonNode node = mapper.readTree(servicoJson);

            double valorService = node.get("valor").asDouble();

            String nomeService = node.get("nome").asText();
            if(StringUtils.containsOnlyDigits(nomeService) || StringUtils.containsLettersAndDigits(nomeService)){
                throw new IllegalArgument("Nome inválido!!");
            }

            String especialidadeService = node.get("especialidade").asText();
            String tipoService = node.get("tipo").asText();

            List<Services> servicos = service.findAll();
            for (Services servico : servicos) {
                if (servico.getNome().equals(nomeService)) {
                    throw new IllegalArgument("Nome de serviço já cadastrado.");
                }
            }

            if (valorService <= 0) {
                throw new IllegalArgument("Valor inválido.");
            }

            switch (tipoService) {
                case "exame" -> newServices = new ServExame();
                case "consulta" -> {
                    ServConsulta aux = new ServConsulta();
                    aux.setTime(30);
                    newServices = aux;
                }
                case "procedimento" -> newServices = new ServProcedimento();
                default -> throw new IllegalArgument("Tipo inválido");
            }

            newServices.setNome(nomeService);
            newServices.setEspecialidade(especialidadeService);
            newServices.setValor(valorService);

            novaAgenda.setListaDias(dias, novaAgenda);
            novaAgenda.setDias(dias);

            novaAgenda.setService(newServices);
            newServices.setAgenda(novaAgenda);

            agendaService.create(novaAgenda);
            service.insert(newServices);

            return ResponseEntity.ok().body("Êxito ao cadastrar");
        } catch (IllegalArgument e) {
            throw new IllegalArgument(e.getMessage());

        } catch (Exception e) {
            throw new IllegalArgument("Falha ao cadastrar");
        }
    }
}