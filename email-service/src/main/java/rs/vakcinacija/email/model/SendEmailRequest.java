package rs.vakcinacija.email.model;

import lombok.Data;

@Data
public class SendEmailRequest {
    private String to;
    private String subject;
    private String content;
}
