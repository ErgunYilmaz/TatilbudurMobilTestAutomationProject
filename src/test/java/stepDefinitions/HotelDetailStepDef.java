package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.hotelDetailPage;
import utilities.ReusableMethods;

public class HotelDetailStepDef {

    hotelDetailPage hotelDetailPage = new hotelDetailPage();
    ReusableMethods reusableMethods=new ReusableMethods();
    @Given("Ilk gorunen Rezervasyon Yap butonuna tiklanir")
    public void ilk_gorunen_rezervasyon_yap_butonuna_tiklanir() {
        ReusableMethods.scrollWithUiScrollable("Rezervasyon Yap");
        reusableMethods.bekleTiklanabilir(hotelDetailPage.rezervasyonYapButonu);
    }
}