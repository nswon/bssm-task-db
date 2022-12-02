package taskdb.taskdb.global.support;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Result<T> {
    private final T data;

    @Builder
    public Result(T data) {
        this.data = data;
    }
}
