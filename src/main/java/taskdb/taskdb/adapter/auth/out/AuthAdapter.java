package taskdb.taskdb.adapter.auth.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.auth.port.out.SaveRefreshTokenPort;
import taskdb.taskdb.domain.auth.entity.RefreshToken;

@Component
@RequiredArgsConstructor
public class AuthAdapter implements SaveRefreshTokenPort {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void save(String key, String value) {
        RefreshToken refreshToken = RefreshToken.of(key, value);
        refreshTokenRepository.save(refreshToken);
    }

    //TODO : findById
}
