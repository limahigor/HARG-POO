package com.hargclinical.harg.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.MedicamentoPrescrito;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.entities.Prescricao;
import com.hargclinical.harg.repositories.PrescricaoRepository;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoResource {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Prescricao>> listarPrescricoes() {
        List<Prescricao> prescricoes = prescricaoRepository.findAll();
        return ResponseEntity.ok(prescricoes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Prescricao> adicionarPrescricao(@RequestBody String prescricaoJSON) {
        ObjectMapper mapper = new ObjectMapper();
        Prescricao novaPrescricao = new Prescricao();
        List<MedicamentoPrescrito> medicamentos = new ArrayList<>();

        try{    
            JsonNode node = mapper.readTree(prescricaoJSON);

            Long pacienteId = node.get("paciente").asLong();
            Long medicoId = node.get("medico").asLong();

            Medico medico = medicoService.findById(medicoId);
            Paciente paciente = pacienteService.findById(pacienteId);

            for(JsonNode aux : node.get("medicamentos")){
                String nomeMedicament = aux.get("nome").asText();
                double valorMedicamento = aux.get("valor").asDouble();
                int intervaloMedicamento = aux.get("intervalo").asInt();

                MedicamentoPrescrito medicamento = new MedicamentoPrescrito();
                medicamento.setIntervalo(intervaloMedicamento);
                medicamento.setValor(valorMedicamento);
                medicamento.setMedicamento(nomeMedicament);
                medicamentos.add(medicamento);
                medicamento.setPrescricao(novaPrescricao);
            }

            novaPrescricao.setMedicamentos(medicamentos);
            novaPrescricao.setMedico(medico);
            novaPrescricao.setPaciente(paciente);

            prescricaoRepository.save(novaPrescricao);
            return ResponseEntity.ok().body(novaPrescricao);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    
}
