package contacts;

import java.time.LocalDateTime;

public class Organization extends Contact {

    public String name;
    public String address;

    public Organization() {
        this.isPerson = false;
        this.timeCreated = LocalDateTime.now();
        this.timeLastEdit = LocalDateTime.now();
    }

    public Organization(String number, String name, String address) {
        super(number);
        this.name = name;
        this.address = address;
        this.isPerson = false;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.timeLastEdit = LocalDateTime.now();
    }

    public String toString() {
        return name;
    }

    @Override
    public void info() {
        System.out.println("Organization name: " + name +
                "\nAddress: " + address +
                "\nNumber: " + number +
                "\nTime created: " + timeCreated +
                "\nTime last edit: " + timeLastEdit);
    }

}
