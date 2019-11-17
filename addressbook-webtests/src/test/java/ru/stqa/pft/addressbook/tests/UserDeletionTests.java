package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goToMainPage();
        if (app.db().users().size() == 0) {
            app.user().createUser(new UserData().withFirstName("Test1").withLastName("Test3"));
        }
    }

    @Test(enabled = false)
    public void deleteUser() {
        Users before = (Users) app.db().users();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        Users after = (Users) app.db().users();
        assertEquals(after.size(), before.size() - 1);

        before.remove(deletedUser);
        assertEquals(before, after);
        assertThat(after, equalTo(before.without(deletedUser)));
    }

}
