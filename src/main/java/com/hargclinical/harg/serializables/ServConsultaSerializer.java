package com.hargclinical.harg.serializables;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Medico;
import com.hargclinical.harg.entities.ServConsulta;

public class ServConsultaSerializer extends StdSerializer<ServConsulta> {

    public ServConsultaSerializer() {
        this(null);
    }

    public ServConsultaSerializer(Class<ServConsulta> t) {
        super(t);
    }

    @Override
    public void serialize(ServConsulta servico, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", servico.getId().toString());
        jsonGenerator.writeStringField("nome", servico.getNome());
        jsonGenerator.writeStringField("especialidade", servico.getEspecialidade());
        jsonGenerator.writeNumberField("valor", servico.getValor());
        jsonGenerator.writeNumberField("time", servico.getTime());

        jsonGenerator.writeArrayFieldStart("profissionais");
        for (Medico medico : servico.getProfissionais()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", medico.getId().toString());
            jsonGenerator.writeStringField("nome", medico.getNome());
            jsonGenerator.writeStringField("crm", medico.getCrm());
            jsonGenerator.writeStringField("especializacao", medico.getEspecializacao());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}