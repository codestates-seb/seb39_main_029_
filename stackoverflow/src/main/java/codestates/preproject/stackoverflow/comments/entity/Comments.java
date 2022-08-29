package codestates.preproject.stackoverflow.comments.entity;

//import codestates.preproject.stackoverflow.checkvote.Checkvote;
import codestates.preproject.stackoverflow.cvote.entity.Cvote;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @Column(name = "isvote")
    private boolean isvote = false;

    @OneToMany(mappedBy = "comments", cascade = CascadeType.REMOVE)
    private List<Cvote> cvoteList = new ArrayList<>();

    public void setPosts(Posts posts) {
        this.posts = posts;
        /*if(!posts.getCommentsList().contains(this)){
            posts.addComments(this);
        }*/

    }

    public void addlist(Cvote cvote){
        this.cvoteList.add(cvote);
        if(cvote.getComments() != this){
            cvote.setComments(this);
        }
    }

    public boolean getIsvote() {
        return this.isvote;
    }
}
