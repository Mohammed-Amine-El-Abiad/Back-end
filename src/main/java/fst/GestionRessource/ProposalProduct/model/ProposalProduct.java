package fst.GestionRessource.ProposalProduct.model;

import fst.GestionRessource.Proposal.model.Proposal;
import fst.GestionRessource.Resource.model.ResourceType;
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
public class ProposalProduct {
  @Id
  private String id;
  private ResourceType type;
  private String brand;
  private Integer quantity;
  private Double unitPrice;

  @ManyToOne
  @JoinColumn(name = "proposalId")
  private Proposal proposal;
}
