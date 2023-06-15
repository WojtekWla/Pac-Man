package GameObjects;

import Interface.GameWindow;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.security.Key;

public class Player extends Thread implements Serializable {
    private String name;
    private int score;
    private int life;
    private Icon icon;
    private JTable jTable;
    private JTextArea scoreTextArea;
    private JTextArea lifeTextArea;
    private Game game;
    private PlayerAnimationController playerAnimationController;
    private String direction;
    private int x;
    private int y;
    private boolean isBlocked;
    private boolean isRunning;
//    private boolean killer;
    private boolean doublePoints;
    private boolean extraSpeed;
    private boolean slowGhosts;
//    private Object[][] gameBoard;

    public Player() {
        isRunning = true;
        this.playerAnimationController = new PlayerAnimationController(this);
        icon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\pacMan.jpg");
        direction = "right";
        isBlocked = false;
//        killer = false;
        life = 2;



    }


    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name;
    }

    public void setJTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PlayerAnimationController getPlayerAnimationController() {
        return playerAnimationController;
    }

    public void setScoreTextArea(JTextArea scoreTextArea) {
        this.scoreTextArea = scoreTextArea;
    }

    public void setLifeTextArea(JTextArea lifeTextArea) {
        this.lifeTextArea = lifeTextArea;
        lifeTextArea.setText( "Lifes remaining: " + life);
    }

    public JTextArea getLifeTextArea() {
        return lifeTextArea;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public JTable getjTable() {
        return jTable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public int getScore() {
        return score;
    }

    public boolean isDoublePoints() {
        return doublePoints;
    }

    public void setDoublePoints(boolean doublePoints) {
        this.doublePoints = doublePoints;
    }

    public boolean isExtraSpeed() {
        return extraSpeed;
    }

    public void setExtraSpeed(boolean extraSpeed) {
        this.extraSpeed = extraSpeed;
    }

    public boolean isSlowGhosts() {
        return slowGhosts;
    }

    public void setSlowGhosts(boolean slowGhosts) {
        this.slowGhosts = slowGhosts;
    }

    @Override
    public void run() {
        isRunning=true;
//
            while (!Thread.interrupted()) {
//                synchronized (Game.monitor) {
                    if (isRunning) {
                        block();
                        move();

                        try {
                            if(extraSpeed) {
                                Thread.sleep(150);
                            }else
                                Thread.sleep(300);
                        } catch (InterruptedException e) {
//                            System.out.println("Interrupted");
                            return;
                        }
                    }else {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
//                            System.out.println("Interrupted");
                            return;
                        }
                    }
//                }
            }

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Icon getIcon() {
        return icon;
    }

    public Game getGame() {
        return game;
    }

    public void setCor(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void block()
    {
//        System.out.println("Block direction " + direction);
        if(direction.equals("up")){
            if(GameWindow.gameBoard[x-1][y].getCellPart() == MapGenerator.cellPart.wall ||
                    (GameWindow.gameBoard[x-1][y].getCellPart() == MapGenerator.cellPart.ghost && !((Ghost)GameWindow.gameBoard[x-1][y].getObject()).getAlive()))
                isBlocked = true;
            else
                isBlocked = false;
        }
        else if(direction.equals("down")){
            if(GameWindow.gameBoard[x+1][y].getCellPart() == MapGenerator.cellPart.wall ||
                    (GameWindow.gameBoard[x+1][y].getCellPart() == MapGenerator.cellPart.ghost && !((Ghost)GameWindow.gameBoard[x+1][y].getObject()).getAlive()))
                isBlocked = true;
            else
                isBlocked = false;
        }else if(direction.equals("right")){
            if(GameWindow.gameBoard[x][y+1].getCellPart() == MapGenerator.cellPart.wall ||
                    (GameWindow.gameBoard[x][y+1].getCellPart() == MapGenerator.cellPart.ghost && !((Ghost)GameWindow.gameBoard[x][y+1].getObject()).getAlive()))
                isBlocked = true;
            else
                isBlocked = false;
        }else if(direction.equals("left")){
            if(GameWindow.gameBoard[x][y-1].getCellPart() == MapGenerator.cellPart.wall ||
                    (GameWindow.gameBoard[x][y-1].getCellPart() == MapGenerator.cellPart.ghost && !((Ghost)GameWindow.gameBoard[x][y-1].getObject()).getAlive()))
                isBlocked = true;
            else
                isBlocked = false;
        }else
            isBlocked = false;
    }

    public void move()
    {
//        System.out.println(direction);

        if(!isBlocked) {
//            System.out.println("move");
            int previousX = x;
            int previousY = y;

            if (direction.equals("up"))
                x--;
            else if (direction.equals("down"))
                x++;
            else if (direction.equals("right"))
                y++;
            else if (direction.equals("left"))
                y--;

            if(GameWindow.gameBoard[x][y].getCellPart() == MapGenerator.cellPart.point) {
                if(doublePoints)
                    score+=2;
                else
                    score += 1;
                scoreTextArea.setText("Score: "+score);

            }
            else if(GameWindow.gameBoard[x][y].getCellPart() == MapGenerator.cellPart.upgrade)  {
                Upgrade upgrade = (Upgrade) GameWindow.gameBoard[x][y].getObject();
                score += upgrade.getPoints();
                if(upgrade instanceof ExtraLifeUpgrade) {
                    setLife(getLife() + 1);
                    lifeTextArea.setText("Lifes remaining: " + life);
                }else if(upgrade instanceof DoublePointsUpgrade)
                    doublePoints = true;
                else if(upgrade instanceof DoubleSpeedUpgrade)
                    extraSpeed = true;
                else if(upgrade instanceof GhostSlowDownUpgrade)
                    slowGhosts = true;
                scoreTextArea.setText("Score: " + score);
//                if(upgrade.getId() == 1){
//                    killer = true;
//                }

            }
            else if(GameWindow.gameBoard[x][y].getCellPart() == MapGenerator.cellPart.ghost ) {
                System.out.println("Player killed from player");
                GameWindow.gameBoard[previousX][previousY].setCellPart(MapGenerator.cellPart.path, null);
                game.playerKilled();
                return;
            }
//            }else if(GameWindow.gameBoard[x][y].getCellPart() == MapGenerator.cellPart.ghost && killer){
//                killGhost();
//            }
//            System.out.println(score);
            ((Cell) GameWindow.gameBoard[previousX][previousY]).setCellPart(MapGenerator.cellPart.path,null);
            ((Cell) GameWindow.gameBoard[x][y]).setCellPart(MapGenerator.cellPart.player, this);
            synchronized (Game.monitor) {
                game.setToChange(true);
            }
//            SwingUtilities.invokeLater(()-> jTable.repaint());
//            }
//            SwingUtilities.invokeLater(()->jTable.setValueAt(GameWindow.gameBoard[previousX][previousY], previousX, previousY));
//            SwingUtilities.invokeLater(()->  jTable.setValueAt(GameWindow.gameBoard[x][y], x, y));
//            jTable.setValueAt(GameWindow.gameBoard[previousX][previousY], previousX, previousY);
//            jTable.setValueAt(GameWindow.gameBoard[x][y], x, y);
        }
    }

    void killGhost(Ghost ghost)
    {
        GameWindow.gameBoard[ghost.getX()][ghost.getY()].setCellPart(MapGenerator.cellPart.player, this);
        GameWindow.gameBoard[ghost.getxBase()][ghost.getyBase()].setCellPart(MapGenerator.cellPart.ghost, ghost);
//        ghost.setAlive(zz);
    }




    @Override
    public String toString() {
        return name + " " + score;
    }
}
