package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.LoginDTO;
import Team6.EpicEnergyBackEnd.DTO.UserDTO;
import Team6.EpicEnergyBackEnd.DTO.UserLoginDTO;
import Team6.EpicEnergyBackEnd.exceptions.BadRequestException;
import Team6.EpicEnergyBackEnd.models.User;
import Team6.EpicEnergyBackEnd.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public LoginDTO login(@RequestBody UserLoginDTO body) {
        return new LoginDTO(authService.authUserAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated UserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.authService.save(body);
    }
}
