package com.playwright.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelPage {
    String firstName, lastName;

    void printNames(){
        System.out.println(getFirstName() + getLastName());
    }
}
