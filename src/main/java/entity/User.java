package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 60,name = "first_name")
    String firstName;
    @Column (length = 60, name = "last_name")
    String  lastName;
    @Column (nullable = false,length = 60,unique = true)
    String login;
    @Column (name="password",nullable = false,length = 60)
    String secret;


}
