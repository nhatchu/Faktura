//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class EntryPropPage extends ResizableDialogBase implements ActionListener, ITowarHolder
{
	private class nextAL implements ActionListener
	{
		private JComponent m_jc;
		public nextAL(JComponent jc)
		{
			m_jc = jc;
		}
		public void actionPerformed(ActionEvent e)
		{
			m_jc.transferFocus();
		}
	}
	private class KL extends KeyAdapter
	{
		public void keyTyped(KeyEvent ke)
		{
			if (ke.getKeyChar() == '\n')
			{
				if (((JButton)ke.getSource()) == OKButton)
				{
					m_ok = true;
				}
				saveDialogPosition();
                setVisible(false);
                dispose();
			}
		}
	}
    
    
	EntryPropPage(Frame f, boolean b)
	{
		super(f, "Dane towaru / us\u0142ugi", b, new Rectangle(100, 200, 750, 150));
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        _dlg = new BazaTowarowDlg(f, true, this);
		Container cp;
		m_fr = f;
		cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		Tnazwa.addActionListener(new nextAL(Tnazwa));
		Tilosc.addActionListener(new nextAL(Tilosc));
		Tcena.addActionListener(new nextAL(Tcena));
		Tsww.addActionListener(new nextAL(Tsww));
		
		cp.add(new JLabel("Nazwa towaru / us\u0142ugi"));
		cp.add(Tnazwa);
		cp.add(new JLabel("Ilo\u015B\u0107"));
		cp.add(Tilosc);
		cp.add(new JLabel("Jednostka"));
		Tjednostka.setEditable(true);
		Tjednostka.addItem("kplt");
		Tjednostka.addItem("mb");
		Tjednostka.addItem("m2");
		Tjednostka.addItem("szt.");
		Tjednostka.setPreferredSize(new Dimension(100, 22));
		cp.add(Tjednostka);
		cp.add(new JLabel("Cena netto"));
		cp.add(Tcena);
		cp.add(new JLabel("PKWiU"));
		cp.add(Tsww);
		cp.add(new JLabel("Stawka VAT"));
		Tvat.addItem("22");
		Tvat.addItem("7");
		Tvat.addItem("zw.");
		Tvat.addItem("0");
		Tvat.setEditable(true);
		Tvat.setPreferredSize(new Dimension(100, 22));
		cp.add(Tvat);
		OKButton.addKeyListener(new KL());
		OKButton.addActionListener(this);
		CancelButton.addKeyListener(new KL());
		CancelButton.addActionListener(this);
		cp.add(OKButton);
		cp.add(CancelButton);
        JMenuItem mi = new JMenuItem("Dodaj do bazy");
        mi.addActionListener(new ActionListener()
                             {
            public void actionPerformed(ActionEvent e)
            {
                Towar t = new Towar();
                t.setNazwa(Tnazwa.getText());
                t.setJednostka((String)Tjednostka.getSelectedItem());
                t.setSWW(Tsww.getText());
                t.setVAT((String)Tvat.getSelectedItem());
                t.setCena(Tcena.getText());
                DBManager.getInstance().getTowary().add(t);
                _dlg.towarAdded();
            }
        });
        _Menu.add(mi);
        mi = new JMenuItem("Baza towar\u00F3w...");
        mi.addActionListener(new ActionListener()
                             {
            public void actionPerformed(ActionEvent e)
            {
                _dlg.setVisible(true);
            }
        });
        _Menu.add(mi);
        
        _MenuBt.addActionListener(new ActionListener()
                                  {
            public void actionPerformed(ActionEvent e)
            {
                _Menu.show(_MenuBt, 0, 0);
            }
        });
        cp.add(_MenuBt);
		transferFocus();
	}
    
	public void sw()
	{
		setVisible(true);
	}
	
	public void initData(DataEntry dat)
	{
		Tnazwa.setText(dat.getNazwa());
		Tilosc.setText(dat.getIlosc());
		Tsww.setText(dat.getSww());
		Tcena.setText(dat.getCenaNetto());
		Tjednostka.setSelectedItem(dat.getJednostka());
		Tvat.setSelectedItem(dat.getVatStawka());
	}
	
	public DataEntry getData()
	{
		DataEntry ret = new DataEntry();
		ret.setNazwa(Tnazwa.getText());
		ret.setIlosc(Tilosc.getText());
		ret.setJednostka((String)Tjednostka.getSelectedItem());
		ret.setCenaNetto(Tcena.getText());
		ret.setSww(Tsww.getText());
		ret.setVatStawka((String)Tvat.getSelectedItem());
		ret.recalculate();
		return ret;
	}
    
    
	public void actionPerformed(ActionEvent e)
	{
		if (((JButton)e.getSource()) == OKButton)
		{
			m_ok = true;
		}
        saveDialogPosition();
		setVisible(false);
        dispose();
	}
	public boolean isOk()
	{
		return m_ok;
	}
	
    public void setCena(String Cena)
    {
        Tcena.setText(Cena);
    }
    
    public void setJednostka(String Jednostka)
    {
        Tjednostka.setSelectedItem(Jednostka);
    }
    
    public void setNazwa(String Nazwa)
    {
        Tnazwa.setText(Nazwa);
    }
    
    public void setSWW(String SWW)
    {
        Tsww.setText(SWW);
    }
    
    public void setVAT(String VAT)
    {
        Tvat.setSelectedItem(VAT);
    }
    
	private CopyPasteTextField Tnazwa = new CopyPasteTextField(67);
	private CopyPasteTextField Tilosc = new CopyPasteTextField(5);
	private JComboBox Tjednostka = new JComboBox();
	private CopyPasteTextField Tcena = new CopyPasteTextField(7);
	private CopyPasteTextField Tsww = new CopyPasteTextField(8);
	private JComboBox Tvat = new JComboBox();
	private JButton OKButton = new JButton("OK");
	private JButton CancelButton = new JButton("Anuluj");
    private JButton _MenuBt = new JButton("Baza...");
	private boolean m_ok = false;
	private Frame m_fr = null;
    private JPopupMenu _Menu = new JPopupMenu();
    private BazaTowarowDlg _dlg;
}
