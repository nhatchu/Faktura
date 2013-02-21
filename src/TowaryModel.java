import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
class TowaryModel extends DefaultTableModel
{
     /** Creates a new instance of KlienciModel */
    public TowaryModel()
    {
        super(new String[] { "Nazwa", "Cena", "Jednostka", "PKOB", "VAT"}, 0);
    }
    
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Towar t = (Towar)DBManager.getInstance().getTowary().get(rowIndex);
        switch (columnIndex)
        {
            case 0: return t.getNazwa(); 
            case 1: return t.getCena();
            case 2: return t.getJednostka();
            case 3: return t.getSWW();
            case 4: return t.getVAT();
        }
        return new String("brak wartosci w modelu?");
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Towar t = (Towar)DBManager.getInstance().getTowary().get(rowIndex);
        switch (columnIndex)
        {
            case 0: t.setNazwa((String)aValue); break;
            case 1: t.setCena((String)aValue); break;
            case 2: t.setJednostka((String)aValue); break;
            case 3: t.setSWW((String)aValue); break;
            case 4: t.setVAT((String)aValue); break;
        }
    }
    
    public int getRowCount()
    {
        return DBManager.getInstance().getTowary().size();
    }
    
    public int getColumnCount()
    {
        return 5;
    }
    
    public void removeRow(int row)
    {
        DBManager.getInstance().getTowary().remove(row);
        fireTableChanged(new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }
}
