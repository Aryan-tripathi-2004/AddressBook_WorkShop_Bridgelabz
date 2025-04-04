package com.example.addressbook.controller;

import com.example.addressbook.interfaces.IUserAuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.addressbook.dto.*;
import com.example.addressbook.exception.UserException;
import com.example.addressbook.dto.UserAuthenticationDTO;

/**
 * Controller for User Authentication operations.
 * Provides endpoints to manage user authentication data.
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")    // Base URL for User Authentication API
public class UserAuthenticationController {

    // UserAuthenticationService instance to perform CRUD operations on user authentication data.
    @Autowired
    IUserAuthenticationService userAuthenticationService;

    /**
     * Endpoint to register a new user.
     * @param userDTO - The UserAuthenticationDTO object containing user details.
     * @return ResponseEntity with created UserAuthenticationDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<?>> register(@Valid @RequestBody UserAuthenticationDTO userDTO) {
        log.info("Registering user with email: {}", userDTO.getEmail());
        try {
            UserAuthenticationDTO user = userAuthenticationService.register(userDTO);
            ResponseDTO<UserAuthenticationDTO> responseUserDTO = new ResponseDTO<UserAuthenticationDTO>("User details is submitted!", user);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("User details is not submitted!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Endpoint to login a user.
     * @param loginDTO - The LoginDTO object containing login details.
     * @return ResponseEntity with JWT sessionToken
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@Valid @RequestBody LoginDTO loginDTO) {
        log.info("Logging in user with email: {}", loginDTO.getEmail());
        try {
            String result = userAuthenticationService.login(loginDTO);
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Login successfully!!", result);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
        } catch (UserException e) {
            log.error("Error logging in user: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Login failed!!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to logout a user.
     * @param sessionToken - The JWT sessionToken of the user to be logged out.
     * @return ResponseEntity with logout message
     */
    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO<?>> logout(@RequestHeader String sessionToken) {
        log.info("Logging out user with sessionToken: {}", sessionToken);
        try {
            String result = userAuthenticationService.logout(sessionToken);
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Logout successfully!!", result);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
        } catch (UserException e) {
            log.error("Error logging out user: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Logout failed!!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Endpoint to reset password for a user.
     * @param resetToken - The JWT resetToken of the user whose password is to be reset.
     * @param resetPasswordDTO - The ResetPasswordDTO object containing new password details.
     * @return ResponseEntity with reset password message
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDTO<?>> resetPassword(@RequestHeader String resetToken, @Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        log.info("Resetting password for user with resetToken: {}", resetToken);
        try {
            if(!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
                log.error("New password and confirm password do not match");
                ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Password reset failed!!", "New password and confirm password do not match");
                return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
            }

            String result = userAuthenticationService.resetPassword(resetToken, resetPasswordDTO);
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Password reset successfully!!", result);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
        } catch (UserException e) {
            log.error("Error resetting password: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Password reset failed!!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Endpoint to handle forgot password request.
     * @param forgotPasswordDTO - The ForgotPasswordDTO object containing email details.
     * @return ResponseEntity with forgot password message
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO<?>> forgotPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        log.info("Processing forgot password for email: {}", forgotPasswordDTO.getEmail());
        try {
            String result = userAuthenticationService.forgotPassword(forgotPasswordDTO);
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Forgot password successfully!!", result);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
        } catch (UserException e) {
            log.error("Error processing forgot password: {}", e.getMessage());
            ResponseDTO<String> responseUserDTO = new ResponseDTO<String>("Forgot password failed!!", e.getMessage());
            return new ResponseEntity<>(responseUserDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
