package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Person extends Contact {

    public String name;
    public String surname;
    public String birthDate;
    public String gender;

    public Person() {
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    public Person(String name, String surname, String number) {
        super(number);
        this.name = name;
        this.surname = surname;
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        if (!checkBirthDate(birthDate)) {
            this.birthDate = "[no data]";
            System.out.println("Bad birth date!");
        } else {
            this.birthDate = birthDate;
        }
        this.timeLastEdit = LocalDateTime.now();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!checkGender(gender)) {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
        } else {
            this.gender = gender;
        }
        this.timeLastEdit = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public boolean checkBirthDate(String birthDate) {
        return !birthDate.isBlank();
    }

    public boolean checkGender(String gender) {
        return gender.equals("M") || gender.equals("F");
    }

    @Override
    public void info() {
        System.out.println("Name: " + name +
                "\nSurname: " + surname +
                "\nBirth date: " + birthDate +
                "\nGender: " + gender +
                "\nNumber: " + number +
                "\nTime created: " + timeCreated +
                "\nTime last edit: " + timeLastEdit);
    }

    @Override
    public String[] getFields() {
        return new String[] {"name", "surname", "birthDate", "gender", "number"};
    }

    @Override
    public String getField(String field) {
        return switch (field) {
            case "name" -> getName();
            case "surname" -> getSurname();
            case "birthDate" -> getBirthDate();
            case "gender" -> getGender();
            case "number" -> getNumber();
            default -> null;
        };
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" -> setName(value);
            case "surname" -> setSurname(value);
            case "birthDate" -> setBirthDate(value);
            case "gender" -> setGender(value);
            case "number" -> setNumber(value);
            default -> System.out.println("Error field");
        }
    }

}
