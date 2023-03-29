package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.ServicesSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity  
@Table(name = "services")  
@Inheritance(strategy = InheritanceType.JOINED)
@JsonSerialize(using = ServicesSerializer.class)
public class Services implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "especialidade") 
    public String especialidade;

    @Column(name = "valor") 
    public double valor;

    @ManyToMany(mappedBy = "medico_servicos")
    public Set<Medico> profissionais = new HashSet<>();

    // @ManyToMany(mappedBy = "procedimentos")
    // protected List<OrcamentoServicos> orcamento = new ArrayList<>();

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Appointment> consulta = new ArrayList<>();

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL)
    private Agenda agenda;

    public Services() {
        
    }

    public Services(String nome, String especialidade, double valor) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.valor = valor;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Set<Medico> getProfissionais() {
        return profissionais;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setProfissionais(Set<Medico> profissionais) {
        this.profissionais = profissionais;
    }

    // public List<OrcamentoServicos> getOrcamento() {
    //     return orcamento;
    // }

    // public void setOrcamento(List<OrcamentoServicos> orcamento) {
    //     this.orcamento = orcamento;
    // }

    public List<Appointment> getConsulta() {
        return consulta;
    }

    public void setConsulta(List<Appointment> consulta) {
        this.consulta = consulta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Services other = (Services) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /*public void addProfissional(Pessoa profissional) {
        profissionais.add(profissional.nome);
    }

    public boolean verificarProfissional(String nomeMedico) {

        if(profissionais.contains(nomeMedico)) {
            return true;
        }else{
            return false;
        }

    }

    public void printService() {
        System.out.println("Procedimento: " + nome);

        if (profissionais.size() != 0) {
            System.out.println("Profissionais: ");

            for(String nome : profissionais) {
                    System.out.println(nome);
            }
        }

        System.out.println("Valor: R$" + valor + "\n");
    }

    public static Services buscarProcedimentoNome(List<Services> allServices, String nome) {
         for (Services search : allServices) {
             if (search.nome.equals(nome)) {
                 return search;
             }
         }
         return null;
    }

    public static Services buscarProcedimentoEsp(List<Services> allServices, String x) {
        for (Services search : allServices) {
            if (search.especialidade.equals(x)) {
                return search;
            }
        }
        return null;
    }*/
    
}
