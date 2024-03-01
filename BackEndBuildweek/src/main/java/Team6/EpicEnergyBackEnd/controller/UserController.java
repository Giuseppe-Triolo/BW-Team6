package Team6.EpicEnergyBackEnd.controller;


import Team6.EpicEnergyBackEnd.DTO.RoleDTO;
import Team6.EpicEnergyBackEnd.models.User;
import Team6.EpicEnergyBackEnd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String order) {
        return this.userService.getUsers(page, size, order);
    }

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User uploadUser(@PathVariable UUID id, @RequestBody User updateUser) {
        return this.userService.update(id, updateUser);
    }

    @PutMapping("/profile")
    public User updateMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody User updateUser) {
        return this.uploadUser(currentUser.getId(), updateUser);
    }

    @DeleteMapping("/profile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMySelf(@AuthenticationPrincipal User currentUser) {
        this.userService.deleteUser(currentUser.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        this.userService.deleteUser(id);
    }

    @PatchMapping("/profile/upload")
    public User uploadAvatar(@AuthenticationPrincipal User currentUser, @RequestParam("avatar") MultipartFile image) throws IOException {
        return this.userService.uploadAvatar(currentUser, image);
    }

    @PatchMapping("/{UserId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateRole(@PathVariable UUID UserId, @RequestBody RoleDTO role) throws Exception {
        return this.userService.updateRole(UserId, role);
    }
}
