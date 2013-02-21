//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Rectangle;
import java.util.prefs.Preferences;


class Faktura extends JFrame 
{
	private class tabCL implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			if (jt.getSelectedIndex() == 1)
			{
				pp.refreshPreview();
			}
			if (jt.getSelectedIndex() == 2)
			{
				fw.refresh();
			}
		}
	}
	
	private class winWL extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
                    Rectangle rect;
                    rect = e.getWindow().getBounds();
                    Preferences prefs = Preferences.userNodeForPackage(e.getWindow().getClass());
                    prefs = prefs.node("MainDlg");
                    prefs.putInt("X", rect.x);
                    prefs.putInt("Y", rect.y);
                    prefs.putInt("Width", rect.width);
                    prefs.putInt("Height", rect.height);
		   
                    try
                    {
                    	DaneWyst = DWTab.getDaneW();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("dw.dat"));
			out.writeObject(DaneWyst);
			out.close();
                    }
                    catch(Exception ex)
                    {
                    	System.out.println("Nie mo\u017Cna zapisa\u0107 pliku z danymi wystawiaj\u0105cego");
                    }
                    DBManager.getInstance().saveData();
                    System.exit(0);
		}
            public void windowOpened(WindowEvent e)
            {
                Rectangle rect = new Rectangle();
                Preferences prefs = Preferences.userNodeForPackage(e.getWindow().getClass());
                prefs = prefs.node("MainDlg");
                rect.x = prefs.getInt("X", 100);
                rect.y = prefs.getInt("Y", 100);
                rect.width = prefs.getInt("Width", 800);
                rect.height = prefs.getInt("Height", 630);
                e.getWindow().setBounds(rect);
            }
	}
	
	Faktura()
	{
		super("Faktura 3.0");
		Container cp = getContentPane();
		jt = new JTabbedPane();
		mt.setMainWnd(this);
		pp = new PrintPreview(mt);
		fw = new FakturyWystawione(this);
		
		DaneWyst = loadDW();
		if (DaneWyst == null)
		{
			DaneWyst = new DaneWystawiajacego();
			DaneWyst.setNazwa("Nazwa firmy");
			DaneWyst.setMiasto("Miasto");
			DaneWyst.setUlica("Ulica");
			DaneWyst.setNip("NIP");
			DaneWyst.setKonto("Konto firmowe");
			DaneWyst.setWystawiajacy("Nazwisko i imi\u0119 wystawiaj\u0105cego");
		}
		DWTab = new DaneWystTab(DaneWyst);

		jt.addTab("Edycja faktury", mt);
		jt.addTab("Podgl\u0105d wydruku", pp);
		jt.addTab("Faktury wystawione", fw);
		jt.addTab("Dane wystawiaj\u0105cego", DWTab);
		jt.addTab("O programie", new About());
		jt.addChangeListener(new tabCL());
		cp.add(jt);
		cp.add(new JLabel(DaneWyst.getNazwa()), BorderLayout.SOUTH);
		addWindowListener(new winWL());
	}
	
	static void init(JFrame jf)
	{
		jf.setResizable(false);
		jf.setVisible(true);
  	}

	public static void main(String[] args) 
	{
		Faktura fakt = new Faktura();
		init(fakt);
	}
	
	public DaneWystawiajacego getDW()
	{
		return DWTab.getDaneW();
	}
	
	private DaneWystawiajacego loadDW()
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("dw.dat"));
			DaneWystawiajacego dw = (DaneWystawiajacego)in.readObject();
			return dw;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Brak pliku z danymi wystawiajacego");
			return null;
		}
		catch(IOException e)
		{
			System.out.println("Blad odczytu pliku z danymi wystawiajacego");
			return null;
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Bledny format pliku z danymi wystawiajacego");
			return null;
		}
	}
	
	public void setData(FakturaData fd)
	{
		DaneWyst = fd.getDaneWystawiajacego();
		DWTab.setData(DaneWyst);
		mt.setData(fd);
	}
	
	private MainTab mt = new MainTab();
	private PrintPreview pp;
	private FakturyWystawione fw;
	private JTabbedPane jt;
	private DaneWystawiajacego DaneWyst;
	private DaneWystTab DWTab;
}
