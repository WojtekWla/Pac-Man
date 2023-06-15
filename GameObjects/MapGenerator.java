package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    public static enum cellPart{
        wall,
        path,
        player,
        point,
        ghost,
        upgrade
    }
    private Cell[][] map;
    private int[][] base;
    private Player player;
    private Ghost[] ghosts;
    public MapGenerator(int x, int y, Player player, Ghost[] ghosts)
    {
        map = new Cell[x][y];
        this.player = player;
        this.ghosts = ghosts;


        //generate base for ghosts
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(i == 0 || i == map.length-1 || j == 0 || j == map[i].length-1)
                    map[i][j] = new Cell(cellPart.wall,null);
                else
                    map[i][j] = new Cell(cellPart.path,null);
            }
        }

        int xx = x/2;
        int yy = y/2;
        //setting location of base
        map[xx][yy-1] = new Cell(cellPart.wall,null);
        map[xx][yy] = new Cell(cellPart.wall,null);
        map[xx][yy+1] = new Cell(cellPart.wall,null);
        map[xx+1][yy-1] = new Cell(cellPart.wall,null);
        map[xx+1][yy] = new Cell(cellPart.wall,null);
        map[xx+1][yy+1] = new Cell(cellPart.wall,null);
        int[][] arr = {{xx,yy-1},
                {xx,yy},
                {xx,yy+1},
                {xx+1, yy-1},
                {xx+1, yy},
                {xx+1, yy+1}
        };
        this.base = arr;



        //setting starting player position
//        map[x-2][y/2] = player;

        ((Cell)map[x-2][y/2]).setCellPart(cellPart.player, player);
        player.setCor(x-2, y/2);
    }

    public Cell[][] getMap() {
        return map;
    }

    public void generate()
    {
        Random random = new Random();
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 1; j < map[i].length-1; j++) {
                if (!((Cell)map[i][j]).isWall() && !((Cell)map[i][j-1]).isWall() && !((Cell)map[i-1][j]).isWall() && !((Cell)map[i][j+1]).isWall() && !((Cell)map[i+1][j]).isWall()
                        && !((Cell)map[i-1][j-1]).isWall() && !((Cell)map[i-1][j+1]).isWall() && !((Cell)map[i+1][j+1]).isWall() && !((Cell)map[i+1][j-1]).isWall()){
                    int size = random.nextInt(3)+2;
                    boolean horizontal = random.nextBoolean();

                    for (int k = 0; k < size; k++) {
                        if(horizontal){
                            if(!((Cell)map[i][j+k+1]).isWall() && !((Cell)map[i-1][j+k+1]).isWall() && !((Cell)map[i+1][j+k+1]).isWall())
                                map[i][j+k] = new Cell(cellPart.wall, null);
                            else
                                break;
                        }else {
                            if (!((Cell)map[i + k + 1][j]).isWall() && !((Cell)map[i+k+1][j-1]).isWall() && !((Cell)map[i+k+1][j+1]).isWall())
                                map[i + k][j] = new Cell(cellPart.wall, null);
                            else
                                break;
                        }
                    }
                }
            }
        }
        //generate point on path

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].getCellPart() == cellPart.path)
                    map[i][j].setCellPart(cellPart.point,null);
            }
        }



//
        map[base[0][0]][base[0][1]].setCellPart(cellPart.ghost, ghosts[0]);
        ghosts[0].setCor(base[0][0], base[0][1]);
        map[base[2][0]][base[2][1]].setCellPart(cellPart.ghost, ghosts[1]);
        ghosts[1].setCor(base[2][0], base[2][1]);
        map[base[3][0]][base[3][1]].setCellPart(cellPart.ghost, ghosts[2]);
        ghosts[2].setCor(base[3][0], base[3][1]);
        map[base[5][0]][base[5][1]].setCellPart(cellPart.ghost, ghosts[3]);
        ghosts[3].setCor(base[5][0], base[5][1]);


        //put ghosts in the base



    }



}
