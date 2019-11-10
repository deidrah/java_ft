package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appManager.TestBase;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFromCSVFile() throws IOException {
    List<Object[]> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")))) {
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new UserData()
                    .withFirstName(split[0])
                    .withLastName(split[1])
                    .withEmailOne(split[2])
                    .withGroup(split[3])
                    .withMobilePhone(split[4])});
            line = reader.readLine();
        }
        return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validUsersFromXMLFile() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")))) {
          StringBuilder xml = new StringBuilder();
          String line = reader.readLine();
          while (line != null) {
              xml.append(line);
              line = reader.readLine();
          }
          XStream xStream = new XStream();
          xStream.processAnnotations(UserData.class);
          List<UserData> users = (List<UserData>) xStream.fromXML(xml.toString());
          return users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
  }

  @DataProvider
  public Iterator<Object[]> validUsersFromJSONFile() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")))) {
          StringBuilder json = new StringBuilder();
          String line = reader.readLine();
          while (line != null) {
              json.append(line);
              line = reader.readLine();
          }
          Gson gson = new Gson();
          List<UserData> users = gson.fromJson(json.toString(), new TypeToken<List<UserData>>() {
          }.getType());  // List<GroupData>.class
          return users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
  }

  @Test(dataProvider = "validUsersFromJSONFile")
  public void testAddUser() throws Exception {
    app.goToMainPage();
    Users before = (Users) app.user().all();
    File photo = new File("src/test/resources/stru.png");
    UserData user = new UserData().withFirstName("Test1").withLastName("Test3").withPhoto(photo);
    app.user().createUser(user);
    Users after = (Users) app.user().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }
  /*
  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
  }
   */

}
