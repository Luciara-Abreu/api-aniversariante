package br.com.luciabreu.birthdateperson.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/adms")
public class AdmController {

  @Autowired
  private IAdmRepository admRepsitory;

  // Crear ADM
  @PostMapping("/")
  public ResponseEntity create(@RequestBody AdmModel admModel) {
    var adm = this.admRepsitory.findByName(admModel.getName());
    var email = this.admRepsitory.findByEmail(admModel.getEmail());
    var userName = this.admRepsitory.findByUserName(admModel.getUserName());

    if (adm != null || email != null || userName != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adm já cadastrado");
    }

    var passwordHasherd = BCrypt.withDefaults().hashToString(12, admModel.getPassword().toCharArray());
    admModel.setPassword(passwordHasherd);

    var admCreated = this.admRepsitory.save(admModel);
    return ResponseEntity.status(HttpStatus.OK).body(admCreated);
  }

}
