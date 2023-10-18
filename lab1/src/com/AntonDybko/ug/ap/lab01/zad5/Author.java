package com.AntonDybko.ug.ap.lab01.zad5;

public class Author {
    private String name;
    private String email;
    private Gender gender;
    public Author(){}
    public  Author(String n, String e, Gender g){
        name = n;
        setEmail(e);
        gender = g;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public Gender getGender(){
        return gender;
    }
    public void setEmail(String e){
        email = e;
    }
    @Override
    public String toString(){
        return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
    }
}
