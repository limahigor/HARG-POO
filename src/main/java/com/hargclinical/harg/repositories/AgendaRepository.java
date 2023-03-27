// package com.hargclinical.harg.repositories;

// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.hargclinical.harg.entities.Agenda;
// import com.hargclinical.harg.entities.Appointment;

// public interface AgendaRepository extends JpaRepository<Agenda, Long> {
//     List<Appointment> findConsultaByAnoAndMes(int ano, int mes);
//     List<Appointment> findConsultaByData(LocalDate data);
//     List<Appointment> findConsultaByAno(int ano);
// }