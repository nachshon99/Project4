public class Address {
    private String nameOfCity;
    private String nameOfStreet;

    public Address(String nameOfCity, String nameOfStreet){
        this.nameOfCity = nameOfCity;
        this.nameOfStreet = nameOfStreet;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public String getNameOfStreet() {
        return nameOfStreet;
    }

    public void setNameOfStreet(String nameOfStreet) {
        this.nameOfStreet = nameOfStreet;
    }

    public String toString() {
        return "Address: " + "\n " +
                "name Of City: " + nameOfCity + "\n" +
                "nameOfStreet: " + nameOfStreet +"\n";
    }
}
