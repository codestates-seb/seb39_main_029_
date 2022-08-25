package codestates.preproject.stackoverflow.post.entity;


import codestates.preproject.stackoverflow.member.entity.Member;
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

    @Column
    private List<String> tag;

    @Column
    private String content;

    @Column
    private int votes=0;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


}
