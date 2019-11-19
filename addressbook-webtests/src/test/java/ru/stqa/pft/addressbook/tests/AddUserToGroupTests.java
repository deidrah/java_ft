package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().users().size() == 0) {
            app.goTo().gotoHomePage();
            app.user().createUser(new UserData()
                    .withFirstName("Test")
                    .withLastName("Testowy"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test23232'"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().gotoHomePage();
        UserData addedContact = app.db().users().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        Users before = group.getUsers();
        app.user().addToGroup(addedContact, group);
        GroupData groupOne = app.db().groups().iterator().next();
        Users after = groupOne.getUsers();
        assertThat(after, equalTo(before.withAdded(addedContact)));
    }

}
