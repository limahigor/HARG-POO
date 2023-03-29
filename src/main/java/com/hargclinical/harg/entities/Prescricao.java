package com.hargclinical.harg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hargclinical.harg.serializables.MedicamentoPrescritoSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prescricoes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prescricao implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToMany(mappedBy = "prescricao_id", cascade = CascadeType.ALL)
    @JsonSerialize(using = MedicamentoPrescritoSerializer.class)
    private List<MedicamentoPrescrito> medicamentos = new ArrayList<>();
    
    @OneToOne(mappedBy = "prescricao", cascade = CascadeType.ALL)
    private OrcamentoMedicamentos orcamentoMedicamentos;

    private boolean orcamento_gerado = false;

    public Prescricao (){
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

    public List<MedicamentoPrescrito> getMedicamentos() {
        return medicamentos;
    }

    public OrcamentoMedicamentos getOrcamentoMedicamentos() {
        return orcamentoMedicamentos;
    }

    public void setOrcamentoMedicamentos(OrcamentoMedicamentos orcamentoMedicamentos) {
        this.orcamentoMedicamentos = orcamentoMedicamentos;
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
        Prescricao other = (Prescricao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setMedicamentos(List<MedicamentoPrescrito> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean isOrcamento_gerado() {
        return orcamento_gerado;
    }

    public void setOrcamento_gerado(boolean orcamento_gerado) {
        this.orcamento_gerado = orcamento_gerado;
    }
}
