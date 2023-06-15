package Interface;

import GameObjects.Player;
import GameObjects.ReadObjectsFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HighScoreWindow extends JFrame {

    public HighScoreWindow(){


        ArrayList<String> scores = ReadObjectsFromFile.readObjects().list;

        scores.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int score1 = 0;
                int score2 = 0;
                try {
                     score1= Integer.parseInt(o1.split(" ")[1]);
                     score2= Integer.parseInt(o2.split(" ")[1]);
                    System.out.println(score1 + " " + score2);
                }catch (NumberFormatException e){
                    System.out.println("Wrong");
                }

                if(score1 > score2)
                    return -1;
                else if (score1 < score2)
                    return 1;
                else
                    return 0;

            }
        });

        MyListModel myListModel = new MyListModel(scores);
        JList jList = new JList(myListModel);
        jList.setCellRenderer(new MyListRenderer());
        jList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q){
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JScrollPane jScrollPane = new JScrollPane(jList);


        add(jScrollPane);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
