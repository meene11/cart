package zerobase.cart.dto;

import lombok.Data;
import zerobase.cart.domain.User;

public class Auth {
    @Data
    public static  class SignIn{
        private String userId;
        private String password;
    }

    @Data
    public static  class SignUp{
        private String userId;
        private String password;
        private String role;

        public User toEntity(){
            return User.builder()
                    .userId(this.userId)
                    .password(this.password)
                    .role(this.role)
                    .build();
        }
    }
}
