//Aleksander Sulkowski 2002.01.01.
import java.util.ArrayList;
import java.io.Serializable;

class FakturaData implements Serializable
{
	public void setNazwaFirmy(String nazwa)
	{
		NazwaFirmy = nazwa;
	}
	
	public final String getNazwaFirmy()
	{
		return NazwaFirmy;
	}
	
	public void setAdresFirmyMiasto(String nazwa)
	{
		AdresFirmyMiasto = nazwa;
	}
	
	public final String getAdresFirmyMiasto()
	{
		return AdresFirmyMiasto;
	}
	
	public void setAdresFirmyUlica(String nazwa)
	{
		AdresFirmyUlica = nazwa;
	}
	
	public final String getAdresFirmyUlica()
	{
		return AdresFirmyUlica;
	}
    
	public void setNip(String nazwa)
	{
		Nip = nazwa;
	}
	
	public final String getNip()
	{
		return Nip;
	}
    
	public void setDataWyst(String nazwa)
	{
		DataWyst = nazwa;
	}
	
	public final String getDataWyst()
	{
		return DataWyst;
	}
	
	public void setDataSprze(String nazwa)
	{
		DataSprze = nazwa;
	}
	public final String getDataSprze()
	{
		return DataSprze;
	}
    
	public void setTerminPlatn(String nazwa)
	{
		TerminPlatn = nazwa;
	}
	
	public final String getTerminPlatn()
	{
		return TerminPlatn;
	}
    
	public void setNumerFakt(final String nazwa)
	{
		NumerFakt = nazwa;
	}
	
	public final String getNumerFakt()
	{
		return NumerFakt;
	}
    
	public void setSposobZapl(final String nazwa)
	{
		SposobZapl = nazwa;
	}
	
	public final String getSposobZapl()
	{
		return SposobZapl;
	}
    
	public void setOdbierajacy(final String nazwa)
	{
		Odbierajacy = nazwa;
	}
	
	public final String getOdbierajacy()
	{
		return Odbierajacy;
	}
    
	public void setMiejsceWystawienia(final String nazwa)
	{
		MiejsceWystawienia = nazwa;
	}
	
	public final String getMiejsceWystawienia()
	{
		return MiejsceWystawienia;
	}
    
	public void setUwagi(String nazwa)
	{
		Uwagi = nazwa;
	}
	
	public final String getUwagi()
	{
		return Uwagi;
	}
    
	public void setWpisy(ArrayList arl)
	{
		wpisy = arl;
	}
	
	public final ArrayList getWpisy()
	{
		return wpisy;
	}
    
	public void setDaneWystawiajacego(DaneWystawiajacego dw)
	{
		DaneW = dw;
	}
	
	public final DaneWystawiajacego getDaneWystawiajacego()
	{
		return DaneW;
	}
	
	private String NazwaFirmy = new String();
	private String AdresFirmyMiasto = new String();
	private String AdresFirmyUlica = new String();
	private String Nip = new String();
	private String DataWyst = new String();
	private String DataSprze = new String();
	private String TerminPlatn = new String();
	private String NumerFakt = new String();
	private String SposobZapl = new String();
	private String Odbierajacy = new String();
	private String MiejsceWystawienia = new String();
	private String Uwagi = new String();
	private ArrayList wpisy = new ArrayList();
	private DaneWystawiajacego DaneW = new DaneWystawiajacego();
}
