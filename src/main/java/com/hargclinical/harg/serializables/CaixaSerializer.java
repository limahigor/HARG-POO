package com.hargclinical.harg.serializables;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.Caixa;

public class CaixaSerializer extends StdSerializer<Caixa>{

    public CaixaSerializer() {
        this(null);
    }

    public CaixaSerializer(Class<Caixa> t) {
        super(t);
    }
    @Override
    public void serialize(Caixa caixa, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", caixa.getId().toString());
        String aux = "0";
        if(caixa.getAberto())aux = "1";
        jsonGenerator.writeStringField("status", aux);
        jsonGenerator.writeStringField("saldo", caixa.getSaldo().toString());
        jsonGenerator.writeEndObject();
    
    }
    
}
