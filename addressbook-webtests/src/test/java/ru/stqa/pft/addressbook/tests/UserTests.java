package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTests extends TestBase {

    @BeforeTest
    public void testUserInit() {
        app.goTo().gotoHomePage();
    }

    @Test
    public void testUserPhones() {
        UserData user = app.user().all().iterator().next();
        UserData contactInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    @Test
    public void testUserMails() {
        UserData user = app.user().all().iterator().next();
        UserData contactInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    @Test
    public void testUserAddresses() {
        UserData user = app.user().all().iterator().next();
        UserData contactInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));

    }

    private String mergeAddresses(UserData user) {
        return Arrays.asList(user.getAddressOne(), user.getAddressTwo())
                .stream().filter((s) -> ! s.equals(""))
                .map(UserTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(UserData user) {
        return Arrays.asList(user.getEmailOne(), user.getEmailTwo(), user.getEmailThree())
                .stream().filter((s) -> ! s.equals(""))
                .map(UserTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(UserData user) {
        return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(UserTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
