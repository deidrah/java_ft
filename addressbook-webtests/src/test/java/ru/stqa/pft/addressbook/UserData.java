package ru.stqa.pft.addressbook;

public class UserData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String homePhone;
    private final String email;

    public UserData(String firstname, String lastname, String address, String homePhone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.homePhone = homePhone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getEmail() {
        return email;
    }
}
