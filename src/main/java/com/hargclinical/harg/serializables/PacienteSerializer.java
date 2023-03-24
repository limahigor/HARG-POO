package com.hargclinical.harg.serializables;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Paciente;

public class PacienteSerializer extends StdSerializer<Paciente> {

    public PacienteSerializer() {
        this(null);
    }

    public PacienteSerializer(Class<Paciente> t) {
        super(t);
    }

    @Override
    public void serialize(Paciente paciente, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", paciente.getId().toString());
        jsonGenerator.writeStringField("nome", paciente.getNome());
        jsonGenerator.writeStringField("cpf", paciente.getCpf());

        // Serializar prontuario

        //Serializar consultas

        //Serializar prescrições

        jsonGenerator.writeEndObject();
    }
}