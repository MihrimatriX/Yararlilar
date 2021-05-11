package giris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Oyun extends Application
{
    //TODO Uygulama Pencere
    public static Stage pencere;

    //TODO Sahneler
    public static Scene girisEkrani;
    public static Scene oyunEkrani;

    //TODO Oyuncular
    public static Oyuncu oyuncuBir;
    public static Oyuncu oyuncuIki;
    public static Oyuncu oyuncuUc;
    public static Oyuncu oyuncuDort;

    //TODO Dosya Cikti
    public static File cikti = new File("cikti.txt");

    public static File adosyasi = new File("A_Oyuncusu.txt");
    public static File bdosyasi = new File("B_Oyuncusu.txt");
    public static File cdosyasi = new File("C_Oyuncusu.txt");
    public static File ddosyasi = new File("D_Oyuncusu.txt");

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        pencere = primaryStage;

        Parent giris = FXMLLoader.load(getClass().getResource("girisyap.fxml"));
        Parent oyun = FXMLLoader.load(getClass().getResource("oyun.fxml"));

        girisEkrani = new Scene(giris, 400, 500);
        oyunEkrani = new Scene(oyun, 1920, 1080);

        pencere.setTitle("ALTINA HUCUM");
        pencere.getIcons().add(new Image("ico.png"));
        pencere.setScene(girisEkrani);
        pencere.centerOnScreen();
        pencere.setResizable(false);
        pencere.show();
    }

    @Override
    public void init() throws Exception
    {
        super.init();
        System.out.println("Ayarlama");
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        System.out.println("Bitis");
        Date tarih = new Date();
        String yazi = tarih.toString();
        try
        {
            FileOutputStream fos = new FileOutputStream(Oyun.cikti);
            fos.write((yazi + " :Bitis").getBytes());
            fos.flush();
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
    }
}