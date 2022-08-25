package codestates.preproject.stackoverflow.member.entity;

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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long memberid;         //


    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;  //

    @Column(nullable = false, unique = true)
    private String email;


    @Column(columnDefinition = "Text")
    private String selfId;

//    @Column
//    private String image;


    @Column
    private String location;

    @Column
    private String title;

    @Column
    private Integer reputation;

//    @OneToMany(mappedBy = "member")
//    List<POSTS> posts = new ArrayList<>();


}
