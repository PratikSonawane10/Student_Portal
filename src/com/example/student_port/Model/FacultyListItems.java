package com.example.student_port.Model;

public class FacultyListItems {

    public String facultyName;

    public FacultyListItems() {
    }

    public FacultyListItems(String studentName) {

        this.facultyName = studentName;
    }

    public String getFacultyName() {
        return facultyName;
    }
    public void setFacultyName(String name) {
        this.facultyName = name;
    }

}
