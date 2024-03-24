package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.UserDto;
import edu.rafael.catalogoprodutos.dto.UserInsertDto;
import edu.rafael.catalogoprodutos.dto.UserUpdateDto;
import edu.rafael.catalogoprodutos.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable){
        Page<UserDto> users = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(users);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> finById(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserInsertDto userDto){
        UserDto newUserDto = userService.save(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newUserDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto){
        UserDto newUserDto = userService.update(id, userDto);
        return ResponseEntity.ok().body(newUserDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
