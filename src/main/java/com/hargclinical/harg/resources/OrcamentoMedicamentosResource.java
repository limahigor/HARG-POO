package com.hargclinical.harg.resources;

import com.hargclinical.harg.entities.OrcamentoMedicamentos;
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

    @GetMapping
    public ResponseEntity<List<OrcamentoMedicamentos>> getOrcamentos() {
        List<OrcamentoMedicamentos> orcamentos = orcamentoMedicamentosRepository.findAll();
        return ResponseEntity.ok(orcamentos);
    }

    @PostMapping
    public OrcamentoMedicamentos saveOrcamento(OrcamentoMedicamentos orcamentoServicos) {
        return orcamentoMedicamentosRepository.save(orcamentoServicos);
    }

}
