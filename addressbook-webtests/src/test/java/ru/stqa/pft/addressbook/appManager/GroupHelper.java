package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
    public WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupForm() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillingGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void groupAddingInit() {
      wd.findElement(By.name("new")).click();
    }

    public void deleteGroup() {
      wd.findElement(By.name("delete")).click();
    }

    public void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }
}
