package message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import user.User;
import chat.PrivateChat;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PrivateChatMessage extends Message implements Serializable {
    //@Column(name = "data", nullable = false)
    private String messageText;

    //@ManyToOne
    private String senderUsername;

   // @ManyToOne
    private int chatId;

    //@ManyToOne
    private PrivateChat privateChat;  // Reference back to PrivateChat
    public PrivateChatMessage(String data, String sender, int chatId) {
        super();
        this.messageText = data;
        this.senderUsername = sender;
        this.chatId = chatId;
    }

    public PrivateChatMessage() {

    }

    @Override
    public String toString() {
        return "PrivateChatMessage{" +
                "data='" + messageText + '\'' +
                ", sender='" + senderUsername + '\'' +
                ", chatId=" + chatId +
                ", privateChat=" + privateChat +
                "} " + super.toString();
    }

    public static PrivateChatMessage fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, PrivateChatMessage.class);
    }
}
