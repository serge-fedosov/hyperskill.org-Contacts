package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

abstract public class Contact implements Serializable {

    public String number;
    boolean isPerson;
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
