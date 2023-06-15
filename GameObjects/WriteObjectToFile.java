package GameObjects;

import java.io.*;

public class WriteObjectToFile {
    public static FileOutputStream fileOutputStream;
    public static ObjectOutputStream objectOutputStream;

    public static void writeObjectToFile(ScoreArray scoreArray)
    {
        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Interface\\scores.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(scoreArray);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
