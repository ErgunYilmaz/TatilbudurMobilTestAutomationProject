package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.hotelListPage;
import utilities.ReusableMethods;

public class HotelListStepDef {
    ReusableMethods reusableMethods=new ReusableMethods();
    hotelListPage hotelListPage =new hotelListPage();
    @Given("Listeden gelen ilk otele tiklanir")
    public void listeden_gelen_ilk_otele_tiklanir() {
        reusableMethods.bekleTiklanabilir(hotelListPage.hotelCardZero);
        reusableMethods.bekle(1);
    }

}
