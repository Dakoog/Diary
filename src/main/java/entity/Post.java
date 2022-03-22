package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Table(name="post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
    @Column (nullable = false)
    String title;
    @Temporal(TemporalType.DATE)
    @Column(name="post_date")
    Date postDate;
    @Lob
    String text;

    public Post(Long id, User user, String title, Date postDate, String text) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.postDate = postDate;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\nWpis z dnia: " +postDate +
                "\n" + title.toUpperCase() + "\n-----------------------\n" + text+"\n" ;
    }
}
