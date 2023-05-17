package com.hargclinical.harg.entities.state;

import com.hargclinical.harg.entities.Appointment;

public class StateGerado implements AppointmentState {
    @Override
    public void generateOrcamento(Appointment appointment) {
        return;
    }

    @Override
    public boolean confirmOrcamento(Appointment appointment) {
        return true;
    }
}

