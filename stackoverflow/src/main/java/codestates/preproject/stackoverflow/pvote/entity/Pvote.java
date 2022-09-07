package codestates.preproject.stackoverflow.pvote.entity;

import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pvote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pVoteId;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
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
