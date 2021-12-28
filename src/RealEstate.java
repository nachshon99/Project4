import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[]properties;
    private Address[]addresses;
    public static final int LENGTH_DIGITS_OF_PHONE_NUMBER = 10;
    public static final int LIMIT_OF_PROPERTIES_Intermediary = 10;
    public static final int LIMIT_OF_PROPERTIES_ORIGIN = 3;
    public static final int INITIALIZE = 0;
    public static final char ZERO_CHAR = '0';
    public static final char FIVE_CHAR = '5';
    public static final int SKIP_OPTION = -999;
    public static final String REGULAR = "regular";
    public static final String PENTHOUSE = "penthouse";
    public static final String PRIVATE = "private";


    public RealEstate(User[]users, Property[]properties,Address[]addresses){
        this.users = users;
        this.properties = properties;
        this.addresses = addresses;
    }

    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        boolean isIntermediary;

        String userName = null;
        do {
            System.out.println("Enter UserName: ");
            userName = scanner.nextLine();
            if(isUserNameExist(userName)){
                System.out.println("The userName is busy!");
            }
        }while (isUserNameExist(userName) || userName.length() == 0);

        String password = null;
        do {
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            if(!isStrongPassword(password)){
                System.out.println("The password is not strong!");
            }
        }while (!isStrongPassword(password));

        String phoneNumber = null;
        do {
            System.out.println("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            if(!isValidNumber(phoneNumber)){
                System.out.println("The number is not valid!");
            }
        }while (!isValidNumber(phoneNumber));

        int intermediary;
        do {
            System.out.println("Are you an intermediary? (press 1 for true, press 0 for false)");
            intermediary = scanner.nextInt();
            if(intermediary != 1 && intermediary != 0){
                System.out.println("The answer is not valid");
            }
        }while (intermediary != 1 && intermediary != 0);
        if(intermediary == 1){
            isIntermediary = true;
        }else {
            isIntermediary = false;
        }
        addUserToArray(userName,password,phoneNumber,isIntermediary);
    }

    private  void addUserToArray(String userName, String password,String phoneNumber, boolean isIntermediary){
        User[] newArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length;i++){
            newArray[i] = this.users[i];
        }
        User userToAdd = new User(userName,password,phoneNumber,isIntermediary);
        newArray[this.users.length] = userToAdd;
        this.users = newArray;
    }

    public User login(){
        Scanner scanner = new Scanner(System.in);
        boolean isExistUser = false;

        int saveIndex = 0;
        System.out.println("Enter userName: ");
        String userName = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length;i++){
            if(users[i].getUserName().equals(userName)){
                if(users[i].getPassword().equals(password)){
                    isExistUser = true;
                    saveIndex = i;
                    break;
                }
            }
        }
        if (isExistUser){
            return users[saveIndex];
        }else {
            return null;
        }
    }

    public boolean postNewProperty(User user){
        Scanner scanner = new Scanner(System.in);
        boolean canPost = false;
        int countOfProperties = INITIALIZE;
        if(user.isMediator()){
            if(countOfPropertiesPossible(user,countOfProperties) < LIMIT_OF_PROPERTIES_Intermediary ){
                canPost = true;
                String[] listCitiesOfUser = printCities(returnArrOfAddressesOfUser(user,this.properties));
                String getAddress = scanner.nextLine();
                for (int i = 0; i < listCitiesOfUser.length;i++){
                    if(listCitiesOfUser[i].equals(getAddress)){
                        System.out.println("----");
                    }
                }

            }
            else {
                System.out.println("You have reached the limit!");
            }
        }else {
            if (countOfPropertiesPossible(user, countOfProperties) < LIMIT_OF_PROPERTIES_ORIGIN) {
                canPost = true;
                printCities(returnArrOfAddressesOfUser(user,properties));
            }
            else {
                System.out.println("You have reached the limit!");
            }
        }

        return canPost;
    } //post new property

    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int[]arrayOfIndexes = new int[properties.length];
        int indexToArray = INITIALIZE;
        if(!isExistUser(user)){
            System.out.println("No exist properties");
        }else {
            for (int i = 0; i < properties.length;i++){
                if(properties[i].getUser().getUserName().equals(user.getUserName())){
                    System.out.println("Press " + i +"- to remove: " + "\n" + properties[i].toString());
                    arrayOfIndexes[indexToArray] = i;
                    indexToArray++;
                }
            }
            int option;
            do {
                System.out.println("Choose which property you want to remove: ");
                option = scanner.nextInt();
            }while(!isExistIndex(arrayOfIndexes, option));

        }
    }

    public void printAllProperties(){
        for (int i = 0; i < properties.length;i++){
            System.out.println(properties[i]);
        }
    }

    public void printAllProperties(User user){
        for (int i = 0; i < properties.length;i++){
            if(properties[i].getUser().getUserName().equals(user.getUserName())){
                System.out.println(properties[i]);
            }
        }
    }

    public Property[]search(){
        Scanner scanner= new Scanner(System.in);
        Property[] newProperty = new Property[properties.length];
        int indexOfNewProperty = INITIALIZE;
        boolean forRent;
        int forRentOrSale;
        do {
            System.out.println("Is the apartment for rent? (1 - for rent, 2 - for sale)");
            forRentOrSale = scanner.nextInt();
            if(forRentOrSale == SKIP_OPTION){
                break;
            }
        }while (forRentOrSale < 1 || forRentOrSale > 2);
        if(forRentOrSale == 1){
            forRent = true;
        }else {
            forRent = false;
        }

        int type;
        String stringType = null;
        do {
            System.out.println("What type of property? \n" +
                    "(Press 1 - for regular floor apartment" + "\n"
                    + "Press 2 - for penthouse floor apartment" + "\n"
                    + "Press 3 - for private home" + "\n"
                    + "Press -999 - to not filter this option)");
            type = scanner.nextInt();
            if(type == SKIP_OPTION){
                break;
            }
        }while (type < 1 || type > 3);
        if(type == 1){
            stringType = REGULAR;
        }else if (type == 2){
            stringType = PENTHOUSE;
        }else if(type == 3){
            stringType = PRIVATE;
        }

        int rooms;
        do {
            System.out.println("How many rooms do you want? (Press -999 - to not filter this option)");
            rooms = scanner.nextInt();
            if(rooms == SKIP_OPTION){
                break;
            }
        }while (rooms < 0);

        int minPrice;
        do {
            System.out.println("What minimum price do you want? (Press -999 - to not filter this option)");
            minPrice = scanner.nextInt();
            if(minPrice == SKIP_OPTION){
                break;
            }
        }while (minPrice < 0);
        int maxPrice;
        do {
            System.out.println("What maximum price do you want? (Press -999 - to not filter this option)");
            maxPrice = scanner.nextInt();
            if(maxPrice == SKIP_OPTION){
                break;
            }
        }while (maxPrice < minPrice);
        for (int i = 0; i < properties.length;i++){
            //******************
            //להמשיך


        }
        return newProperty;
    }

    private  boolean isExistUser(User user){
        boolean isExist = false;
        for (int i = 0; i < properties.length;i++){
            if(properties[i].getUser().getUserName().equals(user.getUserName())){
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    private boolean isExistIndex(int[] indexes, int option){
        boolean exist = false;
        for (int i = 0; i < indexes.length;i++){
            if(indexes[i] == option){
                exist = true;
                break;
            }
        }
        return exist;
    }

    private int countOfPropertiesPossible(User user, int countOfProperties){
        for (int i = 0; i < properties.length;i++){
            if(properties[i].getUser().getUserName().equals(user.getUserName())){
                countOfProperties++;
            }
        }
        return countOfProperties;
    }

    private boolean isUserNameExist(String userNameToCheck){
        boolean exist = false;
        for (int i = 0; i < this.users.length;i++){
            User currentUser = this.users[i];
            if(currentUser.getUserName().equals(userNameToCheck)){
                exist = true;
                break;
            }
        }
        return exist;
    }

    private boolean isStrongPassword(String password){
        boolean strongPassword = false;
        boolean hadChar = false;
        boolean hasDigit = false;
        char[]chars = {'$', '%', '_'};

        for (int i = 0; i < password.length();i++){
            char currentCharPassword = password.charAt(i);
            for (int j = 0; j < chars.length;j++){
                char currentChar = chars[j];
                if(currentChar == currentCharPassword){
                    hadChar = true;
                    break;
                }
            }
            if(Character.isDigit(currentCharPassword)){
                hasDigit = true;
            }
            if(hadChar && hasDigit){
                break;
            }
        }
        if(hadChar && hasDigit){
            strongPassword = true;
        }
        return strongPassword;
    }

    public  boolean isValidNumber(String phoneNumber){
        boolean valid = false;
        if(phoneNumber.length() == LENGTH_DIGITS_OF_PHONE_NUMBER){
            if(phoneNumber.charAt(0) == ZERO_CHAR && phoneNumber.charAt(1) == FIVE_CHAR){
                for (int i = 2; i < phoneNumber.length();i++){
                    if (Character.isDigit(phoneNumber.charAt(i))){
                        valid = true;
                    }else {
                        valid = false;
                        break;
                    }
                }
            }
        }
        return valid;
    }

    private  String[] printCities(Address[] addresses){
        String[] newAddresses = new String[addresses.length];
        int indexToArray = INITIALIZE;
        System.out.println("The list of your cities are: ");
        for (int i = 0; i < addresses.length; i++){
            if (!isExistInArray(newAddresses,addresses[i])) {
                newAddresses[indexToArray] = addresses[i].getNameOfCity();
                System.out.println(newAddresses[indexToArray]);
                indexToArray++;
            }
        }
        String[] listCitiesOfUser = new String[indexToArray-1];
        indexToArray = INITIALIZE;
        for (int i = 0; i < newAddresses.length;i++){
            if(newAddresses[i] != null){
                listCitiesOfUser[indexToArray] = newAddresses[i];
                indexToArray++;
            }else {
                break;
            }
        }
        return listCitiesOfUser;
    }

    private  Address[] returnArrOfAddressesOfUser(User user, Property[] properties){
        Address[]addresses1 = new Address[properties.length];
        int indexToArray = INITIALIZE;
        for (int i = 0; i < properties.length;i++){
            if(properties[i].getUser().equals(user)){
                addresses1[indexToArray] = properties[i].getAddress();
            }
        }
        return addresses1;
    }

    private  boolean isExistInArray(String[] addresses, Address address){
        boolean exist = false;
        for (int i = 0; i < addresses.length;i++){
            if(addresses[i] == null){
                break;
            }
            else if(addresses[i].equals(address.getNameOfCity())){
                exist = true;
                break;
            }else {
                exist = false;
            }
        }
        return exist;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    public String toString() {
        return "RealEstate:" + "\n" +
                "users: " + "\n" + Arrays.toString(users) + "\n" +
                "properties=" + Arrays.toString(properties) + "\n" +
                "addresses=" + Arrays.toString(addresses) +"\n";
    }
}
