package taskdb.taskdb.application.auth.port.out;

public interface SaveRefreshTokenPort {
    void save(String key, String value);
}
