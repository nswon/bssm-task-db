package taskdb.taskdb.infrastructure.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import taskdb.taskdb.infrastructure.security.auth.CustomUserDetails;

public class SecurityUtil {
    public static String getLoginUserEmail() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
