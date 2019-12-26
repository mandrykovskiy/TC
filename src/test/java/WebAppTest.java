import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class WebAppTest {

    private static final String urlDev3 = "https://dev3.layer-one.com/web/";

    @Test
    public void ciTest() {
        open("https://www.google.com/");
        $x("//input[@class='gLFyf gsfi']").sendKeys("teamcity");
        System.out.println("Test finished successfully");
    }

}
