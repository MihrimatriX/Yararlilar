import java.util.Scanner;

public class UsAlmaVeParaBoza
{
    public static void main(String[] args)
    {
        /**
         * TODO // Us Bulma
         */
        Scanner klavye = new Scanner(System.in);
        int taban, us;

        System.out.println("Taban Degeri Girin");
        taban = klavye.nextInt();
        //taban = Integer.parseInt(args[0]);

        System.out.println("Us Degeri Girin");
        us = klavye.nextInt();
        //us = Integer.parseInt(args[1]);

        int sonuc = 1;

        for (int i = 0; i < us; i++)
        {
            sonuc *= taban;
        }
        System.out.println("Sonuc : " + sonuc);

        /***********************************************/

        /**
         * TODO // Para Bozma Makinesi
         */
        int para, fark;

        System.out.println("Paranızı Girin : ");
        para = klavye.nextInt();

        if ( para%5 != 0 )
        {
            fark = para%5;
            if (fark < 3)
            {
                System.out.println(fark + " paranizi yedim.");
                para = para - fark;
            }
            if (fark >= 3)
            {
                System.out.println((5 - fark) + " paramizi yediniz.");
                para += 5 - fark;
            }
            /**
             * 50, 25, 10, 5
             */
            int a50=0, a25=0, a10=0, a5=0;

            //50 lik banknot adedi
            if (para >= 50)
            {
                a50 = para / 50;
                para = para % 50;
            }

            //25 lik banknot adedi
            if (para >= 25)
            {
                a25 = para / 25;
                para = para % 25;
            }

            //10 luk banknot adedi
            if (para >= 10)
            {
                a10 = para / 10;
                para = para % 10;
            }

            //5 lik banknot adedi
            if (para >= 5)
            {
                a5 = para / 5;
                para = para % 5;
            }

            int adet = a50 + a25 + a10 + a5;
            System.out.println(adet + " adet para");
        }
    }
}