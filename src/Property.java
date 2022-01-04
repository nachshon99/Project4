public class Property {
    private Address address;
    private int countOfRoom;
    private int price;
    private String type;
    private boolean isForRent;
    private int numberOfHome;
    private int numberOfFloor;
    private User user;
    public static final String PRIVATE = "Private apartment";

    public Property(Address address, int countOfRoom, int price, String type, boolean isForRent
                    , int numberOfHome , int numberOfFloor, User user){
        this.address = address;
        if(countOfRoom > 0){
            this.countOfRoom = countOfRoom;
        }else {
            System.out.println("The count of rooms can't be negative");
        }
        if(price > 0) {
            this.price = price;
        }else {
            System.out.println("The prise can't be negative");
        }
        this.type = type;
        this.isForRent = isForRent;
        if(numberOfHome > 0){
            this.numberOfHome = numberOfHome;
        }else {
            System.out.println("The number of home can't be negative");
        }
        if(numberOfFloor >= 0) {
            this.numberOfFloor = numberOfFloor;
        }else {
            System.out.println("The number of floor can't be negative");
        }
        this.user = user;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCountOfRoom() {
        return this.countOfRoom;
    }

    public void setCountOfRoom(int countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isForRent() {
        return this.isForRent;
    }

    public void setForRent(boolean forRent) {
        this.isForRent = forRent;
    }

    public int getNumberOfHome() {
        return this.numberOfHome;
    }

    public void setNumberOfHome(int numberOfHome) {
        this.numberOfHome = numberOfHome;
    }

    public int getNumberOfFloor() {
        return this.numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        String output  = "Property: " + "\n"
                + this.type + " - ";
        if(this.isForRent){
            output += "for rent";
        }else {
            output += "for sale";
        }
        output += ": "+ this.countOfRoom;
        if(this.countOfRoom > 1){
            output+= " rooms ";
        }else {
            output += "room ";
        }

        if(!this.type.equals(PRIVATE)){
            output += ",floor " + this.numberOfFloor +"." + "\n";
        }
        output += "price: " + this.price +"$." + "\n"
        + "Contact info: " + this.user + "\n";
        return output;

    }

}
