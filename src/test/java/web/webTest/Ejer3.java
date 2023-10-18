package web.webTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import web.webTest.TestBase2;

import java.util.Random;
public class Ejer3 extends TestBase2 {
    private static Random rand = new Random();

    @Test
    public void createProject() throws InterruptedException{
        String nameUser = "Jhosias"+rand.nextInt(10000);
        mainTodoistPage.loginButton.click();
        loginPage.emailTextBox.setText("lagu@lagu.com");
        loginPage.passwordTextBox.setText("12345678tarea");
        loginPage.loginButton.click();

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        configurationSection.nameProfileText.clearSetText(nameUser);
        configurationSection.actualizarButton.click();
        configurationSection.cerrarConfigButton.click();
        Thread.sleep(3000);

        navBarSection.openInfoButton.click();
        navBarSection.openSettingsButton.click();
        configurationSection.nameProfileButton.click();
        Assertions.assertEquals(configurationSection.nameProfileButton.getTextByAttribute("defaultValue"), nameUser, "ERROR! El usuario no se actualizo");
        Thread.sleep(3000);

    }
}
