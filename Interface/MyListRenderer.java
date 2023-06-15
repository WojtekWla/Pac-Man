package Interface;

import javax.swing.*;
import java.awt.*;

public class MyListRenderer implements ListCellRenderer<String> {
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel jLabel = new JLabel(value);
        jLabel.setOpaque(true);

        jLabel.setForeground(Color.YELLOW);
        jLabel.setBackground(Color.BLACK);


        return jLabel;
    }
}
