public class Rastgele 
{    
    public static void main(String arg[])
    {
        int sayi, indexSec[] = new int[4];
        indexSec[0] = 0;
        for (int i = 1; i < 4; i++)
        {
            sayi = (int) (Math.random() * 8);
            for (int j = 0; j <= i; j++)
            {
                if (indexSec[j] == sayi)
                {
                    sayi = (int) (Math.random() * 8);
                    j = 0;
                }
            }
            indexSec[i] = sayi;
        }
        for (int j = 0; j < 4; j++)
        {
            System.out.println(indexSec[j] + " ");

        }
    }
}