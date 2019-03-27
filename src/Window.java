import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.TipModel;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;
import org.freixas.jcalendar.JCalendarCombo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Window implements ActionListener, ChangeListener {
    JFrame window = new JFrame("interface GUI");
    JPanel status = new JPanel();
    JPanel mainPanel = new JPanel(new FlowLayout());
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel panel;
    JFXPanel chart = new JFXPanel();
    JButton clear = new JButton("clear");
    JButton insert = new JButton("insert");
    JButton save = new JButton("save");
    JButton min = new JButton("Minimum");
    JButton max = new JButton("Maximum");
    JButton sum = new JButton("Sum");
    JButton average = new JButton("average");
    JSlider sliderHorizontal = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
    JSlider sliderVertical = new JSlider(JSlider.VERTICAL, 0, 4, 0);
    JSlider source;
    JLabel textFieldLabel = new JLabel("Podaj wartość: ");
    JLabel textAreaLabel = new JLabel("Wynik działania to: ");
    DefaultTipModel tipModel = new DefaultTipModel();
    JTipOfTheDay tipOfTheDay = new JTipOfTheDay(tipModel);
    JTable table;
    JComboBox cbSource;
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JOutlookBar outlookBar = new JOutlookBar();
    JPanel calendarPanel = new JPanel();
    JCalendarCombo calendarCombo = new JCalendarCombo();
    int ver = 4;
    int hor = 0;
    List<String> names;
    List<Double> values;
    GridBagConstraints c = new GridBagConstraints();

    public Window() {
        createWindow();
        getValueList();
        getValues();
        chart();
        tipOfTheDay.showDialog(window);
    }

    public void createWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        panel = new JPanel(new GridBagLayout());
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
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
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

        c.gridheight = 1;
        setConstraints(0, 0, 1, 4, 0, 0, 0, 0);
        clear.addActionListener(this);
        panel.add(clear, c);

        setConstraints(0, 0, 2, 4, 0, 0, 0, 0);
        insert.addActionListener(this);
        panel.add(insert, c);

        setConstraints(0, 0, 3, 4, 0, 0, 0, 0);
        save.addActionListener(this);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu tools = new JMenu("Narzędzia");
        JMenu about = new JMenu("About");
        JMenuItem autor = new JMenuItem("o autorze", KeyEvent.VK_Q);
        JMenuItem minItem = new JMenuItem("Minimum");
        JMenuItem maxItem = new JMenuItem("Maximum");
        JMenuItem averageItem = new JMenuItem("Average");
        JMenuItem sumItem = new JMenuItem("Sum");
        JMenuItem clearItem = new JMenuItem("clear");
        JMenuItem insertItem = new JMenuItem("insert");
        JMenuItem saveItem = new JMenuItem("save");

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

        sumItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        sumItem.addActionListener(this);
        tools.add(sumItem);

        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
        clearItem.addActionListener(this);
        tools.add(clearItem);

        insertItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        insertItem.addActionListener(this);
        tools.add(insertItem);

        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK));
        saveItem.addActionListener(this);
        tools.add(saveItem);


        //ToolBar
        JToolBar toolBar = new JToolBar("takie se");
        toolBar.setLayout(new GridBagLayout());
        toolBar.setFloatable(false);
        String[] selection = {"Min", "Max", "Average", "Sum"};
        JComboBox<String> jComboBox = new JComboBox<String>(selection);
        jComboBox.addActionListener(this);

        average.addActionListener(this);
        min.addActionListener(this);
        max.addActionListener(this);

        sum.addActionListener(this);
        toolBar.add(min);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(max);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(average);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(sum);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(jComboBox);
        toolBar.setPreferredSize(new Dimension(window.getWidth(), 30));
        toolBar.setBackground(Color.lightGray);

        window.setSize(768, 600);
        window.setLocation(((int) dimension.getWidth() / 2) - (window.getWidth() / 2), ((int) dimension.getHeight() / 2) - ((window.getWidth())) / 2);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        calendarCombo.setDateFormat(dateFormat);
        calendarCombo.setName("data");
        calendarCombo.addDateListener(new DateListener() {
            @Override
            public void dateChanged(DateEvent dateEvent) {
                textArea.setText(calendarCombo.getDate().toString());
            }
        });
        status.add(calendarCombo);

        //zakładki
        tabbedPane.addTab("panel",null,panel);
        tabbedPane.addTab("chart",null,chart);
        mainPanel.add(tabbedPane);

        //outlookbar
        JPanel testttt = new JPanel();
        testttt.setPreferredSize(new Dimension(50,50));
        outlookBar.addTab("one",null,testttt);
        outlookBar.setVisible(true);

        //Pasek Statusu
        status.setPreferredSize(new Dimension(window.getWidth(), 30));
        JLabel statusLabel = new JLabel("Ależ ja kocham Kapibary");
        status.add(statusLabel);
        status.setBackground(Color.lightGray);

        //Tips
        tipModel.add(new DefaultTip("1","Kapibary są fajne"));
        tipModel.add(new DefaultTip("2","I lubią pływać"));
        tipModel.add(new DefaultTip("3","Są bardzo faj"));
        tipModel.add(new DefaultTip("4","kapibara"));

        //Dodawanie do ramki
        window.add(outlookBar,BorderLayout.LINE_START);
        window.add(mainPanel);
        window.setJMenuBar(menuBar);
        window.add(toolBar, BorderLayout.PAGE_START);
        window.add(status, BorderLayout.PAGE_END);
        window.setVisible(true);

    }
    public void chart() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = new Stage();
                stage.setTitle("Pie Chart");
                Scene scene = new Scene(new Group());
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList();
                for (int i = 0; i < names.size(); i++) {
                    pieChartData.add(new PieChart.Data(names.get(i),values.get(i)));
                }
                final PieChart pieChart = new PieChart(pieChartData);
                pieChart.setTitle("Pie Chart");

                ((Group) scene.getRoot()).getChildren().add(pieChart);
                chart.setScene(scene);
            }
        });

    }
    public void getValueList() {
        names = new ArrayList<String>();
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                names.add((String) table.getValueAt(i, j));
                for (int k = names.size(); k >= 0; k--) {
                    if (k >= 2) {
                        if (names.get(names.size() - 1).equals(names.get(k - 2))) {
                            names.remove(names.size() - 1);
                            k = 0;
                        }
                    }
                }
            }
        }
    }
    public void getValues() {
        values = new ArrayList<Double>();
        for (int k = 0; k < names.size(); k++) {
            Double number = 0.0;
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    if (names.get(k).equals((String)table.getValueAt(i,j))){
                        number+=1;
                    }
                }
            }
            values.add(number);
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
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        System.out.println(name);
        switch (name) {
            case "o autorze":
                JOptionPane.showMessageDialog(window, "aplikację wykonał Daniel Kuć");
                break;
            case "clear":
                Object[] wybor = {"Tak", "Nie"};
                int choise = JOptionPane.showOptionDialog(window, "Czy na pewno chcesz wyczyścić całą tabelę?", "Pytanie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, wybor, wybor[1]);
                if (choise == 0) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            table.setValueAt(null, i, j);
                        }
                    }
                } else if (choise == 1) {
                    return;
                }
                break;
            case "insert":
                table.setValueAt(textField.getText(), ver, hor);
                break;
            case "save":
                try {
                    FileWriter fileWriter = new FileWriter("./test.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            bufferedWriter.write((String) table.getValueAt(i, j));
                            bufferedWriter.write(" ");
                        }
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.close();
                } catch (IOException | NumberFormatException ee) {
                    ee.printStackTrace();
                    textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                }
                break;
            case "Minimum":
                int temp = 0;
                int tempk = 0;
                int min = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        try {
                            temp = Integer.parseInt((String) table.getValueAt(i, j));
                            if ((j + 1) < table.getColumnCount()) {
                                tempk = Integer.parseInt((String) table.getValueAt(i, j + 1));
                            }
                            if (i == 0 & j == 0) {
                                if (temp < tempk) {
                                    min = temp;
                                } else {
                                    min = tempk;
                                }
                            }
                            if (temp < min) {
                                min = temp;
                            } else if (tempk < min) {
                                min = tempk;
                            }
                        } catch (NumberFormatException nfe) {
                            textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                        }
                    }
                    textArea.setText(Integer.toString(min));
                }
                break;
            case "Maximum":
                temp = 0;
                tempk = 0;
                int max = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        try {
                            temp = Integer.parseInt((String) table.getValueAt(i, j));
                            if ((j + 1) < table.getColumnCount()) {
                                tempk = Integer.parseInt((String) table.getValueAt(i, j + 1));
                            }
                            if (temp > max) {
                                max = temp;
                            } else if (tempk > max) {
                                max = tempk;
                            }
                        } catch (NumberFormatException nfe) {
                            textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                        }
                    }
                    textArea.setText(Integer.toString(max));
                }
                break;
            case "average":
                int sum = 0;
                int dzielnik = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        try {
                            sum += Integer.parseInt((String) table.getValueAt(i, j));
                            dzielnik++;
                        } catch (NumberFormatException | ArithmeticException nfe) {
                            textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                        }
                    }
                    textArea.setText(Integer.toString(sum / dzielnik));
                }
                break;
            case "Sum":
                sum = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        try {
                            sum += Integer.parseInt((String) table.getValueAt(i, j));
                        } catch (NumberFormatException nfe) {
                            textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                        }
                    }
                    textArea.setText(Integer.toString(sum));
                }
                break;
            case "comboBoxChanged":
                cbSource = (JComboBox) e.getSource();
                String cb = (String) cbSource.getSelectedItem();
                switch (cb) {
                    case "Min":
                        temp = 0;
                        tempk = 0;
                        min = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                try {
                                    temp = Integer.parseInt((String) table.getValueAt(i, j));
                                    if ((j + 1) < table.getColumnCount()) {
                                        tempk = Integer.parseInt((String) table.getValueAt(i, j + 1));
                                    }
                                    if (i == 0 & j == 0) {
                                        if (temp < tempk) {
                                            min = temp;
                                        } else {
                                            min = tempk;
                                        }
                                    }
                                    if (temp < min) {
                                        min = temp;
                                    } else if (tempk < min) {
                                        min = tempk;
                                    }
                                } catch (NumberFormatException nfe) {
                                    textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                                }
                            }
                            textArea.setText(Integer.toString(min));
                        }
                        break;
                    case "Max":
                        temp = 0;
                        tempk = 0;
                        max = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                try {
                                    temp = Integer.parseInt((String) table.getValueAt(i, j));
                                    if ((j + 1) < table.getColumnCount()) {
                                        tempk = Integer.parseInt((String) table.getValueAt(i, j + 1));
                                    }
                                    if (temp > max) {
                                        max = temp;
                                    } else if (tempk > max) {
                                        max = tempk;
                                    }
                                } catch (NumberFormatException nfe) {
                                    textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                                }
                            }
                            textArea.setText(Integer.toString(max));
                        }
                        break;
                    case "Average":
                        sum = 0;
                        dzielnik = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                try {
                                    sum += Integer.parseInt((String) table.getValueAt(i, j));
                                    dzielnik++;
                                } catch (NumberFormatException | ArithmeticException nfe) {
                                    textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                                }
                            }
                            textArea.setText(Integer.toString(sum / dzielnik));
                        }
                        break;
                    case "Sum":
                        sum = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                try {
                                    sum += Integer.parseInt((String) table.getValueAt(i, j));
                                } catch (NumberFormatException nfe) {
                                    textArea.setText("W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane");
                                }
                            }
                            textArea.setText(Integer.toString(sum));
                        }
                        break;
                }
                break;
        }


    }
    @Override
    public void stateChanged(ChangeEvent e) {
        source = (JSlider) e.getSource();
        if (source.getName().equals("ver")) {
            ver = 4 - source.getValue();
        } else {
            hor = source.getValue();
        }
    }
}
