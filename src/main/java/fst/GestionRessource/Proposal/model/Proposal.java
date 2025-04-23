package fst.GestionRessource.Proposal.model;

import java.time.LocalDate;

import fst.GestionRessource.CallForTender.model.CallForTender;
import fst.GestionRessource.Supplier.model.Supplier;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Proposal {
  @Id
  private String id;
  private String resourceType;
  private String brand;
  private Double unitPrice;
  private Integer quantity;
  private Double totalPrice;
  private LocalDate deliveryDate;
  private Integer warrantyMonths;
  private Boolean accepted;

  @ManyToOne
  @JoinColumn(name = "supplierId")
  private Supplier supplier;

  @OneToOne(mappedBy = "selectedProposal")
  private CallForTender selectedBy;

  @ManyToOne
  @JoinColumn(name = "callForTenderId")
  private CallForTender callForTender;
}
