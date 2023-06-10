package com.example.project14.Match;


public class Match {
    private int id;
    private String emailAddress;
    private String password;
    private String dateOfBirth;
    private String firstName;
    private String middleName;
    private String lastName;
private Picture picture;
    private String gender;
    private String phoneNumber;
    private String postalCode;
    private String street;
    private String houseNumber;
    private String city;
    private String country;
    private String role;

    public Match(int id, String emailAddress, String password, String dateOfBirth, String firstName, String middleName, String lastName, Picture picture, String gender, String phoneNumber, String postalCode, String street, String houseNumber, String city, String country, String role) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.picture = picture;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
        this.role = role;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public class Picture {
        private String type;
        private byte[] data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }
    }

}

