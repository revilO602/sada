package model.objectManagment;

import java.io.*;

/**
 * The serializer serializes and deserializes a serializable object.
 *
 * @author leontiev
 */
public class Serializer {
    private Serializable serializable;

    /**
     * Constructor for Serializer.
     *
     * @param serializable the object to be serialized or deserialized.
     */
    public Serializer(Serializable serializable){
        this.serializable=serializable;
    }

    /**
     * Serializes an object to a Data.txt file.
     */
    public void serialize(){
        try {
            FileOutputStream file = new FileOutputStream("Data.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(serializable);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Object not serialized");
        }

    }

    /**
     * Deserializes a serializable object.
     *
     * @return the deserialized object.
     */
    public Serializable deserialize() {
        try {
            FileInputStream file = new FileInputStream("Data.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            this.serializable = (Serializable) in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serializable;
    }
}
