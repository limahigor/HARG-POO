// package com.hargclinical.harg.resources;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.hargclinical.harg.entities.Agenda;
// import com.hargclinical.harg.entities.Appointment;
// import com.hargclinical.harg.services.AgendaService;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping(value = "/agenda")
// public class AgendaResource {

//     @Autowired
//     private AgendaService agendaService;

//     @GetMapping
//     public ResponseEntity<List<Agenda>> findAll() {
//         List<Agenda> agenda = agendaService.findAll();
//         return ResponseEntity.ok().body(agenda);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Agenda> findById(@PathVariable Long id) {
//         Agenda agenda = agendaService.findById(id);
//         return ResponseEntity.ok().body(agenda);
//     }
    
//     @GetMapping("/agenda/{ano}/{mes}/{dia}")
//     public ResponseEntity<List<Appointment>> consultasDoDia(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia) {
//         LocalDate data = LocalDate.of(ano, mes, dia);
//         List<Appointment> consultas = agendaService.getConsultasDoDia(data);
//         if (consultas.isEmpty()) {
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.ok().body(consultas);
//     }

//     @GetMapping("/agenda/{ano}/{mes}")
//     public ResponseEntity<Map<Integer, List<Appointment>>> consultasDoMes(@PathVariable int ano, @PathVariable int mes) {
//         Map<Integer, List<Appointment>> consultasPorDia = agendaService.getConsultasDoMes(ano, mes);
//         if (consultasPorDia.isEmpty()) {
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.ok().body(consultasPorDia);
//     }

//     @GetMapping("/agenda/{ano}")
//     public ResponseEntity<Map<Integer, Map<Integer, List<Appointment>>>> consultasDoAno(@PathVariable int ano) {
//         Map<Integer, Map<Integer, List<Appointment>>> consultasPorMesEDia = agendaService.buscarAgenda(ano);
//         if (consultasPorMesEDia.isEmpty()) {
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.ok().body(consultasPorMesEDia);
//     }

// }