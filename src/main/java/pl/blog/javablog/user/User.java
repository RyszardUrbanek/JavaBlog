package pl.blog.javablog.user;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime registrationDate;
    private String password;

    public User(String username, String email, LocalDateTime registrationDate, String password) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    public User(Integer id, String username, String email, LocalDateTime registrationDate, String password) {
        this(username, email, registrationDate, password);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
