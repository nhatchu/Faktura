import java.util.ArrayList;
import javax.swing.event.*;

class KlienciModel extends javax.swing.table.DefaultTableModel
{
    
    /** Creates a new instance of KlienciModel */
    public KlienciModel()
    {
        super(new String[] { "Nazwa", "Miasto", "Ulica", "NIP"}, 0);
    }
    
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Klient k = (Klient)DBManager.getInstance().getKlienci().get(rowIndex);
        switch (columnIndex)
        {
            case 0: return k.getNazwa();
            case 1: return k.getMiasto();
            case 2: return k.getUlica();
            case 3: return k.getNIP();
        }
        return new String("brak warto\u015Bci w modelu?");
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Klient k = (Klient)DBManager.getInstance().getKlienci().get(rowIndex);
        switch (columnIndex)
        {
            case 0: k.setNazwa((String)aValue); break;
            case 1: k.setMiasto((String)aValue); break;
            case 2: k.setUlica((String)aValue); break;
            case 3: k.setNIP((String)aValue); break;
        }
    }
    
    public int getRowCount()
    {
        return DBManager.getInstance().getKlienci().size();
    }
    
    public int getColumnCount()
    {
        return 4;
    }
    
    public void removeRow(int row)
    {
        DBManager.getInstance().getKlienci().remove(row);
        fireTableChanged(new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }
}
