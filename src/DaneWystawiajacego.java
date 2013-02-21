//Aleksander Sulkowski 2002.01.01.
import java.io.Serializable;

class DaneWystawiajacego implements Serializable
{
	private String WNazwa = new String();
	private String WMiasto = new String();
	private String WUlica = new String();
	private String WNip = new String();
	private String WKonto = new String();
	private String WWystawiajacy = new String();
    
	public DaneWystawiajacego()
	{
	}
	
	public void setNazwa(final String nazwa)
	{
		WNazwa = nazwa;
	}
	public void setMiasto(final String nazwa)
	{
		WMiasto = nazwa;
	}
	public void setUlica(final String nazwa)
	{
		WUlica = nazwa;
	}
	public void setNip(final String nazwa)
	{
		WNip = nazwa;
	}
	public void setKonto(final String nazwa)
	{
		WKonto = nazwa;
	}
	public void setWystawiajacy(final String nazwa)
	{
		WWystawiajacy = nazwa;
	}
    
	public String getNazwa()
	{
		return WNazwa;
	}
	public String getMiasto()
	{
		return WMiasto;
	}
	public String getUlica()
	{
		return WUlica;
	}
	public String getNip()
	{
		return WNip;
	}
	public String getKonto()
	{
		return WKonto;
	}
	public String getWystawiajacy()
	{
		return WWystawiajacy;
	}
}
