package com.hargclinical.harg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hargclinical.harg.entities.Comorbidades;
import com.hargclinical.harg.entities.Paciente;
import com.hargclinical.harg.services.PacienteService;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoResource {
    @Autowired
    private PacienteService service;


    @GetMapping("/{id}")
    public ModelAndView paginaOrcamento(ModelMap model, @PathVariable Long id) {
        Paciente paciente = service.findById(id);

        ModelAndView viewPage = new ModelAndView("/html/templates/pagina-orcamento.html");
        viewPage.addObject("id", paciente.getId());
        viewPage.addObject("nome", paciente.getNome());
        viewPage.addObject("cpf", paciente.getCpf());
        viewPage.addObject("idade", paciente.getIdade());
        viewPage.addObject("sexo", paciente.getSexo());
        
        Comorbidades comorbidade = paciente.getComorbidades();
        viewPage.addObject("fatorRisco", comorbidade.getFactorR());

        if (comorbidade.isGestante())
            viewPage.addObject("gestante", "SIM");
        else
            viewPage.addObject("gestante", "NÃO");

        if (comorbidade.isDiabetes())
            viewPage.addObject("diabetico", "SIM");
        else
            viewPage.addObject("diabetico", "NÃO");

        if (comorbidade.isHipertensao())
            viewPage.addObject("hipertenso", "SIM");
        else
            viewPage.addObject("hipertenso", "NÃO");

        if (comorbidade.isIdade())
            viewPage.addObject("idoso", "SIM");
        else
            viewPage.addObject("idoso", "NÃO");

        if (comorbidade.isObesidade())
            viewPage.addObject("obeso", "SIM");
        else
            viewPage.addObject("obeso", "NÃO");

        if (comorbidade.isTabagismo())
            viewPage.addObject("fumante", "SIM");
        else
            viewPage.addObject("fumante", "NÃO");

        return viewPage;
    }
}
