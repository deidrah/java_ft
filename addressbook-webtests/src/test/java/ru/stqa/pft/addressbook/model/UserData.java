package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name="addressbook")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name="firstname")
    private String firstname;
    @Expose
    @Column(name="lastname")
    private String lastname;
    @Expose
    @Column(name="address")
    @Type(type="text")
    private String address1;
    @Expose
    @Column(name="address2")
    @Type(type="text")
    private String address2;
    @Transient
    private String allAddresses;
    @Column(name="home")
    @Type(type="text")
    private String homePhone;
    @Expose
    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;



    @Column(name="work")
    @Type(type="text")
    private String workPhone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();
    @Transient
    private String allPhones;
    @Expose
    @Column(name="email")
    @Type(type="text")
    private String emailOne;
    @Column(name="email2")
    @Type(type="text")
    private String emailTwo;
    @Column(name="email3")
    @Type(type="text")
    private String emailThree;
    @Transient
    private String allEmails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(firstname, userData.firstname) &&
                Objects.equals(lastname, userData.lastname) &&
                Objects.equals(address1, userData.address1) &&
                Objects.equals(address2, userData.address2) &&
                Objects.equals(homePhone, userData.homePhone) &&
                Objects.equals(mobilePhone, userData.mobilePhone) &&
                Objects.equals(workPhone, userData.workPhone) &&
                Objects.equals(emailOne, userData.emailOne) &&
                Objects.equals(emailTwo, userData.emailTwo) &&
                Objects.equals(emailThree, userData.emailThree) &&
                Objects.equals(photo, userData.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address1, address2, homePhone, mobilePhone, workPhone, emailOne, emailTwo, emailThree, photo);
    }

    @Column(name = "photo")
    @Type(type="text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withAllAddresses(String allAddresses) {
        this.allAddresses = allAddresses;
        return this;
    }

    public UserData withFirstName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public UserData withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public UserData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public UserData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public UserData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public UserData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public UserData withEmailOne(String emailOne) {
        this.emailOne = emailOne;
        return this;
    }

    public UserData withEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
        return this;
    }

    public UserData withEmailThree(String emailThree) {
        this.emailThree = emailThree;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }



    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public String getEmailThree() {
        return emailThree;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllAddresses() {
        return allAddresses;
    }



    public String getAllPhones() {
        return allPhones;
    }

    public UserData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}



