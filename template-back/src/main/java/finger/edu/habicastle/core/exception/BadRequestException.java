package finger.edu.habicastle.core.exception;

import lombok.Getter;
import finger.edu.habicastle.core.utils.TCorpMap;

@Getter
public class BadRequestException extends RuntimeException {
    String code;
    TCorpMap data;

    public BadRequestException(String msg, String code){super(msg);this.code=code;}
    public BadRequestException(String msg, String code, TCorpMap data){super(msg);this.code=code;this.data=data;}
}
