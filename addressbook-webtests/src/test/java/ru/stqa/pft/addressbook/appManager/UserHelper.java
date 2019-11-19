package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void addNewUser() {
        click(By.linkText("add new"));
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        attach(By.name("photo"), userData.getPhoto());
        if (creation) {
            if (userData.getGroups().size() > 0) {
                Assert.assertTrue(userData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getName());
            }
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

    public void editUser(int id)  {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteUser() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void updateUser() {
        click(By.name("update"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
        ;
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createUser(UserData user) {
        addNewUser();
        fillUserForm(user, true);
        submitUserForm();
        goToHomePage();
    }

    public void modify(UserData user) {
        editUser(user.getId());
        fillUserForm(user, false);
        updateUser();
        goToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteUser();
        acceptAlert();
        goToHomePage();
    }

    public void addToGroup(UserData addedContact, GroupData group) {
        selectUserById(addedContact.getId());
        chooseGroupToAddById(group.getId());
        addContactToGroup();
    }

    private void addContactToGroup() {
        click(By.name("add"));
    }

    public void deleteFromGroup(UserData deletedContact, GroupData groupBefore) {
        chooseGroupToDeleteById(groupBefore.getId());
        selectUserById(deletedContact.getId());
        deleteContactFromGroup();
    }

    private void deleteContactFromGroup() {
        click(By.name("remove"));
    }

    private void chooseGroupToDeleteById(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    private void chooseGroupToAddById(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }




    public Set<UserData> all() {
        Set<UserData> users = new HashSet<UserData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allAddresses= cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            users.add(new UserData().withId(id).withFirstName(firstName).withLastName(lastName).withAllAddresses(allAddresses).withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return users;
    }

    public UserData infoFromEditForm(UserData user) {
        initUserModificationById(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String emailOne = wd.findElement(By.name("email")).getAttribute("value");
        String emailTwo = wd.findElement(By.name("email2")).getAttribute("value");
        String emailThree = wd.findElement(By.name("email3")).getAttribute("value");
        String address1 = wd.findElement(By.name("address")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withAddress1(address1).withAddress2(address2).withEmailOne(emailOne).withEmailTwo(emailTwo).withEmailThree(emailThree);
    }

    public List<String> infoFromDetailForm(UserData user) {
        initUserDetailById(user.getId());
        List<String> result = Arrays
                .asList(wd.findElement(By.xpath("//div[@id='content']")).getText()
                .replaceAll("Member of: [a-z0-9]+", "")
                .split("\n")).stream().filter(s -> ! s.equals("")).collect(Collectors.toList());
        wd.navigate().back();
        return result;
    }

    private void initUserDetailById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s",id))).click();
    }

    private void initUserModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}
