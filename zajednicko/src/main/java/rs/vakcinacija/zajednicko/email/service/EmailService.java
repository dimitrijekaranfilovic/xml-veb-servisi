package rs.vakcinacija.zajednicko.email.service;

import rs.vakcinacija.zajednicko.email.model.SendEmailRequest;
import rs.vakcinacija.zajednicko.rabbitmq.MessageBroker;

public class EmailService {
    private final MessageBroker messageBroker;

    public EmailService(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void sendEmail(SendEmailRequest request) {
        messageBroker.send("email-queue", request);
    }
}
