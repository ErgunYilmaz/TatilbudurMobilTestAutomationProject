Feature: Otel ve Tur Arama - Listeleme, Filtreleme ve Detay Kontrolleri
  Uygulamada otel ve tur aramasi yapilir, sonuclar farkli kriterlere göre filtrelenir ve siralanir.
  Ayrica detay sayfalari ve buton kontrolleri dogrulanir.

  Background:
    Given Cerezler kabul edilir
    And Bildirim izni cikarsa kapatilir
    And Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir
    When Anasayfada otel arama alanina tiklanir

  @Tb @SiralamaFiltreleri
    Scenario Outline: Otelleri filtrelenen siralamaya gore listeleme
    And Otel arama alanina "<otelSehirBolgeTema>" adi yazilir
    And Aranilan kategoriye gore listelenen verilerden ilki secilir
    And Tarih secim alanina tiklanir.
    And Giris tarihi olarak bugunden "<girisGunSonra>" gun sonrasinin secilmesi saglanir
    And Cikis tarihi olarak bugunden "<cikisGunSonra>" gun sonrasinin secilmesi saglanir
    And Uygulama butonuna tiklanir
    And Yetiskin sayısı belirleme alaninia tiklanir
    And Acilan ekranda uygula butonuna tiklanir
    And Otel arama islemi baslatilir
    And Siralama butonuna tiklanir
    When Siralama kriteri olarak "<siralamaFiltresi>" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin "<siralamaFiltresi>" gore listelendigi dogrulanir

    Examples:
      | otelSehirBolgeTema | girisGunSonra  | cikisGunSonra  | siralamaFiltresi |
      | Antalya            | 5              | 10             | Fiyat (Artan)    |
      | Antalya            | 10             | 14             | Fiyat (Azalan)   |
      | Antalya            | 10             | 14             | İndirim Oranı    |



   @FiyatArtan
  Scenario Outline: Otelleri fiyata göre artan sekilde listeleme
    And  Siralama butonuna tiklanir
    When Siralama kriteri olarak "Fiyat (artan)" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin fiyata gore artan sekilde listelendigi dogrulanir

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 5             | 10            |
      | Ankara                | 7             | 17            |

  @FiyatAzalan
  Scenario Outline: Otelleri fiyata göre azalan sekilde listeleme
    And  Siralama butonuna tiklanir
    When Siralama kriteri olarak "Fiyat (azalan)" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin fiyata gore azalan sekilde listelendigi dogrulanir

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 5             | 10            |
      | Ankara                | 7             | 17            |

  @PuanAzalan
  Scenario Outline: Otelleri puana göre en yuksekten en dusuge siralama
    And  Siralama butonuna tiklanir
    When Siralama kriteri olarak "Puan (yüksekten düşüğe)" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin puana gore dogru siralandigi dogrulanir

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 5             | 10            |
      | Ankara                | 7             | 17            |

  @PuanArtan
  Scenario Outline: Otelleri puana göre en dusukten en yuksege siralama
    And  Siralama butonuna tiklanir
    When Siralama kriteri olarak "Puan (düşükten yükseğe)" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin puana gore dogru siralandigi dogrulanir

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 5             | 10            |
      | Ankara                | 7             | 17            |

  @IndirimOrani
  Scenario Outline: Otelleri indirim oranina göre siralama
    And  Siralama butonuna tiklanir
    When Siralama kriteri olarak "İndirim oranı" secilir
    And  Sonucu Goster butonuna tiklanir
    Then Sonuclarin indirim oranina gore dogru siralandigi dogrulanir

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 5             | 10            |
      | Ankara                | 7             | 17            |
  @FiyatAraligi
  Scenario Outline: Fiyat araligina göre otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Fiyat araligi "<min>-<max>" secilir
    Then Sonuclarin secilen fiyat araligina gore filtrelendigini dogrulanir

    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | min | max  |
      | İstanbul               | 5             | 10            | 1000| 3000 |

  @PuanFiltre
  Scenario Outline: Misafir puanina göre otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir puani "<puan>" ve uzeri secilir
    Then Sonuclarin misafir puanina gore dogru filtrelendigini dogrulanir

    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | puan |
      | İstanbul               | 4             | 9             | 8    |
  @FiltrelemeKategoriyeGore
  Scenario Outline: Misafir filtreleme kategorisine göre otel listesi filtreleme
    Given Misafir filtreleme sayfasindadir
    And Filtreleme butonuna tiklanir
    And "<FiltrelemeKategorisi>" kategorisine tiklanir
    And "<FiltrelemeIcerigi>" secilir
    When Sonuclari goster butonuna tiklanir
    Then Sonuclarin "<FiltrelemeKategorisi>" kategorisindeki "<FiltrelemeIcerigi>" gore filtrelendigini dogrular
    Examples:
      | otelSehirBolgeTema | girisGunSonra | cikisGunSonra   | FiltrelemeKategorisi  | FiltrelemeIcerigi            |
      | İstanbul            | 4             | 9              | Populer Filtreler     | Her Şey Dahil Oteller        |
      | İstanbul            | 4             | 9              | Bolgeler              | Antalya                      |
      | İstanbul            | 4             | 9              | Konaklama Tipi        | Ultra Her Şey Dahil          |
      | İstanbul            | 4             | 9              | Otel Tipi             | Vogue Otelleri               |
      | İstanbul            | 4             | 9              | Özel Dönemler         | 2026 Yazı İlk Fırsatları     |
      | İstanbul            | 4             | 9              | Aktivite-Eğlence      | Spor Salonu                  |
      | İstanbul            | 4             | 9              | Otel Temaları         | Balayı Otelleri              |
      | İstanbul            | 4             | 9              | Konaklama Tipi        | Her Şey Dahil                |
      | İstanbul            | 4             | 9              | Otel Tipi             | Vogue Otelleri               |
      | İstanbul            | 4             | 9              | Özel Dönemler         | 2026 Yazı İlk Fırsatları     |
      | İstanbul            | 4             | 9              | Aktivite-Eğlence      | Spor Salonu                  |
      | İstanbul            | 4             | 9              | Otel Temaları         | Balayı Otelleri              |

  @PopulerFiltre
    Scenario Outline: Misafir populer filtrelere gore otel listesi filtreleme
      And  Filtreleme butonuna tiklanir
      When Misafir "<populerFiltre>" gore filtreler
      Then Sonuclarin aranan populer filtreye gore filtrelendigini dogrulanir
      Examples:
        | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | populerFiltre|
        | İstanbul               | 4             | 9             |              |
  @BolgeyeFiltre
  Scenario Outline: Misafir bolgeye gore otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir "<bolge>" gore filtreler
    Then Sonuclarin aranan bolgeye gore filtrelendigini dogrulanir
    Examples:
      | otelSehirBolgeTema   | girisGunSonra | cikisGunSonra | bolge|
      | İstanbul             | 4             | 9             | İstanbul|
  @KonaklamayaTipineGoreFiltre
  Scenario Outline: Misafir konaklama tipine gore otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir "<konaklamaTipi>" gore filtreler
    Then Sonuclarin aranan konaklama tipine gore filtrelendigini dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | konaklamaTipi|
      | İstanbul               | 4             | 9             | Her Şey Dahil|
  @OtelTipineGoreFiltre
  Scenario Outline: Misafir otel tipine gore otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir "<otelTipi>" gore filtreler
    Then Sonuclarin aranan konaklama tipine gore filtrelendigini dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | otelTipi|
      | İstanbul               | 4             | 9             | Vogue Otelleri|
  @MisafirOzelDonemeGoreFiltre
  Scenario Outline: Misafir ozel doneme gore otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir "<ozelDonem>" gore filtreler
    Then Sonuclarin aranan konaklama tipine gore filtrelendigini dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | ozelDonem|
      | İstanbul               | 4             | 9             | 2026 Yazı İlk Fırsatları|
  @AktiviteEglenceGoreFiltre
  Scenario Outline: Misafir aktivite eglence gore otel listesi filtreleme
    And  Filtreleme butonuna tiklanir
    When Misafir "<aktiviteEglence>" gore filtreler
    Then Sonuclarin aranan aktivite eglenceye gore filtrelendigini dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | aktiviteEglence|
      | İstanbul               | 4             | 9             | Spor salonu    |
    @OtelTemalarinaGoreFiltre
    Scenario Outline:Misafir otel temalarina gore otel listesi filtreleme
      And  Filtreleme butonuna tiklanir
      When Misafir "<aktiviteEglence>" gore filtreler
      Then Sonuclarin aranan aktivite eglenceye gore filtrelendigini dogrulanir
      Examples:
        | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra | aktiviteEglence|
        | İstanbul               | 4             | 9             | Spor salonu|


  # --- LİSTELEME SAYFASI KONTROLLERİ ---
  @SiziArayalim
  Scenario Outline: Listeleme sayfasinda "Sizi arayalim" butonunun kontrolü
    Then Listeleme sayfasinda "Sizi arayalim" butonunun goruntulendigi dogrulanir
    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 4             | 9             |

  @DahaFazlaGoster
  Scenario Outline: Otel listeleme sayfasinda "Daha fazla göster" kisminin kontrolü
    When Kullanici "Daha fazla göster" butonuna tiklar
    Then Daha fazla otelin listelendigini dogrular
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra |
      | İstanbul               | 4             | 9             |

  # --- OTEL DETAY SAYFASI ---
  @DetaySearchBar
  Scenario Outline: Otel detay sayfasinda search barda tarih degisikligi kontrolu
    When Otel detay sayfasinda arama tarihleri "<yeniGirisGunSonra>" - "<yeniCikisGunSonra>" olarak degistirilir
    Then Sonuclarin yeni tarihlere göre guncellendigi dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra|
      | İstanbul               | 4             | 9            |

    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra | yeniGirisGunSonra | yeniCikisGunSonra |
      | İstanbul              | 5             | 10            | 7                 | 12                |

  @Müsaitlik
  Scenario Outline: Otel müsaitlik kontrolü
    When Secilen otelin müsaitlik durumu sorgulanir
    Then Müsaitlik bilgisinin dogru sekilde gosterildigi dogrulanir
    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 4             | 9             |

  @HaritadaGoster
  Scenario Outline: Otelin haritada gösterilmesi
    When Haritada göster secenegine tiklanir
    Then Otelin konumunun haritada dogru sekilde acildigi dogrulanir
    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 4             | 9             |


  @Favori
  Scenario Outline: Otelin favoriye eklenmesi
    When Kullanici oteli favorilere ekler
    Then Otelin favoriler listesine eklendigi dogrulanir
    Examples:
      | otelSehirBolgeTema    | girisGunSonra | cikisGunSonra |
      | İstanbul              | 4             | 9             |

  @ListeSecim
  Scenario Outline: Otel aramada listeden secim yapma
    When Kullanici listeden bir otel secer
    Then Secilen otelin detay sayfasinin acildigi dogrulanir
    Examples:
      | otelSehirBolgeTema     | girisGunSonra | cikisGunSonra |
      | İstanbul               | 4             | 9             |




