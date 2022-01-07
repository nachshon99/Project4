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
    public static final String REGULAR = "Regular apartment";
    public static final String PENTHOUSE = "Penthouse apartment";
    public static final String PRIVATE = "Private apartment";
    public static final int REGULAR_FLOOR_APARTMENT = 1;
    public static final int PENTHOUSE_FLOOR_APARTMENT = 2;
    public static final int PRIVATE_HOME = 3;
    public static final String NULL = null;
    public static final int ADD_TO_LENGTH_ARRAY = 1;
    public static final int ONE = 1;
    public static final int FOR_RENT = 1;
    public static final int FOR_SALE = 2;
    public static final int MIN_PRICE = 0;
    public static final int MINIMUM_ROOMS = 0;


    public RealEstate(){
        this.users = new User[INITIALIZE];
        this.properties = new Property[INITIALIZE];
        Address address1 = new Address("Ashkelon", "Emek Heffer");
        Address address2 = new Address("Ashdod", "adsd");
        Address address3 = new Address("Ashdod", "adsdass");
        Address address4 = new Address("Tel Aviv", "adsdass");
        Address address5 = new Address("Ashdod", "adsdass");
        Address address6 = new Address("Ashdod", "adsdass");
        Address address7 = new Address("Netivot", "adsdass");
        Address address8 = new Address("Ashdod", "adsdass");
        Address address9 = new Address("Netivot", "adsdass");
        Address address10 = new Address("Eilat", "adsdass");
        Address[] addresses1 = {address1, address2, address3, address4, address5, address6, address7, address8, address9, address10};
        this.addresses = addresses1;
    }

    public void createUser(){
        Scanner scanner = new Scanner(System.in);
        boolean isIntermediary;

        String userName = NULL;

        do {
            System.out.println("Enter UserName: ");
            userName = scanner.nextLine();
            if(users.length != INITIALIZE) {
                if (isUserNameExist(userName)) {
                    System.out.println("The userName is busy!");
                }
            }else {
                break;
            }
        }while (isUserNameExist(userName) || userName.length() == INITIALIZE);


        String password = NULL;
        do {
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            if(!isStrongPassword(password)){
                System.out.println("The password is not strong!");
            }
        }while (!isStrongPassword(password));

        String phoneNumber = NULL;
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
            if(intermediary != ONE && intermediary != INITIALIZE){
                System.out.println("The answer is not valid");
            }
        }while (intermediary != ONE && intermediary != INITIALIZE);
        if(intermediary == ONE){
            isIntermediary = true;
        }else {
            isIntermediary = false;
        }
        addUserToArray(userName,password,phoneNumber,isIntermediary);
    }

    private  void addUserToArray(String userName, String password,String phoneNumber, boolean isIntermediary){
        User[] newArray = new User[this.users.length + ADD_TO_LENGTH_ARRAY];
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

        int saveIndex = INITIALIZE;
        System.out.println("Enter userName: ");
        String userName = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length;i++){
            if(users[i] != null) {
                if (users[i].getUserName().equals(userName)) {
                    if (users[i].getPassword().equals(password)) {
                        isExistUser = true;
                        saveIndex = i;
                        break;
                    }
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
        boolean canPostMediator = false;
        boolean canPostNotMediator = false;
        int countOfProperties = INITIALIZE;
        String[] streets = new String[this.addresses.length];
        if(user.isMediator()){
            if(countOfPropertiesPossible(user,countOfProperties) < LIMIT_OF_PROPERTIES_Intermediary ){
                canPostMediator = true;
            }else {
                canPostMediator = false;
            }
        }else {
            if (countOfPropertiesPossible(user, countOfProperties) < LIMIT_OF_PROPERTIES_ORIGIN) {
                canPostNotMediator = true;
            }else {
                canPostMediator = false;
            }
        }
        if(canPostMediator || canPostNotMediator){
            String[] listCities = printCities(returnArrOfCities(this.addresses));
            System.out.println("Enter name of city from the list: ");
            String getCity = scanner.nextLine();
            for (int i = 0; i < listCities.length;i++){
                if(listCities[i].equals(getCity)){
                    for (int j = 0, k = 0; j < this.addresses.length;j++){
                        if(this.addresses[j].getNameOfCity().equals(getCity)){
                            streets[k] = this.addresses[j].getNameOfStreet();
                            k++;
                            canPost = true;
                        }
                    }
                    printArray(streets);
                    System.out.println("Enter street from list: ");
                    String getStreet = scanner.nextLine();
                    for (int j = 0; j < streets.length;j++){
                        if(streets[j] != null) {
                            if (streets[j].equals(getStreet)) {
                                canPost = true;
                                break;
                            } else {
                                canPost = false;
                            }
                        }
                    }
                    if(canPost){
                        System.out.println("Choose type property: " + "\n"
                                + "Press 1 - for regular floor apartment " + "\n"
                                + "Press 2 - for penthouse floor apartment" + "\n"
                                + "Press 3 - for private home");
                        int getTypeInt = scanner.nextInt();
                        int getFloor = INITIALIZE;
                        switch (getTypeInt){
                            case REGULAR_FLOOR_APARTMENT :
                            case PENTHOUSE_FLOOR_APARTMENT:{
                                System.out.println("Enter floor: ");
                                getFloor = scanner.nextInt();
                                break;
                            }
                            case PRIVATE_HOME:{
                                break;
                            }
                        }
                        String getTypeString = NULL;
                        if(getTypeInt == REGULAR_FLOOR_APARTMENT){
                            getTypeString = REGULAR;
                        }else if(getTypeInt == PENTHOUSE_FLOOR_APARTMENT){
                            getTypeString = PENTHOUSE;
                        }else if(getTypeInt == PRIVATE_HOME){
                            getTypeString = PRIVATE;
                        }
                        System.out.println("Enter how many rooms: ");
                        int getRooms = scanner.nextInt();
                        System.out.println("Enter number of home: ");
                        int getNumHome = scanner.nextInt();
                        System.out.println("Is for rent? (true / false)");
                        boolean getRent = scanner.nextBoolean();
                        System.out.println("Enter a price: ");
                        int getPrice = scanner.nextInt();
                        Address getAddress = new Address(getCity,getStreet);
                        Property newProperty = new Property(getAddress,getRooms,getPrice,getTypeString,getRent,getNumHome,getFloor,user);
                        Property[] newProperties = new Property[properties.length + ONE];
                        for (int k = 0; k < this.properties.length;k++){
                            newProperties[k] = this.properties[k];
                        }
                        newProperties[newProperties.length - ONE] = newProperty;
                        this.properties = newProperties;
                        canPost = true;
                        break;
                    }


                }else {
                    canPost = false;
                }
            }
            if(!canPost) {
                System.out.println("The city is not show in the list!");
            }
        }
        else {
            System.out.println("You have reached the limit!");
        }

        return canPost;
    }

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
            for (int i = 0; i < this.properties.length;i++){
                this.properties[option] = this.properties[this.properties.length - ONE];
                this.properties[this.properties.length-ONE] = null;
                Property[] newProperty = new Property[this.properties.length - ONE];
                if (newProperty.length != INITIALIZE) {
                    for (int j = 0; j < this.properties.length; j++) {
                        if(properties[j] != null) {
                            newProperty[j] = this.properties[j];
                        }
                    }
                }
                this.properties = newProperty;

            }
        }
    }

    public void printAllProperties(){
        if(properties.length != INITIALIZE) {
            for (int i = 0; i < properties.length; i++) {
                    System.out.println(properties[i]);
            }
        }else {
            System.out.println("No exist properties");
        }
    }

    public void printAllProperties(User user){
        if(properties.length != INITIALIZE) {
            for (int i = 0; i < properties.length;i++){
                if(properties[i].getUser().getUserName().equals(user.getUserName())){
                    System.out.println(properties[i]);
                }
            }
        }else {
            System.out.println("No exist properties");
        }
    }

    public Property[]search() {
        Scanner scanner = new Scanner(System.in);
        Property[] searchArray = this.properties;

        int option;
        do {
            System.out.println("Is the apartment for rent? (Press 1 - for rent, Press 2 - for sale, Press -999 to not filter option)");
            option = scanner.nextInt();
        } while ((option < FOR_RENT || option > FOR_SALE) && option != SKIP_OPTION);
        if(option == FOR_RENT){
            for (int i = 0; i < searchArray.length; i++) {
                if (!searchArray[i].isForRent()) {
                    searchArray = removeProperty(searchArray, searchArray[i]);
                    i--;
                }
            }
        }
        else if(option == FOR_SALE) {
            for (int i = 0; i < searchArray.length; i++) {
                if (searchArray[i].isForRent()) {
                    searchArray = removeProperty(searchArray, searchArray[i]);
                    i--;
                }
            }
        }
        do {
            System.out.println("What type of property? \n" +
                    "(Press 1 - for regular floor apartment" + "\n"
                    + "Press 2 - for penthouse floor apartment" + "\n"
                    + "Press 3 - for private home" + "\n"
                    + "Press -999 - to not filter this option)");
            option = scanner.nextInt();
        } while ((option < REGULAR_FLOOR_APARTMENT || option > PRIVATE_HOME) && option != SKIP_OPTION);
        if (option == REGULAR_FLOOR_APARTMENT) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!searchArray[i].getType().equals(REGULAR)) {
                    searchArray = removeProperty(searchArray, searchArray[i]);
                    i--;
                }
            }
        } else if (option == PENTHOUSE_FLOOR_APARTMENT) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!searchArray[i].getType().equals(PENTHOUSE)) {
                    searchArray = removeProperty(searchArray, searchArray[i]);
                    i--;
                }
            }
        } else if (option == PRIVATE_HOME) {
            for (int i = 0; i < searchArray.length; i++) {
                if (!searchArray[i].getType().equals(PRIVATE)) {
                    searchArray = removeProperty(searchArray, searchArray[i]);
                    i--;
                }
            }
        }
        do {
            System.out.println("How many rooms do you want? (Press -999 - to not filter this option)");
            option = scanner.nextInt();
        } while (option < MINIMUM_ROOMS && option != SKIP_OPTION);
        if(option != SKIP_OPTION){
            for (int i = 0; i < searchArray.length;i++){
                if(option != searchArray[i].getCountOfRoom()){
                    searchArray = removeProperty(searchArray,searchArray[i]);
                    i--;
                }
            }
        }

        int minPrice;
        int maxPrice;
        do {
            System.out.println("What minimum price do you want? (Press -999 - to not filter this option)");
            minPrice = scanner.nextInt();
            System.out.println("What maximum price do you want? (Press -999 - to not filter this option)");
            maxPrice = scanner.nextInt();
        } while (((minPrice < MIN_PRICE || maxPrice < MIN_PRICE) && (maxPrice != SKIP_OPTION && minPrice != SKIP_OPTION)) || ((maxPrice < minPrice) && maxPrice != SKIP_OPTION)) ;
        if(minPrice != SKIP_OPTION && maxPrice != SKIP_OPTION){
            for(int i = 0; i < searchArray.length;i++){
                if(searchArray[i].getPrice() < minPrice || searchArray[i].getPrice() > maxPrice){
                    searchArray = removeProperty(searchArray,searchArray[i]);
                    i--;
                }
            }
        }
        printAllProperties(searchArray);

        return searchArray;

    }

    private void printAllProperties(Property[]Properties){
        if(properties.length == 0){
            System.out.println("No exist properties!");
        }else {
            for (int i = 0; i < properties.length;i++){
                System.out.println(i+1 + ".\n" + properties[i]);
            }
        }
    }
    private Property[] removeProperty(Property[] properties, Property propertyToRemove){
        Property[] newArray = new Property[this.properties.length-1];
        int indexToArray =0;
        for (int i = 0; i < properties.length;i++){
            if(properties[i] != propertyToRemove){
                newArray[indexToArray] = properties[i];
                indexToArray++;
            }
        }
        return newArray;
    }

    private void printArray(String[] strings){
        for (int i = 0; i < strings.length;i++){
            if(strings[i] != null)
            System.out.println(strings[i]);
        }
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
            if(properties[i] != null) {
                if (properties[i].getUser().getUserName().equals(user.getUserName())) {
                    countOfProperties++;
                }
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

    private boolean isValidNumber(String phoneNumber){
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

    private  String[] printCities(String[] cities){
        String[] newCities = new String[cities.length];
        int indexToArray = INITIALIZE;
        System.out.println("The list of your cities are: ");
        for (int i = 0; i < cities.length; i++){
            if (!isExistInArray(newCities,cities[i])) {
                newCities[indexToArray] = cities[i];
                System.out.println(newCities[indexToArray]);
                indexToArray++;
            }
        }
        String[] listCities = new String[indexToArray];
        indexToArray = INITIALIZE;
        for (int i = 0; i < newCities.length;i++){
            if(newCities[i] != null){
                listCities[indexToArray] = newCities[i];
                indexToArray++;
            }else {
                break;
            }
        }
        return listCities;
    }

    private  String[] returnArrOfCities(Address[] addresses){
        String[]cities = new String[addresses.length];
        int indexToArray = INITIALIZE;
        int lengthNewArray = INITIALIZE;
        for (int i = 0; i < addresses.length;i++){
            if(addresses[i] != null) {
                cities[indexToArray] = addresses[i].getNameOfCity();
                indexToArray++;
                lengthNewArray++;
            }
        }
        String[] citiesToReturn = new String[lengthNewArray];
        for (int i = 0; i < cities.length;i++){
            if(cities[i] != null){
                citiesToReturn[i] = cities[i];
            }
        }
        return citiesToReturn;
    }

    private  boolean isExistInArray(String[] cities, String city){
        boolean exist = false;
        for (int i = 0, k = 0; i < cities.length;i++){
            if(cities[i] == null){
                break;
            }else if(cities[i].equals(city)){
                exist = true;
                break;
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
                "users: \n" + Arrays.toString(users) + "\n" +
                "properties: \n" + Arrays.toString(properties) + "\n" +
                "addresses: \n" + Arrays.toString(addresses) +"\n";
    }
}
