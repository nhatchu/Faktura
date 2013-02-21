class Klient implements java.io.Serializable
{
    public String getNazwa()
    {
        return _Nazwa;
    }
    public String getNIP()
    {
        return _NIP;
    }
    public String getMiasto()
    {
        return _Miasto;
    }
    public String getUlica()
    {
        return _Ulica;
    }
    
    public void setNazwa(String Nazwa)
    {
        _Nazwa = Nazwa;
    }
    public void setNIP(String NIP)
    {
        _NIP = NIP;
    }
    public void setMiasto(String Miasto)
    {
        _Miasto = Miasto;
    }
    public void setUlica(String Ulica)
    {
        _Ulica = Ulica;
    }

    private String _NIP = new String();
    private String _Nazwa = new String();
    private String _Miasto = new String();
    private String _Ulica = new String();
}
