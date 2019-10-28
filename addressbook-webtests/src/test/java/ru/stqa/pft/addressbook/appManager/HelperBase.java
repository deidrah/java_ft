package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.*;

public class HelperBase {

    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        if (isElementPresent(locator)) {
            wd.findElement(locator).click();
        }
    }

    protected void clear(By locator) {
        if (isElementPresent(locator)) {
            wd.findElement(locator).clear();
        }
    }

    protected void type(By locator, String text) {
        click(locator);

        if (isElementPresent(locator)) {
            wd.findElement(locator).clear();
            if (text != null) {
                wd.findElement(locator).sendKeys(text);
            }
        }
    }


    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
