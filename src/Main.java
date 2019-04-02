import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Window window = new Window();
        //Main main = new Main();
        //main.test();
        //Flow flow = new Flow();
    }
    public void test(){
        JFrame testt = new JFrame();
        JPanel kekk = new JPanel();
        TableModel tableModel = new TableModel();
        JTable table = new JTable(tableModel);
        kekk.add(table.getTableHeader());
        kekk.add(table);
        testt.add(kekk);
        testt.setVisible(true);
        testt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testt.setSize(300,300);
    }

}
