package message;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import chat.Chat;

import java.time.LocalDateTime;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Message {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    //private static int nextId = 0;

    @Column(name="date_time")
    private LocalDateTime time;

    @ManyToOne
    private Chat chat; // Reference to the Chat entity

    public Message() {
        //this.messageId = nextId++;   -habe ich auskommentiert weil messageId jz automatisch generiert werden sollte. ~Favour
        this.time = LocalDateTime.now();
    }
}
