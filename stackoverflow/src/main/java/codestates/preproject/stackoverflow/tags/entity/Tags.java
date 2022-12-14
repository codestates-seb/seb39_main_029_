package codestates.preproject.stackoverflow.tags.entity;

import codestates.preproject.stackoverflow.post.entity.PostTag;
import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagsId;

    @Column
    private String name;

    @OneToMany(mappedBy = "tags")
    private List<PostTag> postTags = new ArrayList<>();

    public Tags(String name) {
        this.name = name;
    }

    public void addPostTag(PostTag postTag) {
        this.postTags.add(postTag);
        if (postTag.getTags()!=this) {
            postTag.addTags(this);
        }
    }

}
