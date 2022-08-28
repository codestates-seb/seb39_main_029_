package codestates.preproject.stackoverflow.cvote.entity;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cvoteId;

    @ManyToOne
    @JoinColumn(name = "COMMENTSID")
    private Comments comments;

    public void setComments(Comments comments) {
        this.comments = comments;
        if(!comments.getCvoteList().contains(this)){
            comments.addlist(this);
        }
    }

    @Column(nullable = false)
    private long memberid;


}
