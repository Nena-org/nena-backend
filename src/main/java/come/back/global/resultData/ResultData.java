package come.back.global.resultData;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ResultData<T>(String resultCode, @JsonIgnore int statusCode, String msg, T data) {
    public ResultData(String resultCode, String msg) {
        this(resultCode, msg, null);
    }

    public ResultData(String resultCode, String msg, T data) {
        this(resultCode, Integer.parseInt(resultCode.split("-", 2)[0]), msg, data);
    }
}
