package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.hotelListPage;
import utilities.ReusableMethods;

import java.util.List;

public class SortingStepDef {
    ReusableMethods reusableMethods=new ReusableMethods();
    hotelListPage hotelListPage =new hotelListPage();
    @When("Siralama butonuna tiklanir")
    public void siralama_butonuna_tiklanir() {
        reusableMethods.bekleTiklanabilir(hotelListPage.hotelListSortButton);
    }
    @When("Siralama kriteri olarak {string} secilir")
    public void siralama_kriteri_olarak_secilir(String siralamaFiltresi) {
        reusableMethods.bekleTiklanabilir(hotelListPage.getSiralamaFiltresi(siralamaFiltresi));
    }
    @When("Sonucu Goster butonuna tiklanir")
    public void sonucu_goster_butonuna_tiklanir() {
        reusableMethods.bekleTiklanabilir(hotelListPage.sortApplyButton);
    }
    @Then("Sonuclarin {string} gore listelendigi dogrulanir")
    public void sonuclarin_gore_listelendigi_dogrulanir(String siralamaFiltresi) {
        List<Double> sayilar = hotelListPage.getSiralamaDegerleriDouble(siralamaFiltresi);

        if (sayilar.size() < 2) {
            throw new AssertionError("Karşılaştırma yapmak için yeterli veri yok: " + sayilar);
        }

        boolean artanMi = siralamaFiltresi.contains("Artan");

        for (int i = 0; i < sayilar.size() - 1; i++) {
            double mevcut = sayilar.get(i);
            double sonraki = sayilar.get(i + 1);

            if (artanMi && mevcut > sonraki) {
                throw new AssertionError("Liste artan sırada değil: " + sayilar);
            } else if (!artanMi && mevcut < sonraki) {
                throw new AssertionError("Liste azalan sırada değil: " + sayilar);
            }
        }

        System.out.println(siralamaFiltresi + " filtresine göre liste doğru sıralanmış: " + sayilar);
    }

}
