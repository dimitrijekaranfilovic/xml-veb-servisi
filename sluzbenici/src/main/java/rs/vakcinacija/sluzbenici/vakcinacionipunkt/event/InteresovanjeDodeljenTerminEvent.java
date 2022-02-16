package rs.vakcinacija.sluzbenici.vakcinacionipunkt.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.vakcinacija.zajednicko.rabbitmq.event.AbstractMessageEvent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InteresovanjeDodeljenTerminEvent {
    private UUID interesovanjeId;
    private Date dodeljeniDatum;
}
