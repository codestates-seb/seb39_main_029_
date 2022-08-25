package codestates.preproject.stackoverflow.tags;

import codestates.preproject.stackoverflow.post.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagsId;

    @Column(nullable = false, unique = true )
    private String data;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    public Tags(String data) {
        this.data = data;
    }

}
