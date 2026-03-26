Feature: Otel rezervasyon tamamlama senaryolari

  Background:
    Given Cerezler kabul edilir
    And Bildirim izni cikarsa kapatilir
    And Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir
    And Anasayfada otel arama alanina tiklanir

  @Tb @Rezervasyon @E2E
  Scenario Outline: Otel rezervasyonu alip odeme ile tamamla ve rezervasyon numarasini kaydet

    # --- Arama / Liste ---
    And Otel arama alanina "<otelSehirBolgeTema>" adi yazilir
    And Aranilan kategoriye gore listelenen verilerden ilki secilir
    And Tarih secim alanina tiklanir.
    And Giris tarihi olarak bugunden "<girisGunSonra>" gun sonrasinin secilmesi saglanir
    And Cikis tarihi olarak bugunden "<cikisGunSonra>" gun sonrasinin secilmesi saglanir
    And Uygulama butonuna tiklanir
    And Yetiskin sayısı belirleme alaninia tiklanir
    And Acilan ekranda uygula butonuna tiklanir
    And Otel arama islemi baslatilir

    # --- Otel / Oda / Rezervasyon ---
    And Listeden gelen ilk otele tiklanir
    And Ilk gorunen Rezervasyon Yap butonuna tiklanir

    # --- Kisi Bilgileri (1. Kisi - ayni sayfa) ---
    And Erkek radio butonuna tiklanir
    And Ad alani "<ad1>" ile doldurulur
    And Soyad alani "<soyad1>" ile doldurulur
    And TC kimlik numarasi "<tc1>" girilir
    And Telefon "<telefon>" girilir
    And Eposta "<eposta>" girilir
    And Dogum tarihi secim alani acilir
    And Dogum tarihi picker ekraninda gun "<gun1>" ay "<ay1>" yil "<yil1>" olacak sekilde kaydirilir
    And Dogum tarihi icin Sec butonuna tiklanir

    # --- 2. Kisi ---
    And 2. kisi ekle butonuna tiklanir
    And 2. kisi icin Kadin radio butonuna tiklanir
    And 2. kisi ad alani "<ad2>" ile doldurulur
    And 2. kisi soyad alani "<soyad2>" ile doldurulur
    And 2. kisi icin dogum tarihi secim alani acilir
    And 2. kisi dogum tarihi picker ekraninda gun "<gun1>" ay "<ay1>" yil "<yil1>" olacak sekilde kaydirilir
    And 2. kisi dogum tarihi icin Sec butonuna tiklanir
    And 2. kisi Ekle butonuna tiklanir

    # --- Odeme ekranina gecis ---
    And Odeme adimina devam edilir
    Then Odeme Bilgileri sayfasi acildigi dogrulanir

    # --- Kart ile Odeme ---
    And Odeme ekraninda kart alanlari gorunene kadar asagi kaydirilir
    And Kart uzerindeki isim "<kartIsim>" girilir
    And Kart numarasi "<kartNo>" girilir
    And Son kullanma ayi secilir
    And Son kullanma yili secilir
    And CVV "<cvv>" girilir
    And On bilgilendirme formu ve satis sozlesmesi checkboxina tiklanir
    And Rezervasyonu Tamamla butonuna tiklanir

    # --- Rezervasyon Onayi ---
    Then Rezervasyonun onaylandigi dogrulanir
    And Rezervasyon numarasi kaydedilir
    And Anasayfaya Don butonuna tiklanir

    Examples:
      | otelSehirBolgeTema | girisGunSonra | cikisGunSonra | ad1 | soyad1 | tc1         | telefon      | eposta       | gun1 | ay1  | yil1 | ad2  | soyad2 | gun2 | ay2   | yil2 | kartIsim  | kartNo           |   cvv |
      | Antalya            | 10            | 14            | Ali | Kaya   | 11111111111 | 5555555555 | ali@test.com | 5    | Mart | 2008 | Ayse | Kaya   | 6    | Nisan | 2008   | Ali Kaya   |  4000300020001000   |   123 |