package com.hargclinical.harg.services;

import com.hargclinical.harg.entities.*;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.repositories.OrcamentoMedicamentosRepository;
import com.hargclinical.harg.repositories.OrcamentoServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrcamentoService {
    @Autowired
    OrcamentoMedicamentosRepository medicamentosRepository;

    @Autowired
    OrcamentoServicosRepository servicosRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CaixaService caixaService;

    public Orcamento gerarOrcamentoServicos(List<Appointment> consultas, Paciente paciente){
        OrcamentoServicos orcamento = new OrcamentoServicos();
        
        List<Double> valores = new ArrayList<>();
        
        for(Appointment consulta : consultas){
            valores.add(consulta.getService().getValor());
        }
        
        orcamento.gerarOrcamento(valores, paciente.getPlanoSaude());

        List<Appointment> list = orcamento.getAppointments();
        list.addAll(consultas);

        return orcamento;
    }

    public Orcamento gerarOrcamentoPrescricao(List<Prescricao> prescricoes, Paciente paciente){
        OrcamentoMedicamentos orcamento = new OrcamentoMedicamentos();
        
        List<Double> valores = new ArrayList<>();

        for(Prescricao prescricao : prescricoes){
            for(MedicamentoPrescrito medicamentoPrescrito : prescricao.getMedicamentos()){
                valores.add(medicamentoPrescrito.getValor());
            }
        }

        orcamento.gerarOrcamento(valores, paciente.getPlanoSaude());

        List<Prescricao> list = orcamento.getPrescricao();
        list.addAll(prescricoes);

        return orcamento;
    }

    public void insertOrcamentoMedicamentos(OrcamentoMedicamentos orcamento){
        caixaService.addOrcamento(orcamento);

        for(Prescricao prescricao : orcamento.getPrescricao()){
            prescricao.setOrcamentoGerado(true);
        }

        medicamentosRepository.save(orcamento);
    }

    public void insertOrcamentoServicos(OrcamentoServicos orcamento){
        appointmentRepository.saveAll(orcamento.getAppointments());

        caixaService.addOrcamento(orcamento);

        for(Appointment consulta : orcamento.getAppointments()){
            consulta.setOrcamentoGerado();
        }

        servicosRepository.save(orcamento);
    }

}
