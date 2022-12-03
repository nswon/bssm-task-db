package taskdb.taskdb.global.support;

import lombok.Getter;

@Getter
public class Result<T> {
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }
}
