package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {
    @Test(enabled = false)
    public void modifyUser() {
        if (app.db().users().size() == 0) {
            app.goToMainPage();
            Users before = (Users) app.db().users();
            UserData modifiedUser = before.iterator().next();
            UserData user = new UserData().withId(modifiedUser.getId()).withFirstName("Test").withLastName("Lalala").withAddress1("Test 4").withHomePhone("55545555").withEmailOne("test2@test.pl");
            app.user().modify(user);
            assertThat(app.group().count(), equalTo(before.size()));
            Users after = (Users) app.db().users();
            assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
            verifyUsersListInUI();
        }

    }


}