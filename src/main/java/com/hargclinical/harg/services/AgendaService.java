// package com.hargclinical.harg.services;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.hargclinical.harg.entities.Agenda;
// import com.hargclinical.harg.entities.Appointment;
// import com.hargclinical.harg.repositories.AgendaRepository;

// @Service
// public class AgendaService {

//     @Autowired
//     private AgendaRepository agendaRepository;

//     public List<Agenda> findAll() {
//         return agendaRepository.findAll();
//     }

//     public Agenda findById(Long id) {
// 		Optional<Agenda> obj = agendaRepository.findById(id);
// 		return obj.get();
// 	}

//     public Agenda create(Agenda agenda) {
//         return agendaRepository.save(agenda);
//     }

//     public void delete(Long id) {
//         agendaRepository.deleteById(id);
//     }

//     public List<Appointment> getConsultasDoDia(LocalDate data) {
//         return this.agendaRepository.findConsultaByData(data);
//     }
    
//     public Map<Integer, List<Appointment>> getConsultasDoMes(int ano, int mes) {
//         List<Appointment> consultas = this.agendaRepository.findConsultaByAnoAndMes(ano, mes);
//         Map<Integer, List<Appointment>> consultasPorDia = new HashMap<>();
//         for (Appointment consulta : consultas) {
//             int dia = consulta.getData().getDayOfMonth();
//             consultasPorDia.computeIfAbsent(dia, k -> new ArrayList<>()).add(consulta);
//         }
//         return consultasPorDia;
//     }
    
//     public Map<Integer, Map<Integer, List<Appointment>>> buscarAgenda(int ano) {
//         Map<Integer, Map<Integer, List<Appointment>>> consultasPorMesEDia = new HashMap<>();
//         List<Appointment> consultas = this.agendaRepository.findConsultaByAno(ano);
//         for (Appointment consulta : consultas) {
//             int mes = consulta.getData().getMonthValue();
//             int dia = consulta.getData().getDayOfMonth();
//             consultasPorMesEDia.computeIfAbsent(mes, k -> new HashMap<>())
//                               .computeIfAbsent(dia, k -> new ArrayList<>())
//                               .add(consulta);
//         }
//         return consultasPorMesEDia;
//     }

// }