package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTests extends TestBase {

    @BeforeMethod
    public void testUserInit() {
        app.goTo().gotoHomePage();
        if (app.user().all().size() == 0) {
            app.user().createUser(new UserData()
                    .withFirstName("Test1")
                    .withLastName("Test4"));
        }
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

    @Test
    public void testUserDetails() {
        UserData user = app.user().all().iterator().next();
        UserData contactFromEditForm = app.user().infoFromEditForm(user);
        List<String> contactInfoFromDetailForm = app.user().infoFromDetailForm(user);
        List<String> contactInfoFromEditForm = mergeData(contactFromEditForm);
        assertThat(contactInfoFromDetailForm, equalTo(contactInfoFromEditForm));
    }

    private List<String> mergeData(UserData user) {
        String fullName = user.getFirstname() + " " + user.getLastname();
        String[] address = user.getAddress1().split("\n");
        String homePhone = (user.getHomePhone().isEmpty()) ? "" : "H: " + user.getHomePhone();
        String mobilePhone = (user.getMobilePhone().isEmpty()) ? "" : "M: " + user.getMobilePhone();
        String workPhone = (user.getWorkPhone().isEmpty())   ? "" : "W: " + user.getWorkPhone();
        String emailOne = user.getEmailOne();
        String emailTwo = user.getEmailTwo();
        String emailThree = user.getEmailThree();
        List<String> details = new ArrayList<>();
        details.add(fullName);
        details.addAll((Arrays.asList(address)));
        details.addAll(Arrays.asList(homePhone, mobilePhone, workPhone, emailOne, emailTwo, emailThree));
        return details.stream().map(UserTests::cleaned).filter(s -> ! s.equals("")).collect(Collectors.toList());
    }

    private String mergeAddresses(UserData user) {
        return Arrays.asList(user.getAddress1(), user.getAddress2())
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

    public static String cleaned(String text) {
        return text
                .replaceAll("of", "")
                .replaceAll("^\\s+|\\s+$", "")
                .replaceAll(" +\\n", "\n")
                .replaceAll("\\n +", "\n")
                .replaceAll("\\s{2,}", " ");

    }
}
