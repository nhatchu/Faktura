import java.awt.Graphics;

public class PrintProba extends AbstractPrintCreator
{
	protected void drawResult(Graphics g)
	{
		g.drawString(m_fd.getNazwaFirmy(), 10, 100);
	}
}
