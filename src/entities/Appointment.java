package entities;

import java.util.Date;
import java.util.Random;

public class Appointment {
    public int id;
    public Date tempo, data;
    public String cpfPaciente;
    public String nomeMedico;
    public String nomeServico;
    
    public Appointment(String cpfPaciente, String nomeMedico, String nomeServico, Date tempo, Date data){
        Random random = new Random();

        this.id = random.nextInt(999999999);
        this.cpfPaciente = cpfPaciente;
        this.nomeMedico = nomeMedico;
        this.nomeServico = nomeServico;
        this.tempo = tempo;
        this.data = data;
    }

    public boolean remarcarData(Date tempo, Date data) {
        if(tempo.after(this.tempo) && data.after(this.data)) {
            this.tempo = tempo;
            this.data = data;
            return true;

        } else {
            return false;
        }
    }

    public boolean remarcarHora(Date tempo) {
        if(tempo.after(this.tempo)) {
            this.tempo = tempo;
            return true;

        } else {
            return false;
        }
    }

}