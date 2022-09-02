package codestates.preproject.stackoverflow.post.entity;


import codestates.preproject.stackoverflow.tags.entity.Tags;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postTagId;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "TAGS_ID")
    private Tags tags;


    public void addTags(Tags tags) {
        this.tags = tags;
        if (!this.tags.getPostTags().contains(this)) {
            this.tags.addPostTag(this);
        }
    }

    public void addPost(Posts posts) {
        this.posts = posts;
        if (!this.posts.getPostTagsList().contains(this)) {
            this.posts.addPostTags(this);
        }
    }
}
