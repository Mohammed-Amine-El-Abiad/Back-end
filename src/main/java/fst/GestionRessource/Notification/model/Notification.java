package fst.GestionRessource.Notification.model;

import java.time.LocalDate;

import fst.GestionRessource.User.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
  @Id
  private String id;
  private String message;
  private LocalDate sentDate;
  private Boolean seen;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
}
