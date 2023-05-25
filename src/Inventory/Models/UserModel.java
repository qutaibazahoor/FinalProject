package Inventory.Models;

public class UserModel {
    private int userId;
    private String name;
    private String userName;
    private String password;
    private String uuid;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public UserModel(int userId, String name, String userName, String uuid, String role, String password){
        this.userId=userId;
        this.userName=userName;
        this.name=name;
        this.password=password;
        this.uuid=uuid;
        this.role=role;
    }
    public UserModel(){}
}
