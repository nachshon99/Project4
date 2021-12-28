public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isMediator;


    public User(String userName, String password, String phoneNumber, boolean isMediator){
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isMediator = isMediator;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMediator() {
        return isMediator;
    }

    public void setMediator(boolean mediator) {
        isMediator = mediator;
    }

    public String toString() {
        return "User:" + "\n" +
                "userName: " + userName + "\n" +
                "password: " + password + "\n" +
                "phoneNumber: " + phoneNumber + "\n" +
                "isMediator: " + isMediator +"\n";
    }
}
