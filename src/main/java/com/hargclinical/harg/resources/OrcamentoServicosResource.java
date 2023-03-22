package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.OrcamentoServicos;
import com.hargclinical.harg.repositories.OrcamentoServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cadastrar-orcamento-servicos")
public class OrcamentoServicosResource {

    @Autowired
    private OrcamentoServicosRepository orcamentoServicosRepository;

    @GetMapping
    public List<OrcamentoServicos> getAllOrcamentoServicos() {
        return orcamentoServicosRepository.findAll();
    }

    @PostMapping
    public OrcamentoServicos addOrcamentoServicos(@RequestBody OrcamentoServicos orcamentoServicos) {
        return orcamentoServicosRepository.save(orcamentoServicos);
    }
}