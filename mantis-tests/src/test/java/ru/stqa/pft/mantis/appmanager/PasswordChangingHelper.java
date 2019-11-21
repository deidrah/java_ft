package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordChangingHelper extends HelperBase {

    public PasswordChangingHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value = 'Login']"));
    }

    public void goToManagePage() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    }

    public void goToManageUsers() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void chooseUser() {
        wd.findElement(By.xpath("html/body/table[3]/tbody/tr[4]/td[1]/a")).click();
    }

    public String getUser() {
        return wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    }

    public String getEmail() {
        return wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    }

    public void resetPassword() {
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void goToResetPage(String link) {
        wd.get(link);
    }

    public void setNewPassword(String newPassword) {
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}