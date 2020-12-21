import java.util.Scanner;

public class UnluHarfSayisiBulma
{
    public static void main(String[] args)
    {
        Scanner klavye = new Scanner(System.in);

        System.out.println("Cumle Giriniz. ( Turke Karakter Olmasin )");
        String cumle = klavye.nextLine();

        int harfSayi = 0;
        char[] dizi = cumle.toCharArray();
        char[] kontrol = new char[dizi.length];

        for (int i = 0; i < cumle.length(); i++)
        {
            if (dizi[i] == 'a' || dizi[i] == 'A')
            {
                harfSayi++;
            }

            if  (dizi[i] != 'a' && dizi[i] != 'e' && dizi[i] != 'I' && dizi[i] != 'i' && dizi[i] != 'u' &&
                 dizi[i] != 'o' && dizi[i] != 'A' && dizi[i] != 'E' && dizi[i] != 'O' && dizi[i] != 'U')
            {
                kontrol[i] = dizi[i];
            }
        }
        System.out.println(kontrol);

        System.out.println("Cumledeki A Sayisi : " + harfSayi);
    }
}