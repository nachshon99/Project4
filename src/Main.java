import java.util.Scanner;

public class Main {
    public static final int INITIALIZE = 0;
    public static final int CREATE_ACCOUNT = 1;
    public static final int CONNECT_TO_ACCOUNT = 2;
    public static final int EXIT = 3;
    public static final int POST_NEW_PROPERTY = 1;
    public static final int REMOVE_PROPERTY = 2;
    public static final int PRINT_ALL_PROPERTIES = 3;
    public static final int PRINT_ALL_PROPERTIES_OF_USER = 4;
    public static final int SEARCH = 5;
    public static final int BACK = 6;

    public static void main(String[] args) {
        RealEstate realEstate = new RealEstate();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            do {
                printMenu();
                System.out.println("What do you want to do?:");
                option = scanner.nextInt();
            }while (option < CREATE_ACCOUNT || option > EXIT);
            switch (option){
                case CREATE_ACCOUNT:{
                    realEstate.createUser();
                    break;
                }
                case CONNECT_TO_ACCOUNT:{
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
                            case POST_NEW_PROPERTY: {
                                System.out.println(realEstate.postNewProperty(client));
                                break;
                            }
                            case REMOVE_PROPERTY: {
                                realEstate.removeProperty(client);
                                break;
                            }
                            case PRINT_ALL_PROPERTIES: {
                                realEstate.printAllProperties();
                                break;
                            }
                            case PRINT_ALL_PROPERTIES_OF_USER: {
                                realEstate.printAllProperties(client);
                                break;
                            }
                            case SEARCH: {
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
                            case BACK: {
                                break;
                            }
                        }
                    }while (newOption != BACK);
                }
                case EXIT:{
                    break;
                }
            }
        }while (option != EXIT);

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
