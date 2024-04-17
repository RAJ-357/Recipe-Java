package com.example.demo.View;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.ErrorResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @SuppressWarnings("null")
    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        HttpStatus httpStatus = getStatus(request);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, errorMessage);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    public String getErrorPath() {
        return "/error";
    }
}
