package com.paololauria.cinema.api.restcontrollers;
import com.paololauria.cinema.dtos.AuthenticationRequestDto;
import com.paololauria.cinema.dtos.AuthenticationResponseDto;
import com.paololauria.cinema.dtos.ErrorResponseDto;
import com.paololauria.cinema.dtos.RegisterRequestDto;
import com.paololauria.cinema.model.exceptions.AuthErrorCode;
import com.paololauria.cinema.model.exceptions.DuplicateEmailException;
import com.paololauria.cinema.services.implementations.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Registers a new user.
     *
     * @param regRequestDto The registration request data.
     * @return ResponseEntity<?> with the registration response or a bad request with an error response.
     */
    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registration successful"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    public ResponseEntity<?> register
            (@RequestBody RegisterRequestDto regRequestDto){
        try {
            return ResponseEntity.ok(authenticationService.register(regRequestDto));
        } catch (DuplicateEmailException de) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto(AuthErrorCode.EMAIL_EXIST.toString(), AuthErrorCode.EMAIL_EXIST.getCode()));
        }
    }

    /**
     * Logs in an existing user.
     *
     * @param authRequestDto The authentication request data.
     * @return ResponseEntity<AuthenticationResponseDto> with the login response.
     */
    @PostMapping("/login")
    @Operation(
            summary = "Login an existing user",
            description = "Logs in an existing user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class))
                    )
            }
    )
    public ResponseEntity<AuthenticationResponseDto> login
            (@RequestBody AuthenticationRequestDto authRequestDto){
        return ResponseEntity.ok(authenticationService.login(authRequestDto));
    }

    /**
     * Refreshes the access token.
     *
     * @param request  The HTTPServletRequest.
     * @param response The HTTPServletResponse.
     * @throws IOException if an I/O exception occurs.
     */
    @PostMapping("/refresh-token")
    @Operation(
            summary = "Refresh access token",
            description = "Refreshes the access token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Token refreshed successfully"
                    )
            }
    )
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
