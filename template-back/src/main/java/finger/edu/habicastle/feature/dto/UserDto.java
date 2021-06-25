package finger.edu.habicastle.feature.dto;

import lombok.*;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Register {

        private String userId;
        private String userPassword;
        private String userName;

        @Builder
        public Register(String userId, String userPassword, String userName) {
            this.userId = userId;
            this.userPassword = userPassword;
            this.userName = userName;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginInfo {
        private String userId;
        private String userPassword;

        @Builder
        public LoginInfo(String userId, String userPassword) {
            this.userId = userId;
            this.userPassword = userPassword;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {
        private Integer userKey;
        private String userId;
        private String userPrePassword;
        private String userNewPassword;
        private String userPreName;
        private String userNewName;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Delete {
        private Integer userKey;
        private String userPassword;
    }
}
