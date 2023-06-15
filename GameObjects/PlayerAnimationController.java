package GameObjects;

import Interface.GameWindow;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerAnimationController extends Thread{
    private List<ImageIcon> images;
    private Player player;
    private ImageIcon currentImageToDisplay;

    public PlayerAnimationController(Player player){
        this.player = player;
        images = new ArrayList<>();
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_1.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_2.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_3.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_4.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_5.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_6.png"));
        images.add(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pac-man\\pacman_7.png"));

    }

    @Override
    public void run() {
        int currentImage = 0;
        boolean side=true;
//        try {
            while (!Thread.interrupted()) {
//                synchronized (player.getGame().monitor) {
                    if (player.isRunning()) {
                        currentImageToDisplay = images.get(currentImage);
                        if (side) {
                            currentImage++;
                            if (currentImage == 6) {
                                side = false;
                            }
                        } else {
                            currentImage--;
                            if (currentImage == 0)
                                side = true;
                        }

                        SwingUtilities.invokeLater(() -> player.getjTable().setValueAt(GameWindow.gameBoard[player.getX()][player.getY()],
                                player.getX(), player.getY()));

//            synchronized (Game.monitor) {
//                player.getGame().setToChange(true);
//            }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
//                            System.out.println("Interupted animation");
                            return;
                        }
                    }else {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
//                            System.out.println("Interupted timer");
                            return;
                        }
                    }
//                }

            }
//        }catch (Exception e){
//            return;
//        }
    }

    public List<ImageIcon> getImages() {
        return images;
    }

    public ImageIcon getCurrentImageToDisplay() {
        return currentImageToDisplay;
    }
}
