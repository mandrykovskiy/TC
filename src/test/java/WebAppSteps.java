import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.SoftAsserts;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Listeners(SoftAsserts.class)
public class WebAppSteps {

    String dvForm = "//div[@class='react-grid-layout']/div";
    String dvHeaderTitle = ".//div[contains(@class,'Card_headerTitle')]";
    String dvRefreshBtn = ".//button[@title='Refresh']";
    String spinner = ".//div[@class='sk-circle-fade']";
    String errorMessage = "//div[@class='Toastify']//div[contains(.,'Unexpected error')]";

    public ElementsCollection getDVModulesOnDasboars() {
        ElementsCollection dvForms = $$x(dvForm);
        return dvForms;
    }

    public void getAllDVTitles() {
        ElementsCollection dvTitles = $$x(dvForm + dvHeaderTitle);
        dvTitles.forEach(t -> System.out.println(t.text()));
    }

    public String getDVTitle(SelenideElement dvForm) {
        return dvForm.find(By.xpath(dvHeaderTitle)).text();
    }

    public void clickDVRefreshBtn(SelenideElement dvForm) {
        dvForm.$x(dvRefreshBtn).click();
        System.out.println("click DVRefresh Btn");
    }

    @Step("refresh All DVOne By One")
    public void refreshAllDVOneByOne() {
        ElementsCollection dvForms = getDVModulesOnDasboars();
        dvForms.forEach(t -> {
            System.out.println("Select '" + getDVTitle(t) + "' Dv Module.");
            clickDVRefreshBtn(t);
        });
    }

    @Step("wait while dv downloaded")
    public void waitWhileDvDownloaded(SelenideElement dvForm) {
        if (dvForm.find(By.xpath(spinner)).isDisplayed())
            dvForm.find(By.xpath(spinner)).waitUntil(not(Condition.visible), 60000);
    }

    @Step("check Error on Screen")
    public void checkErrorScreen() {
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i <= 5; i++) {
            softAssert.assertEquals(false, $x(errorMessage).isDisplayed());
//            $x(errorMessage).should(not(Condition.visible));
        }
    }

    public void waitWhileDVFormPresenr() {
        $x(dvForm).waitUntil(Condition.visible, 10000);
    }
}
