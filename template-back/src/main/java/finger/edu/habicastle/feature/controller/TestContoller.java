package finger.edu.habicastle.feature.controller;

import finger.edu.habicastle.core.utils.TCorpMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : SH Hong
 * @Date : 2021/06/23
 * @Comment :
 * @TODO : none
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestContoller {

    @GetMapping("")
    public TCorpMap getTestResult() {
        TCorpMap response = new TCorpMap();

        return response;
    }
}
