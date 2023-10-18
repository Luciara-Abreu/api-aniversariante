package br.com.luciabreu.birthdateperson.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  Optional<UserModel> findById(UUID id);

  UserModel findByName(String name);

  UserModel findByEmail(String email);

  UserModel findByFone(String fone);
}
