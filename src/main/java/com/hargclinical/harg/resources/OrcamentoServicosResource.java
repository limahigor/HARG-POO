package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.OrcamentoServicos;
import com.hargclinical.harg.repositories.OrcamentoServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orcamento-servicos")
public class OrcamentoServicosResource {

    @Autowired
    private OrcamentoServicosRepository orcamentoServicosRepository;

    @GetMapping
    public List<OrcamentoServicos> getOrcamentos() {
        return orcamentoServicosRepository.findAll();
    }

    @PostMapping
    public OrcamentoServicos saveOrcamento(OrcamentoServicos orcamentoServicos) {
        return orcamentoServicosRepository.save(orcamentoServicos);
    }
}