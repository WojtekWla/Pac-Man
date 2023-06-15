package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class First_Window extends JFrame {
    private int x;
    private int y;
    //private GameWindow gameWindow;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


    public First_Window()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainButtons = new JPanel(new FlowLayout());
//        JPanel inputPanels = new JPanel(new FlowLayout());

        mainPanel.setBackground(Color.BLACK);
        mainButtons.setBackground(Color.BLACK);

        JButton newGame = new JButton("New Game");
        JButton highScores = new JButton("High Scores");
        JButton exit = new JButton("Exit");


        newGame.setForeground(Color.YELLOW);
        highScores.setForeground(Color.YELLOW);
        exit.setForeground(Color.YELLOW);

        newGame.setBackground(Color.BLACK);
        highScores.setBackground(Color.BLACK);
        exit.setBackground(Color.BLACK);

//        JTextField inputX = new JTextField("Input x");
//        JTextField inputY = new JTextField("Input Y");
//        inputX.setVisible(false);
//        inputY.setVisible(false);
//
//        inputPanels.add(inputX);
//        inputPanels.add(inputY);

        First_Window first_window = this;
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Start new Game
                System.out.println("New Game");
//                inputX.setVisible(true);
//                inputY.setVisible(true);
                try {
                    x =Integer.parseInt(JOptionPane.showInputDialog(null, "Input X"));
                    y = Integer.parseInt(JOptionPane.showInputDialog(null, "Input Y"));
                    if((x < 10 || x > 100) || (y < 10 || y > 100) )
                    {
                        throw new WrongNumbersException();
                    }
                }catch(NumberFormatException exc)
                {
                    JOptionPane.showMessageDialog(null, exc.getMessage() + ", setting values x= 10, y=10",
                            "Wrong Number Exception", JOptionPane.ERROR_MESSAGE);
                    x = 10;
                    y = 10;
                }catch(WrongNumbersException exc){
                    JOptionPane.showMessageDialog(null, "Wrong number, setting values x=10, y=10",
                            "Wrong Number Exception", JOptionPane.ERROR_MESSAGE);
                    x = 10;
                    y = 10;
                }

                SwingUtilities.invokeLater(()-> new GameWindow(x,y));
//                gameWindow = new GameWindow(x,y);
//                System.out.println(gameWindow.getX() + " " +gameWindow.getY());
//                System.out.println(gameWindow.getX() + " " +gameWindow.getY());
//                gameWindow.setVisible(true);
//
//                System.out.println(x);
//                System.out.println(y);
            }
        });


        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display high scores on jList
                SwingUtilities.invokeLater(()-> new HighScoreWindow());
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });




        mainButtons.add(newGame);
        mainButtons.add(highScores);
        mainButtons.add(exit);



        mainPanel.add(mainButtons, BorderLayout.CENTER);
        add(mainPanel);
//        add(inputPanels, BorderLayout.LINE_END);
        setSize(500,500);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
