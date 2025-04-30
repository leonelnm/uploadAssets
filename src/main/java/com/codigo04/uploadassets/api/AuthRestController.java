package com.codigo04.uploadassets.api;

import com.codigo04.uploadassets.api.dto.BaseResponse;
import com.codigo04.uploadassets.api.dto.LoginRequest;
import com.codigo04.uploadassets.api.dto.LoginResponse;
import com.codigo04.uploadassets.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try{
            String token = authService.login(loginRequest.username(), loginRequest.password());
            return ResponseEntity.ok(new LoginResponse(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse("Invalid username or password"));
        }
    }


}
