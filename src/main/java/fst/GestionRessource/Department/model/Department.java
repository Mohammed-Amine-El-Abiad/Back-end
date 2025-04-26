package fst.GestionRessource.Department.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fst.GestionRessource.Resource.model.Resource;
import fst.GestionRessource.ResourceRequest.model.ResourceRequest;
import fst.GestionRessource.User.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resourceRequests"})
@ToString(exclude = {"user", "resources", "resourceRequests"})
public class Department {
  @Id
  private String id;
  private String name;

  @JsonIgnoreProperties({"department", "departmentHead", "callForTenders", "resources", "resourceRequests", "panicReports", "notifications", "maintenanceRecords"})
  @OneToOne
  @JoinColumn(name = "headId")
  private User head;

  @JsonIgnoreProperties({"department", "departmentHead", "callForTenders", "resources", "resourceRequests", "panicReports", "notifications", "maintenanceRecords"})
  @OneToOne(mappedBy = "department")
  private User user;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Resource> resources;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ResourceRequest> resourceRequests;
}
