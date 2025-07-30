package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ChangeRoleRequest;
import org.example.dto.LoginRequest;
import org.example.dto.JwtResponse;
import org.example.dto.RegisterRequest;
import org.example.dto.RefreshJwtRequest;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        final JwtResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        final JwtResponse token = authService.register(registerRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/logout")
    public ResponseEntity<JwtResponse> logout(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.logout(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/change-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> changeRole(@RequestBody ChangeRoleRequest request) {
        authService.changeRoleToUser(request);
        return ResponseEntity.ok("новая роль назначена пользователю");
    }


}
