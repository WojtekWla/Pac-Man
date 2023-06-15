package GameObjects;

import javax.swing.*;

public abstract class Upgrade {

    protected int points;
    protected int workingTime;
    protected int id;
    protected ImageIcon imageIcon;
    public Upgrade()
    {
        workingTime = 5;
        points = 100;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public int getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }
}
