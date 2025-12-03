package backend.taskmanagementrestapi.Controller;

import backend.taskmanagementrestapi.Dto.UserLogin;
import backend.taskmanagementrestapi.Dto.UserResponse;
import backend.taskmanagementrestapi.Dto.UserSignUp;
import backend.taskmanagementrestapi.Service.AppUserService;
import backend.taskmanagementrestapi.Utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Handler", description = "Handling Authentication Requests")
public class AuthController {

    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping
    @Operation(summary = "Sign Up New User", description = "Sign Up New User")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUp userSignUp) {
        UserResponse user = appUserService.createUser(userSignUp);
        String token = jwtUtils.generateToken(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }

    @PostMapping("/login")
    @Operation(summary = "Login User", description = "Handle Login User")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        final String token = jwtUtils.generateToken(userLogin.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of ("Token", token));
    }

}
