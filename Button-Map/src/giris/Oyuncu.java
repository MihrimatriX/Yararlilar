package giris;

import java.io.File;

public class Oyuncu
{
    private String oyuncuIsim;
    private int altin = 0;
    public File dosya;

    public File getDosya()
    {
        return dosya;
    }

    public void setDosya(File dosya)
    {
        this.dosya = dosya;
    }

    public String getOyuncuIsim()
    {
        return oyuncuIsim;
    }

    public void setOyuncuIsim(String oyuncuIsim)
    {
        this.oyuncuIsim = oyuncuIsim;
    }

    public int getAltin()
    {
        return altin;
    }

    public void setAltin(int altin)
    {
        this.altin = altin;
    }

    Oyuncu(String isim, int altin)
    {
        this.oyuncuIsim = isim;
        if (altin == 0)
        {
            this.altin = 200;
        }
        this.altin = altin;
        dosya = new File(isim + "Oyuncu.txt");
    }

    Oyuncu(String isim)
    {
        this.oyuncuIsim = isim;
        this.altin = 200;
        dosya = new File(isim + "Oyuncu.txt");
    }

    public void altinAzalt(int düsmeMiktar)
    {
        this.altin = this.altin - düsmeMiktar;
    }

    public void altinArtir(int i)
    {
        this.altin = this.altin + i;
    }
}