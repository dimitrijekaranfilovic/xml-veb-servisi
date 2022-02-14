package rs.vakcinacija.sluzbenici.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xmldb.api.base.XMLDBException;
import rs.vakcinacija.sluzbenici.config.exception.BusinessException;
import rs.vakcinacija.sluzbenici.config.exception.ValidationException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.PunktExistsException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineDoesntExistException;
import rs.vakcinacija.sluzbenici.vakcinacionipunkt.exception.VaccineExistsException;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.exception.SchemaValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SchemaValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleSchemaValidationException(HttpServletRequest request, SchemaValidationException e) {
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler(PunktExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handlePunktExistsException(HttpServletRequest request, PunktExistsException e){
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }
    @ExceptionHandler(VaccineExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleVaccineExistsException(HttpServletRequest request, VaccineExistsException e){
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler(VaccineDoesntExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleVaccineDoesntExistException(HttpServletRequest request, VaccineDoesntExistException e){
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler(XMLDBException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorObject handleXmlDbException(HttpServletRequest request, XMLDBException e) {
        return new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler({DocumentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorObject handleDocumentNotFoundException(HttpServletRequest request, DocumentNotFoundException e) {
        return new ErrorObject(HttpStatus.NOT_FOUND, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleValidationException(HttpServletRequest request, ValidationException e) {
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleValidationException(HttpServletRequest request, BusinessException e) {
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }
}
