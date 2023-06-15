package GameObjects;

import javax.swing.*;
import java.lang.management.PlatformLoggingMXBean;

public class Timer extends Thread{

    private int time;
    private JTextArea timeArea;
    private Ghost[] ghosts;
    private Player player;
    private boolean isRunning;


    public Timer(Ghost[] ghosts){
        isRunning = true;
        this.ghosts = ghosts;
        this.player = player;
        time = 0;

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setjTextArea(JTextArea jTextArea) {
        this.timeArea = jTextArea;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        int spawningTimer = 0;
        String t = new String();
        int doublePointsTimer = 0;
        int slowGhostsTimer = 0;
        int doubleSpeedTimer = 0;
        while(!Thread.interrupted()) {
            if(isRunning) {
                time++;
                int min = time / 60;
                int sec = time - min * 60;

                if (sec < 10)
                    t = min + ":0" + sec;
                else
                    t = min + ":" + sec;


                timeArea.setText(t);

                for (int i = 0; i < ghosts.length; i++) {
                    if (ghosts[i].isAlive()) {
                        synchronized (ghosts[i]) {
                            if (ghosts[i].isAlive()) {
                                ghosts[i].setSpawningUpgradeTime(ghosts[i].getSpawningUpgradeTime() + 1);
//                                System.out.println("ghost" + ghosts[i].getGhostNum() + " " + ghosts[i].getSpawningUpgradeTime());
                            }
                        }
                    }
                }

                if(player.isDoublePoints()){
                    doublePointsTimer++;
                    if(doublePointsTimer >= 5)
                    {
                        player.setDoublePoints(false);
                        doublePointsTimer = 0;
                    }
                }

                if(player.isExtraSpeed()){
                    doubleSpeedTimer++;
                    if(doubleSpeedTimer >= 5)
                    {
                        player.setExtraSpeed(false);
                        doubleSpeedTimer = 0;
                    }
                }
                if(player.isSlowGhosts()){
                    slowGhostsTimer++;
                    if(slowGhostsTimer >= 5)
                    {
                        player.setSlowGhosts(false);
                        slowGhostsTimer = 0;
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    System.out.println("Timer");
                }
            }else
                return;
        }

    }
}
