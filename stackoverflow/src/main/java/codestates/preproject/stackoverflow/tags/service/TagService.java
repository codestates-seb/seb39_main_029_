package codestates.preproject.stackoverflow.tags.service;

import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.tags.Repository.TagRepository;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tags> tagsFind(long postId) {
        return repository.findTagsAll(postId).get();

    }
}
