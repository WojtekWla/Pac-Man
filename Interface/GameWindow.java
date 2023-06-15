package Interface;

import GameObjects.*;
import GameObjects.Timer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow extends JDialog {
    public static Cell[][] gameBoard;

    private int x;
    private int y;
    private Player player;
    private Game game;
    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public GameWindow(int x, int y){

        Ghost[] ghosts = {new Ghost(), new Ghost(), new Ghost(), new Ghost()};
        Timer timer = new Timer(ghosts);
        this.game = new Game(new Player(), ghosts, timer);
        this.player = game.getPlayer();
        timer.setPlayer(game.getPlayer());

//        this.game = game;
//        this.player = new Player();

        this.x = x;
        this.y = y;


        MapGenerator mapGenerator = new MapGenerator(x,y,player, ghosts);
        mapGenerator.generate();
        gameBoard = mapGenerator.getMap();
        //create own jTable and override getRowHeight to create square cells
        TableGameModel tableGameModel = new TableGameModel(mapGenerator.getMap());
//        JTable jTable = new JTable(tableGameModel);
        MyTable jTable = new MyTable(tableGameModel);
//        jTable.setDefaultRenderer(Cell.class, new CellRenderer());
        player.setJTable(jTable);
        player.setGame(game);
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setjTable(jTable);
        }

        game.setjTable(jTable);

//        Game game = new Game(player, jTable, mapGenerator.getMap());

//        jTable.setRowHeight(jTable.getColumnModel().getColumn(0).getWidth());
//        jTable.getColumnModel().getColumn(1).setCellRenderer(new IconRenderer());


//        jTable.setValueAt(player.getIcon(), 1, 1);

//        System.out.println(tableGameModel.getColumnCount());
//        System.out.println(tableGameModel.getRowCount());

        jTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //character moving
                switch (e.getKeyChar()){
                    case 'w':
                        player.setDirection("up");
                        break;
                    case 's':
                        player.setDirection("down");
                        break;
                    case 'a':
                        player.setDirection("left");
                        break;
                    case 'd':
                        player.setDirection("right");
                        break;

                }




//                System.out.println(player.getDirection());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q)
                {
                    game.closeGame();
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing window");
                game.closeGame();
//                //UPDATE
//                player.setRunning(false);
//                for (int i = 0; i < game.getGhosts().length; i++) {
//                    game.getGhosts()[i].setRunning(false);
//                }
//                timer.setRunning(false);
//                //END
//                if(!game.endGame) {
//                    String[] tab = {"Yes", "No"};
//                    int result = JOptionPane.showOptionDialog(null, "Would you like to save your result ?", "Exit",
//                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, tab, tab[0]);
//
//                    if (result == 0) {
//                        game.saveResult();
//                    }
//                }
//
//
//
//                player.getPlayerAnimationController().interrupt();
//                player.interrupt();
//                for (int i = 0; i < game.getGhosts().length; i++) {
//                    game.getGhosts()[i].interrupt();
//                }
//
////                Thread.currentThread().interrupt();
//                game.interrupt();
//                Ghost.ghostNumGen = 0;
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });//interrupting Thread when closing JDialog

//        player.start();

//        Ghost[] ghosts = {new Ghost()};
//        Game game = new Game(player, ghosts);
//        this.game = game;
//        game.start();
        JPanel jPanel = new JPanel(new BorderLayout());
        JPanel statsDisplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea score = new JTextArea("Score: 0");
        JTextArea time = new JTextArea("0:00");
        JTextArea health = new JTextArea("Health");



        jPanel.setBackground(Color.BLACK);
        score.setBackground(Color.BLACK);
        time.setBackground(Color.BLACK);
        health.setBackground(Color.BLACK);

        score.setForeground(Color.YELLOW);
        time.setForeground(Color.YELLOW);
        health.setForeground(Color.YELLOW);

        score.setEditable(false);
        score.setEnabled(false);
        time.setEditable(false);
        time.setEnabled(false);
        health.setEnabled(false);


        statsDisplay.add(score);
        statsDisplay.add(time);
        statsDisplay.add(health);

        jPanel.add(statsDisplay, BorderLayout.NORTH);
        jPanel.add(jTable, BorderLayout.CENTER);
        add(jPanel);
//        getContentPane().setBackground(Color.BLACK);
        player.setScoreTextArea(score);
        player.setLifeTextArea(health);
        timer.setjTextArea(time);
        game.startGame();
//        add(jTable);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class IconRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel jLabel = (JLabel) super.getTableCellRendererComponent(table,value,isSelected, hasFocus,row,column);
            jLabel.setIcon((ImageIcon) value);

            return jLabel;
        }
    }



}
