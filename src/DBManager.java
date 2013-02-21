import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

class DBManager
{
    private class TowarComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            String s1 = ((Towar)o1).getNazwa();
            String s2 = ((Towar)o2).getNazwa();
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }
    
    private class KlienciComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            String s1 = ((Klient)o1).getNazwa();
            String s2 = ((Klient)o2).getNazwa();
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

  
    public ArrayList getKlienci()
    {
        return _KlienciLst;
    }
    
    public ArrayList getTowary()
    {
        return _TowaryLst;
    }
    
    private DBManager()
    {
        readTowary();
        readKlienci();
        Collections.sort(_TowaryLst, new TowarComparator());
        Collections.sort(_KlienciLst, new KlienciComparator());
    }
    
    public static DBManager getInstance()
    {
        if (_TheInstance == null)
        {
            _TheInstance = new DBManager();
        }
        return _TheInstance;
    }
    
    public void saveData()
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Klienci.dat"));
            out.writeObject(_KlienciLst);
            out.close();
	}
	catch(Exception ex)
	{
            JOptionPane.showMessageDialog(null, "Nie moøna zapisa\u0107 danych klient\u00F3w", "B\u0142\u0105d zapisu", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Towary.dat"));
            out.writeObject(_TowaryLst);
            out.close();
	}
	catch(Exception ex)
	{
            JOptionPane.showMessageDialog(null, "Nie moøna zapisa\u0107 danych towar\u00F3w", "B\u0142\u0105d zapisu", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    
    private void readTowary()
    {
        try
	{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Towary.dat"));
            _TowaryLst = (ArrayList)in.readObject();
	}
	catch(FileNotFoundException e)
	{
            System.out.println("Brak pliku z danymi towarow");
            _TowaryLst = new ArrayList();
	}
	catch(IOException e)
	{
            System.out.println("Blad odczytu pliku z danymi towarow");
            _TowaryLst = new ArrayList();
	}
	catch(ClassNotFoundException e)
	{
            System.out.println("Bledny format pliku z danymi towarow");
            _TowaryLst = new ArrayList();
	}
    }
    
    private void readKlienci()
    {
        try
	{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Klienci.dat"));
            _KlienciLst = (ArrayList)in.readObject();
	}
	catch(FileNotFoundException e)
	{
            System.out.println("Brak pliku z danymi klient\u00F3w");
            _KlienciLst = new ArrayList();
	}
	catch(IOException e)
	{
            System.out.println("B\u0142ad odczytu pliku z danymi klient\u00F3w");
            _KlienciLst = new ArrayList();
	}
	catch(ClassNotFoundException e)
	{
            System.out.println("B\u0142edny format pliku z danymi klient\u00F3w");
            _KlienciLst = new ArrayList();
	}
    }
    
    private static DBManager _TheInstance = null;
    private ArrayList _KlienciLst;
    private ArrayList _TowaryLst;
}
