package com.hargclinical.harg.serializables;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Agenda;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.Dias;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Services;

public class AgendaSerializer extends StdSerializer<Agenda> {

    public AgendaSerializer() {
        this(null);
    }

    public AgendaSerializer(Class<Agenda> t) {
        super(t);
    }

    @Override
    public void serialize(Agenda agenda, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", agenda.getId().toString());

        Medico medico = agenda.getMedico();
        Services service = agenda.getService();
        
        if(medico != null) {
            jsonGenerator.writeStringField("owner", medico.getNome());
        } else if(service != null) {
            jsonGenerator.writeStringField("owner", service.getNome());
        } else {
            jsonGenerator.writeStringField("owner", "geral");
        }

        jsonGenerator.writeArrayFieldStart("dias");
        for(Dias dia : agenda.getDias()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("dia", dia.getDia());

            jsonGenerator.writeArrayFieldStart("consultas");
            for(Appointment consulta : dia.getConsultas()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", consulta.getId().toString());
                jsonGenerator.writeStringField("paciente", consulta.getPaciente().getNome());
                jsonGenerator.writeStringField("medico", consulta.getMedico().getNome());
                jsonGenerator.writeStringField("servico", consulta.getService().getNome());
                jsonGenerator.writeStringField("data", consulta.getData().toString());
                jsonGenerator.writeStringField("horario", consulta.getHorario().toString());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}