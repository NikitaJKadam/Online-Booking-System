package com.stackroute.slothandlingservice.util;

import java.util.Random;

public class UtilMethods {


     public static String generateRandomId(){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());

            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static Long generateLongRandomId(){

        String alphabet = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());

            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return Long.getLong(sb.toString());
    }
}
