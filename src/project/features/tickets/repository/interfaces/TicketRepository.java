package project.features.tickets.repository.interfaces;

import project.features.tickets.model.dao.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketRepository {
    public void createTicket(Ticket ticket) throws SQLException;
    public Ticket getTicketById(int id) throws SQLException;
    public List<Ticket> getAllTickets() throws SQLException;
    public void updateTicket(Ticket ticket) throws SQLException;
    public void deleteTicket(int id) throws SQLException;
}
