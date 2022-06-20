package pl.blog.javablog.vote;

import pl.blog.javablog.user.User;
import pl.blog.javablog.user.UserDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class VoteService {
    private final VoteDao voteDao = new VoteDao();
    private final VoteMapper voteMapper = new VoteMapper();

    public void addVote(PostVote vote) {
        Vote voteToSave = voteMapper.map(vote);
        voteDao.save(voteToSave);
    }

    private static class VoteMapper {
        private final UserDao userDao = new UserDao();

        Vote map(PostVote postVote) {
            Optional<User> user = userDao.findByUsername(postVote.getUsername());
            return new Vote(
                    user.orElseThrow().getId(),
                    postVote.getPostId(),
                    Vote.Type.valueOf(postVote.getType()),
                    LocalDateTime.now()
            );
        }
    }
}
