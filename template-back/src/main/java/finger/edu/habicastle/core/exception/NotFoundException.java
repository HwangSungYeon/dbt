package finger.edu.habicastle.core.exception;

import finger.edu.habicastle.core.utils.TCorpMap;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    String code;
    TCorpMap data;

    public NotFoundException(String msg, String code){super(msg);this.code=code;this.data=null;}
    public NotFoundException(String msg, String code, TCorpMap data){super(msg);this.code=code;this.data=data;}
}
