package rs.vakcinacija.zajednicko.email.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class SendEmailRequest {
    private String to;
    private String subject;
    private String content;
}
