package codestates.preproject.stackoverflow.post.entity;


import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.member.entity.Member;

import codestates.preproject.stackoverflow.tags.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column
    private String subject;

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<PostTag> postTagsList = new ArrayList<>();

    @Column
    private String content;

    @Column
    private int votes=0;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void addPostTags(PostTag postTag) {
        this.postTagsList.add(postTag);
        if (postTag.getPosts() != this) {
            postTag.addPost(this);
        }
    }

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Comments> commentsList = new ArrayList<>();

    public void addComments(Comments comments){
        this.commentsList.add(comments);
        if(comments.getPosts() != this){
            comments.setPosts(this);

        }
    }
}
