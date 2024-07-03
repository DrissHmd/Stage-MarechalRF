package com.marechalrf.marechalrfback.util;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateSecretKey {

    public static byte[] main() {
        // Génération d'une chaîne aléatoire de 256 bits (32 octets)
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);

        // Encodage de la chaîne en Base64 pour une représentation lisible
        String secretKey = Base64.getEncoder().encodeToString(bytes);

        System.out.println("SECRET_KEY: " + secretKey);
        return bytes;
    }
}
