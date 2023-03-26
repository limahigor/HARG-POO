package com.hargclinical.harg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping(value = "/medico")
public class MedicoResource{

    @Autowired
    private MedicoService service;

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> searchMedicoByName(@RequestParam("name") String name) {
        List<Medico> medicos = null;
        
        if(name.isEmpty()){
            System.out.println("Parametro vazio");
            medicos = service.findAll();
        }else{
            if(StringUtils.containsLettersAndDigits(name)){

                return ResponseEntity.badRequest().body(null);

            }else if(StringUtils.containsOnlyDigits(name)){

                medicos = service.findByCrmContaining(name);

            }else if(StringUtils.containsOnlyLetters(name)){

                medicos = service.findByNameContaining(name);

            }
        }

        return ResponseEntity.ok().body(medicos);
    }
    
    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id){
        Medico medico = service.findById(id);

        ModelAndView viewPage = new ModelAndView("/html/templates/pagina-medico.html");
        viewPage.addObject("nome", medico.getNome());
        viewPage.addObject("crm", medico.getCrm());
        viewPage.addObject("especializacao", medico.getEspecializacao());

        return viewPage;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico jsonData) {
        System.out.println(jsonData.nome);
        jsonData = service.insert(jsonData);
        return ResponseEntity.ok().body(jsonData);
    }

}