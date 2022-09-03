package codestates.preproject.stackoverflow.s3.entity;

import codestates.preproject.stackoverflow.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ImagesId;

    private String imagesKey;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBERID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (member.getImage() != this) {
            member.setImage(this);
        }
    }
    
}