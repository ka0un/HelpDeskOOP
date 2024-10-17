package project.features.tickets.controllers;

import project.core.api.CoreAPI;
import project.features.tickets.model.dao.Message;
import project.features.tickets.model.dao.Ticket;
import project.features.tickets.service.TicketServiceImpl;
import project.features.tickets.service.interfaces.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/ticketController")
public class TicketController extends HttpServlet {

    private TicketService ticketService = new TicketServiceImpl()  ;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "getTicket":
                    getTicket(request, response);
                    break;
                case "getAllTickets":
                    getAllTickets(request, response);
                    break;
                case "getMessages":
                    getMessages(request, response);
                    break;
                case "markAsResolved":
                    markAsResolved(request, response);
                    break;
                case "deleteTicket":
                    deleteTicket(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "createTicket":
                    createTicket(request, response);
                    break;
                case "updateTicket":
                    updateTicket(request, response);
                    break;
                case "deleteTicket":
                    deleteTicket(request, response);
                    break;
                case "addMessage":
                    addMessage(request, response);
                    break;
                case "markAsResolved":
                    markAsResolved(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void markAsResolved(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Ticket ticket = ticketService.getTicketById(Integer.parseInt(request.getParameter("ticketId")));
        ticket.setClosed(true);
        ticket.setDeleted(false);
        ticket.setClosedDate(new Timestamp(System.currentTimeMillis()));
        ticket.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        ticketService.updateTicket(ticket);
        response.sendRedirect("TicketController?action=getAllTickets");
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Ticket ticket = new Ticket();
        ticket.setUserId(Integer.parseInt(request.getParameter("userId")));
        ticket.setTitle(request.getParameter("title"));
        ticket.setDescription(request.getParameter("description"));
        ticket.setClosed(false);
        ticket.setDeleted(false);
        ticket.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        ticket.setNew(true);
        ticketService.createTicket(ticket);
        int createdTicketId = ticketService.getAllTickets().stream().mapToInt(Ticket::getId).max().orElse(0);
        ticketService.addMessageToTicket(new Message(0, createdTicketId, ticket.getUserId(), ticket.getDescription(), new Timestamp(System.currentTimeMillis()), false));
        response.sendRedirect("TicketController?action=getMessages&ticketId=" + createdTicketId);
    }

    private void getTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ticket ticket = ticketService.getTicketById(id);
        request.setAttribute("ticket", ticket);
        try {
            request.getRequestDispatcher("/admin_tickets.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllTickets(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<Ticket> tickets = ticketService.getAllTickets();

        //get the user id from the session
        int userId = (int) request.getSession().getAttribute("userId");

        //check if user is admin
        boolean isAmdin =  CoreAPI.getInstance().getPermissionRegisterService().hasPermission(userId, "admin");

        //filter tickets based on user id
        if (!isAmdin) {
            tickets = tickets.stream().filter(ticket -> ticket.getUserId() == userId).toList();
        }

        request.setAttribute("tickets", tickets);
        request.setAttribute("openTickets", ticketService.getAllTickets().stream().filter(ticket -> !ticket.isClosed()).count());
        request.setAttribute("closedTickets", ticketService.getAllTickets().stream().filter(Ticket::isClosed).count());
        try {
            request.getRequestDispatcher("/admin_tickets.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(request.getParameter("id")));
        ticket.setUserId(Integer.parseInt(request.getParameter("userId")));
        ticket.setTitle(request.getParameter("title"));
        ticket.setDescription(request.getParameter("description"));
        ticket.setClosed(Boolean.parseBoolean(request.getParameter("isClosed")));
        ticket.setDeleted(Boolean.parseBoolean(request.getParameter("isDeleted")));
        ticket.setCreatedDate(Timestamp.valueOf(request.getParameter("createdDate")));
        ticket.setClosedDate(Timestamp.valueOf(request.getParameter("closedDate")));
        ticket.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        ticket.setNew(Boolean.parseBoolean(request.getParameter("isNew")));
        ticketService.updateTicket(ticket);
        response.sendRedirect("TicketController?action=getAllTickets");
    }

    private void deleteTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("ticketId"));
        ticketService.deleteTicket(id);
        response.sendRedirect("TicketController?action=getAllTickets");
    }

    private void addMessage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Message message = new Message();
        message.setTicketId(Integer.parseInt(request.getParameter("ticketId")));
        message.setSenderId(Integer.parseInt(request.getParameter("senderId")));
        message.setMessage(request.getParameter("message"));
        message.setSentTime(new Timestamp(System.currentTimeMillis()));
        message.setSenderAdmin(Boolean.parseBoolean(request.getParameter("isSenderAdmin")));
        ticketService.addMessageToTicket(message);
        response.sendRedirect("TicketController?action=getMessages&ticketId=" + message.getTicketId());
    }

    private void getMessages(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        List<Message> messages = ticketService.getMessagesForTicket(ticketId);
        request.setAttribute("messages", messages);
        try {
            request.getRequestDispatcher("/ticket_view.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}