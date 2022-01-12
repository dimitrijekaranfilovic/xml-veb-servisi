package rs.vakcinacija.zajednicko.data.context;

import lombok.extern.slf4j.Slf4j;
import rs.vakcinacija.zajednicko.exception.SchemaValidationException;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

@Slf4j
public class SchemaValidationEventHandler implements ValidationEventHandler {
    @Override
    public boolean handleEvent(ValidationEvent event) {

        // Ako nije u pitanju WARNING metoda vraća false
        if (event.getSeverity() != ValidationEvent.WARNING) {
            ValidationEventLocator validationEventLocator = event.getLocator();
            String message = "ERROR: Line "
                    + validationEventLocator.getLineNumber() + ": Col"
                    + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage();
            log.error(message);
            throw new SchemaValidationException(message);
        } else {
            ValidationEventLocator validationEventLocator = event.getLocator();
            log.info("WARNING: Line "
                    + validationEventLocator.getLineNumber() + ": Col"
                    + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage());

            // Nastavlja se dalje izvršavanje
            return true;
        }
    }
}
