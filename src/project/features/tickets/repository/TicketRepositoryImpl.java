package project.features.tickets.repository;


import project.core.api.CoreAPI;
import project.features.tickets.model.dao.Ticket;
import project.features.tickets.repository.interfaces.TicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    @Override
    public void createTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (userId, title, description, isClosed, isDeleted, createdDate, closedDate, updatedDate, isNew) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getUserId());
            statement.setString(2, ticket.getTitle());
            statement.setString(3, ticket.getDescription());
            statement.setBoolean(4, ticket.isClosed());
            statement.setBoolean(5, ticket.isDeleted());
            statement.setTimestamp(6, ticket.getCreatedDate());
            statement.setTimestamp(7, ticket.getClosedDate());
            statement.setTimestamp(8, ticket.getUpdatedDate());
            statement.setBoolean(9, ticket.isNew());
            statement.executeUpdate();
        }
    }

    @Override
    public Ticket getTicketById(int id) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToTicket(resultSet);
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                tickets.add(mapRowToTicket(resultSet));
            }
        }
        return tickets;
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE tickets SET userId = ?, title = ?, description = ?, isClosed = ?, isDeleted = ?, createdDate = ?, closedDate = ?, updatedDate = ?, isNew = ? WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ticket.getUserId());
            statement.setString(2, ticket.getTitle());
            statement.setString(3, ticket.getDescription());
            statement.setBoolean(4, ticket.isClosed());
            statement.setBoolean(5, ticket.isDeleted());
            statement.setTimestamp(6, ticket.getCreatedDate());
            statement.setTimestamp(7, ticket.getClosedDate());
            statement.setTimestamp(8, ticket.getUpdatedDate());
            statement.setBoolean(9, ticket.isNew());
            statement.setInt(10, ticket.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection connection = CoreAPI.getInstance().getDatabaseService().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Ticket mapRowToTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("id"));
        ticket.setUserId(resultSet.getInt("userId"));
        ticket.setTitle(resultSet.getString("title"));
        ticket.setDescription(resultSet.getString("description"));
        ticket.setClosed(resultSet.getBoolean("isClosed"));
        ticket.setDeleted(resultSet.getBoolean("isDeleted"));
        ticket.setCreatedDate(resultSet.getTimestamp("createdDate"));
        ticket.setClosedDate(resultSet.getTimestamp("closedDate"));
        ticket.setUpdatedDate(resultSet.getTimestamp("updatedDate"));
        ticket.setNew(resultSet.getBoolean("isNew"));
        return ticket;
    }
}