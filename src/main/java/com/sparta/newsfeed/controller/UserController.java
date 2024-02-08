package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("users/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupRequest request, BindingResult bindingResult) {
        System.out.println("UserController.signup");
        String errorMessages = "";
        if (bindingResult.hasFieldErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessages += fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        userService.signup(request);

        return ResponseEntity.ok("회원가입 성공");
    }

}
