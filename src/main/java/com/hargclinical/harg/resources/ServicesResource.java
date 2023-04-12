package com.hargclinical.harg.resources;

import java.io.Console;
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
    public ResponseEntity<String> adicionarServico(@RequestBody String servicoJson){
        ObjectMapper mapper = new ObjectMapper();

        Services newServices;

        Agenda novaAgenda = new Agenda();
        List<Dias> dias = new ArrayList<>();

        try{
            JsonNode node = mapper.readTree(servicoJson);

            String nomeService = node.get("nome").asText();
            String especialidadeService = node.get("especialidade").asText();
            double valorService = node.get("valor").asDouble();
            String tipoService = node.get("tipo").asText();

            List <Services> servicos = service.findAll();
            for(Services servico : servicos) {
                if(servico.getNome().equals(nomeService)) {
                    throw new IllegalArgument("Nome de serviço já cadastrado.");
                }
            }

            if(valorService <= 0) {
                throw new IllegalArgument("Valor inválido.");
            }

            if(tipoService.equals("exame")){
                newServices = new ServExame();
            }else if(tipoService.equals("consulta")){
                ServConsulta aux = new ServConsulta();
                aux.setTime(30);

                newServices = aux;
            }else if(tipoService.equals("procedimento")){
                newServices = new ServProcedimento();
            }else{
                return ResponseEntity.badRequest().body("Falha ao cadastrar");
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

        }catch (IllegalArgument e) {
            System.out.println("ERROR!");
            return ResponseEntity.badRequest().build();

        }catch(Exception e){
            System.out.println("Servico Error!!!");
            System.out.println(e.getMessage());

            return ResponseEntity.badRequest().body("Falha ao cadastrar");
        }

    }
    
}