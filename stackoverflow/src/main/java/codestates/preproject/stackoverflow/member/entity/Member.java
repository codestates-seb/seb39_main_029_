package codestates.preproject.stackoverflow.member.entity;

import codestates.preproject.stackoverflow.comments.entity.Comments;

import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.s3.entity.Images;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberid;         //

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;  //

    @Column(nullable = false, unique = true)
    private String email;


    @Column(columnDefinition = "Text")
    private String selfId;

    @Column
    private String location;

    @Column
    private String title;

    @Column
    private int reputation;

    @Column
    private String roles;


    @OneToMany(mappedBy = "member")
    List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Comments> commentsList = new ArrayList<>();

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Images image;

    public void addImage(Images image) {
        this.image = image;
        if (image.getMember() != this) {
            image.setMember(this);
        }
    }
}