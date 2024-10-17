package project.features.tickets.service.interfaces;

import project.features.tickets.model.dao.Ticket;
import project.features.tickets.model.dao.Message;
import java.util.List;

import java.sql.SQLException;

public interface TicketService {
    public void createTicket(Ticket ticket) throws SQLException;
    public Ticket getTicketById(int id) throws SQLException;
    public List<Ticket> getAllTickets() throws SQLException;
    public void updateTicket(Ticket ticket) throws SQLException;
    public void deleteTicket(int id) throws SQLException;
    public void addMessageToTicket(Message message) throws SQLException;
    public List<Message> getMessagesForTicket(int ticketId) throws SQLException;

}
