package fst.GestionRessource.PanicReport.model;

public enum Status {
  OPEN,        // Le rapport est nouvellement créé et en attente de traitement
  IN_PROGRESS, // Le problème est en cours de résolution
  RESOLVED,    // Le problème a été résolu
  CLOSED       // Le rapport est clos (plus d'actions nécessaires)
}
