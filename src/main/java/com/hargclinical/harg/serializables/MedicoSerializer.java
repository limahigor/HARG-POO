package com.hargclinical.harg.serializables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.Services;

public class MedicoSerializer extends StdSerializer<Medico> {

    public MedicoSerializer() {
        this(null);
    }

    public MedicoSerializer(Class<Medico> t) {
        super(t);
    }

    @Override
    public void serialize(Medico medico, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", medico.getId().toString());
        jsonGenerator.writeStringField("nome", medico.getNome());
        jsonGenerator.writeStringField("crm", medico.getCrm());
        jsonGenerator.writeStringField("especializacao", medico.getEspecializacao());

        jsonGenerator.writeArrayFieldStart("servicos");
        for (Services servico : medico.getServicos()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", servico.getId().toString());
            jsonGenerator.writeStringField("nome", servico.getNome());
            jsonGenerator.writeStringField("especialidade", servico.getEspecialidade());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}