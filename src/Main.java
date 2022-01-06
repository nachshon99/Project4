import java.util.Scanner;

public class Main {
    public static final int INITIALIZE = 0;

    public static void main(String[] args) {
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
