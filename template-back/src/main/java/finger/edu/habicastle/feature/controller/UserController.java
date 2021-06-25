package finger.edu.habicastle.feature.controller;

import finger.edu.habicastle.core.utils.TCorpMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import finger.edu.habicastle.feature.dto.UserDto;
import finger.edu.habicastle.feature.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public TCorpMap getResult() {
        TCorpMap response = new TCorpMap();

        return response;
    }

    // 회원가입
    @PostMapping("/reg")
    public TCorpMap register(@ModelAttribute(name = "regData") UserDto.Register regData) {
        regData.setUserPassword(passwordEncoder.encode(regData.getUserPassword()));
        TCorpMap result = userService.register(regData);
        return result;
    }

    // 회원 정보 수정
    @PutMapping("/update")
    public TCorpMap update(@ModelAttribute(name = "update") UserDto.Update update) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getName().equals("anonymousUser")) {
            update.setUserKey(Integer.valueOf(authentication.getName()));
        }
        if(update.getUserNewPassword() != null){
            update.setUserNewPassword(passwordEncoder.encode(update.getUserNewPassword()));
        }
        TCorpMap result = new TCorpMap();
        if(update.getUserPrePassword() != null) {
            String password = userService.getPassword(update.getUserKey());
            if(!passwordEncoder.matches(update.getUserPrePassword(), password)) {
                result.put("Code", "21");
                result.put("Message", "Password Not Match");
            }
            else {
                result = userService.updateUser(update);
            }
        } else {
            result = userService.updateUser(update);
        }

        return result;
    }

    // 회원 정보 삭제
    @DeleteMapping("/delete")
    public TCorpMap delete(@ModelAttribute(name = "delete") UserDto.Delete delete) {
        TCorpMap result = new TCorpMap();

        String password = userService.getPassword(delete.getUserKey());
        if(!passwordEncoder.matches(delete.getUserPassword(), password)) {
            result.put("Code", "21");
            result.put("Message", "Password Not Match");
        } else{
            result = userService.deleteUser(delete);
        }

        return result;
    }
}
