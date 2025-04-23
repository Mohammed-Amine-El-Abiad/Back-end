package fst.GestionRessource.Resource.model;

import java.util.List;

import fst.GestionRessource.Departement.model.Departement;
import fst.GestionRessource.PanicReport.model.PanicReport;
import fst.GestionRessource.Supplier.model.Supplier;
import fst.GestionRessource.User.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"user", "departement"})
public class Resource {
  @Id
  private String inventoryNumber;
  private String type;
  private String brand;
  private String specifications;

  @ManyToOne
  @JoinColumn(name = "supplierId")
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "assignedToUser")
  private User user;

  @ManyToOne
  @JoinColumn(name = "assignedToDepartement")
  private Departement departement;

  @OneToMany(mappedBy = "resource")
  private List<PanicReport> panicReports;
}
