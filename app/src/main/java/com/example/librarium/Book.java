package com.example.librarium;

public class Book {

    private String name;
    private String authorName;
    private String genre;
    private String location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Book(String name, String author, String genre, String location){
        this.name = name;
        this.authorName = author;
        this.genre = genre;
        this.location = location;
    }
}
