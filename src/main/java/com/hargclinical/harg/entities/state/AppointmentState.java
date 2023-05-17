package com.hargclinical.harg.entities.state;

import com.hargclinical.harg.entities.Appointment;

public interface AppointmentState {
    void generateOrcamento(Appointment appointment);

    boolean confirmOrcamento(Appointment appointment);
    

}
