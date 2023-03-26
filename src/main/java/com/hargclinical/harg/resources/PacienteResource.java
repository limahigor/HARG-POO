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

import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.services.PacienteService;
import com.hargclinical.harg.utils.StringUtils;

@RestController
@RequestMapping("/paciente")
public class PacienteResource{

    @Autowired
    private PacienteService service;

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> searchPacienteByName(@RequestParam("name") String name) {
        List<Paciente> pacientes = null;
        
        if(name.isEmpty()){
            System.out.println("Parametro vazio");
            pacientes = service.findAll();
        }else{
            if(StringUtils.containsLettersAndDigits(name)){

                return ResponseEntity.badRequest().body(null);

            }else if(StringUtils.containsOnlyDigits(name)){

                pacientes = service.findByCpfContaining(name);

            }else if(StringUtils.containsOnlyLetters(name)){

                pacientes = service.findByNomeContaining(name);

            }
        }

        return ResponseEntity.ok().body(pacientes);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Paciente> findById(@PathVariable Long id) {
    //     Paciente paciente = service.findById(id);
    //     return ResponseEntity.ok().body(paciente);
    // }

    @GetMapping("/{id}")
    public ModelAndView paginaPaciente(ModelMap model, @PathVariable Long id){
        Paciente paciente = service.findById(id);

        
        ModelAndView teste = new ModelAndView("/html/templates/pagina-paciente.html");
        teste.addObject("nome", paciente.nome);
        teste.addObject("cpf", paciente.cpf);
        teste.addObject("idade", paciente.idade);
        teste.addObject("sexo", paciente.sexo);

        return teste;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente jsonData) {
        System.out.println(jsonData.nome);
        jsonData = service.insert(jsonData);
        return ResponseEntity.ok().body(jsonData);
    }

}