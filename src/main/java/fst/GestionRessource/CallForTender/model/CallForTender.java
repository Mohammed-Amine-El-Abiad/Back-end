package fst.GestionRessource.CallForTender.model;

import java.time.LocalDate;
import java.util.List;

import fst.GestionRessource.Proposal.model.Proposal;
import fst.GestionRessource.User.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallForTender {  // Appel d'offre
  @Id
  private String id;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;

  @ManyToOne
  @JoinColumn(name = "resourceManagerId", nullable = false)
  private User resourceManager;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "selectedProposalId")
  private Proposal selectedProposal;

  @OneToMany(mappedBy = "callForTender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Proposal> proposals;
}
