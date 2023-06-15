import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class TestFrame implements Serializable {

    private String name;
    private int score;
    private transient int height;

    public TestFrame(String name, int score, int height)
    {
        this.name = name;
        this.score = score;
        this.height = height;

    }

    @Override
    public String toString() {
        return "TestFrame{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", height=" + height +
                '}';
    }
}
