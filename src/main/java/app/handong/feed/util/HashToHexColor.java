package app.handong.feed.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashToHexColor {
    public static String hashToHexColor(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());

            // Use the first 3 bytes to generate a hex color
            String hexColor = String.format("%02x%02x%02x", hash[0] & 0xFF, hash[1] & 0xFF, hash[2] & 0xFF);
            return hexColor;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        String input = "leetcode";
        String hexColor = hashToHexColor(input);
        System.out.println("Hex color: " + hexColor);
    }
}
