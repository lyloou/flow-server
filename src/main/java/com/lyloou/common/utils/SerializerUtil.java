package com.lyloou.common.utils;

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializerUtil {

    public static Object deSerializer(byte[] bytes) throws IOException, ClassNotFoundException {
        if (null == bytes || 0 == bytes.length) {
            throw new NullPointerException("object deSerializer bytes is can not be null");
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             FSTObjectInput fstObjectInput = new FSTObjectInput(bais)) {
            return fstObjectInput.readObject();
        }
    }



    public static byte[] serializer(Object object) throws IOException {
        if (null == object) {
            throw new NullPointerException("serializer object is can not be null");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FSTObjectOutput output = new FSTObjectOutput(baos)) {
            output.writeObject(object);
            output.flush();
            return baos.toByteArray();
        }
    }

}
