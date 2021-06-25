package finger.edu.habicastle.core.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import finger.edu.habicastle.core.exception.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected TCorpMap badRequestException(HttpServletRequest request, BadRequestException e) {

        TCorpMap result = new TCorpMap();
        result.put("Code", e.getCode());
        result.put("Message", e.getMessage());
        if (e.getData() != null) {
            for (String key : e.getData().keySet()) {
                result.put(key, e.getData().get(key));
            }
        }

        return result;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected TCorpMap notfoundException(HttpServletRequest request, NotFoundException e) {

        TCorpMap result = new TCorpMap();
        result.put("Code", e.getCode());
        result.put("Message", e.getMessage());
        if(e.getData() !=null) {
            for (String key : e.getData().keySet()) {
                result.put(key, e.getData().get(key));
            }
        }

        return result;
    }
}

