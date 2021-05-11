package giris;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.Nullable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static giris.Oyun.*;

public class OyunController
{
    //TODO Arayüz Elemanları
    @FXML
    Button baslat;
    @FXML
    TextField biAd;
    @FXML
    TextField ikiAd;
    @FXML
    TextField ucAd;
    @FXML
    TextField doAd;
    @FXML
    Label altin1;
    @FXML
    Label altin2;
    @FXML
    Label altin3;
    @FXML
    Label altin4;
    @FXML
    Pane panel;

    public Random r = new Random();
    public Font font = new Font(20);

    private static final int EXIT_SUCCESS = 0;
    public static Boolean[][] altinli;
    public static int[][] altinVarYokDizi;
    public static Button[][] btnlr;
    public int altinliSayi = 0;
    public int gizliAltinSayi = 0;

    public static int[][] A_Konum;
    public static int[][] B_Konum;
    public static int[][] C_Konum;
    public static int[][] D_Konum;

    public static FileOutputStream a_dos_yaz;
    public static FileOutputStream b_dos_yaz;
    public static FileOutputStream c_dos_yaz;
    public static FileOutputStream d_dos_yaz;

    public static int yatay;
    public static int dikey;
    public static int oran;
    public static int topAltin;

    public static Boolean baslangic = false;
    public static GridPane grid;
    public static int[][] altinlar;

    private Pane makeGrid()
    {
        int satir;
        int sutun;
        grid = new GridPane();

        for (satir = 0; satir < GirisController.boyutTahtaEn; satir++)
        {
            Node[] nodes = new Node[GirisController.boyutTahtaBoy];// Ilk Satirin Butonları

            for (sutun = 0; sutun < GirisController.boyutTahtaBoy; sutun++)
            {
                Button node = new Button("");
                node.setPrefSize((float) 1520 / (dikey), (float) 1040 / (yatay));

                if (altinVarYokDizi[satir][sutun] == 1)
                {
                    node.setText(String.valueOf(altinlar[satir][sutun]));
                    node.setFont(font);
                    node.setTextFill(Color.RED);
                }
                else if (altinVarYokDizi[satir][sutun] == 2)
                {
                    node.setText(String.valueOf(altinlar[satir][sutun]));
                    node.setFont(font);
                    node.setTextFill(Color.BLUE);
                    node.setDisable(true);
                }
                else {
                    node.setText(String.valueOf(altinlar[satir][sutun]));
                    node.setFont(font);
                    node.setTextFill(Color.BLACK);
                    node.setDisable(true);
                }
                nodes[sutun] = node;
            }
            grid.addRow(satir, nodes);
        }
        return grid;
    }

    public void optimizeAltinSayisi()
    {
        altinli = new Boolean[yatay][dikey];
        altinVarYokDizi = new int[yatay][dikey];
        altinlar = new int[yatay][dikey];

        int alan = yatay * dikey;

        int yuzde20 = alan / 5;
        boolean deadlock = true;
        int gizliyuzde = yuzde20 / 10;

        int i1 = 0;
        int j1 = 0;

        while (deadlock)
        {
            altinliSayi = 0;
            for (i1 = 0; i1 < yatay; i1++)
            {
                for (j1 = 0; j1 < dikey; j1++)
                {
                    if ((r.nextInt(5) + 1) == 2) {
                        altinli[i1][j1] = true;
                        altinVarYokDizi[i1][j1] = 1;
                        altinliSayi++;
                    }
                    else {
                        altinVarYokDizi[i1][j1] = 0;
                        altinli[i1][j1] = false;
                    }
                }
            }

            if (altinliSayi == yuzde20)
            {
                deadlock = false;
            }
        }

        deadlock = true;
        while (deadlock)
        {
            gizliAltinSayi = 0;
            for (i1 = 0; i1 < yatay; i1++)
            {
                for (j1 = 0; j1 < dikey; j1++)
                {
                    int rast = (r.nextInt(9) + 1);
                    if (rast == 2 && altinVarYokDizi[i1][j1] == 1)
                    {
                        altinVarYokDizi[i1][j1] = 2;
                        gizliAltinSayi++;
                    }
                }
            }
            if (gizliAltinSayi == gizliyuzde)
            {
                deadlock = false;
            }
            else {
                for (int i = 0; i < yatay; i++)
                {
                    for (int j = 0; j < dikey; j++)
                    {
                        if (altinVarYokDizi[i][j] == 2)
                        {
                            altinVarYokDizi[i][j] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                if (altinVarYokDizi[i][j] == 1 || altinVarYokDizi[i][j] == 2)
                {
                    altinlar[i][j] = (r.nextInt(4) + 1) * 5;
                }
                else {
                    altinlar[i][j] = 0;
                }
            }
        }
    }

    public void konumlariAyarla(String A, String B, String C, String D)
    {
        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                if (A.equals("A") && i == 0 && j == 0)
                {
                    A_Konum[i][j] = 1;
                }
                else{
                    A_Konum[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                if (B.equals("B") && i == yatay -1 && j == 0)
                {
                    A_Konum[i][j] = 1;
                }
                else{
                    A_Konum[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                if (C.equals("C") && i == 0 && j == dikey - 1)
                {
                    A_Konum[i][j] = 1;
                }
                else{
                    A_Konum[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                if (D.equals("D") && i == yatay - 1 && j == dikey - 1)
                {
                    A_Konum[i][j] = 1;
                }
                else{
                    A_Konum[i][j] = 0;
                }
            }
        }
    }

    public void baslamak() throws IOException
    {
        a_dos_yaz = new FileOutputStream(Oyun.adosyasi);
        b_dos_yaz = new FileOutputStream(Oyun.bdosyasi);
        c_dos_yaz = new FileOutputStream(Oyun.cdosyasi);
        d_dos_yaz = new FileOutputStream(Oyun.ddosyasi);

        A_Konum = new int[yatay][dikey];
        B_Konum = new int[yatay][dikey];
        C_Konum = new int[yatay][dikey];
        D_Konum = new int[yatay][dikey];

        String a = "A";
        String b = "B";
        String c = "C";
        String d = "D";

        konumlariAyarla(a, b, c, d);
        //konumYazdir();

        baslangic = true;

        yatay = GirisController.boyutTahtaEn;
        dikey = GirisController.boyutTahtaBoy;

        biAd.setText(oyuncuBir.getOyuncuIsim());
        biAd.setEditable(false);
        ikiAd.setText(oyuncuIki.getOyuncuIsim());
        ikiAd.setEditable(false);
        ucAd.setText(oyuncuUc.getOyuncuIsim());
        ucAd.setEditable(false);
        doAd.setText(oyuncuDort.getOyuncuIsim());
        doAd.setEditable(false);

        altin1.setText(String.valueOf(oyuncuBir.   getAltin()));
        altin2.setText(String.valueOf(oyuncuIki.   getAltin()));
        altin3.setText(String.valueOf(oyuncuUc.    getAltin()));
        altin4.setText(String.valueOf(oyuncuDort.  getAltin()));

        optimizeAltinSayisi();

        panel.getChildren().addAll(makeGrid());

        if (baslangic) baslat.setDisable(true);

        //altinAraA();

        a_dos_yaz.write(konumdosyaYaz(A_Konum).getBytes());


        logTut();
    }

    private void konumYazdir()
    {
        System.out.println("Girilen Konum : ");
        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                System.out.println("\t" + A_Konum[i][j]);
            }
        }
    }

    private String konumdosyaYaz(int[][] oyuncuKonumu)
    {
        String konum = "";
        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                konum += "\t" + oyuncuKonumu[i][j];
            }
            konum += "\n";
        }
        return konum;
    }

    public void logTut()
    {
        ayir();
        varOlupOlmama();

        ayir();
        altinlariYaz();

        ayir();
        butonIcerik();

        ayir();
        altinOran();

        ayir();
        konumYazdir();
    }

    public void butonIcerik()
    {
        System.out.println("Butonlarin Icerikleri : ");

        btnlr = new Button[yatay][dikey];
        for (int i = 0; i < yatay; i++)
        {
            for (int j = 0; j < dikey; j++)
            {
                btnlr[i][j] = (Button) getNodeFromGridPane(grid, i, j);
                System.out.print("\t" + btnlr[i][j].getText());
            }
            System.out.println();
        }
    }

    private @Nullable
    Node getNodeFromGridPane(GridPane gridPane, int row, int col)
    {
        /**
         * @implNote : Buradan Griddeki Butonların Icerikleri
         */
        for (Node btn : gridPane.getChildren())
        {
            if (GridPane.getColumnIndex(btn) == col && GridPane.getRowIndex(btn) == row)
            {
                return btn;
            }
        }
        return null;
    }

    public void altinlariYaz()
    {
        System.out.println("Karelerdeki Altinlar : ");

        for (int i = 0; i < GirisController.boyutTahtaEn; i++)
        {
            for (int j = 0; j < GirisController.boyutTahtaBoy; j++)
            {
                System.out.print("\t" + altinlar[i][j]);
            }
            System.out.println();
        }
    }

    public void varOlupOlmama()
    {
        System.out.println("Altinlarin Olma Durumu : ");

        for (int i = 0; i < GirisController.boyutTahtaEn; i++)
        {
            for (int j = 0; j < GirisController.boyutTahtaBoy; j++)
            {
                System.out.print("\t" + altinVarYokDizi[i][j]);
            }
            System.out.println();
        }
    }

    public void altinOran()
    {
        System.out.println("Altinlarin Orani : ");
        System.out.println("Toplam Altinli Sayi : " + altinliSayi);

        topAltin = GirisController.boyutTahtaBoy * GirisController.boyutTahtaEn;
        oran = topAltin * (GirisController.oran / 100);

        System.out.println("Toplam Kutu : " + topAltin);
        System.out.println("Altin Orani : " + oran);
        System.out.println("Gizli Altin Sayisi : " + gizliAltinSayi);
    }


    //TODO A Degiskenleri
    int xA = 0;
    int yA = 0;
    int altinSayisiA = 200;

    int hedefAdim1Ax = 0;
    int hedefAdim1Ay = 0;
    int hedefAdim2Ax = 0;
    int hedefAdim2Ay = 0;
    int hedefAdim3Ax = 0;
    int hedefAdim3Ay = 0;
    int hedefAdim4Ax = 0;
    int hedefAdim4Ay = 0;
    int hedefAdim5Ax = 0;
    int hedefAdim5Ay = 0;

    int adimA = 0;
    boolean hamleA = true;
    int hedefAdimSayisi = 5;  //maksimum 5 adima göre algoritma

    //TODO B Degiskenleri
    int xB = 0;
    int yB = 0;
    int altinSayisiB = 200;

    int hedefAdim1Bx = 0;
    int hedefAdim1By = 0;
    int hedefAdim2Bx = 0;
    int hedefAdim2By = 0;
    int hedefAdim3Bx = 0;
    int hedefAdim3By = 0;
    int hedefAdim4Bx = 0;
    int hedefAdim4By = 0;
    int hedefAdim5Bx = 0;
    int hedefAdim5By = 0;

    int adimB = 0;
    boolean hamleB = true;
    int enKarliB = 0;
    int tempEnKarliB = 0;





    public void altinAraA()
    {
        if (hamleA == true && adimA < hedefAdimSayisi)
        {
            if (altinVarYokDizi[xA + 1][yA] == 1 && (xA+1) < yatay)// && xA+1 < ScreenSize.x eklenecek
            {
                xA++;
                //altin arttirma islemi yapilacak

                oyuncuBir.altinArtir(altinlar[xA][yA]);
                altin1.setText(String.valueOf(oyuncuBir.getAltin()));



                if (adimA == 0)
                {
                    hedefAdim1Ax = xA;
                    hedefAdim1Ay = yA;
                }
                if (adimA == 1)
                {
                    hedefAdim2Ax = xA;
                    hedefAdim2Ay = yA;
                }
                if (adimA == 2)
                {
                    hedefAdim3Ax = xA;
                    hedefAdim3Ay = yA;
                }
                if (adimA == 3)
                {
                    hedefAdim4Ax = xA;
                    hedefAdim4Ay = yA;
                }
                if (adimA == 4)
                {
                    hedefAdim5Ax = xA;
                    hedefAdim5Ay = yA;
                }
                adimA++;
                altinAraA();
            }
/*            else if (altinVarYokDizi[xA - 1][yA] == 1 && xA - 1 > 0)
            {
                xA--;
                //altin arttirma islemi yapilacak

                oyuncuBir.altinArtir(altinlar[xA][yA]);
                altin1.setText(String.valueOf(oyuncuBir.getAltin()));

                if (adimA == 0)
                {
                    hedefAdim1Ax = xA;
                    hedefAdim1Ay = yA;
                }
                if (adimA == 1)
                {
                    hedefAdim2Ax = xA;
                    hedefAdim2Ay = yA;
                }
                if (adimA == 2)
                {
                    hedefAdim3Ax = xA;
                    hedefAdim3Ay = yA;
                }
                if (adimA == 3)
                {
                    hedefAdim4Ax = xA;
                    hedefAdim4Ay = yA;
                }
                if (adimA == 4)
                {
                    hedefAdim5Ax = xA;
                    hedefAdim5Ay = yA;
                }
                adimA++;
                altinAraA();
            }*/
            else if (altinVarYokDizi[xA][yA + 1] == 1  && yA + 1 < dikey)// && yA+1 < ScreenSize.y eklenecek
            {
                yA++;
                //altin arttirma islemi yapilacak

                oyuncuBir.altinArtir(altinlar[xA][yA]);
                altin1.setText(String.valueOf(oyuncuBir.getAltin()));

                if (adimA == 0)
                {
                    hedefAdim1Ax = xA;
                    hedefAdim1Ay = yA;
                }
                if (adimA == 1)
                {
                    hedefAdim2Ax = xA;
                    hedefAdim2Ay = yA;
                }
                if (adimA == 2)
                {
                    hedefAdim3Ax = xA;
                    hedefAdim3Ay = yA;
                }
                if (adimA == 3)
                {
                    hedefAdim4Ax = xA;
                    hedefAdim4Ay = yA;
                }
                if (adimA == 4)
                {
                    hedefAdim5Ax = xA;
                    hedefAdim5Ay = yA;
                }
                adimA++;
                altinAraA();
            }/*
            else if (altinVarYokDizi[xA][yA - 1] == 1 && yA - 1 > 0)
            {
                yA--;
                //altin arttirma islemi yapilacak

                oyuncuBir.altinArtir(altinlar[xA][yA]);
                altin1.setText(String.valueOf(oyuncuBir.getAltin()));

                if (adimA == 0)
                {
                    hedefAdim1Ax = xA;
                    hedefAdim1Ay = yA;
                }
                if (adimA == 1)
                {
                    hedefAdim2Ax = xA;
                    hedefAdim2Ay = yA;
                }
                if (adimA == 2)
                {
                    hedefAdim3Ax = xA;
                    hedefAdim3Ay = yA;
                }
                if (adimA == 3)
                {
                    hedefAdim4Ax = xA;
                    hedefAdim4Ay = yA;
                }
                if (adimA == 4)
                {
                    hedefAdim5Ax = xA;
                    hedefAdim5Ay = yA;
                }
                adimA++;
                altinAraA();
            }*/
        }

        else {
            hamleA = false;
            adimA = 0;
        }
    }

    public void altinAraB()
    {
        int sayacX = 0;
        int sayacY = 0;
        if (hamleB == true && adimB < hedefAdimSayisi)
        {
            for (int i = 0; i < hedefAdimSayisi * 2 + 1; i++)
            {
                for (int j = 0; j < hedefAdimSayisi * 2 + 1; j++)
                {
                    if (altinVarYokDizi[xB - hedefAdimSayisi + i + sayacX][yB - hedefAdimSayisi + j + sayacY] == 1) ;
                    {
                        if (hedefAdimSayisi == Math.abs(xB - (xB - hedefAdimSayisi + i + sayacX)) + Math.abs(yB - (yB - hedefAdimSayisi + j + sayacY)))
                        //bu kareden B'nin bulundugu yere hamle sayisinca gidiş var demek.
                        {
                            tempEnKarliB = altinlar[xB - hedefAdimSayisi + i + sayacX][yB - hedefAdimSayisi + j + sayacY];
                            adimB++;
                            merkezeGitB();
                        }
                    }
                }
            }

            if (altinVarYokDizi[xB + 1][yB] == 1 && xB + 1 < yatay)// && xA+1 < ScreenSize.x eklenecek
            {
                if (enKarliB < altinlar[xB + 1][yB])
                {
                    enKarliB = altinlar[xB + 1][yB];
                }

                xB++;
                //altin arttirma islemi yapilacak
                if (adimB == 0)
                {
                    hedefAdim1Bx = xB;
                    hedefAdim1By = yB;
                }
                if (adimB == 1)
                {
                    hedefAdim2Bx = xB;
                    hedefAdim2By = yB;
                }
                if (adimB == 2)
                {
                    hedefAdim3Bx = xB;
                    hedefAdim3By = yB;
                }
                if (adimA == 3)
                {
                    hedefAdim4Bx = xB;
                    hedefAdim4By = yB;
                }
                if (adimA == 4)
                {
                    hedefAdim5Bx = xB;
                    hedefAdim5By = yB;
                }
                adimB++;
                altinAraB();
            }
            else if (altinVarYokDizi[xB - 1][yB] == 1 && xB - 1 > 0)
            {
                xB--;
                //altin arttirma islemi yapilacak
                if (adimB == 0)
                {
                    hedefAdim1Bx = xB;
                    hedefAdim1By = yB;
                }
                if (adimB == 1)
                {
                    hedefAdim2Bx = xB;
                    hedefAdim2By = yB;
                }
                if (adimB == 2)
                {
                    hedefAdim3Bx = xB;
                    hedefAdim3By = yB;
                }
                if (adimA == 3)
                {
                    hedefAdim4Bx = xB;
                    hedefAdim4By = yB;
                }
                if (adimA == 4)
                {
                    hedefAdim5Bx = xB;
                    hedefAdim5By = yB;
                }
                adimB++;
                altinAraB();
            }
            else if (altinVarYokDizi[xB][yB + 1] == 1 && yB + 1 < dikey )// && yA+1 < ScreenSize.y eklenecek, kac yazdiysan artik.
            {
                yB++;
                //altin arttirma islemi yapilacak

                if (adimB == 0)
                {
                    hedefAdim1Bx = xB;
                    hedefAdim1By = yB;
                }
                if (adimB == 1)
                {
                    hedefAdim2Bx = xB;
                    hedefAdim2By = yB;
                }
                if (adimB == 2)
                {
                    hedefAdim3Bx = xB;
                    hedefAdim3By = yB;
                }
                if (adimA == 3)
                {
                    hedefAdim4Bx = xB;
                    hedefAdim4By = yB;
                }
                if (adimA == 4)
                {
                    hedefAdim5Bx = xB;
                    hedefAdim5By = yB;
                }
                adimB++;
                altinAraB();
            }
            else if (altinVarYokDizi[xB][yB - 1] == 1 && yB - 1 > 0)
            {
                yB--;
                //altin arttirma islemi yapilacak
                if (adimB == 0)
                {
                    hedefAdim1Bx = xB;
                    hedefAdim1By = yB;
                }
                if (adimB == 1)
                {
                    hedefAdim2Bx = xB;
                    hedefAdim2By = yB;
                }
                if (adimB == 2)
                {
                    hedefAdim3Bx = xB;
                    hedefAdim3By = yB;
                }
                if (adimB == 3)
                {
                    hedefAdim4Bx = xB;
                    hedefAdim4By = yB;
                }
                if (adimB == 4)
                {
                    hedefAdim5Bx = xB;
                    hedefAdim5By = yB;
                }
                adimB++;
                altinAraB();
            }
        }
        else {
            hamleA = false;
            adimA = 0;
        }
    }

    public void merkezeGitB()
    {
        //Burada bulunan her altından merkeze giderkenki bütün olası durumlar  hesaplatılır. En iyisi EnKarlıB'de tutulacak.
        //her seferinde büyük olursa olarak tempEnKarliB'ye atılacak. algoritma sonunda en karlıya atacak.
    }

    public void ayir()
    {
        System.out.println("\n\n\n");
    }

    public void cik()
    {
        System.exit(EXIT_SUCCESS);
    }
}