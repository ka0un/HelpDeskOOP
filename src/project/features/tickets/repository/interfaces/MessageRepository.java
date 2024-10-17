package project.features.tickets.repository.interfaces;

import project.features.tickets.model.dao.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageRepository {
    public void createMessage(Message message) throws SQLException;
    public Message getMessageById(int id) throws SQLException;
    public List<Message> getAllMessages() throws SQLException;
    public void updateMessage(Message message) throws SQLException;
    public void deleteMessage(int id) throws SQLException;
}
