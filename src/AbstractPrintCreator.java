import java.awt.print.*;
import java.awt.image.*;
import java.awt.*;
import javax.swing.JOptionPane;

public abstract class AbstractPrintCreator implements Printable
{
	protected abstract void drawResult(Graphics g);
	
	public final void print()
	{
		PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog()) 
		{
            try
			{
                printJob.print();
            } 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "B\u0142\u0105d drukowania", ex.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	
	public final int print(Graphics g, PageFormat pf, int pi)
                          throws PrinterException 
	{
		if (pi >= 1) 
		{
			return Printable.NO_SUCH_PAGE;
		}
		drawResult(g);
		return Printable.PAGE_EXISTS;
	}
	
	public final void setFakturaData(FakturaData fd)
	{
		m_fd = fd;
	}
	
	public final BufferedImage getImg()
	{
		BufferedImage img = new BufferedImage(xPageSize, yPageSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		drawResult(g2);
		return img;
	}
	
	protected FakturaData m_fd;
	
	public static final int xPageSize = 600;
	public static final int yPageSize = 850;
	public static final int maxXVisible = 520;
	public static final int maxYVisible = 770;
}
