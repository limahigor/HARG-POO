package com.hargclinical.harg.services;

import com.hargclinical.harg.entities.*;
import com.hargclinical.harg.entities_enums.Plano;
import com.hargclinical.harg.repositories.AppointmentRepository;
import com.hargclinical.harg.repositories.OrcamentoMedicamentosRepository;
import com.hargclinical.harg.repositories.OrcamentoServicosRepository;
import com.hargclinical.harg.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Arrays;
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
        Orcamento orcamento = new OrcamentoServicos();
        
        List<Double> valores = new ArrayList<>();
        
        for(Appointment consulta : consultas){
            valores.add(consulta.getService().getValor());
        }
        
        orcamento.gerarOrcamento(valores, paciente.getPlanoSaude());
        
        if(orcamento instanceof OrcamentoServicos){
            OrcamentoServicos orcamentoServicos = (OrcamentoServicos) orcamento;
            List<Appointment> list = orcamentoServicos.getAppointments();
            list.addAll(consultas);
        }

        return orcamento;
    }

    public Orcamento gerarOrcamentoPrescricao(Prescricao prescricao){
        Orcamento orcamento = new OrcamentoMedicamentos();
        
        List<Double> valores = new ArrayList<>();
        
        for(MedicamentoPrescrito medicamentoPrescrito : prescricao.getMedicamentos()){
            valores.add(medicamentoPrescrito.getValor());
        }
        
        orcamento.gerarOrcamento(valores, prescricao.getPaciente().getPlanoSaude());

        if(orcamento instanceof OrcamentoMedicamentos){
            OrcamentoMedicamentos orcamentoMedicamentos = (OrcamentoMedicamentos) orcamento;
            orcamentoMedicamentos.setPrescricao(prescricao);
        }

        return orcamento;
    }

    public void insertOrcamentoMedicamentos(OrcamentoMedicamentos orcamento){
        orcamento.getPrescricao().setOrcamentoGerado(true);

        caixaService.addOrcamento(orcamento);

        medicamentosRepository.save(orcamento);
    }

    public void insertOrcamentoServicos(OrcamentoServicos orcamento){
        for(Appointment consulta : orcamento.getAppointments()){
            consulta.setOrcamentoGerado(true);
        }
        appointmentRepository.saveAll(orcamento.getAppointments());

        caixaService.addOrcamento(orcamento);

        servicosRepository.save(orcamento);
    }

}
