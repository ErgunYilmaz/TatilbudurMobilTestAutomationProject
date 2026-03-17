Feature: Otel aratma ve listeyi fiyat(artan)'a gore siralama Uygulamada otel aramasi yapilir ve cikan sonuclar fiyata gore artan sekilde siralanir.
  @Tb @OtelAratmaVeListeyiFiyatArtanSiralama
  Scenario: Bodrum otellerini fiyat artan siralama ile listeleme
    Given Cerezler kabul edilir
    And Bildirim izni cikarsa kapatilir
    And Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir
    When Anasayfada otel arama alanina "antalya" yazilir ve secilir
    And Giris tarihi olarak bugunden "5" gun sonrasinin secilmesi saglanir
    And Cikis tarihi olarak bugunden "10" gun sonrasinin secilmesi saglanir
    And Otel arama islemi baslatilir
    Then Cikan sonuclar fiyata gore artan sekilde siralanir
    And Sonuclarin fiyata gore artan sekilde listelendigi dogrulanir


