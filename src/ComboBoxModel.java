import javax.swing.*;
import javax.swing.event.ListDataListener;

public class ComboBoxModel extends AbstractListModel implements javax.swing.ComboBoxModel {
    String[] options;
    String selection = null;

    public ComboBoxModel(){

    }
    public ComboBoxModel(String[] options){
        this.options = options;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
    @Override
    public int getSize() {
        return options.length;
    }
    @Override
    public Object getElementAt(int index) {
        return options[index];
    }
    @Override
    public void setSelectedItem(Object anItem) {
        selection =(String) anItem;
    }

}
