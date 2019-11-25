package com.hrd.homework005;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishYear;
    private double price;
    private String category;
    private int amount;

    public Book(){
        this.id = 0;
    }

    public Book(String title, String author, String category, int publishYear, double price, int amount) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
