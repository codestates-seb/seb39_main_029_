package codestates.preproject.stackoverflow.comments.entity;

import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentsid;

    @ManyToOne
    @JoinColumn(name = "POSTSID")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "MEMEBERID")
    private Member member;

    @Column
    private int votes;

    @Column(columnDefinition = "Text")
    private String content;

    public void setPosts(Posts posts) {
        this.posts = posts;
        if(!posts.getCommentsList().contains(this)){
            posts.addComments(this);
        }
    }
}
