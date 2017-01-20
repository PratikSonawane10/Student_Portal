package com.example.student_port.Model;

public class BookListItems {

    public String bookName;
    public String bookSemister;
    public String bookPath;

    public BookListItems() {
    }

    public BookListItems(String nameOfBook, String studentSemister, String path) {

        this.bookName = nameOfBook;
        this.bookSemister = studentSemister;
        this.bookPath = path;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String name) {
        this.bookName = name;
    }

    public String getBookSemister() {
        return bookSemister;
    }
    public void setBookSemister(String Semister) {
        this.bookSemister = Semister;
    }

    public String getBookPath() {
        return bookPath;
    }
    public void setBookPath(String path) {
        this.bookPath = path;
    }
}
