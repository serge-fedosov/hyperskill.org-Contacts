package contacts;

public class Main {
    public static void main(String[] args) {

        String fileName = null;
        if (args.length > 0) {
            fileName = args[0];
        }

        //fileName = "test.data";

        Contacts contacts = new Contacts();
        contacts.init(fileName);
        contacts.menu();
    }
}
