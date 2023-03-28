// package com.hargclinical.harg.entities;

// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;

// @Entity
// public class Caixa {
//     @Id
//     private Long id;
//     private Double saldo;
//     private boolean aberto;

//     @OneToMany(mappedBy = "movimentacoes", cascade = CascadeType.ALL)
//     List<MovimentacaoCaixa> movimentacoes = new ArrayList<>();

//     public Caixa(){
//         this.saldo = 0.0;
//         this.aberto = true;
//     }
    
//     public Double getSaldo() {
//         return saldo;
//     }
//     public void setSaldo(Double saldo) {
//         this.saldo = saldo;
//     }

//     public boolean getAberto() {
//         return aberto;
//     }
//     public void setAberto(boolean aberto) {
//         this.aberto = aberto;
//     }

//     public List<MovimentacaoCaixa> getMovimentacoes() {
//         return movimentacoes;
//     }

//     public void setMovimentacoes(List<MovimentacaoCaixa> movimentacoes) {
//         this.movimentacoes = movimentacoes;
//     }

// }