package com.AntonDybko.ug.ap.lab01.zad5;

public class Main {
    public static void main(String[] args) {
        Author author = new Author("Anton", "a228@gmail.com", Gender.MALE);
        System.out.println(author.toString());

        Book book = new Book("JavaCourse", author, 228.99);
        book.setQty(17);
        System.out.println(book.toString());
    }
}