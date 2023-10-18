package web.webTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import web.pages.todoist.*;
import web.session.Session;
import web.pages.todoLy.CentralSection;

public class TestBase2 {


    protected MainTodoistPage mainTodoistPage = new MainTodoistPage();
    protected LoginPage loginPage = new LoginPage();

    protected AddProjectPopUp addProjectPopUp = new AddProjectPopUp();

    protected LateralProjectSection lateralProjectSection = new LateralProjectSection();

    protected CentralProjectSection centralProjectSection = new CentralProjectSection();

    protected EditProjectPopUp editProjectPopUp = new EditProjectPopUp();

    protected NavBarSection navBarSection = new NavBarSection();

    protected ConfigurationSection configurationSection = new ConfigurationSection();

    @BeforeEach
    public void openBroswer() {
        Session.getSession().goTo("https://todoist.com/");
    }

    @AfterEach
    public void closeBroswer() {

        Session.getSession().closeBrowser();

    }

}
