package come.back.global.globalExceptionHandler;

import come.back.global.exception.ServiceException;
import come.back.global.resultData.ResultData;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResultData<Void>> handle(NoSuchElementException ex) {
        return new ResponseEntity<>(
                new ResultData<>(
                        "404-1",
                        "해당 데이터가 존재하지 않습니다."
                ),
                NOT_FOUND
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultData<Void>> handle(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String field = violation.getPropertyPath().toString().split("\\.", 2)[1];
                    String[] messageTemplateBits = violation.getMessageTemplate()
                            .split("\\.");
                    String code = messageTemplateBits[messageTemplateBits.length - 2];
                    String _message = violation.getMessage();

                    return "%s-%s-%s".formatted(field, code, _message);
                })
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(
                new ResultData<>(
                        "400-1",
                        message
                ),
                BAD_REQUEST
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<Void>> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .map(error -> error.getField() + "-" + error.getCode() + "-" + error.getDefaultMessage())
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(
                new ResultData<>(
                        "400-1",
                        message
                ),
                BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultData<Void>> handle(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                new ResultData<>(
                        "400-1",
                        "요청 본문이 올바르지 않습니다."
                ),
                BAD_REQUEST
        );
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResultData<Void>> handle(MissingRequestHeaderException ex) {
        return new ResponseEntity<>(
                new ResultData<>(
                        "400-1",
                        "%s-%s-%s".formatted(
                                ex.getHeaderName(),
                                "NotBlank",
                                ex.getLocalizedMessage()
                        )
                ),
                BAD_REQUEST
        );
    }

    @ExceptionHandler(ServiceException.class)
    public ResultData<Void> handle(ServiceException ex, HttpServletResponse response) {
        ResultData<Void> rsData = ex.getResultData();

        response.setStatus(rsData.statusCode());

        return rsData;
    }

}
