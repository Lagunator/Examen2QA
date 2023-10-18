package web.webTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.session.Session;
import web.webTest.TestBase;
import java.util.Random;

public class Ejer4 extends TestBase {
    private static Random rand = new Random();
    @BeforeAll
    public static void setup(){
        userRand = "lagu"+rand.nextInt(10000)+"@lagu.com";
    }
    static String userRand;
    @Test
    public void verifyEjercicio3Test() throws InterruptedException {
        mainPage.signUpButton.click();
        signUpPage.fullNameTextbox.setText(userRand);
        signUpPage.emailTextbox.setText(userRand);
        signUpPage.passwordTextbox.setText("lagugu");
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertTrue(centralSection.openSettingsButton.isControlDisplayed(), "ERROR usuario no creado!");

        menuSection.settingButton.click();
        settingsSection.accountButton.click();
        settingsSection.deleteAccountButton.click();
        Session.getSession().getBrowser().switchTo().alert().accept();

        Thread.sleep(3000);
        Assertions.assertTrue(mainPage.loginButton.isControlDisplayed(), "ERROR! usuario eliminado");

    }
}
