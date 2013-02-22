//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainTab extends JPanel implements IKlientHolder
{
	class dataFL extends FocusAdapter
	{
		public void focusGained(FocusEvent e)
		{
			DataSprze.setText(DataWyst.getText());
		}
	}
	
	class dodajAL implements ActionListener
	{
        public void actionPerformed(ActionEvent e)
		{
			_epp.sw();
			DataEntry dat = _epp.getData();
			if (_epp.isOk())
			{
				DataModel dm = (DataModel)loa.getModel();
				dm.addDataEntry(dat);
			}
		}
	}
	
	class kasujAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			DataModel dm = (DataModel)loa.getModel();
			dm.delSelectedEntry(loa.getSelectedRow());
		}
	}
	
	class  tableML extends MouseAdapter
	{
        public void mouseClicked(MouseEvent e)
		{
			int sel = loa.getSelectedRow();
			if (sel == -1)
			{
				return;
			}
			
			DataModel dm = (DataModel)loa.getModel();
			_epp.initData(dm.getDataEntry(sel));
			_epp.sw();
			if (_epp.isOk())
			{
				DataEntry dat = _epp.getData();
				dm.setDataEntry(sel, dat);
			}
		}
	}
	
	class selL implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if (loa.getSelectedRow() != -1)
			{
				jkasuj.setEnabled(true);
			}
			else
			{
				jkasuj.setEnabled(false);
			}
		}
	}
    
    
	MainTab()
	{
        _BazaKlientowDlg = new BazaKlientowDlg(m_mainFr, false, this);
        _epp = new EntryPropPage(m_mainFr, true);
        JMenuItem mi = new JMenuItem("Dodaj do bazy");
        mi.addActionListener(new ActionListener()
                             {
            public void actionPerformed(ActionEvent e)
            {
                Klient k = new Klient();
                k.setNazwa(NazwaFirmy.getText());
                k.setNIP(Nip.getText());
                k.setMiasto(AdresFirmyMiasto.getText());
                k.setUlica(AdresFirmyUlica.getText());
                DBManager.getInstance().getKlienci().add(k);
                _BazaKlientowDlg.klientAdded();
            }
        });
        _BazaMenu.add(mi);
        mi = new JMenuItem("Baza klient\u00F3w...");
        mi.addActionListener(new ActionListener()
                             {
            public void actionPerformed(ActionEvent e)
            {
                _BazaKlientowDlg.setVisible(true);
            }
        });
        _BazaMenu.add(mi);
        Box bvMain = Box.createVerticalBox();
        Box bv1 = Box.createHorizontalBox();
        Box bv2 = Box.createHorizontalBox();
        Box bv3 = Box.createHorizontalBox();
        Box bv4 = Box.createHorizontalBox();
        Box bv5 = Box.createHorizontalBox();
        Box bv6 = Box.createHorizontalBox();
        Box bv7 = Box.createHorizontalBox();
        Box bv8 = Box.createHorizontalBox();
        Box bv9 = Box.createHorizontalBox();
        Box bv10 = Box.createHorizontalBox();
        Box bv11 = Box.createHorizontalBox();
        
        bv1.add(new JLabel("Nazwa firmy"));
        bv1.add(Box.createHorizontalStrut(10));
        bv1.add(NazwaFirmy);
        bv1.add(Box.createHorizontalStrut(10));
        _btBaza = new JButton("Baza...");
        _btBaza.addActionListener(new ActionListener()
                                  {
            public void actionPerformed(ActionEvent e)
            {
                _BazaMenu.show(_btBaza, 0, _btBaza.getY());
            }
        });
        bv1.add(_btBaza);
        bv1.add(Box.createHorizontalStrut(10));
		bv2.add(new JLabel("Adres firmy"));
		bv3.add(new JLabel("Miasto"));
		bv3.add(Box.createHorizontalStrut(10));
		bv3.add(AdresFirmyMiasto);
		bv3.add(Box.createHorizontalStrut(10));
		bv3.add(new JLabel("Ulica"));
		bv3.add(Box.createHorizontalStrut(10));
		bv3.add(AdresFirmyUlica);
		bv4.add(new JLabel("NIP"));
		bv4.add(Box.createHorizontalStrut(10));
		bv4.add(Nip);
		bv4.add(Box.createHorizontalStrut(20));
		bv4.add(new JLabel("Data wystawienia faktury"));
		bv4.add(Box.createHorizontalStrut(10));
		bv4.add(DataWyst);
		bv4.add(Box.createHorizontalStrut(10));
		bv4.add(new JLabel("Data wykonania us\u0142ugi"));
		bv4.add(Box.createHorizontalStrut(10));
		DataSprze.addFocusListener(new dataFL());
		bv4.add(DataSprze);
		bv5.add(new JLabel("Miejsce wystawienia"));
		bv5.add(Box.createHorizontalStrut(10));
		MiejsceWystawienia.setMaximumSize(new Dimension(1000, 21));
		MiejsceWystawienia.setMinimumSize(new Dimension(400, 21));
		bv5.add(MiejsceWystawienia);
		bv5.add(Box.createHorizontalStrut(10));
		bv5.add(new JLabel("Spos\u00F3b zap\u0142aty"));
		bv5.add(Box.createHorizontalStrut(10));
		SposZapl.addItem("got\u00F3wka");
		SposZapl.addItem("przelew");
		SposZapl.setEditable(true);
		SposZapl.setMaximumSize(new Dimension(300, 22));
		bv5.add(SposZapl);
		bv6.add(new JLabel("Termin p\u0142atno\u015Bci"));
		bv6.add(Box.createHorizontalStrut(10));
		bv6.add(TerminPlatn);
		bv6.add(Box.createHorizontalStrut(20));
		bv6.add(new JLabel("Odbieraj\u0105cy"));
		bv6.add(Box.createHorizontalStrut(10));
		bv6.add(Odbierajacy);
		bv6.add(Box.createHorizontalStrut(20));
		bv6.add(new JLabel("Numer faktury"));
		bv6.add(Box.createHorizontalStrut(10));
		NumerFaktury.setText("/" + getRok());
		bv6.add(NumerFaktury);
		DefaultListSelectionModel dlsm = new DefaultListSelectionModel();
		dlsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dlsm.addListSelectionListener(new selL());
		loa.setSelectionModel(dlsm);
		loa.addMouseListener(new tableML());
		JScrollPane scrollPane = new JScrollPane(loa);
		bv7.add(new JLabel("Lista towar\u00F3w do umieszczenia na fakturze"));
		scrollPane.setPreferredSize(new Dimension(750, 130));
		bv8.add(scrollPane);
        
		bv10.add(new JLabel("Uwagi"));
		
		JScrollPane UwagiScroll = new JScrollPane(Uwagi);
		UwagiScroll.setPreferredSize(new Dimension(200, 80));
		bv11.add(UwagiScroll);
        
		jbdodaj.addActionListener(new dodajAL());
		jkasuj.addActionListener(new kasujAL());
		bv9.add(jbdodaj);
		bv9.add(Box.createHorizontalStrut(20));
		jkasuj.setEnabled(false);
		bv9.add(jkasuj);
		
		bvMain.add(Box.createVerticalStrut(20));
		bvMain.add(bv1);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv2);
		bvMain.add(Box.createVerticalStrut(5));
		bvMain.add(bv3);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv4);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv5);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv6);
		bvMain.add(Box.createVerticalStrut(30));
		bvMain.add(bv7);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv8);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(bv9);
        
		bvMain.add(Box.createVerticalStrut(30));
		bvMain.add(bv10);
		bvMain.add(Box.createVerticalStrut(5));
		bvMain.add(bv11);
		add(bvMain);
	}
	
	void setMainWnd(JFrame fr)
	{
		m_mainFr = (Faktura)fr;
	}
	
	public FakturaData getFakturaData()
	{
		FakturaData fd = new FakturaData();
		fd.setNazwaFirmy(NazwaFirmy.getText());
		fd.setAdresFirmyMiasto(AdresFirmyMiasto.getText());
		fd.setAdresFirmyUlica(AdresFirmyUlica.getText());
		fd.setNip(Nip.getText());
		fd.setDataWyst(DataWyst.getText());
		fd.setDataSprze(DataSprze.getText());
		fd.setTerminPlatn(TerminPlatn.getText());
		fd.setNumerFakt(NumerFaktury.getText());
		fd.setSposobZapl((String)SposZapl.getSelectedItem());
		fd.setOdbierajacy(Odbierajacy.getText());
		fd.setMiejsceWystawienia(MiejsceWystawienia.getText());
		fd.setUwagi(Uwagi.getText());
		DataModel dm = (DataModel)loa.getModel();
		ArrayList wpisy = dm.getData();
		fd.setWpisy(wpisy);
		fd.setDaneWystawiajacego(m_mainFr.getDW());
		return fd;
	}
	
	public void setData(final FakturaData fd)
	{
		NazwaFirmy.setText(fd.getNazwaFirmy());
		AdresFirmyMiasto.setText(fd.getAdresFirmyMiasto());
		AdresFirmyUlica.setText(fd.getAdresFirmyUlica());
		Nip.setText(fd.getNip());
		DataWyst.setText(fd.getDataWyst());
		DataSprze.setText(fd.getDataSprze());
		TerminPlatn.setText(fd.getTerminPlatn());
		NumerFaktury.setText(fd.getNumerFakt());
		SposZapl.setSelectedItem(fd.getSposobZapl());
		Odbierajacy.setText(fd.getOdbierajacy());
		MiejsceWystawienia.setText(fd.getMiejsceWystawienia());
		Uwagi.setText(fd.getUwagi());
		DataModel dm = (DataModel)loa.getModel();
		dm.setData(fd.getWpisy());
	}
	
	public static String getRok()
	{
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String rok;
		rok = "" + calendar.get(calendar.YEAR);
		return rok;
	}
	
    public void setKlientMiasto(String Miasto)
    {
        AdresFirmyMiasto.setText(Miasto);
    }
    
    public void setKlientNIP(String NIP)
    {
        Nip.setText(NIP);
    }
    
    public void setKlientNazwa(String Nazwa)
    {
        NazwaFirmy.setText(Nazwa);
    }
    
    public void setKlientUlica(String Ulica)
    {
        AdresFirmyUlica.setText(Ulica);
    }
    
	private JButton jbdodaj = new JButton("Dodaj");
	private JButton jkasuj = new JButton("Kasuj");
	private ListOfArts loa = new ListOfArts();
	private Faktura m_mainFr;
	private CopyPasteTextField NazwaFirmy = new CopyPasteTextField(35);
	private CopyPasteTextField AdresFirmyMiasto = new CopyPasteTextField(10);
	private CopyPasteTextField AdresFirmyUlica = new CopyPasteTextField(10);
	private CopyPasteTextField Nip = new CopyPasteTextField(8);
	private CopyPasteTextField DataWyst = new CopyPasteTextField(7);
	private CopyPasteTextField DataSprze = new CopyPasteTextField(7);
	private CopyPasteTextField TerminPlatn = new CopyPasteTextField(7);
	private CopyPasteTextField NumerFaktury = new CopyPasteTextField(4);
	private JComboBox SposZapl = new JComboBox();
	private CopyPasteTextField Odbierajacy = new CopyPasteTextField(10);
	private CopyPasteTextField MiejsceWystawienia = new CopyPasteTextField(10);
	private JTextArea Uwagi = new JTextArea(3, 10);
    private JPopupMenu _BazaMenu = new JPopupMenu();
    private BazaKlientowDlg _BazaKlientowDlg;
    private JButton _btBaza;
    private EntryPropPage _epp;
    
};

