package fst.GestionRessource.Computer.model;

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
public class Computer extends Resource {
  private String cpu;
  private String ram;
  private String hardDisk;
  private String monitor;
}
