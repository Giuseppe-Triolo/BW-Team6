package Team6.EpicEnergyBackEnd.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(

        @NotEmpty(message = "Username required")
         String username,

         @NotEmpty(message = "Email required")
         @Email(message = "Invalid email")
         String email,

         @NotEmpty(message = "Password required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
         String password,

         @NotEmpty(message = "Name required")
         String name,

         @NotEmpty(message = "Surname required")
         String surname
) {
}
