//Aleksander Sulkowski 2002.01.01.
import java.io.Serializable;
class DataEntry implements Serializable
{
	public void setNazwa(String s)
	{
		Nazwa = s;
	}
	public void setIlosc(String s)
	{
		try
		{
			Ilosc = Ilosc.valueOf(s);
		}
		catch(NumberFormatException e)
		{
			Ilosc = new Double(0);
		}
	}
	public void setJednostka(String s)
	{
		Jednostka = s;
	}
	public void setCenaNetto(String s)
	{
		try
		{
			CenaNetto = CenaNetto.valueOf(s);
		}
		catch(NumberFormatException e)
		{
			CenaNetto = new Double(0);
		}
	}
	public void setSww(String s)
	{
		Sww = s;
	}
	public void setVatStawka(String s)
	{
		VatStawka = s;
	}
	
	public String getNazwa()
	{
		return Nazwa;
	}
	public String getIlosc()
	{
		return "" + Ilosc;
	}
	public String getJednostka()
	{
		return Jednostka;
	}
	public String getCenaNetto()
	{
		return "" + CenaNetto;
	}
	public String getSww()
	{
		return Sww;
	}
	public String getVatStawka()
	{
		return VatStawka;
	}
	public String getVatKwota()
	{
		return VatKwota;
	}
	public String getWartoscBrutto()
	{
		return WartoscBrutto;
	}
	public String getWartoscNetto()
	{
		return WartoscNetto;
	}
    
	public void recalculate()
	{
		try
		{
			double wn = CenaNetto.doubleValue() * Ilosc.doubleValue();
			WartoscNetto = "" + wn;
			VatKwota = "" + (wn * Double.valueOf(VatStawka).doubleValue()) / 100;
			WartoscBrutto = "" + (wn + (wn * Double.valueOf(VatStawka).doubleValue()) / 100);
		}
		catch(NumberFormatException e)
		{
			WartoscNetto = "" + (CenaNetto.doubleValue() * Ilosc.doubleValue());
			WartoscBrutto =  WartoscNetto;
		}
	}
	public static String rd(final String d)
	{
		String ret = new String();
		if (d .length() == 0)
		{
			return ret;
		}
        
		int i = d.indexOf(".");
		if ( (i != -1) && (i < d.length() - 1))
		{
			String newStr = new String();
			int j = 0;
			while (j <= i)
			{
				ret += d.charAt(j);
				j++;
			}
			
			j = i + 1;
			
			while (j < d.length())
			{
				newStr += d.charAt(j);
				j++;
			}
			
			if (newStr.length() > 2)
			{
				char c = newStr.charAt(2);
				Character ch = new Character(c);
				int v = ch.digit(c, 10);
				if (v >= 5)
				{
					Double dv = new Double(0);
					Double translator = new Double(0);
					try
					{
						dv = translator.valueOf(d);
						double wyn = dv.doubleValue() + 0.01;
						ret = "" + wyn;
						return ret.substring(0, i + 3);
					}
					catch(NumberFormatException e)
					{
						return d;
					}
				}
				else
				{
					ret += newStr.charAt(0);
					ret += newStr.charAt(1);
				}
			}
			else
			{
				if (newStr.length() < 2)
				{
					return (d + "0");
				}
				return (d);
			}
			return ret;
		}
		else
		{
			if (i == -1)
			{
				return (d + ".00");
			}
			return d;
		}
	}
	
	private String Nazwa = new String();
	private Double Ilosc = new Double(0);
	private String Jednostka = new String();
	private Double CenaNetto = new Double(0);
	private String Sww = new String();
	private String VatStawka = new String();
	private String WartoscBrutto = new String();
	private String WartoscNetto = new String();
	private String VatKwota = new String();
}
