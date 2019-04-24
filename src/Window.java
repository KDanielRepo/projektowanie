import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.TipModel;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import org.apache.log4j.*;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
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
    PatternLayout patternLayout = new PatternLayout("%-5p%d%F[%L]:%m%n");
    static Logger logger = Logger.getLogger("mylogger");
    int ver = 4;
    int hor = 0;
    List<String> names;
    List<Double> values;
    GridBagConstraints c = new GridBagConstraints();
    String[] columny = {"jeden", "dwa", "trzy", "cztery", "piec"};
    Integer[][] dane = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
    TableModel tableModel = new TableModel(dane, columny);
    JFileChooser fileChooser = new JFileChooser();
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public Window() {
        createWindow();
        getValueList();
        getValues();
        tipOfTheDay.showDialog(window);
    }

    public void createWindow() {
        panel = new JPanel(new GridBagLayout());
        table = new JTable(tableModel);
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
        panel.add(save, c);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu tools = new JMenu("Tools");
        JMenu about = new JMenu("About");
        JMenuItem autor = new JMenuItem("o autorze", KeyEvent.VK_Q);
        JMenuItem minItem = new JMenuItem("Minimum");
        JMenuItem maxItem = new JMenuItem("Maximum");
        JMenuItem averageItem = new JMenuItem("Average");
        JMenuItem sumItem = new JMenuItem("Sum");
        JMenuItem clearItem = new JMenuItem("clear");
        JMenuItem insertItem = new JMenuItem("insert");
        JMenuItem saveItem = new JMenuItem("save");
        JMenuItem help = new JMenuItem("help");

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(tools);
        menuBar.add(about);

        autor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        autor.addActionListener(this);
        about.add(autor);

        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        help.addActionListener(this);
        about.add(help);

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
        edit.add(clearItem);

        insertItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        insertItem.addActionListener(this);
        edit.add(insertItem);

        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK));
        saveItem.addActionListener(this);
        file.add(saveItem);


        //ToolBar
        JToolBar toolBar = new JToolBar("toolBar");
        toolBar.setFloatable(false);
        String[] selection = {"Min", "Max", "Average", "Sum"};
        JComboBox<String> jComboBox = new JComboBox<String>(new ComboBoxModel(selection));
        jComboBox.addActionListener(this);
        jComboBox.setMaximumSize(new Dimension(50,30));

        average.addActionListener(this);
        ImageIcon averageIcon = new ImageIcon("./resources/avg.png");
        average.setIcon(averageIcon);
        min.addActionListener(this);
        ImageIcon minIcon = new ImageIcon("./resources/min.png");
        min.setIcon(minIcon);
        ImageIcon maxIcon = new ImageIcon("./resources/max.png");
        max.addActionListener(this);
        max.setIcon(maxIcon);
        ImageIcon sumIcon = new ImageIcon("./resources/sum.png");
        sum.setIcon(sumIcon);
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
        toolBar.addSeparator(new Dimension(400, 10));
        toolBar.setPreferredSize(new Dimension(window.getWidth(), 64));
        toolBar.setBackground(Color.lightGray);

        window.setSize(800, 600);
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
        tabbedPane.addTab("panel", null, panel);
        tabbedPane.addTab("chart", null, chart);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                getValueList();
                getValues();
                chart();
            }
        });
        mainPanel.add(tabbedPane);

        //outlookbar
        JPanel outlook = new JPanel();
        outlook.setPreferredSize(new Dimension(50, 50));
        outlookBar.addTab("one", null, outlook);
        outlookBar.setVisible(true);

        //Pasek Statusu
        status.setPreferredSize(new Dimension(window.getWidth(), 30));
        JLabel statusLabel = new JLabel("Ależ ja kocham Kapibary");
        status.add(statusLabel);
        status.setBackground(Color.lightGray);

        //Tips
        tipModel.add(new DefaultTip("1", "Kapibary są fajne"));
        tipModel.add(new DefaultTip("2", "I lubią pływać"));
        tipModel.add(new DefaultTip("3", "Są bardzo fajne"));
        tipModel.add(new DefaultTip("4", "kapibara"));

        //Dodawanie do ramki
        window.add(outlookBar, BorderLayout.LINE_START);
        window.add(mainPanel);
        window.setJMenuBar(menuBar);
        window.add(toolBar, BorderLayout.PAGE_START);
        window.add(status, BorderLayout.PAGE_END);
        window.setVisible(true);

        //logger
        BasicConfigurator.configure();
        try {
            FileAppender fileAppender = new FileAppender(patternLayout, "filelogs.log");
            ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout);
            logger.addAppender(fileAppender);
            logger.addAppender(consoleAppender);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("start");

    }
        //Informacje o autorze w nowym oknie nr. versji, nazwa aplikacji, autor, licencja
    //dodac help
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
                    pieChartData.add(new PieChart.Data(names.get(i), values.get(i)));
                }
                final PieChart pieChart = new PieChart(pieChartData);
                pieChart.setTitle("Pie Chart");

                ((Group) scene.getRoot()).getChildren().add(pieChart);
                chart.setScene(scene);
            }
        });
        for (int i = 0; i < names.size(); i++) {
            logger.info("nazwa: " + names.get(i) + ", ilość: " + values.get(i));
        }
    }

    public void getValueList() {
        names = new ArrayList<String>();
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                names.add(Integer.toString((Integer) table.getValueAt(i, j)));
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
                    if (names.get(k).equals(Integer.toString((Integer) table.getValueAt(i, j)))) {
                        number += 1;
                    }
                }
            }
            values.add(number);
        }
    }

    public void showAuthor(){
        JFrame jFrame = new JFrame("About Me");
        JPanel jPanel = new JPanel(new GridLayout(5,1));
        JLabel apk = new JLabel("Aplikacja zaliczeniowa");
        JLabel ver = new JLabel("Nr. wersji: 0.9.0");
        JLabel lic = new JLabel("Licencja Freeware");
        JLabel me = new JLabel("Twórca: Daniel Kuć");
        JLabel con = new JLabel("Kontakt: Daniel200825@wp.pl");
        jFrame.add(jPanel);
        jPanel.add(apk);
        jPanel.add(ver);
        jPanel.add(lic);
        jPanel.add(me);
        jPanel.add(con);
        jFrame.setSize(300,300);
        jFrame.setLocation(((int) dimension.getWidth() / 2) - (window.getWidth() / 2), ((int) dimension.getHeight() / 2) - ((window.getWidth())) / 2);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    public void showHelp(){
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new FlowLayout());
        JTabbedPane jTabbedPane = new JTabbedPane();
        jPanel.setSize(200,200);
        JLabel apk = new JLabel("Aplikacja testowa");
        JLabel ver = new JLabel("Nr. wersji: 1.0.0");
        JLabel lic = new JLabel("Licencja Freeware");
        JLabel me = new JLabel("Twórca: Daniel Kuć");
        JPanel fileHelp = new JPanel();
        jPanel.add(apk);
        fileHelp.add(apk);
        JPanel editHelp = new JPanel();
        editHelp.add(ver);
        JPanel toolsHelp = new JPanel();
        toolsHelp.add(lic);
        JPanel aboutHelp = new JPanel();
        aboutHelp.add(me);
        jTabbedPane.addTab("file", null,fileHelp);
        jTabbedPane.addTab("edit", null,editHelp);
        jTabbedPane.addTab("tools", null,toolsHelp);
        jTabbedPane.addTab("about", null,aboutHelp);
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });
        jPanel.add(jTabbedPane);
        jFrame.add(jPanel);
        jFrame.setSize(300,300);
        jFrame.setLocation(((int) dimension.getWidth() / 2) - (window.getWidth() / 2), ((int) dimension.getHeight() / 2) - ((window.getWidth())) / 2);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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
                //JOptionPane.showMessageDialog(window, "aplikację wykonał Daniel Kuć");
                showAuthor();
                logger.info("o autorze");
                break;
            case "help":
                showHelp();
                break;
            case "clear":
                Object[] wybor = {"Tak", "Nie"};
                int choise = JOptionPane.showOptionDialog(window, "Czy na pewno chcesz wyczyścić całą tabelę?", "Pytanie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, wybor, wybor[1]);
                if (choise == 0) {
                    tableModel.clearTableValues();
                    logger.info("table cleared");
                } else if (choise == 1) {
                    logger.info("clear aborted");
                    return;
                }
                break;
            case "insert":
                tableModel.insertIntoTable(ver, hor, Integer.parseInt(textField.getText()));
                logger.info(Integer.parseInt(textField.getText())+" value inserted");
                break;
            case "save":
                File file = new File("./test.txt");
                fileChooser.setCurrentDirectory(file);
                fileChooser.setSelectedFile(file);
                int fileChoise = fileChooser.showSaveDialog(window);
                if (fileChoise == JFileChooser.APPROVE_OPTION) {
                    try {
                        if(fileChooser.getSelectedFile().exists()){
                            Object[] yesNo = {"Tak", "Nie"};
                            int choisee = JOptionPane.showOptionDialog(window, "Plik o takiej nazwie już istnieje, czy nadpisać go?", "Pytanie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesNo, yesNo[1]);
                            if (choisee == 0) {
                                fileChooser.getSelectedFile().createNewFile();
                                FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile());
                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                for (int i = 0; i < tableModel.getRowCount(); i++) {
                                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                                        bufferedWriter.write((Integer.toString((Integer) tableModel.getValueAt(i, j))));
                                        bufferedWriter.write(" ");
                                    }
                                    bufferedWriter.newLine();
                                }
                                logger.info("File overriten and saved!");
                                textArea.setText("File Saved!");
                                bufferedWriter.close();
                            } else if (choisee == 1) {
                                return;
                            }
                        }else{
                            fileChooser.getSelectedFile().createNewFile();
                            FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile());
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            for (int i = 0; i < tableModel.getRowCount(); i++) {
                                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                                    bufferedWriter.write((Integer.toString((Integer) tableModel.getValueAt(i, j))));
                                    bufferedWriter.write(" ");
                                }
                                bufferedWriter.newLine();
                            }
                            logger.info("File saved!");
                            textArea.setText("File Saved!");
                            bufferedWriter.close();
                        }
                    } catch (IOException ee) {
                        ee.printStackTrace();
                        textArea.setText("File save failed due to an unexpected Error");
                    }
                }else if(fileChoise == JFileChooser.CANCEL_OPTION){
                    logger.info("save aborted");
                    textArea.setText("Save Aborted");
                }
                break;
            case "Minimum":
                textArea.setText(Integer.toString((Integer) tableModel.showMinimum()));
                logger.info("Showed Minimum");
                break;
            case "Maximum":
                textArea.setText(Integer.toString((Integer) tableModel.showMaximum()));
                logger.info("Showed Maximum");
                break;
            case "average":
                logger.info("Showed Average");
                textArea.setText(Integer.toString((Integer) tableModel.showAverage()));
                break;
            case "Sum":
                logger.info("Showed Sum");
                textArea.setText(Integer.toString((Integer) tableModel.showSum()));
                break;
            case "comboBoxChanged":
                cbSource = (JComboBox) e.getSource();
                String cb = (String) cbSource.getSelectedItem();
                switch (cb) {
                    case "Min":
                        textArea.setText(Integer.toString((Integer) tableModel.showMinimum()));
                        logger.info("Showed minimum");
                        break;
                    case "Max":
                        textArea.setText(Integer.toString((Integer) tableModel.showMaximum()));
                        logger.info("Showed Maximum");
                        break;
                    case "Average":
                        textArea.setText(Integer.toString((Integer) tableModel.showAverage()));
                        logger.info("Showed Average");
                        break;
                    case "Sum":
                        textArea.setText(Integer.toString((Integer) tableModel.showSum()));
                        logger.info("Showed Sum");
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
