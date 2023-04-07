package com.hargclinical.harg.serializables;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Appointment;
import com.hargclinical.harg.entities.MedicamentoPrescrito;
import com.hargclinical.harg.entities.Prescricao;

public class PrescricaoSerializer extends StdSerializer<Prescricao> {
    public PrescricaoSerializer() {
        this(null);
    }

    public PrescricaoSerializer(Class<Prescricao> t) {
        super(t);
    }

    @Override
    public void serialize(Prescricao prescricao, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", prescricao.getId().toString());

        jsonGenerator.writeFieldName("paciente");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", prescricao.getProntuario().getPaciente().getId().toString());
        jsonGenerator.writeStringField("name", prescricao.getProntuario().getPaciente().getNome().toString());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeFieldName("medico");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", prescricao.getMedico().getId().toString());
        jsonGenerator.writeStringField("name", prescricao.getMedico().getNome().toString());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeFieldName("medicamentos");
        jsonGenerator.writeStartArray();
        for (MedicamentoPrescrito medicamentoPrescrito : prescricao.getMedicamentos()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", medicamentoPrescrito.getMedicamento());
            jsonGenerator.writeNumberField("intervalo", medicamentoPrescrito.getIntervalo());
            jsonGenerator.writeNumberField("valor", medicamentoPrescrito.getValor());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
