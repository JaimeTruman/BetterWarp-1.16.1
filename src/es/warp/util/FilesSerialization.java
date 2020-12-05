package es.warp.util;

import java.io.*;

public final class FilesSerialization {
    private FilesSerialization() {}

    public static void serialize (Object what, File where) throws IOException {
        ObjectOutputStream dataStream = new ObjectOutputStream(new FileOutputStream(where));
        dataStream.writeObject(what);

        dataStream.close();
    }

    public static Object desSerialize (File where) throws IOException, ClassNotFoundException {
        ObjectInputStream dataStream = new ObjectInputStream(new FileInputStream(where));
        Object objectToReturn = dataStream.readObject();

        dataStream.close();
        return objectToReturn;
    }
}