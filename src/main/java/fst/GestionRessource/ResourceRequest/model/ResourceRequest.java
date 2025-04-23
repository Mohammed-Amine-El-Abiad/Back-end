package fst.GestionRessource.ResourceRequest.model;

import fst.GestionRessource.Departement.model.Departement;
import fst.GestionRessource.User.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceRequest {
  @Id
  private String id;
  private String resourceType;
  private String specifications;
  private Status status;

  @ManyToOne
  @JoinColumn(name = "teacherId")
  private User teacher;

  @ManyToOne
  @JoinColumn(name = "departementId")
  private Departement departement;
}
