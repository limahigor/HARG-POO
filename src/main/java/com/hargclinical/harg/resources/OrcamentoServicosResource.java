package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.entities.OrcamentoServicos;
import com.hargclinical.harg.repositories.CaixaRepository;
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

    @Autowired
    private CaixaRepository caixaRepository;

    @GetMapping
    public List<OrcamentoServicos> getOrcamentos() {
        return orcamentoServicosRepository.findAll();
    }

    @PostMapping
    public OrcamentoServicos saveOrcamento(OrcamentoServicos orcamentoServicos) {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa = caixas.get(caixas.size()-1);
        caixa.addMovimentacoesCaixa(orcamentoServicos);
        return orcamentoServicosRepository.save(orcamentoServicos);
    }
}