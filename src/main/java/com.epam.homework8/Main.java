package com.epam.homework8;

import com.epam.homework8.exception.EmptyArrayRuntimeException;
import com.epam.homework8.menu.Menu;


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        try {
            Menu menu = new Menu();
            menu.menuUser();
        } catch (NullPointerException | EmptyArrayRuntimeException e) {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        }
    }
}