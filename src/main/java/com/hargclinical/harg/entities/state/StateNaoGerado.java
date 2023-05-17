package com.hargclinical.harg.entities.state;

import com.hargclinical.harg.entities.Appointment;

public class StateNaoGerado implements AppointmentState {
    @Override
    public void generateOrcamento(Appointment appointment) {
        appointment.setState(new StateGerado());
    }

    @Override
    public boolean confirmOrcamento(Appointment appointment) {
        return false;
    }
}
