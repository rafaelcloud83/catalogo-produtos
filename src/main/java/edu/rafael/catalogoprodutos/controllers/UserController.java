package edu.rafael.catalogoprodutos.controllers;

import edu.rafael.catalogoprodutos.dto.UserDto;
import edu.rafael.catalogoprodutos.dto.UserInsertDto;
import edu.rafael.catalogoprodutos.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable){
        Page<UserDto> users = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> finById(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserInsertDto userDto){
        UserDto newUserDto = userService.save(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newUserDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        userDto = userService.update(id, userDto);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
