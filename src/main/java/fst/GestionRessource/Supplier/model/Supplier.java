package fst.GestionRessource.Supplier.model;

import java.util.List;

import fst.GestionRessource.Resource.model.Resource;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
  @Id
	private String id;
	private String companyName;
	private String address;
	private String website;
	private String managerName;
	private boolean blacklisted;
  private String blacklistReason;

  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Resource> resources;
}
