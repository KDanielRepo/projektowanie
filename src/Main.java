import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window window = new Window();
            }
        });
        //launch(args);
        //Main main = new Main();
        //main.test();
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
