//Aleksander Sulkowski 2002.01.01.
import javax.swing.table.*;
import java.util.ArrayList;
import javax.swing.event.*;

class DataModel extends AbstractTableModel
{
	public int getRowCount()
	{
		return data.size();
	}
	public int getColumnCount()
	{
		return ColumnsTitles.length;
	}
	public Object getValueAt(int row, int column)
	{
		DataEntry dat = (DataEntry)data.get(row);
		switch (column)
		{
			case 0:
			{
				return ("" + (row + 1));
			}
			case 1:
			{
				return dat.getNazwa();
			}
			case 2:
			{
				return dat.getIlosc();
			}
			case 3:
			{
				return dat.getJednostka();
			}
			case 4:
			{
				return dat.getCenaNetto();
			}
			case 5:
			{
				return DataEntry.rd(dat.getWartoscNetto());
			}
			case 6:
			{
				return dat.getSww();
			}
			case 7:
			{
				return dat.getVatStawka();
			}
			case 8:
			{
				return DataEntry.rd(dat.getVatKwota());
			}
			case 9:
			{
				return DataEntry.rd(dat.getWartoscBrutto());
			}
		}
		return "";
	}
	public String getColumnName(int column)
	{
		return ColumnsTitles[column];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}
	public void addDataEntry(DataEntry dat)
	{
		data.add(dat);
		TableModelEvent e = new TableModelEvent(this, data.size() - 1, data.size() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		fireTableChanged(e);
	}
	public void delSelectedEntry(int sel)
	{
		if (sel != -1)
		{
			data.remove(sel);
			TableModelEvent e = new TableModelEvent(this, sel, data.size(), TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
			fireTableChanged(e);
		}
	}
	public DataEntry getDataEntry(int i)
	{
		DataEntry dat = (DataEntry)data.get(i);
		return dat;
	}
	
	public void setDataEntry(int i, DataEntry dat)
	{
		data.set(i, dat);
		TableModelEvent e = new TableModelEvent(this, i);
		fireTableChanged(e);
	}
	
	public ArrayList getData()
	{
		return data;
	}
	
	public void setData(ArrayList al)
	{
		data = al;
		TableModelEvent e = new TableModelEvent(this);
		fireTableChanged(e);
	}
	
	public static final String[] ColumnsTitles = {"Lp", "Nazwa", "Ilo\u015B\u0107", "j.m",
    "Cena netto", "Wart. netto", "PKWiU", "VAT", "VAT(z\u0142)", "Wart. brutto"};
    
	ArrayList data = new ArrayList();
}
