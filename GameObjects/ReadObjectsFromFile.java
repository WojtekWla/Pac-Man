package GameObjects;

import java.io.*;

public class ReadObjectsFromFile {
    public static FileInputStream fileInputStream;
    public static ObjectInputStream objectInputStream;

    public static ScoreArray readObjects()
    {
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Interface\\scores.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                return (ScoreArray) objectInputStream.readObject();
            }catch (EOFException e)
            {
                System.out.println(e.getMessage());
            }
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ScoreArray();
    }
}
