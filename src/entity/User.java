package entity;

import java.util.Date;
import enums.Role;

/**
 * Abstract base class for all system users.
 * Maps to the User abstract entity in the class diagram.
 */
public abstract class User {

    protected int userId;
    protected String name;
    protected int age;
    protected String phone;
    protected String email;
    protected String password;
    protected Role role;
    protected Date createdAt;
    protected Date lastLogin;

    public User() {}

    public User(int userId, String name, int age, String phone, String email,
                String password, Role role, Date createdAt, Date lastLogin) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    /** Domain method: validate provided credentials against this user. */
    public boolean login(String email, String password) {
        return this.email != null
                && this.email.equalsIgnoreCase(email)
                && this.password != null
                && this.password.equals(password);
    }

    /** Domain method: mark logout timestamp (session end). */
    public void logout() {
        this.lastLogin = new Date();
    }

    /** Domain method: change password with current-password check. */
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password != null && this.password.equals(oldPassword)
                && newPassword != null && newPassword.length() >= 6) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    /** Domain method: update profile fields in one call. */
    public void updateProfile(String name, int age, String phone, String email) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    // Getters / setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
