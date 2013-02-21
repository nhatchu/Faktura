class Towar implements java.io.Serializable
{
    public String getNazwa()
    {
        return _Nazwa;
    }
    public String getSWW()
    {
        return _SWW;
    }
    public String getVAT()
    {
        return _VAT;
    }
    public String getJednostka()
    {
        return _Jednostka;
    }
    public String getCena()
    {
        return _Cena;
    }
    
    public void setNazwa(String Nazwa)
    {
        _Nazwa = Nazwa;
    }
    public void setSWW(String SWW)
    {
        _SWW = SWW;
    }
    public void setVAT(String VAT)
    {
        _VAT = VAT;
    }
    public void setJednostka(String Jednostka)
    {
        _Jednostka = Jednostka;
    }
    public void setCena(String Cena)
    {
        _Cena = Cena;
    }
    
    private String _Nazwa = new String();
    private String _SWW = new String();
    private String _VAT = new String();
    private String _Cena = new String();
    private String _Jednostka = new String();
}
