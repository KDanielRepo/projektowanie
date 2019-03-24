import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements ActionListener {
    JFrame window = new JFrame("interface GUI");
    JButton clear = new JButton("clear");
    JButton insert = new JButton("insert");
    JButton save = new JButton("save");
    JLabel textFieldLabel = new JLabel("Podaj wartość: ");
    JLabel textAreaLabel = new JLabel("Wynik działania to: ");
    JTable kek;
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    GridBagConstraints c = new GridBagConstraints();
    public Window(){
        createWindow();
    }

    public void createWindow(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel panel = new JPanel(new GridBagLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu firstMenu = new JMenu("firstMenu");
        JMenuItem menuItem = new JMenuItem("testing here",KeyEvent.VK_R);
        JMenuItem autor = new JMenuItem("o autorze",KeyEvent.VK_Q);
        JToolBar toolBar = new JToolBar("takie se");
        JButton left = new JButton("<--");
        JButton right = new JButton("-->");
        String [] columny = {"jeden","dwa","trzy","cztery","piec"};
        Object[][] dane = {{"w1","w2","w5","w1","w1"},{"w3","w4","w6","w1","w1"},{"w7","w8","w9","w1","w1"},{"w7","w8","w9","w1","w1"},{"w7","w8","w9","w1","w1"}};
        kek = new JTable(dane,columny);
        kek.setRowHeight(40);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(100,0,0,0,0,0,0,0);
        panel.add(kek.getTableHeader(),c);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0,0,0,1,0,0,0,0);
        panel.add(kek,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0,0,1,0,0,0,0,0);
        panel.add(textFieldLabel,c);

        c.fill = GridBagConstraints.BOTH;
        setConstraints(0,0,1,1,0,0,0,0);
        c.gridheight = 2;
        panel.add(textField,c);

        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0,0,2,0,0,0,0,0);
        panel.add(textAreaLabel,c);

        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        setConstraints(0,0,2,1,0,0,0,0);
        textArea.setEnabled(false);
        panel.add(textArea,c);

        clear.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(100,40,0,3,0,0,0,0);
        panel.add(clear,c);

        insert.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(100,40,2,3,0,0,0,0);
        panel.add(insert,c);

        save.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(100,40,1,3,0,0,0,0);
        panel.add(save,c);

        firstMenu.setMnemonic(KeyEvent.VK_T);
        firstMenu.getAccessibleContext().setAccessibleDescription("to jest test");
        menuBar.add(firstMenu);

        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("tutaj nic");
        menuItem.addActionListener(this);
        firstMenu.add(menuItem);
        menuItem.getAccessibleContext().setAccessibleDescription("tutaj tez nic");
        autor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
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
        window.setSize(1024,768);
        if(dimension.getWidth() <= window.getWidth() | dimension.getHeight() <= window.getHeight()){
            window.setSize((int)dimension.getWidth()/2,(int)dimension.getHeight()/2);
        }
        window.setLocation(((int)dimension.getWidth()/2)-(window.getWidth()/2),((int)dimension.getHeight()/2)-((window.getWidth()))/2);
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setJMenuBar(menuBar);
        window.add(toolBar);
        window.add(panel);
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
                System.out.println("autor");
                JOptionPane.showMessageDialog(window,"aplikacje wykonal Daniel Kuć");
                break;
            case "testing here":
                System.out.println("testingggg");
                break;
            case "clear":
                for(int i = 0; i<kek.getRowCount();i++){
                    for(int j = 0; j<kek.getColumnCount();j++){
                        kek.setValueAt(null,i,j);
                    }
                }
                break;
            case "insert":
                for(int i = 0; i<kek.getRowCount();i++){
                    for(int j = 0; j<kek.getColumnCount();j++){
                        kek.setValueAt(textField.getText(),i,j);
                    }
                }
                break;
            case "save":
                break;
        }

    }
    public void setConstraints(int a, int b, int d, int e,int f, int g,int h, int o){
        c.ipadx = a; //ustawia szerokosc
        c.ipady = b; //ustawia wysokosc
        c.gridx = d; //ustawia pozycje x,y komorki w siatce
        c.gridy = e;
        c.insets = new Insets(f,g,h,o); //ustawia paddingi miedzy komorkami (gora,lewo,dol,prawo)
    }
}
