package com.example.project14.Match;


import android.util.Log;

import androidx.annotation.NonNull;
//TODO skill

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
    private String matchingScore;
    private String postalCode;
    private String street;
    private String houseNumber;
    private String city;
    private String country;
    private String role;
    private int userId;
    private String seekingCity;
    private String liveWith;
    private String budget;
    private String period;
    private String nights;
    private String pet;
    private String ownPet;
    private String ownPetDescription;
    private String starDate;
    private String endDate;
    private String reason;
    private String schoolFinished;
    private String schoolDoing;
    private String skill;
    private int work;
    private String workDescription;
    private String healthRisk;
    private String healthRiskDescription;
    private String selfDescription;
    private String selfWords;
    private String idealSpace;
    private String offer;
    private String offerYou;
    private String importantNote;
    private String volunteer;
    private String volunteerDescription;
    private String religion;
    private String comment;
    private String overallcomment;
    private String situation;
    private String house;
    private String found;
    private String motivation;
    private Picture housePicture;
    private String roomSize;
    private String roomType;
    private String furniture;
    private String furnitureDescription;
    private String price;
    private String describe;
    private String hobby;
    private String petDescription;


    public Match(int id, String matchingScore, String emailAddress, String password, String dateOfBirth, String firstName, String middleName, String lastName, Picture picture, String gender, String phoneNumber, String postalCode, String street, String houseNumber, String city, String country, String role, int userId, String seekingCity, String liveWith, String budget, String period, String nights, String pet, String ownPet, String ownPetDescription, String starDate, String endDate, String reason, String schoolFinished, String schoolDoing, String skill, int work, String workDescription, String healthRisk, String healthRiskDescription, String selfDescription, String selfWords, String idealSpace, String offer, String offerYou, String importantNote, String volunteer, String volunteerDescription, String religion, String comment, String overallcomment, String situation, String house, String found, String motivation, Picture housePicture, String roomSize, String roomType, String furniture, String furnitureDescription, String price, String describe, String hobby, String petDescription) {
        this.id = id;
        this.matchingScore = matchingScore;
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
        this.userId = userId;
        this.seekingCity = seekingCity;
        this.liveWith = liveWith;
        this.budget = budget;
        this.period = period;
        this.nights = nights;
        this.pet = pet;
        this.ownPet = ownPet;
        this.ownPetDescription = ownPetDescription;
        this.starDate = starDate;
        this.endDate = endDate;
        this.reason = reason;
        this.schoolFinished = schoolFinished;
        this.schoolDoing = schoolDoing;
        this.skill = skill;
        this.work = work;
        this.workDescription = workDescription;
        this.healthRisk = healthRisk;
        this.healthRiskDescription = healthRiskDescription;
        this.selfDescription = selfDescription;
        this.selfWords = selfWords;
        this.idealSpace = idealSpace;
        this.offer = offer;
        this.offerYou = offerYou;
        this.importantNote = importantNote;
        this.volunteer = volunteer;
        this.volunteerDescription = volunteerDescription;
        this.religion = religion;
        this.comment = comment;
        this.overallcomment = overallcomment;
        this.situation = situation;
        this.house = house;
        this.found = found;
        this.motivation = motivation;
        this.housePicture = housePicture;
        this.roomSize = roomSize;
        this.roomType = roomType;
        this.furniture = furniture;
        this.furnitureDescription = furnitureDescription;
        this.price = price;
        this.describe = describe;
        this.hobby = hobby;
        this.petDescription = petDescription;
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

    public String getMatchingScore() {
        return matchingScore;
    }

    public void setMatchingScore(String matchingScore) {
        this.matchingScore = matchingScore;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSeekingCity() {
        return seekingCity;
    }

    public void setSeekingCity(String seekingCity) {
        this.seekingCity = seekingCity;
    }

    public String getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(String liveWith) {
        this.liveWith = liveWith;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getOwnPet() {
        return ownPet;
    }

    public void setOwnPet(String ownPet) {
        this.ownPet = ownPet;
    }

    public String getOwnPetDescription() {
        return ownPetDescription;
    }

    public void setOwnPetDescription(String ownPetDescription) {
        this.ownPetDescription = ownPetDescription;
    }

    public String getStarDate() {
        return starDate;
    }

    public void setStarDate(String starDate) {
        this.starDate = starDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSchoolFinished() {
        return schoolFinished;
    }

    public void setSchoolFinished(String schoolFinished) {
        this.schoolFinished = schoolFinished;
    }

    public String getSchoolDoing() {
        return schoolDoing;
    }

    public void setSchoolDoing(String schoolDoing) {
        this.schoolDoing = schoolDoing;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getHealthRisk() {
        return healthRisk;
    }

    public void setHealthRisk(String healthRisk) {
        this.healthRisk = healthRisk;
    }

    public String getHealthRiskDescription() {
        return healthRiskDescription;
    }

    public void setHealthRiskDescription(String healthRiskDescription) {
        this.healthRiskDescription = healthRiskDescription;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public String getSelfWords() {
        return selfWords;
    }

    public void setSelfWords(String selfWords) {
        this.selfWords = selfWords;
    }

    public String getIdealSpace() {
        return idealSpace;
    }

    public void setIdealSpace(String idealSpace) {
        this.idealSpace = idealSpace;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getOfferYou() {
        return offerYou;
    }

    public void setOfferYou(String offerYou) {
        this.offerYou = offerYou;
    }

    public String getImportantNote() {
        return importantNote;
    }

    public void setImportantNote(String importantNote) {
        this.importantNote = importantNote;
    }

    public String getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(String volunteer) {
        this.volunteer = volunteer;
    }

    public String getVolunteerDescription() {
        return volunteerDescription;
    }

    public void setVolunteerDescription(String volunteerDescription) {
        this.volunteerDescription = volunteerDescription;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOverallcomment() {
        return overallcomment;
    }

    public void setOverallcomment(String overallcomment) {
        this.overallcomment = overallcomment;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Picture getHousePicture() {
        return housePicture;
    }

    public void setHousePicture(Picture housePicture) {
        this.housePicture = housePicture;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
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


    @NonNull
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

