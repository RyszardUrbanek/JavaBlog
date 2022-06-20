package pl.blog.javablog.user;

import java.time.LocalDateTime;

public class UserInfo {
    private final String username;
    private final String email;
    private final LocalDateTime registrationDate;
    private final Integer voteUp;
    private final Integer voteDown;

    public UserInfo(String username, String email, LocalDateTime registrationDate, Integer voteUp, Integer voteDown) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.voteUp = voteUp;
        this.voteDown = voteDown;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Integer getVoteUp() {
        return voteUp;
    }

    public Integer getVoteDown() {
        return voteDown;
    }
}
