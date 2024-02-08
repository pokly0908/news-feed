package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.user.UserProfileRequest;
import com.sparta.newsfeed.dto.user.UserProfileResponse;
import com.sparta.newsfeed.dto.user.UserSignupRequest;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupRequest request, BindingResult bindingResult) {

        String errorMessages = "";
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessages += fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        userService.signup(request);

        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/users/profile")
    public ResponseEntity<UserProfileResponse> getProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponse response = userService.getProfile(userDetails.getUser());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/users/profile")
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UserProfileRequest request,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String errorMessages = "";
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessages += fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        UserProfileResponse response = userService.updateProfile(userDetails.getUser(), request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
