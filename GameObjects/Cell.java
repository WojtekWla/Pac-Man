package GameObjects;

import java.awt.*;
import java.util.Map;


public class Cell {

    private MapGenerator.cellPart cellPart;
    private boolean isWall;
    private Object object;
    private boolean isOccupied;
//    private Color color;
//
//    public Color getColor() {
//        return color;
//    }

    public MapGenerator.cellPart getCellPart() {
        return cellPart;
    }

    public void setCellPart(MapGenerator.cellPart cellPart, Object object) {
        this.cellPart = cellPart;
        this.object = object;
    }

    public Cell(MapGenerator.cellPart cellPart, Object object)
    {
        this.cellPart = cellPart;
        this.object = object;
//        if(cellPart == MapGenerator.cellPart.wall)
//            color = Color.blue;

//        else if(cellPart == MapGenerator.cellPart.path)
//            color = Color.YELLOW;
//        else
//            color = Color.RED;
        isWall = cellPart == MapGenerator.cellPart.wall;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Object getObject() {
        return object;
    }

//    @Override
//    public String toString() {
//        if(cellPart == MapGenerator.cellPart.wall)
//            return "wall";
//        else if(cellPart == MapGenerator.cellPart.path)
//            return "path";
//        else if (cellPart == MapGenerator.cellPart.player)
//            return "player";
//        else if (cellPart == MapGenerator.cellPart.ghost)
//            return "ghost";
//        else
//            return "point";
//    }
}
