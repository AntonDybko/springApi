package com.AntonDybko.ug.ap.lab01.zad5;

public class Book {
    private String name;
    private Author author;
    private double price;
    private int qty;
    public Book(){}
    public  Book(String n, Author a, double p){
        name = n;
        author = a;
        setPrice(p);
        qty = 0;
    }
    public String getName(){
        return name;
    }
    public Author getAuthor(){
        return author;
    }
    public double getPrice(){
        return price;
    }
    public int getQty(){
        return qty;
    }
    public void setPrice(double p){
        price = p;
    }
    public void setQty(int q){
        qty = q;
    }
    @Override
    public String toString(){
        return "Author[name=" + name + ",author=" + author.toString() + ",price=" + price + ",qty=" + qty + "]";
    }
}
