package pl.blog.javablog.user;

import pl.blog.javablog.vote.VoteDao;

import java.time.LocalDateTime;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    public void register(UserRegistration userRegistration) {
        User userToSave = userMapper.mapToUser(userRegistration);
        userDao.save(userToSave);
    }

    public Integer findUserIdByName(String username) {
        return userDao.findByUsername(username).orElseThrow().getId();
    }

    public String getUserRoleByUsername(String username) {
        return userDao.getUserRoleByUsername(username).orElseThrow();
    }

    public UserInfo getUserInfoByName(String name) {
        User user = userDao.findByUsername(name).orElseThrow();
        return userMapper.mapToUserInfo(user);
    }

    private static class UserMapper {
        private final VoteDao voteDao = new VoteDao();

        User mapToUser(UserRegistration userRegistration) {
            return new User(
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    LocalDateTime.now(),
                    userRegistration.getPassword()
            );
        }

        UserInfo mapToUserInfo(User user) {
            return new UserInfo(
                    user.getUsername(),
                    user.getEmail(),
                    user.getRegistrationDate(),
                    voteDao.countVoteUpByUserId(user.getId()),
                    voteDao.countVoteDownByUserId(user.getId())
            );
        }
    }
}