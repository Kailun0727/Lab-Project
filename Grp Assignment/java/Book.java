package com.example.libraryapp;

public class Book {
    //Member variables representing the title and information about the sport
    private String id;
    private String title;
    private String author;
    private final int imageResource;
    private String numberCopies;

    /**
     * Constructor for the Sport data model
     * @param title The name of the book.
     * @param author The name of the author.
     */
    Book (String id,String title, String author,int imageResource, String numberCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageResource = imageResource;
        this.numberCopies = numberCopies;
    }

    /**
     * Gets the title of the sport
     * @return The title of the sport.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the info about the sport
     * @return The info about the sport.
     */
    public String  getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getNumberCopies() {
        return numberCopies;
    }

    public int getImageResource() {
        return imageResource;
    }
}
