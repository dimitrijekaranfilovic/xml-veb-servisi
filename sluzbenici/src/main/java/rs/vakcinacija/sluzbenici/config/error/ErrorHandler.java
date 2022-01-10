package rs.vakcinacija.sluzbenici.config.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xmldb.api.base.XMLDBException;
import rs.vakcinacija.sluzbenici.shared.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorObject handleNotFoundException(HttpServletRequest request, NotFoundException e){
        return new ErrorObject(HttpStatus.NOT_FOUND, request.getServletPath(), new Date(), e.getMessage());
    }

    @ExceptionHandler(XMLDBException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorObject handleXmlDbException(HttpServletRequest request, XMLDBException e){
        return new ErrorObject(HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), new Date(), e.getMessage());
    }

}
