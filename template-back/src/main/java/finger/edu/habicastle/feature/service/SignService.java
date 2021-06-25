package finger.edu.habicastle.feature.service;

import finger.edu.habicastle.core.exception.BadRequestException;
import finger.edu.habicastle.core.exception.NotFoundException;
import finger.edu.habicastle.core.utils.TCorpMap;
import finger.edu.habicastle.feature.config.JwtTokenProvider;
import finger.edu.habicastle.feature.domain.User;
import finger.edu.habicastle.feature.dto.UserDto;
import finger.edu.habicastle.feature.persistance.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TCorpMap Login(UserDto.LoginInfo loginInfo) {
        TCorpMap result = new TCorpMap();
        User user = userRepo.findByUserId(loginInfo.getUserId()).orElseThrow(()->new NotFoundException("Login Failed - Id Not Found", "10"));

        if(passwordEncoder.matches(loginInfo.getUserPassword(), user.getUserPassword())) {
            result.put("Code", "00");
            result.put("Message", "Success");
            result.put("userId", user.getUserId());
            result.put("SessionToken", jwtTokenProvider.createToken(String.valueOf(user.getUserId()), "user"));
        } else throw new NotFoundException("Login Failed - Not Matched Password", "11");

        return result;
    }
}
