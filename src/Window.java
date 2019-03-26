import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Window implements ActionListener, ChangeListener {
    JFrame window = new JFrame("interface GUI");
    JButton clear = new JButton("clear");
    JButton insert = new JButton("insert");
    JButton save = new JButton("save");
    JButton min = new JButton("Minimum");
    JButton max = new JButton("Maximum");
    JButton average = new JButton("average");
    JSlider sliderHorizontal = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
    JSlider sliderVertical = new JSlider(JSlider.VERTICAL, 0, 4, 0);
    JSlider source;
    JLabel textFieldLabel = new JLabel("Podaj wartość: ");
    JLabel textAreaLabel = new JLabel("Wynik działania to: ");
    JTable table;
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JTextArea result;
    int ver = 4;
    int hor = 0;
    GridBagConstraints c = new GridBagConstraints();

    public Window() {
        createWindow();
    }

    public void createWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel panel = new JPanel(new GridBagLayout());
        String[] columny = {"jeden", "dwa", "trzy", "cztery", "piec"};
        Object[][] dane = {{"3", "2", "5", "5", "5"}, {"3", "4", "6", "5", "5"}, {"7", "8", "9", "5", "5"}, {"7", "8", "9", "5", "5"}, {"7", "8", "9", "5", "10"}};
        table = new JTable(dane, columny);
        table.setRowHeight(40);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(100, 0, 1, 0, 0, 0, 0, 0);
        panel.add(table.getTableHeader(), c);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0, 0, 1, 1, 0, 0, 0, 0);
        panel.add(table, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0, 0, 2, 0, 0, 0, 0, 0);
        panel.add(textFieldLabel, c);

        c.fill = GridBagConstraints.BOTH;
        setConstraints(0, 0, 2, 1, 0, 0, 0, 0);
        c.gridheight = 2;
        panel.add(textField, c);

        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0, 0, 3, 0, 0, 0, 0, 0);
        panel.add(textAreaLabel, c);

        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        setConstraints(0, 0, 3, 1, 0, 0, 0, 0);
        textArea.setEditable(false);
        Font font = new Font("Times New Roma", Font.PLAIN, 16);
        textArea.setForeground(Color.black);
        textArea.setFont(font);
        panel.add(textArea, c);

        sliderHorizontal.setName("hor");
        sliderVertical.setName("ver");
        sliderHorizontal.setMinorTickSpacing(1);
        sliderHorizontal.setMajorTickSpacing(5);
        sliderHorizontal.addChangeListener(this);
        sliderVertical.addChangeListener(this);
        sliderVertical.setMinorTickSpacing(1);
        sliderVertical.setMajorTickSpacing(5);
        sliderVertical.setPaintTicks(true);
        sliderHorizontal.setPaintTicks(true);

        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        setConstraints(0, 0, 1, 2, 0, 0, 0, 0);
        panel.add(sliderHorizontal, c);

        c.gridheight = 2;
        setConstraints(0, 0, 0, 0, 0, 0, 0, 0);
        panel.add(sliderVertical, c);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu tools = new JMenu("Narzędzia");
        JMenu about = new JMenu("About");
        JMenuItem autor = new JMenuItem("o autorze", KeyEvent.VK_Q);
        JMenuItem minItem = new JMenuItem("Minimum");
        JMenuItem maxItem = new JMenuItem("Maximum");
        JMenuItem averageItem = new JMenuItem("Average");

        menuBar.add(tools);
        menuBar.add(about);

        autor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        autor.addActionListener(this);
        about.add(autor);

        minItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        minItem.addActionListener(this);
        tools.add(minItem);

        maxItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        maxItem.addActionListener(this);
        tools.add(maxItem);

        averageItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        averageItem.addActionListener(this);
        tools.add(averageItem);


        //ToolBar
        JToolBar toolBar = new JToolBar("takie se");
        toolBar.setLayout(new GridBagLayout());
        result = new JTextArea();
        result.setPreferredSize(new Dimension(100,50));
        result.setEditable(false);

        average.addActionListener(this);
        min.addActionListener(this);
        max.addActionListener(this);
        clear.addActionListener(this);
        save.addActionListener(this);
        insert.addActionListener(this);
        toolBar.add(clear);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(save);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(insert);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(min);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(max);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(average);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(result);

        window.setSize(768, 480);
        if (dimension.getWidth() <= window.getWidth() | dimension.getHeight() <= window.getHeight()) {
            window.setSize((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);
        }
        window.setLocation(((int) dimension.getWidth() / 2) - (window.getWidth() / 2), ((int) dimension.getHeight() / 2) - ((window.getWidth())) / 2);
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
        switch (name) {
            case "o autorze":
                JOptionPane.showMessageDialog(window, "aplikacje wykonal Daniel Kuć");
                break;
            case "clear":
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        table.setValueAt(null, i, j);
                    }
                }
                break;
            case "insert":
                int ver = sliderVertical.getValue();
                int hor = sliderHorizontal.getValue();
                result.setText((String)table.getValueAt(ver, hor));
                table.setValueAt(textField.getText(), ver, hor);
                break;
            case "save":
                try {
                    FileWriter fileWriter = new FileWriter("./test.txt",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            bufferedWriter.write((String)table.getValueAt(i,j));
                            bufferedWriter.write(" ");
                        }
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                break;
            case "Minimum":
                int temp = 0;
                int tempk = 0;
                int min = 99999999;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        temp = Integer.parseInt((String)table.getValueAt(i, j));
                        if ((j + 1) < table.getColumnCount()) {
                            tempk = Integer.parseInt((String)table.getValueAt(i, j + 1));
                        }
                        if (temp < min) {
                            min = temp;
                        } else if (tempk < min) {
                            min = tempk;
                        }
                        result.setText(Integer.toString(min));
                    }
                }
                break;
            case "Maximum":
                temp = 0;
                tempk = 0;
                int max = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        temp = Integer.parseInt((String) table.getValueAt(i, j));
                        if ((j + 1) < table.getColumnCount()) {
                            tempk = Integer.parseInt((String) table.getValueAt(i, j + 1));
                        }
                        if (temp > max) {
                            max = temp;
                        } else if (tempk > max) {
                            max = tempk;
                        }
                        result.setText(Integer.toString(max));
                    }
                }
                break;
            case "average":
                int sum = 0;
                int dzielnik = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        sum += Integer.parseInt((String) table.getValueAt(i, j));
                        dzielnik++;
                    }
                    result.setText(Integer.toString(sum / dzielnik));
                }
                break;
        }

    }

    public void setConstraints(int a, int b, int d, int e, int f, int g, int h, int o) {
        c.ipadx = a; //ustawia szerokosc
        c.ipady = b; //ustawia wysokosc
        c.gridx = d; //ustawia pozycje x,y komorki w siatce
        c.gridy = e;
        c.insets = new Insets(f, g, h, o); //ustawia paddingi miedzy komorkami (gora,lewo,dol,prawo)
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        source = (JSlider) e.getSource();
        if (source.getName().equals("ver")) {
            ver = 4 - source.getValue();
        } else {
            hor = source.getValue();
        }
        result.setText((String)table.getValueAt(ver, hor));
    }
}
