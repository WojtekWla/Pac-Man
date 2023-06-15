package Interface;

import javax.swing.*;
import java.util.List;

public class MyListModel extends AbstractListModel<String> {
    private List<String> items;

    public MyListModel(List<String> items)
    {
        this.items = items;
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public String getElementAt(int index) {
        return items.get(index);
    }


}
