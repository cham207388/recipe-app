package com.abc.recipemainservice.feign;

import com.abc.recipemainservice.exception.BadRequestException;
import com.abc.recipemainservice.exception.ServerNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
    /**
     * @param methodKey is FeignclientName+invokeMethod
     * @param response  is response from server
     * @return and exception
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        String reason = response.reason();
        HttpStatus httpStatus = HttpStatus.valueOf(status);

        switch (status) {
            case 400:
                throw new BadRequestException(reason);
            case 404:
                if (methodKey.contains("findBy")) {
                    throw new ResponseStatusException(httpStatus, reason);
                }
                break;
            case 500:
                throw new ServerNotFoundException("The server is down");
            default:
                return new Exception(reason);
        }
        return new Exception(reason);
    }
}
