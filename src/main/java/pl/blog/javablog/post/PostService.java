package pl.blog.javablog.post;

import pl.blog.javablog.category.CategoryDao;
import pl.blog.javablog.user.UserDao;
import pl.blog.javablog.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    private final PostDao postDao = new PostDao();
    PostMapper postMapper = new PostMapper();

    public List<MainPagePost> findAllPostsToDisplay() {
        List<MainPagePost> posts = postDao.findAll().stream().map(postMapper::mapToPostDisplay).collect(Collectors.toList());
        Collections.reverse(posts);
        return posts;
    }

    public List<ContentsPost> findPostLinksById(Integer categoryId) {
        return postDao.findAllByCategory(categoryId).stream().map(postMapper::mapToPostLink).collect(Collectors.toList());
    }

    public PostPagePost getPostPageById(Integer id) {
        return postMapper.mapToPostPage(postDao.findById(id).orElseThrow());
    }

    public void savePost(PostPublication postPublication) {
        postDao.save(postMapper.mapToPost(postPublication));
    }

    public List<MainPagePost> getSearchedPosts(String userInput, Integer categoryId, Integer sort) {
        List<MainPagePost> searchedPosts = new ArrayList<>();
        if (categoryId != 0) {
            searchedPosts.addAll(postDao
                    .findAllBySubstringAndCategory(userInput, categoryId)
                    .stream()
                    .map(postMapper::mapToPostDisplay).toList());
        } else if (!userInput.equals("")) {
            searchedPosts.addAll(postDao
                    .findAllBySubstring(userInput)
                    .stream()
                    .map(postMapper::mapToPostDisplay).toList());
        } else {
            searchedPosts.addAll(postDao
                    .findAll()
                    .stream()
                    .map(postMapper::mapToPostDisplay).toList());
        }
        if (sort == 1 || sort == 0) {
            Collections.reverse(searchedPosts);
        }
        return searchedPosts;
    }

    private static class PostMapper {
        private final UserDao userDao = new UserDao();
        private final CategoryDao categoryDao = new CategoryDao();
        private final VoteDao voteDao = new VoteDao();

        MainPagePost mapToPostDisplay(Post post) {
            return new MainPagePost(
                    post.getId(),
                    post.getTitle(),
                    post.getIntroduction(),
                    post.getDateAdded(),
                    post.getUrl(),
                    userDao.findById(post.getUserId()).orElseThrow().getUsername(),
                    categoryDao.findById(post.getCategoryId()).orElseThrow().getName(),
                    voteDao.countVoteUpByPostId(post.getId()),
                    voteDao.countVoteDownByPostId(post.getId())
            );
        }

        Post mapToPost(PostPublication postPublication) {
            return new Post(
                    postPublication.getTitle(),
                    postPublication.getIntroduction(),
                    postPublication.getMainContent(),
                    LocalDateTime.now(),
                    postPublication.getUserId(),
                    postPublication.getCategoryId()
            );
        }

        PostPagePost mapToPostPage(Post post) {
            return new PostPagePost(
                    post.getTitle(),
                    post.getMainContent(),
                    post.getDateAdded(),
                    userDao.findById(post.getUserId()).orElseThrow().getUsername()
            );
        }

        ContentsPost mapToPostLink(Post post) {
            return new ContentsPost(
                    post.getTitle(),
                    post.getUrl()
            );
        }
    }
}
