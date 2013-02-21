//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

class PrintPreview extends JPanel
{
	class printAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			prn.print();
		}
	}
    
	class saveAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String nazwa = JOptionPane.showInputDialog(null, "Podaj nazw\u015B pliku", "Zapisywanie faktury", JOptionPane.QUESTION_MESSAGE);
				if ( (nazwa == null) || (nazwa.length() < 1))
				{
					return;
				}
				FakturaData fd = m_mt.getFakturaData();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nazwa + ".faktura"));
				out.writeObject(fd);
				out.close();
				JOptionPane.showMessageDialog(null, "Faktura zapisana", "Zapisano faktur\u0119", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Nie moøna zapisa\u0107 pliku faktury", "B\u0142\u0105d zapisu", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public PrintPreview(MainTab mt)
	{
		m_mt = mt;
        jbprint.addActionListener(new printAL());
        jbsave.addActionListener(new saveAL());
	}
    
	
	public void refreshPreview()
	{
		if (m_bv != null)
		{
			remove(m_bv);
		}
		
		Box bvMain = Box.createVerticalBox();
		Box bvButt = Box.createHorizontalBox();
		prn.setFakturaData(m_mt.getFakturaData());
		BufferedImage img = prn.getImg();
		ImageIcon icon = new ImageIcon(img);
		JScrollPane prv = new JScrollPane(new JLabel(icon));
		prv.setPreferredSize(new Dimension(630, 490));
		prv.getVerticalScrollBar().setUnitIncrement(10);
		bvMain.add(Box.createVerticalStrut(10));
		bvMain.add(prv);
		bvMain.add(Box.createVerticalStrut(10));
		bvButt.add(jbprint);
		bvButt.add(Box.createHorizontalStrut(20));
		bvButt.add(jbsave);
		bvMain.add(bvButt);
		add(bvMain);
		m_bv = bvMain;
        
	}
	
	private Box m_bv = null;
	private AbstractPrintCreator prn = new PrintCreator();
	private JButton jbprint = new JButton("Drukuj");
	private JButton jbsave = new JButton("Zapisz");
	private MainTab m_mt;
}
