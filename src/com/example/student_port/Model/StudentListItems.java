package com.example.student_port.Model;

public class StudentListItems {

    public String studentName;
    public String studentSemister;

    public StudentListItems() {
    }

    public StudentListItems(String studentName, String studentSemister) {

        this.studentName = studentName;
        this.studentSemister = studentSemister;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getStudentSemister() {
        return studentSemister;
    }
    public void setStudentSemister(String Semister) {
        this.studentSemister = Semister;
    }

}
