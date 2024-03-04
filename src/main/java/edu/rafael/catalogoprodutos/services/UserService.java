package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.RoleDto;
import edu.rafael.catalogoprodutos.dto.UserDto;
import edu.rafael.catalogoprodutos.dto.UserInsertDto;
import edu.rafael.catalogoprodutos.dto.UserUpdateDto;
import edu.rafael.catalogoprodutos.entities.Role;
import edu.rafael.catalogoprodutos.entities.User;
import edu.rafael.catalogoprodutos.repositories.RoleRepository;
import edu.rafael.catalogoprodutos.repositories.UserRepository;
import edu.rafael.catalogoprodutos.services.exceptions.DatabaseException;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(x -> new UserDto(x));
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.orElseThrow(() -> new EntitiesNotFoundException("Entidade não encontrada!!!"));
        return new UserDto(user);
    }

    @Transactional
    public UserDto save(UserInsertDto userDto) {
        User user = new User();
        copyDtoToEntity(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return new UserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto userDto) {
        try {
            User user = userRepository.getReferenceById(id);
            copyDtoToEntity(userDto, user);
            return new UserDto(userRepository.save(user));
        } catch (EntityNotFoundException e){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!userRepository.existsById(id)){
            throw new EntitiesNotFoundException("Id " + id + " não existe!!!");
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial, esse usuário não pode ser deletado!!!");
        }
    }

    private void copyDtoToEntity(UserDto userDto, User user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.getRoles().clear();
        for (RoleDto roleDto : userDto.getRoles()){
            Role role = roleRepository.getReferenceById(roleDto.getId());
            user.getRoles().add(role);
        }
    }
}
