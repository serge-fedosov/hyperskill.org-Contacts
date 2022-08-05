package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

abstract public class Contact implements Serializable {

    public String number;
    LocalDateTime timeCreated;
    LocalDateTime timeLastEdit;

    public Contact() {
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    public Contact(String number) {
        this.number = number;
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    abstract public void info();
    abstract public String[] getFields();
    abstract public String getField(String field);
    abstract public void setField(String field, String value);

    public void editField(String field) {
        Scanner scanner = new Scanner(System.in);

        String msg = switch (field) {
            case "number" -> "Enter number: ";
            case "name" -> "Enter name: ";
            case "surname" -> "Enter surname: ";
            case "birthDate" -> "Enter the birth date: ";
            case "gender" -> "Enter the gender (M, F): ";
            case "address" -> "Enter address: ";
            default -> "Error field";
        };

        System.out.print(msg);
        String value = scanner.nextLine();
        setField(field, value);

        //scanner.close();
    }

    public void add() {

        for (var field : getFields()) {
            editField(field);
        }
    }

    public void edit() {

        Scanner scanner = new Scanner(System.in);
        String fields = Arrays.toString(getFields());
        fields = fields.substring(1, fields.length() - 1);

        System.out.print("Select a field (" + fields + "): ");
        String field = scanner.nextLine().trim();

        editField(field);

        System.out.println("Saved");
        //scanner.close();
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (!checkNumber(number)) {
            this.number = "[no number]";
            System.out.println("Wrong number format!");
        } else {
            this.number = number;
        }
        this.timeLastEdit = LocalDateTime.now();
    }

    public boolean hasNumber() {
        return number != null && !number.isBlank() && !number.equals("[no number]");
    }

    public boolean checkNumber(String number) {
        String numberFormatFilter = "\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*";
        return Pattern.matches(numberFormatFilter, number);
    }

}
