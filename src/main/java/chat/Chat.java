package chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import message.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

@Getter
@Setter
@ToString

public abstract class Chat {
    private int chatId;
    private LocalDateTime date;
    private List<Message> messageList;

    public Chat(int chatId) {
        this.chatId = chatId;
        this.date = LocalDateTime.now();
        this.messageList = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chatId == chat.chatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
