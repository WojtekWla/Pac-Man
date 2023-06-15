package GameObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreArray implements Serializable {
    public ArrayList<String> list;

    public ScoreArray()
    {
        list = new ArrayList<>();
    }

    @Override
    public String toString() {
        String s = "ScoreArray { ";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i);
        }

        return s;
    }
}
