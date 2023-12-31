package br.com.luciabreu.birthdateperson.adm;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_adms")
public class AdmModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(unique = true)
  private String name;
  private String userName;
  private String password;
  private String email;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
