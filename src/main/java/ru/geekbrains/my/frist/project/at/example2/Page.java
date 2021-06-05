package ru.geekbrains.my.frist.project.at.example2;


import lombok.Data;

@Data
public class Page {
    private final Navigation navigation = new Navigation();
    private final Header header = new Header();
}
