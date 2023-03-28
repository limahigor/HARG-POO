// package com.hargclinical.harg.entities;

// import java.time.LocalDate;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

// @Entity
// public class MovimentacaoCaixa{
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private Double valor;
//     private LocalDate data;


//     @ManyToOne
//     @JoinColumn(name = "caixa_id")
//     private Caixa caixa;
    
//     public MovimentacaoCaixa(){
//     }
    
//     public Caixa getCaixa() {
//         return caixa;
//     }

//     public void setCaixa(Caixa caixa) {
//         this.caixa = caixa;
//     }

//     public MovimentacaoCaixa(Double valor, LocalDate data) {
//         this.valor = valor;
//         this.data = data;
//     }

//     public Double getValor() {
//         return valor;
//     }
//     public void setValor(Double valor) {
//         this.valor = valor;
//     }
//     public LocalDate getData() {
//         return data;
//     }
    
// }