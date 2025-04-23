package fst.GestionRessource.Utils;

import java.util.UUID;

public class IdGenerator {
    public static String generateId(String prefix) {
        return prefix + UUID.randomUUID().toString();
    }
}
