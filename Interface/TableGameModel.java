package Interface;

import GameObjects.Cell;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TableGameModel extends AbstractTableModel {
    private Cell[][] objects;
    public TableGameModel(Cell[][] objects){
        this.objects = objects;
    }

    @Override
    public int getRowCount() {
        return objects.length;
    }

    @Override
    public int getColumnCount() {
        return objects[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return objects[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        objects[rowIndex][columnIndex] = (Cell)aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
