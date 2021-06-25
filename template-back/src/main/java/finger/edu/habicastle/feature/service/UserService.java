package finger.edu.habicastle.feature.service;

import finger.edu.habicastle.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import finger.edu.habicastle.core.utils.TCorpMap;
import finger.edu.habicastle.feature.dto.UserDto;
import finger.edu.habicastle.feature.domain.*;
import finger.edu.habicastle.feature.persistance.UserRepo;
import finger.edu.habicastle.core.exception.BadRequestException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private boolean chkRegisterData(String userId) {
        User chkResult = userRepo.findByUserId(userId).orElse(null);

        if (chkResult != null) {
            TCorpMap result = new TCorpMap();
            result.put("userId",chkResult.getUserId());
            throw new BadRequestException("userExists","06",result);
        } else {
            return true;
        }
    }

    public String getPassword(Integer userKey) {
        User user = userRepo.findByUserKey(userKey).orElseThrow(() -> new NotFoundException("NotFound User","05"));
        return user.getUserPassword();
    }

    @Transactional
    public TCorpMap register(UserDto.Register register) {

        TCorpMap result = new TCorpMap();

        if(this.chkRegisterData(register.getUserId())) {
            User user = User.builder()
                    .userId(register.getUserId())
                    .userPassword(register.getUserPassword())
                    .userName(register.getUserName())
                    .build();

            result.put("Code", "00");
            result.put("Message", "Success");
            result.put("Info", user);

            userRepo.save(user);
        } else throw new BadRequestException("Required Parameter Missing", "03");

        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    public TCorpMap updateUser(UserDto.Update update) {
        TCorpMap result = new TCorpMap();
        User user = userRepo.findByUserKey(update.getUserKey()).orElseThrow(()->new NotFoundException("NotFound User","05"));

        if(update.getUserNewName() != null && update.getUserNewPassword() != null){
            user.setUserPassword(update.getUserNewPassword());
            user.setUserName(update.getUserNewName());
            userRepo.save(user);

            result.put("Code", "00");
            result.put("Message", "Name and Password Changed Successfully");
        }  else if(update.getUserNewName() != null) {
            user.setUserPassword(update.getUserNewPassword());
            userRepo.save(user);

            result.put("Code", "00");
            result.put("Message", "Password Changed Successfully");
        }  else if(update.getUserNewPassword() != null) {
            user.setUserName(update.getUserNewName());
            userRepo.save(user);

            result.put("Code", "00");
            result.put("Message", "Name Changed Successfully");
        }  else throw new BadRequestException("Required Parameter Missing", "03");

        return result;
    }

    @Transactional
    public TCorpMap deleteUser(UserDto.Delete delete) {
        TCorpMap result = new TCorpMap();

        User user = userRepo.findByUserKey(delete.getUserKey()).orElseThrow(()->new NotFoundException("NotFound User","05"));

        userRepo.deleteByUserKey(user.getUserKey());

        result.put("Code", "00");
        result.put("Message", "Deleted Successfully");

        return result;
    }
}
