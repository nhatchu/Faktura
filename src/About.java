//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;
import java.awt.*;
class About extends JPanel
{
	public About() 
	{
		Box bvMain = Box.createVerticalBox();
		Box bv = Box.createVerticalBox();
		bvMain.add(Box.createVerticalStrut(100));
		bv.add(new JLabel("Program Faktura 3.0 jest licencjonowany (freeware)."));
		bv.add(new JLabel("Program dzia³a porpawnie w rozdzielczoœci >= 1024 X 768"));
		bv.add(new JLabel("Autor : Aleksander Su³kowski"));
		bv.add(new JLabel("Czekam na wszelkie uwagi pod adresem : sulkowskia@poczta.onet.pl"));
		bvMain.add(bv);
		add(bvMain);
	}
}
