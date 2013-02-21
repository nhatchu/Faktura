//Aleksander Sulkowski 2002.01.01.
import javax.swing.*;

class DaneWystTab extends JPanel
{
	CopyPasteTextField nazwa = new CopyPasteTextField(50);
	CopyPasteTextField ulica = new CopyPasteTextField(50);
	CopyPasteTextField miasto = new CopyPasteTextField(50);
	CopyPasteTextField nip = new CopyPasteTextField(50);
	CopyPasteTextField wystawiajacy = new CopyPasteTextField(50);
	CopyPasteTextField konto = new CopyPasteTextField(50);
	
	public DaneWystTab(final DaneWystawiajacego dw)
	{
		Box bx = Box.createVerticalBox();
		bx.add(Box.createVerticalStrut(20));
		
		bx.add(new JLabel("Nazwa firmy"));
		bx.add(Box.createVerticalStrut(10));
		nazwa.setText(dw.getNazwa());
		bx.add(nazwa);
		bx.add(Box.createVerticalStrut(20));

		bx.add(new JLabel("Miasto"));
		bx.add(Box.createVerticalStrut(10));
		miasto.setText(dw.getMiasto());
		bx.add(miasto);
		bx.add(Box.createVerticalStrut(20));

		bx.add(new JLabel("Ulica"));
		bx.add(Box.createVerticalStrut(10));
		ulica.setText(dw.getUlica());
		bx.add(ulica);
		bx.add(Box.createVerticalStrut(20));

		bx.add(new JLabel("NIP"));
		bx.add(Box.createVerticalStrut(10));
		nip.setText(dw.getNip());
		bx.add(nip);
		bx.add(Box.createVerticalStrut(20));

		bx.add(new JLabel("Konto"));
		bx.add(Box.createVerticalStrut(10));
		konto.setText(dw.getKonto());
		bx.add(konto);
		bx.add(Box.createVerticalStrut(20));
		
		bx.add(new JLabel("Wystawiaj\u0105cy"));
		bx.add(Box.createVerticalStrut(10));
		wystawiajacy.setText(dw.getWystawiajacy());
		bx.add(wystawiajacy);
		bx.add(Box.createVerticalStrut(20));
		add(bx);
	}
	public DaneWystawiajacego getDaneW()
	{
		DaneWystawiajacego dw = new DaneWystawiajacego();
		dw.setNazwa(nazwa.getText());
		dw.setNip(nip.getText());
		dw.setUlica(ulica.getText());
		dw.setMiasto(miasto.getText());
		dw.setWystawiajacy(wystawiajacy.getText());
		dw.setKonto(konto.getText());
		return dw;
	}
	public void setData(final DaneWystawiajacego dw)
	{
		nazwa.setText(dw.getNazwa());
		miasto.setText(dw.getMiasto());
		ulica.setText(dw.getUlica());
		nip.setText(dw.getNip());
		konto.setText(dw.getKonto());
		wystawiajacy.setText(dw.getWystawiajacy());
	}
}
