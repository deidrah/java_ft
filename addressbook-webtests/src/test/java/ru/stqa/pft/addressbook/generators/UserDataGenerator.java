package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {
    @Parameter(names = "-c", description = "User count")
    public int count;

    @Parameter(names = "-f", description = "Target File")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("csv")) {
            saveAsCSV(users, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(users, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(users, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJSON(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(file);) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<UserData> users, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(UserData.class);
        String xml = xStream.toXML(users);
        try (Writer writer = new FileWriter(file);) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<UserData> users, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (UserData user : users) {
                writer.write(String.format("%s;%s;%s;%s;%s;\n",
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmailOne(),
                        user.getGroup(),
                        user.getMobilePhone()
                ));
            }
        }
    }

    private List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData()
                    .withFirstName(String.format("Test %s", i))
                    .withLastName(String.format("Testowy %s", i))
                    .withEmailOne(String.format("test@test.pl%s", i))
                    .withGroup("test1")
                    .withMobilePhone("43434"));
        }
        return users;
    }
}
