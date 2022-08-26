package codestates.preproject.stackoverflow.tags.entity;

import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagsId;

    @Column()
    private String data;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    public Tags(String data) {
        this.data = data;
    }

    public void addPosts(Posts posts) {
        this.posts = posts;
        if (!this.posts.getTag().contains(this)) {
            this.posts.addTags(this);
        }
    }

}
