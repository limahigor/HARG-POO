// package com.hargclinical.harg.config;

// import com.hargclinical.harg.repositories.MedicamentoPrescritoRepository;
// import com.hargclinical.harg.repositories.MedicoRepository;
// import com.hargclinical.harg.repositories.PrescricaoRepository;
// import com.hargclinical.harg.repositories.ServicesRepository;
// import com.hargclinical.harg.entities.MedicamentoPrescrito;
// import com.hargclinical.harg.entities.Medico;
// import com.hargclinical.harg.entities.Prescricao;
// import com.hargclinical.harg.entities.ServConsulta;
// import com.hargclinical.harg.entities.ServExame;
// import com.hargclinical.harg.entities.ServProcedimento;
// import com.hargclinical.harg.entities.Services;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Arrays;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.data.repository.ListCrudRepository;

// @Configuration
// public class TestConfig implements CommandLineRunner{

//     private static final String Services = null;

//     @Autowired
//     private ServicesRepository serviceRepository;

//     @Autowired
//     private MedicoRepository medicoRepository;

//     @Autowired
//     private PrescricaoRepository prescricaoRepository;

//     @Autowired
//     private MedicamentoPrescritoRepository medicamentoPrescritoRepository;

//     @Override
//     public void run(String... args) throws Exception {

//         try{
//             Services serv1 = new ServConsulta("Cardiologista", "Cardiologia", 200, 30);
//             Services serv2 = new ServProcedimento("Botox", "Dermatologista", 200);
//             Services serv3 = new ServExame("Hemograma Completo", "Endocrinologia", 200);
//             Services serv4 = new ServProcedimento("Ecocardiograma", "Cardiologia", 200);
//             serviceRepository.saveAll(Arrays.asList(serv1, serv2, serv3, serv4));

//             Medico medico1 = new Medico("Manoel Gomes", "16543122515616", 36, 'M', "Dermatologista", "12312412");
//             Medico medico2 = new Medico("Higor Gomes", "1654156415616", 36, 'M', "Dermatologista", "12312412");
//             Medico medico3 = new Medico("Guilherme Gomes", "1654116515616", 36, 'M', "Cardiologia", "12312412");
//             Medico medico4 = new Medico("Anderson Silva", "165411515616", 36, 'M', "Endocrinologia", "12312412");
//             medicoRepository.saveAll(Arrays.asList(medico1, medico2, medico3,medico4));
            
//             medico1.getServicos().add(serv2);
//             medico2.getServicos().add(serv2);
//             medico3.getServicos().add(serv1);
//             medico3.getServicos().add(serv4);
//             medico4.getServicos().add(serv3);
//             medicoRepository.saveAll(Arrays.asList(medico1, medico2, medico3,medico4));
            
//             Prescricao presc1 = new Prescricao();
//             Prescricao presc2 = new Prescricao();
//             prescricaoRepository.saveAll(Arrays.asList(presc1, presc2));

//             MedicamentoPrescrito medicamento1 = new MedicamentoPrescrito("Ibuprofeno", 6, 10.0);
//             MedicamentoPrescrito medicamento2 = new MedicamentoPrescrito("Nimesulida", 6, 12.0);
//             MedicamentoPrescrito medicamento3 = new MedicamentoPrescrito("Dipirona", 8, 18.0);
//             MedicamentoPrescrito medicamento4 = new MedicamentoPrescrito("Paracetamol", 6, 5.0);
//             MedicamentoPrescrito medicamento5 = new MedicamentoPrescrito("Ibuprofeno", 6, 10.0);
//             MedicamentoPrescrito medicamento6 = new MedicamentoPrescrito("Desloratadina", 12, 23.0);

//             medicamento1.setPrescricao(presc1);
//             medicamento2.setPrescricao(presc1);
//             medicamento3.setPrescricao(presc1);

//             medicamento4.setPrescricao(presc2);
//             medicamento5.setPrescricao(presc2);
//             medicamento6.setPrescricao(presc2);

//             medicamentoPrescritoRepository.saveAll(Arrays.asList(medicamento1, medicamento2, medicamento3, medicamento4, medicamento5, medicamento6));

//             presc1.getMedicamentos().add(medicamento1);
//             presc1.getMedicamentos().add(medicamento2);
//             presc1.getMedicamentos().add(medicamento3);

//             presc2.getMedicamentos().add(medicamento4);
//             presc2.getMedicamentos().add(medicamento5);
//             presc2.getMedicamentos().add(medicamento6);

//             prescricaoRepository.saveAll(Arrays.asList(presc1, presc2));

            
//         }catch(Exception e){
//             System.out.println("TESTE ERROR");
//             System.out.println(e.getMessage());
//         }

//     }
    
// }
