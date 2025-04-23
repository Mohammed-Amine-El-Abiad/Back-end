package fst.GestionRessource.User.model;

public enum Role {
  TEACHER,            // Enseignants
  DEPARTMENT_HEAD,    // Chef de département
  RESOURCE_MANAGER,   // Responsable des ressources
  SUPPLIER,           // Fournisseurs
  TECHNICIAN,         // Service de maintenance (techniciens)
  SUPER_ADMIN         // Administrateur (implicite)
}
