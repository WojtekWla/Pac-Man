package GameObjects;

import Interface.GameWindow;

import javax.swing.*;

public class Game extends Thread{
    public static String monitor = new String();
    public static boolean isPlayerKilled = false;
    public boolean toChange;
    public boolean endGame;
    private boolean closedWindow;

    private Player player;
//    private int timer;
    private Ghost[] ghosts;
    private JTable jTable;
    private Timer timer;
    private boolean isPlayerAlive;


    public Game(Player player, Ghost[] ghosts, Timer timer){
        this.player = player;
        this.ghosts = ghosts;
        this.timer = timer;
        toChange = false;
        isPlayerAlive = true;
        endGame = false;
        closedWindow = false;

        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setGame(this);
        }
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public Player getPlayer() {
        return player;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public boolean isToChange() {
        return toChange;
    }

    public void setToChange(boolean toChange) {
//        synchronized (monitor) {
            this.toChange = toChange;
//        }
    }

    public boolean isPlayerAlive() {
        synchronized (monitor) {
            return isPlayerAlive;
        }
    }

    public void setPlayerAlive(boolean playerAlive) {
        synchronized (monitor) {
            isPlayerAlive = playerAlive;
        }
    }

    public void startGame()
    {
        player.start();
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].start();
        }
        start();
        player.getPlayerAnimationController().start();
        timer.start();
    }

    public void playerKilled()
    {
        isPlayerKilled = true;
//        synchronized (monitor)
//        {
//        isPlayerAlive = false;
//            try {
////                monitor.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        player.setLife(player.getLife()-1);
        System.out.println(player.getLife());

        player.getLifeTextArea().setText("Lifes remaining: " + player.getLife());

        player.setRunning(false);

        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i].setRunning(false);
        }


        if(player.getLife() >=0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //reorganize to the beginning the whole map

            GameWindow.gameBoard[player.getX()][player.getY()].setCellPart(MapGenerator.cellPart.path, null);

            GameWindow.gameBoard[GameWindow.gameBoard.length - 2][GameWindow.gameBoard[0].length / 2].setCellPart(MapGenerator.cellPart.player, player);
            player.setX(GameWindow.gameBoard.length - 2);
            player.setY(GameWindow.gameBoard[0].length / 2);
            for (int i = 0; i < ghosts.length; i++) {

                System.out.println(ghosts[i].getPreviousX() + " " + ghosts[i].getPreviousY() + " " + ghosts[i].getPreviousCellPart() + " " + ghosts[i].isTraveling()
                        + " " + ghosts[i].isExitBase());
//
//            if (ghosts[i].getPreviousCellPart() == MapGenerator.cellPart.upgrade) {
//                GameWindow.gameBoard[ghosts[i].getPreviousX()][ghosts[i].getPreviousY()].setCellPart(ghosts[i].getPreviousCellPart(),
//                        ghosts[i].getPreviousCellUpgrade());
//            }
////            } else
                if (ghosts[i].getAlive()) {
                    if (ghosts[i].getPreviousCellPart() == MapGenerator.cellPart.upgrade) {
                        GameWindow.gameBoard[ghosts[i].getPreviousX()][ghosts[i].getPreviousY()].setCellPart(ghosts[i].getPreviousCellPart(),
                                ghosts[i].getPreviousCellUpgrade());
                    }else
                        GameWindow.gameBoard[ghosts[i].getPreviousX()][ghosts[i].getPreviousY()].setCellPart(ghosts[i].getPreviousCellPart(), null);
                }
                GameWindow.gameBoard[ghosts[i].getxBase()][ghosts[i].getyBase()].setCellPart(MapGenerator.cellPart.ghost, ghosts[i]);
//
                ghosts[i].setX(ghosts[i].getxBase());
                ghosts[i].setY(ghosts[i].getyBase());

                ghosts[i].setTraveling(false);
                ghosts[i].setExitBase(true);
                ghosts[i].setAlive(false);
            }


            SwingUtilities.invokeLater(()-> jTable.repaint());
//
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Game interrupted");
            }

            player.setRunning(true);
            for (int i = 0; i < ghosts.length; i++) {
                ghosts[i].setRunning(true);
            }

            isPlayerKilled = false;
        }else
        {
            System.out.println("End of game");
//            String nameInput = JOptionPane.showInputDialog("End of game, input name");
//            nameInput = nameInput.replace(" ", "_");
//            player.setPlayerName(nameInput);
//            System.out.println(player.getPlayerName());
//
////
//            ScoreArray scoreArray = ReadObjectsFromFile.readObjects();
//            System.out.println(scoreArray);
////
////            if(scoreArray ==null)
////                scoreArray = new ScoreArray();
////
////            System.out.println(scoreArray);
//            scoreArray.list.add(nameInput + " " + player.getScore());
//            System.out.println(scoreArray);
////
//            WriteObjectToFile.writeObjectToFile(scoreArray);

            endGame = true;
            closeGame();
        }
    }


    public void saveResult()
    {
        String nameInput = JOptionPane.showInputDialog("End of game, input name");
        if(nameInput == null)
            nameInput = "unknown";
        nameInput = nameInput.replace(" ", "_");
        player.setPlayerName(nameInput);

//        System.out.println(player.getPlayerName());

//
        ScoreArray scoreArray = ReadObjectsFromFile.readObjects();
//        System.out.println(scoreArray);
//
//            if(scoreArray ==null)
//                scoreArray = new ScoreArray();
//
//            System.out.println(scoreArray);
        scoreArray.list.add(nameInput + " " + player.getScore());
        System.out.println(scoreArray);
//
        WriteObjectToFile.writeObjectToFile(scoreArray);

    }

    public void closeGame()
    {
        //UPDATE
        if(!closedWindow) {
            player.setRunning(false);
            for (int i = 0; i < getGhosts().length; i++) {
                getGhosts()[i].setRunning(false);
            }
            timer.setRunning(false);
            //END
            if (!endGame) {
                String[] tab = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null, "Would you like to save your result ?", "Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, tab, tab[0]);

                if (result == 0) {
                    saveResult();
                }
            } else
                saveResult();


            player.getPlayerAnimationController().interrupt();
            player.interrupt();
            for (int i = 0; i < getGhosts().length; i++) {
                getGhosts()[i].interrupt();
            }

//                Thread.currentThread().interrupt();
            interrupt();
            Ghost.ghostNumGen = 0;
            closedWindow = true;
        }


    }



    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
//            timer++;
//            System.out.println("Timer " + timer);
                if (toChange) {
                    SwingUtilities.invokeLater(() -> jTable.repaint());
//                jTable.repaint();
//                synchronized (monitor) {
                    toChange = false;
//                }
                }
            }
        }catch (Exception e){
            return;
        }
    }




}
