package codestates.preproject.stackoverflow.pvote.entity;

import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pvote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pVoteId;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    public void addPosts(Posts posts) {
        this.posts = posts;
        if (!this.posts.getPVotes().contains(this)) {
            this.posts.getPVotes().add(this);
        }
    }

    @Column(nullable = false, name="MEMBER_ID")
    private long memberId;

}
