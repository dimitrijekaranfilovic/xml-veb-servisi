package rs.vakcinacija.imunizacija.config.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xmldb.api.base.XMLDBException;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.exception.SchemaValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(SchemaValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleSchemaValidationException(HttpServletRequest request, SchemaValidationException e){
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorObject handleNoSuchElementException(HttpServletRequest request, NoSuchElementException e){
        return new ErrorObject(HttpStatus.BAD_REQUEST, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler(XMLDBException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorObject handleXmlDbException(HttpServletRequest request, XMLDBException e){
        return new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler({DocumentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorObject handleDocumentNotFoundException(HttpServletRequest request, DocumentNotFoundException e){
        return new ErrorObject(HttpStatus.NOT_FOUND, request.getServletPath(), new Date(), e.getMessage());
    }

}
