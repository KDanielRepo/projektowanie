import javax.swing.*;

public class TableController {
    JTable table;
    TableView tableView;
    public TableController(JTable table,TableView tableView){
        this.table = table;
        this.tableView = tableView;
    }
    public void setTableValues(String[]a,Object[][]b){
        table = new JTable(b,a);
    }
    public void resetTable(){
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                table.setValueAt(null, i, j);
            }
        }
    }
}

