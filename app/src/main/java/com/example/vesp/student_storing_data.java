package com.example.vesp;

public class student_storing_data {
    String email,uname,ped;

    public student_storing_data(String email, String uname, String ped) {
        this.email = email;
        this.uname = uname;
        this.ped = ped;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPed() {
        return ped;
    }

    public void setPed(String ped) {
        this.ped = ped;
    }
}
