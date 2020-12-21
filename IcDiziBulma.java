import java.util.Random;

public class IcDiziBulma
{
    /**
     * Dizi İcinde Dizi Bulma
     */

    public static void main(String[] args)
    {
        int[] dizi = new int[20];
        Random rd = new Random();

        for (int i = 0; i < dizi.length; i++)
        {
            dizi[i] = rd.nextInt() % 10;
            if (dizi[i] < 0)
            {
                dizi[i] *= -1;
            }
        }

        for (int i = 0; i < dizi.length; i++)
        {
            System.out.print(dizi[i] + "   ");
            // Dizimiz
        }

        // Kontrol Dizisi Olu┼čturduk.
        int[] kontrolEt = new int[dizi.length + 1];
        for (int i = 0; i < kontrolEt.length - 1; i++)
        {
            if (i == dizi.length + 1)
            {
                kontrolEt[i] = 0;
                break;
            }
            kontrolEt[i] = dizi[i];
        }

        System.out.println("");
        int uzunluk = 0;
        int uzunMax = 0;

        for (int i = 0; i < dizi.length; i++)
        {
            if (dizi[i] < kontrolEt[i + 1])
            {
                uzunluk++;
                if (uzunluk > uzunMax)
                {
                    uzunMax = uzunluk;
                }
            }

            if (dizi[i] == kontrolEt[i + 1] || dizi[i] > kontrolEt[i + 1])
            {
                uzunluk = 0;
            }
        }
        System.out.println("max uzun dizi : " + (uzunMax+1));
    }
}

