package GameObjects;

import javax.swing.*;

public class GhostAnimationController extends Thread{
    private ImageIcon imageIcon;
    private Ghost ghost;
    public GhostAnimationController(Ghost ghost)
    {
        if(ghost.getGhostNum() == 1){
            imageIcon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\Ghosts\\ghost_1.png");
        }else if(ghost.getGhostNum() == 2){
            imageIcon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\Ghosts\\ghost_2.png");
        }else if(ghost.getGhostNum() == 3){
            imageIcon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\Ghosts\\ghost_3.png");
        }else
            imageIcon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\Ghosts\\ghost_4.png");
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
