package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewUser() {
        click(By.linkText("add new"));
    }

    public void fillUserForm(UserData userData, boolean creation) {
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
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitUserForm() {
        click(By.name("submit"));
    }

    public void goToHomePage() {
        click(By.xpath("//img[@alt='Addressbook']"));
    }

    public void editUser(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteUser() { click(By.xpath("(//input[@value='Delete'])")); }

    public void updateUser() { click(By.name("update")); }

    public void acceptAlert() { wd.switchTo().alert().accept();; }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createUser(UserData user) {
        addNewUser();
        fillUserForm(user, true);
        submitUserForm();
        userCache = null;
        goToHomePage();
    }

    public void modify(UserData user) {
        editUser(user.getId());
        fillUserForm(user, false);
        updateUser();
        userCache = null;
        goToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteUser();
        acceptAlert();
        userCache = null;
        goToHomePage();
    }

    private Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            userCache.add(new UserData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return new Users(userCache);
    }
}
