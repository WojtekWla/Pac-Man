package Interface;

import GameObjects.Cell;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class MyTable extends JTable {

    public MyTable(TableModel tableModel)
    {
        super(tableModel);

//        setDefaultRenderer(Cell.class, new CellRenderer());
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        setShowGrid(false);
    }

    @Override
    public int getRowHeight() {
        return getColumnModel().getColumn(0).getWidth();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getColumnCount()*30, getRowCount()*30);
    }
}
