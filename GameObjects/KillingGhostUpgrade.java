package GameObjects;

import javax.swing.*;
import java.security.UnresolvedPermission;

public class KillingGhostUpgrade extends Upgrade {

    public KillingGhostUpgrade()
    {
        super();
        imageIcon = new ImageIcon("C:\\Users\\Precision\\Desktop\\szkola\\GUI\\GUI_Project2\\src\\Icons\\Upgrades\\big_point.png");
        id = 1;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
