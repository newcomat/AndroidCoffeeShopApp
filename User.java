package alexnewcomer.cs360.com.Model;

public class User {

    //class to get the user information
    private String email;
    private static String name;
    private String password;
    private static String phoneNo;
    private String username;
    private String address;


    public User() {

    }

    public User(String email, String name, String password, String phoneNo, String username, String address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.username = username;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
