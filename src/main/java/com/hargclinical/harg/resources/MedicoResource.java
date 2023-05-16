package com.hargclinical.harg.resources;

import java.util.List;

import com.hargclinical.harg.services.exceptions.ResourceNotFoundException;
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
import com.hargclinical.harg.services.AgendaService;
import com.hargclinical.harg.services.MedicoService;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.services.ServicesService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping(value = "/medico")
public class MedicoResource{

    @Autowired
    private MedicoService service;

    @Autowired
    private ServicesService serviceService;

    @Autowired
    private PacienteService servicoPaciente;

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> searchMedicoByName(@RequestParam("name") String name) {
        List<Medico> medicos = null;
        
        if(name.isEmpty()){
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

        assert medicos != null;
        if(medicos.isEmpty())return ResponseEntity.badRequest().body(null);
        
        return ResponseEntity.ok().body(medicos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> searchMedicoById(@PathVariable Long id){

        Medico medico = service.findById(id);

        return ResponseEntity.ok().body(medico);
    }
    
    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id){
        try {
            Medico medico = service.findById(id);
            ModelAndView viewPage = new ModelAndView("/html/templates/pagina-medico.html");
            return service.getModelAndView(medico, viewPage);
        }catch(ResourceNotFoundException e) {
            return new ModelAndView("/html/templates/404.html");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody String jsonData){
        Medico newMedico = service.cadastrarMedicoService(jsonData);

        return ResponseEntity.ok().body(newMedico);
    }

}