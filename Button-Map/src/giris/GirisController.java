package giris;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class GirisController
{
    @FXML
    TextField boyKutu;
    @FXML
    TextField enKutu;
    @FXML
    TextField oy1;
    @FXML
    TextField oy2;
    @FXML
    TextField oy3;
    @FXML
    TextField oy4;
    @FXML
    TextField a1;
    @FXML
    TextField a2;
    @FXML
    TextField a3;
    @FXML
    TextField a4;
    @FXML
    TextArea bolgeYazi;
    @FXML
    TextField oranimiz;

    public static int boyutTahtaEn;
    public static int boyutTahtaBoy;
    public static int oran;

    public void oyunaGir()
    {
        String en  = enKutu .getText();
        String boy = boyKutu.getText();
        String oranyazi = oranimiz.getText();

        int eni     = Integer.parseInt(en);
        int boyu    = Integer.parseInt(boy);
        int orani   = Integer.parseInt(oranyazi);

        boyutTahtaEn  = eni;
        boyutTahtaBoy = boyu;
        oran          = orani;

        String oyuncu1 = oy1.getText();
        String oyuncu2 = oy2.getText();
        String oyuncu3 = oy3.getText();
        String oyuncu4 = oy4.getText();

        bolgeYazi.setEditable(false);

        if (eni > 30 || boyu > 30)
        {
            boyKutu.setText("");
            enKutu.setText("");

            Alert yok = new Alert(Alert.AlertType.CONFIRMATION);
            yok.setTitle("DIKKAT");
            yok.setHeaderText("OKU");
            yok.setContentText("Bir kenar en fazla 30 olarak seçilebilir.");
            yok.showAndWait();
        }

        int aa1 = Integer.parseInt(a1.getText());
        int aa2 = Integer.parseInt(a2.getText());
        int aa3 = Integer.parseInt(a3.getText());
        int aa4 = Integer.parseInt(a4.getText());

        if (    oyuncu1.trim().equals("") ||
                oyuncu2.trim().equals("") ||
                oyuncu3.trim().equals("") ||
                oyuncu4.trim().equals(""))
        {
            Alert bilgi = new Alert(Alert.AlertType.CONFIRMATION);
            bilgi.setTitle("DIKKAT");
            bilgi.setHeaderText("OKU");
            bilgi.setContentText("Oyuncu isimlerini boş geçmeyin.");
            bilgi.showAndWait();
        }

        if (    (aa1 == 0 || aa1 < 0) ||
                (aa2 == 0 || aa2 < 0) ||
                (aa3 == 0 || aa3 < 0) ||
                (aa4 == 0 || aa4 < 0))
        {
            Alert bilgi = new Alert(Alert.AlertType.CONFIRMATION);
            bilgi.setTitle("DIKKAT");
            bilgi.setHeaderText("OKU");
            bilgi.setContentText("Altın sayılarını sıfırdan büyük giriniz.");
            bilgi.showAndWait();
        }
        else if(aa1 > 0 || aa2 > 0 || aa3 > 0 || aa4 > 0)
        {
            Oyun.oyuncuBir  = new Oyuncu(oyuncu1, aa1);
            Oyun.oyuncuIki  = new Oyuncu(oyuncu2, aa2);
            Oyun.oyuncuUc   = new Oyuncu(oyuncu3, aa3);
            Oyun.oyuncuDort = new Oyuncu(oyuncu4, aa4);
        }
        else {
            Oyun.oyuncuBir   = new Oyuncu(oyuncu1);
            Oyun.oyuncuIki   = new Oyuncu(oyuncu2);
            Oyun.oyuncuUc    = new Oyuncu(oyuncu3);
            Oyun.oyuncuDort  = new Oyuncu(oyuncu4);
        }

        Date oyunSaat = new Date();
        String giris = oyunSaat.toString() + " : Oyun Baslangıcı \n";
        String oyuncular = oyunSaat.toString() + " : Oyuncular : " + oyuncu1 + ", " + oyuncu2 + ", " + oyuncu3 + ", " + oyuncu4 + "\n";
        String nok = giris + oyuncular;

        try
        {
            FileOutputStream fos = new FileOutputStream(Oyun.cikti);
            fos.write(nok.getBytes());
            //fos.close(); //Sistemde beklememesi icin kapatabiliriz.
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Oyun.pencere.setScene(Oyun.oyunEkrani);
        Oyun.pencere.centerOnScreen();
        //Oyun.pencere.setMaximized(true);
        Oyun.pencere.setFullScreen(true);

        System.out.println("Kutu Olculer : [" + en + "] x [" + boy + "]");
        System.out.println("Oyuncu Isimler : " + oyuncu1 + ", " + oyuncu2 + ", " + oyuncu3 + ", " + oyuncu4);
    }
}