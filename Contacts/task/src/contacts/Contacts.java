package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacts {

    Scanner scanner = new Scanner(System.in);
    List<Contact> contacts = new ArrayList<>();

    public Contacts() {
    }

    public void count() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        if (type.equals("person")) {
            Person person = new Person();

            System.out.print("Enter the name: ");
            person.setName(scanner.nextLine());

            System.out.print("Enter the surname: ");
            person.setSurname(scanner.nextLine());

            System.out.print("Enter the birth date: ");
            person.setBirthDate(scanner.nextLine());

            System.out.print("Enter the gender (M, F): ");
            person.setGender(scanner.nextLine());

            System.out.print("Enter the number: ");
            person.setNumber(scanner.nextLine());

            contacts.add(person);
        } else {
            Organization organization = new Organization();

            System.out.print("Enter the organization name: ");
            organization.setName(scanner.nextLine());

            System.out.print("Enter the address: ");
            organization.setAddress(scanner.nextLine());

            System.out.print("Enter the number: ");
            organization.setNumber(scanner.nextLine());

            contacts.add(organization);
        }

        System.out.println("The record added.");
    }

    public void remove() {
        if (contacts.size() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        list();

        System.out.print("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());

        contacts.remove(record - 1);
        System.out.println("The record removed!");
    }

    public void edit() {
        if (contacts.size() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        list();

        System.out.print("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());

        if (contacts.get(record - 1).isPerson) {

            System.out.print("Select a field (name, surname, birth, gender, number): ");
            String field = scanner.nextLine().trim();

            Person person = (Person) contacts.get(record - 1);

            if (field.equals("name")) {
                System.out.print("Enter name: ");
                person.setName(scanner.nextLine());
            } else if (field.equals("surname")) {
                System.out.print("Enter surname: ");
                person.setSurname(scanner.nextLine());
            } else if (field.equals("birth")) {
                System.out.print("Enter the birth date: ");
                person.setBirthDate(scanner.nextLine());
            } else if (field.equals("gender")) {
                System.out.print("Enter the gender (M, F): ");
                person.setGender(scanner.nextLine());
            } else if (field.equals("number")) {
                System.out.print("Enter number: ");
                person.setNumber(scanner.nextLine());
            }

            contacts.set(record - 1, person);

        } else {

            System.out.print("Select a field (address, number): ");
            String field = scanner.nextLine().trim();

            Organization organization = (Organization) contacts.get(record - 1);

            if (field.equals("address")) {
                System.out.print("Enter address: ");
                organization.setAddress(scanner.nextLine());
            } else if (field.equals("number")) {
                System.out.print("Enter number: ");
                organization.setNumber(scanner.nextLine());
            }

            contacts.set(record - 1, organization);
        }

        System.out.println("The record updated!");
    }

    public void info() {
        if (contacts.size() == 0) {
            System.out.println("No records to show!");
            return;
        }

        list();

        System.out.print("Enter index to show info: ");
        int index = Integer.parseInt(scanner.nextLine());

        contacts.get(index - 1).info();
    }

    public void list() {
        int c = 1;
        for (var contact : contacts) {
            System.out.println(Integer.toString(c) + ". " +  contact);
            c++;
        }
    }

    public void menu() {

        String action = null;
        do {

            System.out.print("\nEnter action (add, remove, edit, count, info, exit): ");
            action = scanner.nextLine();

            switch (action) {
                case "count":
                    count();
                    break;
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "edit":
                    edit();
                    break;
                case "info":
                    info();
                    break;
                default:
            }

        } while (!action.equals("exit"));

    }
}
