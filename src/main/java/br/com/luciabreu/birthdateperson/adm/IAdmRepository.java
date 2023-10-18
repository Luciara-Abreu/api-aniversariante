package br.com.luciabreu.birthdateperson.adm;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdmRepository extends JpaRepository<AdmModel, UUID> {
  Optional<AdmModel> findById(UUID id);
  AdmModel findByName(String name);
  AdmModel findByUserName(String userName);
  AdmModel findByEmail(String email);
}
