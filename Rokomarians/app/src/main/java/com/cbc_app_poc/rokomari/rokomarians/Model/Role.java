package com.cbc_app_poc.rokomari.rokomarians.Model;


import java.util.List;

public class Role {


    private User user;

    private List<String> role = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }



    public static class User {


        private String firstName;

        private String lastName;

        private String email;

        private String password;

        private String phone;

        private String imagePath;

        private String status;

        private UserPersonalInfo userPersonalInfo;

        private UserOfficeInfo userOfficeInfo;


        public User(String firstName, String lastName, String email, String password, String phone,
                    String imagePath, String status) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.imagePath = imagePath;
            this.status = status;
        }


        public User(String firstName, String lastName, String phone, String imagePath,
                    String status, UserPersonalInfo userPersonalInfo,
                    UserOfficeInfo userOfficeInfo) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.imagePath = imagePath;
            this.status = status;
            this.userPersonalInfo = userPersonalInfo;
            this.userOfficeInfo = userOfficeInfo;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UserPersonalInfo getUserPersonalInfo() {
            return userPersonalInfo;
        }

        public void setUserPersonalInfo(UserPersonalInfo userPersonalInfo) {
            this.userPersonalInfo = userPersonalInfo;
        }

        public UserOfficeInfo getUserOfficeInfo() {
            return userOfficeInfo;
        }

        public void setUserOfficeInfo(UserOfficeInfo userOfficeInfo) {
            this.userOfficeInfo = userOfficeInfo;
        }

    }


    public static class UserOfficeInfo {


        private String team;

        private String designation;

        private String work;

        private String joiningDate;


        public UserOfficeInfo(String team, String designation, String work, String joiningDate) {
            this.team = team;
            this.designation = designation;
            this.work = work;
            this.joiningDate = joiningDate;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getJoiningDate() {
            return joiningDate;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }

    }


    public static class UserPersonalInfo {


        private String homeTown;

        private String education;

        private String hobbies;

        private String address;


        public UserPersonalInfo(String homeTown, String education, String hobbies, String address) {
            this.homeTown = homeTown;
            this.education = education;
            this.hobbies = hobbies;
            this.address = address;
        }

        public String getHomeTown() {
            return homeTown;
        }

        public void setHomeTown(String homeTown) {
            this.homeTown = homeTown;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getHobbies() {
            return hobbies;
        }

        public void setHobbies(String hobbies) {
            this.hobbies = hobbies;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }



}
