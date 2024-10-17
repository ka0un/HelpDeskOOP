package project.features.tickets.service;

import project.features.tickets.model.dao.Message;
import project.features.tickets.model.dao.Ticket;
import project.features.tickets.repository.MessageRepositoryImpl;
import project.features.tickets.repository.TicketRepositoryImpl;
import project.features.tickets.repository.interfaces.MessageRepository;
import project.features.tickets.repository.interfaces.TicketRepository;
import project.features.tickets.service.interfaces.TicketService;

import java.sql.SQLException;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository = new TicketRepositoryImpl();
    private final MessageRepository messageRepository = new MessageRepositoryImpl();


    @Override
    public void createTicket(Ticket ticket) throws SQLException {
        ticketRepository.createTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int id) throws SQLException {
        return ticketRepository.getTicketById(id);
    }

    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        return ticketRepository.getAllTickets();
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        ticketRepository.updateTicket(ticket);
    }

    @Override
    public void deleteTicket(int id) throws SQLException {
        ticketRepository.deleteTicket(id);
    }

    @Override
    public void addMessageToTicket(Message message) throws SQLException {
        messageRepository.createMessage(message);
    }

    @Override
    public List<Message> getMessagesForTicket(int ticketId) throws SQLException {
        return messageRepository.getAllMessages().stream()
                .filter(message -> message.getTicketId() == ticketId)
                .toList();
    }
}