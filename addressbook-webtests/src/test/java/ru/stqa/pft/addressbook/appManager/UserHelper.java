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
        clear(By.name("firstname"));
        wd.findElement(By.name("firstname")).sendKeys(userData.getFirstname());
        clear(By.name("lastname"));
        wd.findElement(By.name("lastname")).sendKeys(userData.getLastname());
        clear(By.name("address"));
        wd.findElement(By.name("address")).sendKeys(userData.getAddress());
        clear(By.name("home"));
        wd.findElement(By.name("home")).sendKeys(userData.getHomePhone());
        click(By.name("email"));
        clear(By.name("email"));
        wd.findElement(By.name("email")).sendKeys(userData.getEmail());
    }

    public void submitUserForm() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.xpath("//img[@alt='Addressbook']"));
    }

    public void editUser() { click(By.xpath("//img[@alt='Edit']")); }

    public void deleteUser() { click(By.xpath("(//input[@name='update'])[2]")); }

    public void updateUser() { click(By.name("update")); }

    public void acceptAlert() { wd.switchTo().alert().accept();; }

}
