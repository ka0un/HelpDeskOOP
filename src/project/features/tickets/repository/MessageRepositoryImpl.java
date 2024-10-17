package project.features.tickets.repository;

import project.core.api.CoreAPI;
import project.features.tickets.model.dao.Message;
import project.features.tickets.repository.interfaces.MessageRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public void createMessage(Message message) throws SQLException {
        String sql = "INSERT INTO messages (ticketId, senderId, message, sentTime, isSenderAdmin) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, message.getTicketId());
            statement.setInt(2, message.getSenderId());
            statement.setString(3, message.getMessage());
            statement.setTimestamp(4, message.getSentTime());
            statement.setBoolean(5, message.isSenderAdmin());
            statement.executeUpdate();
        }
    }

    @Override
    public Message getMessageById(int id) throws SQLException {
        String sql = "SELECT * FROM messages WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToMessage(resultSet);
            }
        }
        return null;
    }

    @Override
    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                messages.add(mapRowToMessage(resultSet));
            }
        }
        return messages;
    }

    @Override
    public void updateMessage(Message message) throws SQLException {
        String sql = "UPDATE messages SET ticketId = ?, senderId = ?, message = ?, sentTime = ?, isSenderAdmin = ? WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, message.getTicketId());
            statement.setInt(2, message.getSenderId());
            statement.setString(3, message.getMessage());
            statement.setTimestamp(4, message.getSentTime());
            statement.setBoolean(5, message.isSenderAdmin());
            statement.setInt(6, message.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteMessage(int id) throws SQLException {
        String sql = "DELETE FROM messages WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }


    private Message mapRowToMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setTicketId(resultSet.getInt("ticketId"));
        message.setSenderId(resultSet.getInt("senderId"));
        message.setMessage(resultSet.getString("message"));
        message.setSentTime(resultSet.getTimestamp("sentTime"));
        message.setSenderAdmin(resultSet.getBoolean("isSenderAdmin"));
        return message;
    }
}
