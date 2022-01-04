import java.util.Scanner;

public class Main {
    public static final int INITIALIZE = 0;

    public static void main(String[] args) {
        /*User user = new User("Nachshon99", "a123$", "0541234567", true);
        User user1 = new User("nachshon", "a1$", "0545492958", false);

*/
        /*Address address1 = new Address("Ashkelon", "Emek Heffer");
        Address address2 = new Address("Ashdod", "Keren Hayesod");
        Address address3 = new Address("Ashdod", "Herzel");
        Address address4 = new Address("Tel Aviv", "Hashlom");
        Address address5 = new Address("Ashdod", "Bnei Brit");
        Address address6 = new Address("Ashdod", "adsdass");
        Address address7 = new Address("Netivot", "adsdass");
        Address address8 = new Address("Ashdod", "adsdass");
        Address address9 = new Address("Netivot", "adsdass");
        Address address10 = new Address("Eilat", "adsdass");

        Address[]addresses = {address1,address2,address3,address4,address5,address6,address7,address8,address9,address10};
*/
       /* Property property = new Property(address1, 4,1350000, "Private apartment", true, 8,10,user);
        Property property1 = new Property(address2, 5,1500000, "Regular apartment", true, 5,5,user);
        Property property2 = new Property(address3, 4,1350000, "Private apartment", true, 1,1,user);
        Property property3 = new Property(address4, 3,1350000, "Penthouse apartment", true, 2,1,user);
        Property property4 = new Property(address5, 4,1350000, "Private apartment", false, 6,1,user1);
        Property property5 = new Property(address6, 2,350000, "Private apartment", true, 2,1,user);
        Property property6 = new Property(address7, 4,900000, "Private apartment", true, 2,1,user);
        Property property7 = new Property(address8, 4,1050000, "Private apartment", true, 2,1,user);
        Property property8 = new Property(address9, 4,1350000, "Private apartment", false, 2,1,user1);
        Property property9 = new Property(address10, 4,1350000, "Private apartment", false, 2,1,user1);
        Property[]properties = {property,property1,property2,property3,property4,property5,property6,property7,property8, property9};*/

        RealEstate realEstate = new RealEstate();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            do {
                printMenu();
                System.out.println("What do you want to do?:");
                option = scanner.nextInt();
            }while (option < 1 || option > 3);
            switch (option){
                case 1:{
                    realEstate.createUser();
                    break;
                }
                case 2:{
                    User client;
                    do {
                        client = realEstate.login();
                        if (client == null) {
                            System.out.println("No user exists!");
                            break;
                        }
                    }while (client == null);
                    if(client == null){
                        break;
                    }
                    int newOption;
                    do {
                        do {
                            printNewMenu();
                            System.out.println("What do you want to do?:");
                            newOption = scanner.nextInt();
                        } while (newOption < 1 || newOption > 6);
                        switch (newOption) {
                            case 1: {
                                System.out.println(realEstate.postNewProperty(client));
                                break;
                            }
                            case 2: {
                                realEstate.removeProperty(client);
                                break;
                            }
                            case 3: {
                                realEstate.printAllProperties();
                                break;
                            }
                            case 4: {
                                realEstate.printAllProperties(client);
                                break;
                            }
                            case 5: {
                                Property[] search = realEstate.search();
                                if(search != null){
                                    for (int i = 0; i < search.length; i++) {
                                        if (search[i] != null) {
                                            System.out.println(search[i]);
                                        }
                                    }
                                }
                                break;
                            }
                            case 6: {
                                break;
                            }
                        }
                    }while (newOption != 6);
                }
                case 3:{
                    break;
                }
            }
        }while (option != 3);

    }

    private static void printMenu(){
        System.out.println("Press 1 - Create user.");
        System.out.println("Press 2 - Connect to an existing account.");
        System.out.println("Press 3 - Finish the program.");
    }

    private static void printNewMenu(){
        System.out.println("Press 1 - publish new property.");
        System.out.println("Press 2 - remove publish.");
        System.out.println("Press 3 - present all properties in system.");
        System.out.println("Press 4 - present all properties you have posted");
        System.out.println("Press 5 - search property by parameters");
        System.out.println("Press 6 - log out and back to first menu");
    }



}
