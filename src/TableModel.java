import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final int countRowTable = 5;
    private final int countColumnTable = 5;
    private final int names = 5;
    private String[] colname = new String[names];
    private Integer[][] data = new Integer[countRowTable][countColumnTable];
    public TableModel(){
        for (int i = 0; i<countRowTable;i++){
            for(int j = 0; j<countColumnTable;j++){
                data[i][j] = j;
            }
        }
    }
    public TableModel(Integer[][] a,String[] b){
        this.data = a;
        this.colname = b;
    }
    public String getColumnName(int column) { return colname[column].toString();}
    public void insertIntoTable(int a,int b,int c){
        data[a][b] = c;
        fireTableDataChanged();
    }
    public void clearTableValues() {
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                data[i][j] = 0;
            }
        }
        fireTableDataChanged();
    }
    public void printTableValues() {
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                System.out.println(data[i][j]);
            }
        }
    }
    public Object showMinimum(){
        int temp = 0;
        int tempk = 0;
        int min = 0;
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                try {
                    temp = data[i][j];
                    if ((j + 1) < countColumnTable) {
                        tempk = data[i][j+1];
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
                    return "W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane";
                }
            }
        }
        return min;
    }
    public Object showMaximum(){
        int temp = 0;
        int tempk = 0;
        int max = 0;
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                try {
                    temp = data[i][j];
                    if ((j + 1) < countColumnTable) {
                        tempk = data[i][j+1];
                    }
                    if (temp > max) {
                        max = temp;
                    } else if (tempk > max) {
                        max = tempk;
                    }
                } catch (NumberFormatException nfe) {
                    return "W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane";
                }
            }
        }
        return max;
    }
    public Object showAverage(){
        int sum = 0;
        int dzielnik = 0;
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                try {
                    sum += data[i][j];
                    dzielnik++;
                } catch (NumberFormatException | ArithmeticException nfe) {
                    return "W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane";
                }
            }
        }
        return sum/dzielnik;
    }
    public Object showSum(){
        int sum = 0;
        for (int i = 0; i < countRowTable; i++) {
            for (int j = 0; j < countColumnTable; j++) {
                try {
                    sum += data[i][j];
                } catch (NumberFormatException nfe) {
                    return "W tabeli nie znajdują się liczby lub są one niepoprawnie zapisane";
                }
            }
        }
        return sum;
    }

    @Override
    public int getColumnCount() {
        return countColumnTable;
    }

    @Override
    public int getRowCount() {
        return countRowTable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object object = (Object) data[rowIndex][columnIndex];
        return object;
    }
}
