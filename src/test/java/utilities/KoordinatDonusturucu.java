package utilities;

import java.awt.*;

public class KoordinatDonusturucu {
    // Orijinal ekran boyutları
    private static final int ORIJINAL_EKRAN_GENISLIK = 1080;
    private static final int ORIJINAL_EKRAN_YUKSEKLIK = 2280;

    // Yeni cihazın ekran boyutları
    private static int yeniEkranGenislik;
    private static int yeniEkranYukseklik;

    // Koordinatları dönüştürme metodu
    public static Point koordinatlariDonustur(Point orijinalNokta, int yeniCihazEkranGenislik, int yeniCihazEkranYukseklik) {
        yeniEkranGenislik = yeniCihazEkranGenislik;
        yeniEkranYukseklik = yeniCihazEkranYukseklik;

        double xOran = (double) yeniEkranGenislik / ORIJINAL_EKRAN_GENISLIK;
        double yOran = (double) yeniEkranYukseklik / ORIJINAL_EKRAN_YUKSEKLIK;

        int yeniX = (int) (orijinalNokta.x * xOran);
        int yeniY = (int) (orijinalNokta.y * yOran);

        return new Point(yeniX, yeniY);
    }

    public static void main(String[] args) {
        // Orijinal ekran boyutlarına göre bir nokta belirleyelim
        Point orjinalnokta=new Point(500,1850);

        // Yeni cihazın ekran boyutları
        int yeniCihazEkranGenislik = 1440; //pixel 7 pro
        int yeniCihazEkranYukseklik = 3120;

        // Koordinatları dönüştürelim
        Point donusturulmusNokta = koordinatlariDonustur(orjinalnokta, yeniCihazEkranGenislik, yeniCihazEkranYukseklik);

        // Yeni koordinatları yazdıralım
        System.out.println("Yeni X Koordinatı: " + donusturulmusNokta.x);  //666
        System.out.println("Yeni Y Koordinatı: " + donusturulmusNokta.y); //2531

    }

}
