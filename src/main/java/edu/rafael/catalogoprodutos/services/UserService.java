package edu.rafael.catalogoprodutos.services;

import edu.rafael.catalogoprodutos.dto.RoleDto;
import edu.rafael.catalogoprodutos.dto.UserDto;
import edu.rafael.catalogoprodutos.dto.UserInsertDto;
import edu.rafael.catalogoprodutos.dto.UserUpdateDto;
import edu.rafael.catalogoprodutos.entities.Role;
import edu.rafael.catalogoprodutos.entities.User;
import edu.rafael.catalogoprodutos.projections.UserDetailsProjection;
import edu.rafael.catalogoprodutos.repositories.RoleRepository;
import edu.rafael.catalogoprodutos.repositories.UserRepository;
import edu.rafael.catalogoprodutos.services.exceptions.DatabaseException;
import edu.rafael.catalogoprodutos.services.exceptions.EntitiesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserDto::new);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.orElseThrow(() -> new EntitiesNotFoundException("Entidade não encontrada!!!"));
        return new UserDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto findUserLogged() {
        User user = authService.authenticated();
        return new UserDto(user);
    }

    @Transactional
    public UserDto save(UserInsertDto userDto) {
        User user = new User();
        copyDtoToEntity(userDto, user);
        user.getRoles().clear();
        Role role = roleRepository.findByAuthority("ROLE_OPERATOR");
        user.getRoles().add(role);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }
        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
