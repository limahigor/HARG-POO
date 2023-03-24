package com.hargclinical.harg.serializables;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hargclinical.harg.entities.MedicamentoPrescrito;

public class MedicamentoPrescritoSerializer extends StdSerializer<List<MedicamentoPrescrito>>{

    public MedicamentoPrescritoSerializer() {
         this(null);
     }
 
     public MedicamentoPrescritoSerializer(Class<List<MedicamentoPrescrito>> t) {
         super(t);
     }
 
     @Override
     public void serialize(
       List<MedicamentoPrescrito> items, 
       JsonGenerator generator, 
       SerializerProvider provider) 
       throws IOException, JsonProcessingException {
            generator.writeStartArray();
            for (MedicamentoPrescrito medicamentoPrescrito : items) {
                generator.writeStartObject();
                generator.writeStringField("nome", medicamentoPrescrito.getMedicamento());
                generator.writeNumberField("intervalo", medicamentoPrescrito.getIntervalo());
                generator.writeNumberField("valor", medicamentoPrescrito.getValor());
                generator.writeEndObject();
            }
            generator.writeEndArray();
     }
 }