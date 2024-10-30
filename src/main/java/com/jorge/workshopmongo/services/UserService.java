package com.jorge.workshopmongo.services;

import com.jorge.workshopmongo.domain.User;
import com.jorge.workshopmongo.dto.UserDTO;
import com.jorge.workshopmongo.repository.UserRepository;
import com.jorge.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User newUser) {
        return userRepository.insert(newUser);
    }

    public void delete(String id) {
        findById(id);

        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User newObj = userRepository.findById(obj.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
