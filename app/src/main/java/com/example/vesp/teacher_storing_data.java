package com.example.vesp;

public class teacher_storing_data {
    String email,username,post,course,password;

    public teacher_storing_data(String email, String username, String post, String course, String password) {
        this.email = email;
        this.username = username;
        this.post = post;
        this.course = course;
        this.password = password;
    }

    public teacher_storing_data() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
