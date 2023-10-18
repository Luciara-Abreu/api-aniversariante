package br.com.luciabreu.birthdateperson.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luciabreu.birthdateperson.utils.Utils;



@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  // register user
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {

    var user = this.userRepository.findByName(userModel.getName());
    var email = this.userRepository.findByEmail(userModel.getEmail());
    var fone = this.userRepository.findByFone(userModel.getFone());

    if (user != null || fone != null || email != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
    }

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userCreated);

  }

  // List users
  @GetMapping("/")
  public List<UserModel> list() {
    var users = userRepository.findAll();
    return users;
  }

  // Endpoint para atualizar os dados do usuário pelo ID
  @PutMapping("/{id}")
  public ResponseEntity updateUser(@PathVariable UUID id, @RequestBody UserModel userModel) {
    var user = userRepository.findById(id);

    if (user == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
    }

    // Copia propriedades não nulas do objeto atualizado para o objeto existente
    Utils.copyNonNullProperties(userModel, user);

    // Salva a entidade de usuário atualizada no banco de dados
    var userUpdated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
  }

}
