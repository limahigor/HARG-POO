package com.hargclinical.harg.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hargclinical.harg.entities.*;
import com.hargclinical.harg.services.*;
import com.hargclinical.harg.services.exceptions.IllegalArgument;
import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoResource {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescricaoService prescricaoService;

    @Autowired
    private OrcamentoService orcamentoServices;

    @GetMapping("/{id}")
    public ModelAndView paginaOrcamento(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.findById(id);
            ModelAndView viewPage = new ModelAndView("/html/templates/pagina-orcamento.html");

            return pacienteService.getModelAndView(paciente, viewPage);
        }catch(ResourceNotFoundException e) {
            return new ModelAndView("/html/templates/404.html");
        }
    }

    @PostMapping("/valor-total")
    public String calcularValorTotal(@RequestBody String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            double total = 0;
            Map<String, Object> data = objectMapper.readValue(jsonData, Map.class);

            String type = (String) data.get("type");
            Long idPaciente = Long.parseLong((String)data.get("paciente"));
            List<String> stringIds = (List<String>) data.get("ids");
            List<Long> ids = stringIds.stream()
                             .map(Long::parseLong)
                             .collect(Collectors.toList());

            Paciente paciente = pacienteService.findById(idPaciente);

            if(type.equals("consultas")){
                List<Appointment> consultas = new ArrayList<>();
                for(Long id : ids){
                    consultas.add(appointmentService.findById(id));
                }
                Orcamento orcamento = orcamentoServices.gerarOrcamentoServicos(consultas, paciente);

                total = orcamento.getValor();
            }else if(type.equals("prescricao")){
                Prescricao prescricao = null;

                for(Long id : ids) {
                    prescricao = prescricaoService.findById(id);
                }
                Orcamento orcamento = orcamentoServices.gerarOrcamentoPrescricao(prescricao);

                total = orcamento.getValor();
            }

            return String.valueOf(total);
        } catch (Exception e) {
            e.printStackTrace();
            return "erro";
        }
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> cadastrarOrcamento(@RequestBody String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, Object> data = objectMapper.readValue(jsonData, Map.class);

            String type = (String) data.get("type");
            Long idPaciente = Long.parseLong((String) data.get("paciente"));
            List<String> stringIds = (List<String>) data.get("ids");
            List<Long> ids = stringIds.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            Paciente paciente = pacienteService.findById(idPaciente);

            if (type.equals("consultas")) {
                List<Appointment> consultas = new ArrayList<>();
                for (Long id : ids) {
                    consultas.add(appointmentService.findById(id));
                }
                OrcamentoServicos orcamento = (OrcamentoServicos) orcamentoServices.gerarOrcamentoServicos(consultas, paciente);
                orcamentoServices.insertOrcamentoServicos(orcamento);
            } else if (type.equals("prescricao")) {
                Prescricao prescricao = null;

                for (Long id : ids) {
                    prescricao = prescricaoService.findById(id);
                }
                OrcamentoMedicamentos orcamento = (OrcamentoMedicamentos) orcamentoServices.gerarOrcamentoPrescricao(prescricao);
                orcamentoServices.insertOrcamentoMedicamentos(orcamento);
            }

            return ResponseEntity.ok().body("Orçamento gerado com sucesso!!");
        } catch (Exception e) {
            throw new IllegalArgument("Erro ao gerar!!");
        }
    }
}
