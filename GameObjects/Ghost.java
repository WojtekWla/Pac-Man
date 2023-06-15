package GameObjects;

import Interface.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ghost extends Thread{
    public static int ghostNumGen = 0;


    private Game game;
    private JTable jTable;
    private GhostAnimationController ghostAnimationController;
    private int spawningUpgradeTime;
//    private List<Point> moves;
    private int ghostNum;
    private int x;
    private int y;
    private int directionX;
    private int directionY;
    private int xBase;
    private int yBase;
    private Color color;
    private MapGenerator.cellPart previousCellPart;
    private Upgrade previousCellUpgrade;
    private int previousX;
    private int previousY;
    private boolean inBase;
    private boolean isAlive;
    private boolean isTraveling;
    private boolean exitBase;
    private boolean isRunning;


    public Ghost()
    {
        isRunning = true;
        inBase = true;
        isAlive = false;
        isTraveling = false;
        exitBase = true;
        directionX = 0;
        directionY = 0;
        ghostNum = ++ghostNumGen;
        ghostAnimationController = new GhostAnimationController(this);
        spawningUpgradeTime = 0;

        if(ghostNum == 1)
            color = Color.CYAN;
        else if(ghostNum == 2)
            color = Color.MAGENTA;
        else if(ghostNum == 3)
            color = Color.ORANGE;
        else
            color = Color.RED;
    }

    public Color getColor() {
        return color;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getGhostNum() {
        return ghostNum;
    }

    public GhostAnimationController getGhostAnimationController() {
        return ghostAnimationController;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MapGenerator.cellPart getPreviousCellPart() {
        return previousCellPart;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getxBase() {
        return xBase;
    }

    public int getyBase() {
        return yBase;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTraveling() {
        return isTraveling;
    }

    public void setTraveling(boolean traveling) {
        isTraveling = traveling;
    }

    public boolean isExitBase() {
        return exitBase;
    }

    public void setExitBase(boolean exitBase) {
        this.exitBase = exitBase;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getSpawningUpgradeTime() {
        return spawningUpgradeTime;
    }

    public void setSpawningUpgradeTime(int spawningUpgradeTime) {
        synchronized (this) {
            this.spawningUpgradeTime = spawningUpgradeTime;
        }
    }

    public Upgrade getPreviousCellUpgrade() {
        return previousCellUpgrade;
    }



    @Override
    public void run() {

        isRunning = true;
//
            while (!Thread.interrupted()) {
//                synchronized (game.monitor) {
                    if (isRunning) {
                        try {
                            if(game.getPlayer().isSlowGhosts()) {
                                Thread.sleep(400);
                            }else
                                Thread.sleep(200);
                        } catch (InterruptedException e) {
//                            System.out.println("Interrupted ghost");
                            return;
                        }
                        if (exitBase)
                            exitBase();
                        if (!isTraveling)
                            chooseDestination();

                        else if (isTraveling) {
                            move();
                        }
                    }else {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
//                            System.out.println("Interrupted ghost");
                            return;
                        }
                    }
//                }
            }
//        }


    }

    public void setCor(int x, int y){
        this.x = x;
        this.y = y;
        xBase = x;
        yBase = y;
    }

    void chooseDestination()
    {
        Random random = new Random();

        while(GameWindow.gameBoard[directionX][directionY].getCellPart() != MapGenerator.cellPart.path
                && GameWindow.gameBoard[directionX][directionY].getCellPart() != MapGenerator.cellPart.point)
        {
            directionX = random.nextInt(GameWindow.gameBoard.length);
            directionY = random.nextInt(GameWindow.gameBoard[0].length);
        }

        isTraveling = true;

//        System.out.println("X: " + directionX + " Y: " + directionY);
    }

    void move() {

        Cell[][] gameBoard = GameWindow.gameBoard;

//        while(directionX != x
//                && directionY != y)
//        {
//        System.out.println(previousCellPart + " " + previousX + " " + previousY);

        if(y > directionY)
        {
            if(gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.ghost){
                y--;
            }
            else if(x > directionX){
                if(gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.ghost)
                    x--;
                else{
                    directionY = 0;
                    directionX = 0;
                    isTraveling = false;
                    return;
                }
            }else if (x < directionX) {
                if (gameBoard[x + 1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x+1][y].getCellPart() != MapGenerator.cellPart.ghost)
                    x++;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else{
                directionX = 0;
                directionY = 0;
                isTraveling = false;
                return;
            }

        }else if(y < directionY){
            if(gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.ghost) {
                y++;
            }else if(x > directionX){
                if(gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.ghost)
                    x--;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else if (x < directionX) {
                if (gameBoard[x + 1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x+1][y].getCellPart() != MapGenerator.cellPart.ghost)
                    x++;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else{
                directionX = 0;
                directionY = 0;
                isTraveling = false;
                return;
            }

        }else if(x > directionX){
            if(gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x-1][y].getCellPart() != MapGenerator.cellPart.ghost ){
                x--;
            }else if(y > directionY){
                if(gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.ghost)
                    y--;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else if (y < directionY) {
                if (gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.ghost)
                    y++;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else{
                directionX = 0;
                directionY = 0;
                isTraveling = false;
                return;
            }
        }else if(x < directionX ){
            if(gameBoard[x+1][y].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x+1][y].getCellPart() != MapGenerator.cellPart.ghost){
                x++;
            }else if(y > directionY){
                if(gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y-1].getCellPart() != MapGenerator.cellPart.ghost)
                    y--;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else if (y < directionY) {
                if (gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.wall && gameBoard[x][y+1].getCellPart() != MapGenerator.cellPart.ghost)
                    y++;
                else{
                    directionX = 0;
                    directionY = 0;
                    isTraveling = false;
                    return;
                }
            }else{
                directionX = 0;
                directionY = 0;
                isTraveling = false;
                return;
            }
        }





        MapGenerator.cellPart c = gameBoard[x][y].getCellPart();
        int pX = x;
        int pY = y;
        Upgrade previousUpgrade = null;
        Upgrade upgrade = generateUpgrade();
        if(c == MapGenerator.cellPart.upgrade){
//            Upgrade up = (Upgrade)gameBoard[x][y].getObject();
            previousUpgrade = (Upgrade)gameBoard[x][y].getObject();

        }
        else if(gameBoard[x][y].getCellPart() == MapGenerator.cellPart.player){
//            System.out.println("Player killed from ghost");
            game.playerKilled();
            return;
        }

        if(upgrade != null)
            gameBoard[previousX][previousY].setCellPart(MapGenerator.cellPart.upgrade, upgrade);
        else if(previousCellPart == MapGenerator.cellPart.upgrade){
            gameBoard[previousX][previousY].setCellPart(previousCellPart, previousCellUpgrade);
        }
        else
            gameBoard[previousX][previousY].setCellPart(previousCellPart, null);

        gameBoard[x][y].setCellPart(MapGenerator.cellPart.ghost, this);



        game.setToChange(true);
//        }
        if(directionY == y && directionX == x)
            arrivedAtDestination();


        previousCellPart = c;
        previousX = pX;
        previousY = pY;
        previousCellUpgrade = previousUpgrade;
//
    }



    void arrivedAtDestination()
    {
        directionX = 0;
        directionY= 0;
        isTraveling = false;
    }

    void exitBase()
    {
        try {
            Thread.sleep(4000*(ghostNum-1));
        } catch (InterruptedException e) {
            System.out.println("Ghost");
        }

        if(Game.isPlayerKilled)
            return;

        inBase = false;
        isAlive = true;
        x = GameWindow.gameBoard.length/2-1;
        y = GameWindow.gameBoard[0].length/2;
        previousCellPart = GameWindow.gameBoard[x][y].getCellPart();
        previousX = x;
        previousY = y;
        Upgrade upgrade = null;
        if(previousCellPart == MapGenerator.cellPart.upgrade)
        {
            previousCellUpgrade =(Upgrade)GameWindow.gameBoard[x][y].getObject();
        }
        GameWindow.gameBoard[x][y].setCellPart(MapGenerator.cellPart.ghost, this);
        GameWindow.gameBoard[xBase][yBase].setCellPart(MapGenerator.cellPart.wall, null);
//        SwingUtilities.invokeLater(()-> jTable.setValueAt(GameWindow.gameBoard[xBase][yBase], xBase, yBase));
//        SwingUtilities.invokeLater(()-> jTable.setValueAt(GameWindow.gameBoard[x][y], x, y));
        SwingUtilities.invokeLater(()-> jTable.repaint());
        exitBase = false;
        isAlive = true;
//        isTraveling = true;
    }


    Upgrade generateUpgrade()
    {
        if(spawningUpgradeTime >= 5 && isAlive)
        {
            Random random = new Random();
            setSpawningUpgradeTime(0);
            int chance = random.nextInt(100);
            if(chance <= 25){

                int randomUpdate = random.nextInt(5)+1;

                if(randomUpdate == 1)
                    return new KillingGhostUpgrade();
                else if(randomUpdate == 2)
                    return new DoubleSpeedUpgrade();
                else if(randomUpdate == 3)
                    return new DoublePointsUpgrade();
                else if(randomUpdate == 4)
                    return new ExtraLifeUpgrade();
                else
                    return new GhostSlowDownUpgrade();

            }
        }else if(!isAlive){
            setSpawningUpgradeTime(0);
        }

        return null;
    }




}
