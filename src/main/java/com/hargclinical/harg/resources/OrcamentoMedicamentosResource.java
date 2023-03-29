package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.Caixa;
import com.hargclinical.harg.entities.OrcamentoMedicamentos;
import com.hargclinical.harg.repositories.CaixaRepository;
import com.hargclinical.harg.repositories.OrcamentoMedicamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos-medicamentos")
public class OrcamentoMedicamentosResource {

    @Autowired
    private OrcamentoMedicamentosRepository orcamentoMedicamentosRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @GetMapping
    public ResponseEntity<List<OrcamentoMedicamentos>> getOrcamentos() {
        List<OrcamentoMedicamentos> orcamentos = orcamentoMedicamentosRepository.findAll();
        return ResponseEntity.ok(orcamentos);
    }

    @PostMapping
    public OrcamentoMedicamentos saveOrcamento(OrcamentoMedicamentos orcamentoServicos) {
        List<Caixa> caixas = caixaRepository.findAll();
        Caixa caixa = caixas.get(caixas.size()-1);
        caixa.addMovimentacoesCaixa(orcamentoServicos);
        return orcamentoMedicamentosRepository.save(orcamentoServicos);
    }

}
