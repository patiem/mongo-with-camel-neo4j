package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<Friend> {

    public CustomSerializer() {
        this(null);
    }

    public CustomSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Friend friend, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String name = friend.getName();
        jsonGenerator.writeObject(name);
    }
}