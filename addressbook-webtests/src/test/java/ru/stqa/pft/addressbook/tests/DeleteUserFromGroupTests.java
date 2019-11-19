package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.testng.Assert.assertTrue;

public class DeleteUserFromGroupTests extends TestBase {
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
                    .withName("Test434343'"));
        }
        if (app.db().usersInGroup().size()==0){
            app.goTo().gotoHomePage();
            app.user().addToGroup(app.db().users().iterator().next(), app.db().groups().iterator().next());
        };
    }

    @Test
    public void testDeleteContactFromGroup() {
        app.goTo().gotoHomePage();
        Users before = app.db().usersInGroup();
        UserData deletedUser= before.iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.user().deleteFromGroup(deletedUser, group);
        Users after = app.db().users();
        assertTrue(after.iterator().next().getGroups().isEmpty());

    }
}
