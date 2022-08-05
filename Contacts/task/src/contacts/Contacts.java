package contacts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts {

    Scanner scanner = new Scanner(System.in);
    List<Contact> contacts = new ArrayList<>();
    String fileName;

    public Contacts() {
    }

    public void loadFile() {
        try {
            contacts = (ArrayList<Contact>) SerializationUtils.deserialize(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            SerializationUtils.serialize(contacts, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if (fileName != null) {
            try {
                SerializationUtils.serialize(contacts, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void init(String fileName) {
        this.fileName = fileName;

        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
                // loading data
                loadFile();
            } else {
                // save the empty list to a new file
                saveFile();
            }
        }
    }

    public void count() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void add() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        if (type.equals("person")) {
            Contact person = new Person();
            person.add();
            contacts.add(person);
        } else {
            Contact organization = new Organization();
            organization.add();
            contacts.add(organization);
        }

        System.out.println("The record added.");
        save();
    }

    public void delete(int index) {
        if (contacts.size() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        contacts.remove(index - 1);
        System.out.println("The record removed!");
        save();
    }

    public void edit() {
        if (contacts.size() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        printList();

        System.out.print("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());
        contacts.get(record - 1).edit();
        //System.out.println("The record updated!");
        save();
    }

    public void list() {
        if (contacts.size() == 0) {
            System.out.println("No records to show!");
            return;
        }

        printList();

        String action = null;
        do {

            System.out.print("\n[list] Enter action ([number], back): ");
            action = scanner.nextLine();

            if (!action.equals("back")) {
                int index = Integer.parseInt(action);
                contacts.get(index - 1).info();
                record(index);
                action = "back";
            }

        } while(!action.equals("back"));
    }

    public void record(int index) {

        String action = null;
        do {

            System.out.print("\n[record] Enter action (edit, delete, menu): ");
            action = scanner.nextLine();
            switch (action) {
                case "edit" -> contacts.get(index - 1).edit();
                case "delete" -> delete(index);
                //case "menu" -> menu();
                //default -> System.out.println("Error action");
            }

        } while(!action.equals("menu"));

    }

    public void printList() {
        int c = 1;
        for (var contact : contacts) {
            System.out.println(Integer.toString(c) + ". " +  contact);
            c++;
        }
    }

    public void search() {

        HashMap<Integer, Integer> search = new HashMap<>();
        String action = "again";
        do {

            String query = null;
            if (action.equals("again")) {

                System.out.print("Enter search query: ");
                query = scanner.nextLine();


            } else {
                int index = Integer.parseInt(action);
                contacts.get(search.get(index - 1)).info(); //!!!!!
                record(index);
                action = "back";
                return;
            }



            search.clear();
            Pattern javaPattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
            int c = 0;
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);

                boolean addContact = false;
                for (var field : contact.getFields()) {
                    Matcher matcher = javaPattern.matcher(contact.getField(field));
                    if (matcher.find()) {
                        addContact = true;
                    }
                }

                if (addContact) {
                    search.put(c, i);
                    c++;
                }
            }

            System.out.println("Found " + c + " results:");
            int c1 = 1;
            for (int i = 0; i < search.size(); i++) {
                Contact contact = contacts.get(search.get(i));
                System.out.println(Integer.toString(c1) + ". " +  contact);
                c1++;
            }

            System.out.print("\n[search] Enter action ([number], back, again): ");
            action = scanner.nextLine();

        } while(!action.equals("back"));

    }

    public void menu() {

        String action = null;
        do {

            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            action = scanner.nextLine();

            switch (action) {
                case "add":
                    add();
                    break;
                case "list":
                    list();
                    break;
                case "search":
                    search();
                    break;
                case "count":
                    count();
                    break;
                default:
            }

        } while (!action.equals("exit"));

        System.exit(0);
    }
}
