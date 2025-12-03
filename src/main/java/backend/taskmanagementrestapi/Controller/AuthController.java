package backend.taskmanagementrestapi.Controller;

import backend.taskmanagementrestapi.Dto.UserSignUp;
import backend.taskmanagementrestapi.Service.AppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Handler", description = "Handling Authentication Requests")
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUp userSignUp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(userSignUp));
    }

}
