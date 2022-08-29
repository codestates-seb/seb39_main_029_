package codestates.preproject.stackoverflow.post.entity;


import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.pvote.entity.Pvote;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @ManyToOne
    @JoinColumn(name = "MEMBERID")
    private Member member;

    @Column
    private String subject;
    @OneToMany(mappedBy = "posts", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<PostTag> postTagsList = new ArrayList<>();

    @Column
    private String content;

    @Column
    private int votes=0;

    @Column(name="isvote")
    private boolean isvote=false;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void addPostTags(PostTag postTag) {
        this.postTagsList.add(postTag);
        if (postTag.getPosts() != this) {
            postTag.addPost(this);
        }
    }

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comments> commentsList = new ArrayList<>();

//    public void addComments(Comments comments){
//        this.commentsList.add(comments);
//        if(comments.getPosts() != this){
//            comments.setPosts(this);
//        }
//    }

    //상수가 작성한 코드 입니다.
    @Column
    private int commentsCount;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    List<Pvote> PVotes = new ArrayList<>();

    public void addPVote(Pvote pvote){
        this.PVotes.add(pvote);
        if(pvote.getPosts() != this){
            pvote.setPosts(this);
        }
    }



    public boolean getIsVote() {
        return this.isvote;
    }
}
