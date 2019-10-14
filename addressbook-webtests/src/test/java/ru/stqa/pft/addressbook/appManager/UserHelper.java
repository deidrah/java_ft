package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewUser() {
        click(By.linkText("add new"));
    }

    public void fillUserForm(UserData userData) {
        click(By.name("firstname"));
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(userData.getAddress());
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(userData.getHomePhone());
        click(By.name("email"));
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(userData.getEmail());
    }

    public void submitUserForm() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.xpath("//img[@alt='Addressbook']"));
    }

}
