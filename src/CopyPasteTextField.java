import javax.swing.*;
import java.awt.event.*;
import java.awt.datatransfer.*;


class CopyPasteTextField extends JTextField
{
	public CopyPasteTextField(int columns)
	{
		super(columns);
		init();
	}
	
	public CopyPasteTextField()
	{
		init();
	}
	
	protected void init()
	{
		m_copy.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					copy();
				}
			}
		);
		
		m_paste.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					paste();
				}
			}
		);
		
		m_menu.add(m_copy);
		m_menu.add(m_paste);
		addMouseListener(new MenuML());
	}
	
	class MenuML extends MouseAdapter
	{
    	public void mousePressed(MouseEvent e) 
    	{
      		showMenu(e);
    	}
    	public void mouseReleased(MouseEvent e) 
    	{
      		showMenu(e);
    	}
    	private void showMenu(MouseEvent e) 
    	{
      		if (e.isPopupTrigger())
      		{
      			boolean enable = false;
      			
      			if (getSelectedText() == null)
      			{
      				enable = false;
      			}
      			else
      			{
      				enable = true;
      			}
      			m_copy.setEnabled(enable);
      			
      			Transferable clipData = getToolkit().getSystemClipboard().getContents(CopyPasteTextField.this);
      			try 
      			{
        			String clipString = (String)clipData.getTransferData(DataFlavor.stringFlavor);
        			if (clipString.length() != 0)
        			{
        				enable = true;
        			}
        			else
        			{
        				enable = false;
        			}
      			} 
      			catch(Exception ex) 
      			{
        			enable = false;
      			}
      			m_paste.setEnabled(enable);
      			
        		m_menu.show(e.getComponent(), e.getX(), e.getY());
        	}
    	}
	}
	
	private JPopupMenu m_menu = new JPopupMenu();
	JMenuItem m_copy = new JMenuItem("Kopiuj");
	JMenuItem m_paste = new JMenuItem("Wklej");
}
