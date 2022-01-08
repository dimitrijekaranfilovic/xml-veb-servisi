package rs.vakcinacija.sluzbenici.config.error;


import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorObject {

    @XmlElement(name = "status")
    private Integer status;

    @XmlElement(name = "reason")
    private String reason;

    @XmlElement(name = "path")
    private String path;

    @XmlElement(name = "timestamp")
    @XmlSchemaType(name = "date")
    private Date timestamp;


    @XmlElement(name = "message")
    private String message;


    public ErrorObject(HttpStatus status, String path, Date timestamp, String message) {
        this.status = status.value();
        this.reason = status.getReasonPhrase();
        this.path = path;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ErrorObject() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
