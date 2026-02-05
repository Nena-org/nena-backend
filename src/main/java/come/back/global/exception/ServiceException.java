package come.back.global.exception;

import come.back.global.resultData.ResultData;

public class ServiceException extends RuntimeException {
    private final String resultCode;
    private final String msg;

    public ServiceException(String resultCode, String msg) {
        super(resultCode + " : " + msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public ResultData<Void> getResultData() {
        return new ResultData<>(resultCode, msg, null);
    }
}
