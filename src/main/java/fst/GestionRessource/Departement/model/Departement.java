package fst.GestionRessource.Departement.model;

import java.util.List;


import fst.GestionRessource.Resource.model.Resource;
import fst.GestionRessource.ResourceRequest.model.ResourceRequest;
import fst.GestionRessource.User.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"resources", "resourceRequests"})
public class Departement {
  @Id
  private String id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "headId")
  private User head;

  @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Resource> resources;

  @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ResourceRequest> resourceRequests;
}
