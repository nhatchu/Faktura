//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

class FakturyWystawione extends JPanel
{
	private class FnFilter implements FilenameFilter
	{
		public boolean accept(File dir, String name)
		{
			if (name.indexOf(".faktura") != -1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	private class kasujAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			String sel = (String)FaktFilesList.getSelectedValue();
			if (sel == null)
			{
				return;
			}
			String[] options = { "OK", "Anuluj" };
			int res = JOptionPane.showOptionDialog(null, "Skasowa\u0107 " + sel, "Kasowanie faktury",
                                                   JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (res != 0)
			{
				return;
			}
            
			File f = new File(sel);
			if (f.delete())
			{
				String[] fl;
				File fil = new File(".");
				fl = fil.list(new FnFilter());
				FaktFilesList.setListData(fl);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nie moøna skasowa\u0107 pliku faktury", "B\u0142\u0105d zapisu", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
    
	private class loadAL implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String sel = (String)FaktFilesList.getSelectedValue();
				if (sel == null)
				{
					return;
				}
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(sel));
				FakturaData fd = (FakturaData)in.readObject();
				m_f.setData(fd);
				in.close();
				JOptionPane.showMessageDialog(null, "Faktura za\u0142adowana", "Wczytano faktur\u0119", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "B\u0142\u0105d odczytu pliku faktury", "B\u0142\u0105d odczytu", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class selL implements ListSelectionListener
	{
		private JButton m_jb;
		public selL(JButton jb)
		{
			m_jb = jb;
		}
		public void valueChanged(ListSelectionEvent e)
		{
			if (FaktFilesList.getSelectedIndex() != -1)
			{
				m_jb.setEnabled(true);
			}
			else
			{
				m_jb.setEnabled(false);
			}
		}
	}
    
	public FakturyWystawione(Faktura fakt)
	{
		m_f = fakt;
		String[] fl;
		File f = new File(".");
		fl = f.list(new FnFilter());
		if (fl != null)
		{
			FaktFilesList = new JList(fl);
		}
		else
		{
			FaktFilesList = new JList();
		}
		
		DefaultListSelectionModel selm = new DefaultListSelectionModel();
		selm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selL selLKasuj = new selL(jbkasuj);
		selL selLOpen = new selL(jbopen);
		selm.addListSelectionListener(selLKasuj);
		selm.addListSelectionListener(selLOpen);
		FaktFilesList.setSelectionModel(selm);
		ScrollFiles = new JScrollPane(FaktFilesList);
		ScrollFiles.setPreferredSize(new Dimension(250, 450));
		Box mbox = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		b1.add(ScrollFiles);
		mbox.add(Box.createVerticalStrut(10));
		mbox.add(b1);
		jbopen.addActionListener(new loadAL());
		jbopen.setEnabled(false);
		jbkasuj.setEnabled(false);
		b2.add(jbopen);
		b2.add(Box.createHorizontalStrut(10));
		jbkasuj.addActionListener(new kasujAL());
		b2.add(jbkasuj);
		mbox.add(Box.createVerticalStrut(15));
		mbox.add(b2);
		add(mbox);
	}
	
	public void refresh()
	{
		String[] fl;
		File fil = new File(".");
		fl = fil.list(new FnFilter());
		FaktFilesList.setListData(fl);
	}
	
	private Faktura m_f;
	private JList FaktFilesList;
	private JButton jbopen = new JButton("Wczytaj");
	private JButton jbkasuj = new JButton("Kasuj faktur\u0119");
	private JScrollPane ScrollFiles;
}
