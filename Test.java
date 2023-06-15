import GameObjects.*;

public class Test{


    public static void main(String[] args) {
//        TestFrame testFrame = new TestFrame("Maciej", 3243, 20);
//        TestFrame testFrame2 = new TestFrame("Ziomek", 432, 22);
//
//        try {
//            FileOutputStream f = new FileOutputStream("testFile.txt");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
//            objectOutputStream.writeObject(testFrame);
//            objectOutputStream.writeObject(testFrame2);
//
//            objectOutputStream.flush();
//            objectOutputStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        TestFrame testFrame1 = null;
//
//        try {
//            FileInputStream f = new FileInputStream("testFile.txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(f);
//
//            do{
//                testFrame = (TestFrame) objectInputStream.readObject();
//            }while(testFrame !=null);
//
//            objectInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(testFrame1);

//
//        ScoreArray scoreArray = ReadObjectsFromFile.readObjects();
//
//        System.out.println(scoreArray);

        Upgrade upgrade = new GhostSlowDownUpgrade();
        System.out.println(upgrade.getId());


    }



}
