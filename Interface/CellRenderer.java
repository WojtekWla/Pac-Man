package Interface;

import GameObjects.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer implements TableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel jLabel = new JLabel();
        jLabel.setOpaque(true);

//        table.setRowHeight(row, table.getRowHeight()/table.getInputMethodRequests());

        if(((Cell)value).getCellPart() == MapGenerator.cellPart.wall)
        {
            jLabel.setBackground(Color.BLUE);
        }else if(((Cell)value).getCellPart() == MapGenerator.cellPart.path)
            jLabel.setBackground(Color.BLACK);
        else if(((Cell)value).getCellPart() == MapGenerator.cellPart.point)
            jLabel.setIcon(new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\point.png"));
        else if(((Cell) value).getCellPart() == MapGenerator.cellPart.ghost)
        {
            jLabel.setIcon(((Ghost)((Cell) value).getObject()).getGhostAnimationController().getImageIcon());
        }
        else if(((Cell) value).getCellPart() == MapGenerator.cellPart.upgrade){
            jLabel.setIcon(((Upgrade)((Cell) value).getObject()).getImageIcon());
//            Upgrade up = (Upgrade)((Cell)value).getObject();
//            if(up instanceof KillingGhostUpgrade)
//                jLabel.setIcon(((KillingGhostUpgrade)up).getImageIcon());
//            else if(up instanceof DoublePointsUpgrade)
//                jLabel.setIcon(((DoublePointsUpgrade)up).getImageIcon());
//            else if(up instanceof DoubleSpeedUpgrade)
//                jLabel.setIcon(((DoubleSpeedUpgrade)up).getImageIcon());
//            else if(up instanceof ExtraLifeUpgrade)
//                jLabel.setIcon(((ExtraLifeUpgrade)up).getImageIcon());
//            else if(up instanceof GhostSlowDownUpgrade)
//                jLabel.setIcon(((GhostSlowDownUpgrade)up).getImageIcon());
//
//            if(up.getId()==1)
//                jLabel.setIcon(((KillingGhostUpgrade)up).getImageIcon());
//            else if(up.getId() == 2)
//                jLabel.setIcon(((DoubleSpeedUpgrade)up).getImageIcon());
//            else if(up.getId() == 3)
//                jLabel.setIcon(((DoublePointsUpgrade)up).getImageIcon());
//            else if(up.getId()==4)
//                jLabel.setIcon(((ExtraLifeUpgrade)up).getImageIcon());
//            else
//                jLabel.setIcon(((GhostSlowDownUpgrade)up).getImageIcon());
        }
        else
           jLabel.setIcon (((Player) ((Cell) value).getObject()).getPlayerAnimationController().getCurrentImageToDisplay());

//        jLabel.setBackground(((Cell)value).getColor());

        return jLabel;
    }
}
