package com.hargclinical.harg.serializables;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Appointment;

public class AppointmentSerializer extends StdSerializer<Appointment>{

    public AppointmentSerializer() {
        this(null);
    }

    public AppointmentSerializer(Class<Appointment> t) {
        super(t);
    }

    @Override
    public void serialize(Appointment consulta, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", consulta.getId().toString());

        jsonGenerator.writeFieldName("paciente");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", consulta.getProntuario().getPaciente().getId().toString());
        jsonGenerator.writeStringField("name", consulta.getProntuario().getPaciente().getNome().toString());
        jsonGenerator.writeEndObject();
        
        jsonGenerator.writeFieldName("medico");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", consulta.getMedico().getId().toString());
        jsonGenerator.writeStringField("name", consulta.getMedico().getNome().toString());
        jsonGenerator.writeEndObject();
        
        jsonGenerator.writeFieldName("servico");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", consulta.getService().getId().toString());
        jsonGenerator.writeStringField("name", consulta.getService().getNome().toString());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("data", consulta.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        jsonGenerator.writeStringField("hora", consulta.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")));
        
        jsonGenerator.writeEndObject();
    }
    
}
