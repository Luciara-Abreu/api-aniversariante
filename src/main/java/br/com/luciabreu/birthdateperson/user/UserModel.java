package br.com.luciabreu.birthdateperson.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(unique = true)
  private String name;
  private String lastname;
  private String email;
  private String birthdate;
  private String sexualOrientation;
  private String fone;
  private String avatar;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
