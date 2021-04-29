package com.utkarsh.practice;

public class User {

    public String userName, userAge, userEmail, userID;


    public User() { }

    public User(String Name, String Age, String email){
        this.userName = Name;
        this.userAge = Age;
        this.userEmail = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getUserAge() {
        return userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }
}
