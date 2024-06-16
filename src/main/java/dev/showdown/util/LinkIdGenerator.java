package dev.showdown.util;

import java.security.SecureRandom;

/**
 * This class is responsible for generating unique link IDs.
 * The generated IDs are used as unique identifiers for the tables in the application.
 */
public class LinkIdGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 20;
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a unique link ID.
     * The ID is a random string of characters of length ID_LENGTH.
     * Each character in the ID is randomly selected from the CHARACTERS string.
     *
     * @return a unique link ID
     * Example of a generated ID: fp2tQlDoudztygYvcla2
     */
    public static String generateLinkId() {
        StringBuilder id = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            id.append(CHARACTERS.charAt(index));
        }
        return id.toString();
    }
}
