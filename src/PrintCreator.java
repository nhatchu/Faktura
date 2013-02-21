//Aleksander Sulkowski 2002.01.01.
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import java.awt.image.*;
import java.util.*;

class PrintCreator extends AbstractPrintCreator 
{
	private class VatEntry
	{
		public String WNetto = new String();
		public String WBrutto = new String();
		public String Vat = new String();
	}

	PrintCreator()
	{
		TableX = new Integer[DataModel.ColumnsTitles.length + 1];
	}
		
	protected void drawResult(Graphics g)
	{
		String Razem;
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2.scale(2.0, 2.0);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, xPageSize - 1, yPageSize - 1);
		g.setColor(Color.black);
		drawHeader(g);
		drawTableHeader(g);
		ArrayList al = m_fd.getWpisy();
		for (int i = 0; i < al.size(); i++)
		{
			drawTableEntry(g, i, (DataEntry)al.get(i));
		}
		Razem = countRazem(al);
		drawRazem(g, Razem);
		drawPodatek(g, al);
		drawUwagi(g, m_fd.getUwagi());
		drawSlownie(g, Razem);
		drawPlatnosc(g, m_fd.getSposobZapl(), m_fd.getTerminPlatn());
	}
	
	private void drawHeader(Graphics g)
	{
		DaneWystawiajacego dw = m_fd.getDaneWystawiajacego();
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString("Faktura VAT", xPageSize / 2 - 60, 90);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		g.drawString("ORYGINA\u0141 / KOPIA", xPageSize / 2 - 70, 110);
		g.setFont(newRomanNormal);
		g.drawString("Nr faktury :", xPageSize / 2 + 35, 140);
		g.drawString("Data wystawienia :", xPageSize / 2 + 35, 150);
		g.drawString("Data sprzeda\u017Cy :", xPageSize / 2 + 35, 160);
		g.drawString("Miejsce wystawienia :", xPageSize / 2 + 35, 170);
		
		g.drawString(m_fd.getNumerFakt(), xPageSize / 2 + 125, 140);
		g.drawString(m_fd.getDataWyst(), xPageSize / 2 + 125, 150);
		g.drawString(m_fd.getDataSprze(), xPageSize / 2 + 125, 160);
		g.drawString(m_fd.getMiejsceWystawienia(), xPageSize / 2 + 125, 170);
		
		g.setFont(ArialBig);
		g.drawString("Sprzedawca :", 80, 170);
		g.drawString("Nabywca :", 80, 230);

		g.setFont(newRomanNormal);
		if (dw != null)
		{
			g.drawString(dw.getNazwa(), 90, 185);
			g.drawString(dw.getMiasto(), 90, 195);
			g.drawString(dw.getUlica(), 90, 205);
			g.drawString("NIP : " + dw.getNip(), 90, 215);
		}

		g.drawString(m_fd.getNazwaFirmy(), 90, 245);
		g.drawString(m_fd.getAdresFirmyMiasto(), 90, 255);
		g.drawString(m_fd.getAdresFirmyUlica(), 90, 265);
		g.drawString("NIP : " + m_fd.getNip(), 90, 275);


		g.setFont(newRomanSmall);
		g.drawString("Imi\u0119 i nazwisko osoby", 117, maxYVisible - 80);
		g.drawString("upowa\u017Cnionej do wystawienia faktury VAT", 80, maxYVisible - 70);
		g.drawString("Imi\u0119 i nazwisko osoby", maxXVisible - 115, maxYVisible - 80);
		g.drawString("upowa\u017Cnionej do odbioru faktury VAT", maxXVisible - 145, maxYVisible - 70);
		g.setFont(newRomanNormal);
		if (dw != null)
		{
			g.drawString(dw.getWystawiajacy(), 117, maxYVisible - 10);
		}
		g.drawString(m_fd.getOdbierajacy(), maxXVisible - 115, maxYVisible - 10);

	}
	
	private void drawTableHeader(Graphics g)
	{
		int dx;
		g.drawLine(80, 300, maxXVisible - 1, 300);
		TableX[0] = new Integer(80);
		g.drawLine(80, 300, 80, 315);
		TableX[TableX.length - 1] = new Integer(maxXVisible - 1);
		g.drawLine(maxXVisible - 1, 300, maxXVisible - 1, 315);
		g.setFont(newRomanNormal);
		
		g.drawString(DataModel.ColumnsTitles[0], 82, 312);
		g.drawLine(95, 300, 95, 315); 
		TableX[1] = new Integer(95);
		g.drawString(DataModel.ColumnsTitles[1], 97, 312);
		g.drawLine(215, 300, 215, 315);//240
		TableX[2] = new Integer(215);//240
		
		dx = 215;//240
		dx += 2;
		g.drawString(DataModel.ColumnsTitles[2], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[2]);
		dx += 2;
		g.drawLine(dx, 300, dx, 315);
		TableX[3] = new Integer(dx);
	
		dx += 2;
		g.drawString(DataModel.ColumnsTitles[3], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[3]);
		dx += 2;
		g.drawLine(dx, 300, dx, 315);
		TableX[4] = new Integer(dx);

		dx += 2;
		g.drawString(DataModel.ColumnsTitles[4], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[4]);
		dx += 2;
		g.drawLine(dx, 300, dx, 315);
		TableX[5] = new Integer(dx);

		dx += 2;
		g.drawString(DataModel.ColumnsTitles[5], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[5]);
		dx += 2;
		g.drawLine(dx, 300, dx, 315);
		TableX[6] = new Integer(dx);

		dx += 2;
		g.drawString(DataModel.ColumnsTitles[6], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[6]);
		dx += 17;
		g.drawLine(dx, 300, dx, 315);
		TableX[7] = new Integer(dx);

		dx += 2;
		g.drawString(DataModel.ColumnsTitles[7], dx, 312);
		dx += g.getFontMetrics().stringWidth(DataModel.ColumnsTitles[7]);
		dx += 2;
		g.drawLine(dx, 300, dx, 315);
		TableX[8] = new Integer(dx);
		
		g.drawString(DataModel.ColumnsTitles[8], 430, 312);
		g.drawLine(467, 300, 467, 315);
		TableX[9] = new Integer(467);
		g.drawString(DataModel.ColumnsTitles[9], 469, 312);
		g.drawLine(80, 315, maxXVisible - 1, 315);
		TableY = 315;
	}
	private void drawTableEntry(Graphics g, int i, final DataEntry dat)
	{
		for (int j = 0; j < TableX.length; j++)
		{
			g.drawLine(TableX[j].intValue(), TableY, TableX[j].intValue(), TableY + 15);
		}
		TableY += 15;
		
		g.setFont(newRomanNormal);
		g.drawString("" + (i + 1), TableX[0].intValue() + 2, TableY - 3);
		drawNazwa(g, dat.getNazwa(), TableX[1].intValue(), TableX[2].intValue());
		drawTxt(g, dat.getIlosc(), TableX[2].intValue(), TableX[3].intValue());
		drawTxt(g, dat.getJednostka(), TableX[3].intValue(), TableX[4].intValue());
		drawTxt(g, dat.getCenaNetto(), TableX[4].intValue(), TableX[5].intValue());
		drawTxt(g, DataEntry.rd(dat.getWartoscNetto()), TableX[5].intValue(), TableX[6].intValue());
		drawTxt(g, dat.getSww(), TableX[6].intValue(), TableX[7].intValue());
		try
		{
			Double D = new Double(dat.getVatStawka());
			drawTxt(g, dat.getVatStawka()+"%", TableX[7].intValue(), TableX[8].intValue());
		}
		catch(NumberFormatException e)
		{
			drawTxt(g, dat.getVatStawka(), TableX[7].intValue(), TableX[8].intValue());
		}
		drawTxt(g, DataEntry.rd(dat.getVatKwota()), TableX[8].intValue(), TableX[9].intValue());
		drawTxt(g, DataEntry.rd(dat.getWartoscBrutto()), TableX[9].intValue(), TableX[10].intValue());
		g.drawLine(80, TableY, maxXVisible - 1, TableY);
	}
	
	private void drawTxt(Graphics g, final String txt, int from, int to)
	{
		g.setFont(newRomanNormal);
		if (g.getFontMetrics().stringWidth(txt) + 3 > (to - from) )
		{
			g.setFont(newRomanSmall);
			if ( g.getFontMetrics().stringWidth(txt) + 2 > (to - from) )
			{
				g.setFont(newRomanTiny);
				g.drawString(txt, from + 1, TableY - 3);
			}
			else
			{
				g.drawString(txt, from + 2, TableY - 3);
			}
		}
		else
		{
			g.drawString(txt, from + 2, TableY - 3);
		}
	}

	private boolean drawNazwa(Graphics g, final String txts, int from, int to)
	{
		String txt = new String(txts);
		txt += " ";
		g.setFont(newRomanNormal);
		if (g.getFontMetrics().stringWidth(txt) + 3 > (to - from))
		{
			g.setFont(newRomanSmall);
			if (g.getFontMetrics().stringWidth(txt) + 2 > (to - from))
			{
				String tmpstr = new String();
				String newstr = new String();
				int i = 0;
				while (i < txt.length())
				{
					tmpstr += txt.charAt(i);
					if (txt.charAt(i) == ' ')
					{
						if (g.getFontMetrics().stringWidth(newstr + tmpstr) + 3 < (to - from))
						{
							newstr += tmpstr;
							tmpstr = "";
							if (i == txt.length() - 1)
							{
								g.drawString(newstr, from + 2, TableY - 3);
								for (int j = 0; j < TableX.length; j++)
								{
									g.drawLine(TableX[j].intValue(), TableY - 10, TableX[j].intValue(), TableY);
								}
								TableY += 10;
								newstr = "";
							}
						}
						else
						{
							if (newstr.length() != 0)
							{
								g.drawString(newstr, from + 2, TableY - 3);
								newstr = new String(tmpstr);
								tmpstr = "";
								for (int j = 0; j < TableX.length; j++)
								{
									g.drawLine(TableX[j].intValue(), TableY - 10, TableX[j].intValue(), TableY);
								}
								TableY += 10;
							}
							else
							{
								return false;
							}
						}
					}
					i++;
				}
				if ((tmpstr.length() != 0) || ( (tmpstr.length() == 0) && (newstr.length() != 0)) )
				{
					g.drawString(newstr + tmpstr, from + 2, TableY - 3);
					for (int j = 0; j < TableX.length; j++)
					{
						g.drawLine(TableX[j].intValue(), TableY - 10, TableX[j].intValue(), TableY);
					}
					TableY += 10;
				}
				TableY -= 10;
			}
			else //small
			{
				g.drawString(txt, from + 2, TableY - 3);
			}
		}
		else
		{
			g.drawString(txt, from + 2, TableY - 3);
		}
		return true;
	}
	
	private String countRazem(final ArrayList al)
	{
		String ret = new String();
		//double sum = 0;
		java.math.BigDecimal sum = new java.math.BigDecimal(0).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
		double translator;
		try
		{
			for (int i = 0; i < al.size(); i++)
			{
				DataEntry d = (DataEntry)al.get(i);
				translator = Double.parseDouble(d.getWartoscBrutto());
				sum = sum.add(new java.math.BigDecimal(translator).setScale(2, java.math.BigDecimal.ROUND_HALF_UP));
			}
			/*
			ret = "" + sum;
			ret = DataEntry.rd(ret);
			if (ret.charAt(ret.length() - 1) == '.')
			{
				ret += "00";
				return ret;
			}
			if (ret.charAt(ret.length() - 2) == '.')
			{
				ret += "0";
				return ret;
			}
			*/
			return sum.toString();
		}
		catch(NumberFormatException e) {
		}
		return ret;
	}
	
	private void drawRazem(Graphics g, final String razem)
	{
		String st = new String("Razem : ");
		st += razem;
		st += " ";
		st += "z\u0142";
		g.setFont(ArialBig);
		g.drawString(st, TableX[7].intValue(), TableY + 20);
		TableY += 20;
	}
	
	private void drawPodatek(Graphics g, final ArrayList al)
	{
		g.setFont(newRomanNormal);
		HashMap VatMap = new HashMap(7);
		double sumNetto = 0;
		double sumBrutto = 0;
		double sumVat = 0;

		for (int i = 0; i < al.size() ; i++)
		{
			DataEntry dat = (DataEntry)al.get(i);
			String Stawka = dat.getVatStawka();
			String VatKwota = DataEntry.rd(dat.getVatKwota());
			String WNetto = dat.getWartoscNetto();
			String WBrutto = dat.getWartoscBrutto();
			if (VatMap.containsKey(Stawka))
			{
				VatEntry ve = (VatEntry)VatMap.get(Stawka);
				try
				{
					Double dWNetto = new Double(ve.WNetto);
					Double dWBrutto = new Double(ve.WBrutto);
					Double dVatKwota = new Double(0);
                                        try
                                        {
                                            dVatKwota = new Double(ve.Vat);
                                        }
                                        catch (NumberFormatException e)
                                        {
                                        }
					
					Double vk = new Double(0);
                                        try
                                        {
                                            vk = new Double(VatKwota);
                                        }
                                        catch (NumberFormatException e)
                                        {
                                        }
                                        
					Double wn = new Double(WNetto);
					Double wb = new Double(WBrutto);
					
					sumNetto += wn.doubleValue();
					sumBrutto += wb.doubleValue();
					sumVat += vk.doubleValue();
					
					VatEntry newEntry = new VatEntry();

					newEntry.WNetto = "" + (dWNetto.doubleValue() + wn.doubleValue());
					newEntry.WBrutto = "" + (dWBrutto.doubleValue() + wb.doubleValue());
					newEntry.Vat = "" + (dVatKwota.doubleValue() + vk.doubleValue());
					
					VatMap.put(Stawka, newEntry);
				}
				catch(NumberFormatException e)
				{
				}
			}
			else
			{
				VatEntry ve = new VatEntry();
				ve.WNetto = WNetto;
				ve.WBrutto = WBrutto;
				ve.Vat = VatKwota;
				try
				{
					Double vk = new Double(0);
                                        try
                                        {
                                            vk = new Double(VatKwota);
                                        }
                                        catch (NumberFormatException e)
                                        {
                                        }
					Double wn = new Double(WNetto);
					Double wb = new Double(WBrutto);
					
					sumNetto += wn.doubleValue();
					sumBrutto += wb.doubleValue();
					sumVat += vk.doubleValue();
				}
				catch(NumberFormatException e)
				{
				}
				VatMap.put(Stawka, ve);
			}
		}
		
		TableY += 20;
		g.setFont(newRomanSmall);
		g.drawString("Netto", 350, TableY);
		g.drawString("Brutto", 400, TableY);
		g.drawString("Vat", 450, TableY);
		TableY += 15;
		int k = 0;
		while (k < al.size())
		{
			DataEntry dat = (DataEntry)al.get(k);
			VatEntry v = new VatEntry();
			if (VatMap.containsKey(dat.getVatStawka()))
			{			
				v = (VatEntry)VatMap.get(dat.getVatStawka());
				g.drawString(DataEntry.rd(dat.getVatStawka()), 300, TableY);
				g.drawString(DataEntry.rd(v.WNetto), 350, TableY);
				g.drawString(DataEntry.rd(v.WBrutto), 400, TableY);
				g.drawString(DataEntry.rd(v.Vat), 450, TableY);
				TableY += 10;
				VatMap.remove(dat.getVatStawka());
			}
			k++;
		}
		g.drawString("Razem : ", 300, TableY);
		g.drawString(DataEntry.rd("" + sumNetto), 350, TableY);
		g.drawString(DataEntry.rd("" + sumBrutto), 400, TableY);
		g.drawString(DataEntry.rd("" + sumVat), 450, TableY);

	}
	
	private void drawSlownie(Graphics g, final String razem)
	{
		int pos = razem.indexOf('.');
		String zlotych = new String();
		String groszy = new String();
		if (pos == -1)
		{
			zlotych = razem;
		}
		else
		{
			zlotych = razem.substring(0, pos);
			if (pos != razem.length() - 1)
			{
				groszy = razem.substring(pos + 1);
			}
		}
		try
		{
			String pr = new String();
			Integer izlotych = new Integer(zlotych);
			Integer igroszy = new Integer(groszy);
			Slownie SlownGroszy = new Slownie(igroszy.intValue());
			Slownie SlownZlotych = new Slownie(izlotych.intValue());
			pr = "S\u0142ownie : ";
			pr += SlownZlotych.getString();
			pr += "z\u0142. ";
			if (igroszy.intValue() != 0)
			{
				pr += SlownGroszy.getString();
				pr += "gr.";
			}
			g.setFont(newRomanSmall);
			g.drawString(pr, 250, TableY + 20);
			pr = "Do zap\u0142aty : ";
			pr += DataEntry.rd(razem);
			pr += " z\u0142.";
			g.setFont(ArialBig);
			g.drawString(pr, 80, TableY + 20);
		}
		catch(NumberFormatException e)
		{
		}
	}
	
	private void drawUwagi(Graphics g, final String uwagi)
	{
		TableY += 15;
		g.setFont(newRomanSmall);
		int pos = 0;
		String ln = new String();
		while (pos < uwagi.length())
		{
			if (uwagi.charAt(pos) == '\n')
			{
				g.drawString(ln, 80, TableY);
				ln = "";
				TableY += 10;
			}
			else
			{
				ln += uwagi.charAt(pos);
			}
			pos++;
		}
		g.drawString(ln, 80, TableY);
	}
	
	private void drawPlatnosc(Graphics g, final String platn, final String termin)
	{
		DaneWystawiajacego dw = m_fd.getDaneWystawiajacego();
		TableY += 50;
		g.setFont(newRomanNormal);
		String s = new String("P\u0142atno\u015B\u0107 : ");
		s += platn;
		if (platn.substring(0,7).compareToIgnoreCase("przelew") == 0)
		{
			s += " na konto ";
			s += dw.getKonto();
		}
		g.drawString(s, 80, TableY);
		s = "Termin p\u0142atno\u015Bci : ";
		s += termin;
		g.drawString(s, 80, TableY + 15);
	}
	
	private Font newRomanNormal = new Font("Times New Roman", Font.PLAIN, 10);
	private Font newRomanSmall = new Font("Times New Roman", Font.PLAIN, 8);
	private Font newRomanTiny = new Font("Times New Roman", Font.PLAIN, 6);
	private Font ArialBig = new Font("Arial", Font.BOLD, 12);
	private int TableY;
	private Integer[] TableX;
}
