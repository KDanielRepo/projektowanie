import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Flow implements ActionListener {
    JFrame window = new JFrame("interface GUI");
    JButton clear = new JButton("clear");
    JButton insert = new JButton("insert");
    JButton save = new JButton("save");
    JTable table;
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    FlowLayout flowLayout = new FlowLayout();
    public Flow(){
        createWindow();
    }

    public void createWindow(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel panel = new JPanel(flowLayout);
        JMenuBar menuBar = new JMenuBar();
        JMenu firstMenu = new JMenu("firstMenu");
        JMenuItem menuItem = new JMenuItem("testing here", KeyEvent.VK_R);
        JMenuItem autor = new JMenuItem("o autorze");
        JToolBar toolBar = new JToolBar("takie se");
        JButton left = new JButton("<--");
        JButton right = new JButton("-->");
        String [] columny = {"jeden","dwa","trzy","cztery","piec"};
        Object[][] dane = {{"w1","w2","w5","w1","w1"},{"w3","w4","w6","w1","w1"},{"w7","w8","w9","w1","w1"},{"w7","w8","w9","w1","w1"},{"w7","w8","w9","w1","w1"}};

        table = new JTable(dane,columny);
        table.setRowHeight(40);

        panel.add(table.getTableHeader());
        panel.add(table);

        textField.setPreferredSize(new Dimension(150,50));
        panel.add(textField);
        textArea.setPreferredSize(new Dimension(400,100));
        panel.add(textArea);
        clear.addActionListener(this);
        panel.add(clear);
        insert.addActionListener(this);
        panel.add(insert);
        firstMenu.setMnemonic(KeyEvent.VK_T);
        firstMenu.getAccessibleContext().setAccessibleDescription("to jest test");
        menuBar.add(firstMenu);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("tutaj nic");
        menuItem.addActionListener(this);
        firstMenu.add(menuItem);
        autor.addActionListener(this);
        firstMenu.add(autor);
        left.setActionCommand("<--");
        left.setToolTipText("nie dziala");
        left.addActionListener(this);
        right.setActionCommand("-->");
        right.setToolTipText("ten tez");
        right.addActionListener(this);
        toolBar.add(left);
        toolBar.add(right);
        window.setJMenuBar(menuBar);
        window.add(toolBar);
        window.add(panel);

        window.setSize(1024,768);
        if(dimension.getWidth() <= window.getWidth() | dimension.getHeight() <= window.getHeight()){
            window.setSize((int)dimension.getWidth()/2,(int)dimension.getHeight()/2);
        }
        window.setLocation(((int)dimension.getWidth()/2)-(window.getWidth()/2),((int)dimension.getHeight()/2)-((window.getWidth()))/2);
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        System.out.println(name);
        switch (name){
            case "<--":
                System.out.println("lewoooo");

                break;
            case "-->":
                System.out.println("prawoooo");
                break;
            case "o autorze":
                JOptionPane.showMessageDialog(window,"aplikacje wykonal Daniel Kuć");
                break;
            case "testing here":
                System.out.println("testingggg");
                break;
            case "clear":
                for(int i = 0; i<table.getRowCount();i++){
                    for(int j = 0; j<table.getColumnCount();j++){
                        table.setValueAt(null,i,j);
                    }
                }
                break;
            case "insert":
                for(int i = 0; i<table.getRowCount();i++){
                    for(int j = 0; j<table.getColumnCount();j++){
                        table.setValueAt(textField.getText(),i,j);
                    }
                }
                break;
            case "save":
                break;
        }

    }
}
