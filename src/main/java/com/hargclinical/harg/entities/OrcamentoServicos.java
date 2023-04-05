package com.hargclinical.harg.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hargclinical.harg.entities_enums.Plano;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="orcamento_servicos")
@PrimaryKeyJoinColumn(name = "id")
public class OrcamentoServicos extends Orcamento {
    @OneToMany(mappedBy = "orcamentos", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public OrcamentoServicos() {
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


}
