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

    public OrcamentoServicos gerarOrcamentoServicos(List<Appointment> consultas, Paciente paciente){
        OrcamentoServicos orcamentoServicos = new OrcamentoServicos();
        List<Appointment> list = orcamentoServicos.getAppointments();
        list.addAll(consultas);

        List<Double> valores = new ArrayList<>();

        for(Appointment consulta : consultas){
            valores.add(consulta.getService().getValor());
        }

        orcamentoServicos.gerarOrcamento(valores, paciente.getPlanoSaude());

        return orcamentoServicos;
    }

    public OrcamentoMedicamentos gerarOrcamentoPrescricao(Prescricao prescricao){
        OrcamentoMedicamentos orcamentoMedicamentos = new OrcamentoMedicamentos();
        orcamentoMedicamentos.setPrescricao(prescricao);

        List<Double> valores = new ArrayList<>();

        for(MedicamentoPrescrito medicamentoPrescrito : prescricao.getMedicamentos()){
            valores.add(medicamentoPrescrito.getValor());
        }

        orcamentoMedicamentos.gerarOrcamento(valores, prescricao.getPaciente().getPlanoSaude());

        return orcamentoMedicamentos;
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
