package pl.crazydev.dcakelibrary.data;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;

public class ByteTools {
    public static byte[] objectToByteArray(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object toObject(byte[] bytes) {
        try{
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static byte[] bukkitObjectToByteArray(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream objectOutputStream =  new BukkitObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static Object toBukkitObject(byte[] bytes) {
        try{
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
