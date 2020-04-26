import org.junit.jupiter.api.Test;

import java.io.*;

public class ImageTest {
    @Test
    void testSave(){
        File t = new File("c:/test.dat");
        Data data = new Data();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(t);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    void loadSave(){
        File t = new File("c:/test.dat");
        Data data = new Data();

        try {
            FileInputStream fos = new FileInputStream(t);
            ObjectInputStream oos = new ObjectInputStream(fos);
            data = (Data)oos.readObject();

            System.out.println(data.a);
            System.out.println(data.b);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

class Data implements Serializable{
    int a = 4;
    int b = 5;
}

