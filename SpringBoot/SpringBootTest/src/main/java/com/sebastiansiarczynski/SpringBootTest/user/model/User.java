package com.sebastiansiarczynski.SpringBootTest.user.model;

import java.util.List;

public record User(int id, String name, String lastName, int age, List<String> hobby,
                   int phoneNumber, Habitation habitation) {

}
