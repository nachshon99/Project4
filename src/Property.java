public class Property {
    private Address address;
    private int countOfRoom;
    private int price;
    private String type;
    private boolean isForRent;
    private int numberOfHome;
    private int numberOfFloor;
    private User user;

    public Property(Address address, int countOfRoom, int price, String type, boolean isForRent
                    , int numberOfHome , int numberOfFloor, User user){
        this.address = address;
        this.countOfRoom = countOfRoom;
        this.price = price;
        this.type = type;
        this.isForRent = isForRent;
        this.numberOfHome = numberOfHome;
        this.numberOfFloor = numberOfFloor;
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(int countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isForRent() {
        return isForRent;
    }

    public void setForRent(boolean forRent) {
        isForRent = forRent;
    }

    public int getNumberOfHome() {
        return numberOfHome;
    }

    public void setNumberOfHome(int numberOfHome) {
        this.numberOfHome = numberOfHome;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Property: " + "\n" +
                "address: " + address + "\n" +
                "countOfRoom: " + countOfRoom + "\n" +
                "price: " + price + "\n" +
                "type: " + type + "\n" +
                "isForRent: " + isForRent + "\n" +
                "numberOfHome: " + numberOfHome + "\n" +
                "numberOfFloor: " + numberOfFloor + "\n" +
                "user: " + user +"\n";
    }

}
