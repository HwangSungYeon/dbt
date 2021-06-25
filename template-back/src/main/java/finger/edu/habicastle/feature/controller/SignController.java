package finger.edu.habicastle.feature.controller;

import finger.edu.habicastle.core.utils.TCorpMap;
import finger.edu.habicastle.feature.dto.UserDto;
import finger.edu.habicastle.feature.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class SignController {

    private final SignService signService;

    @PostMapping(value = "/login")
    public TCorpMap login(@ModelAttribute(name = "loginInfo") UserDto.LoginInfo loginInfo) {
        TCorpMap result = signService.Login(loginInfo);

        return result;
    }
}
