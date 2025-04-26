package fst.GestionRessource.Printer.model;

import fst.GestionRessource.Resource.model.Resource;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Printer extends Resource {
  private String resolution;
  private String printSpeed;
}
