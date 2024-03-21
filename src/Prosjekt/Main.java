package Prosjekt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        meny();
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();

        try {
            int ansattId = 1;
            Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);

            if (ansatt != null) {
                System.out.println("Found ansatt: " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
            } else {
                System.out.println("Ansatt with ID " + ansattId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ansattDAO.close();
        }
        try {
            int avdelingID = 1;
            Avdeling avdeling= avdelingDAO.finnAvdelingMedId(avdelingID);

            if (avdeling != null) {
                System.out.println("Found avdeling: " + avdeling.getAvdelingsnavn());
            } else {
                System.out.println("Avdeling with ID " + avdelingID + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            avdelingDAO.close();
        }
    }


    //TODO alt
    public static void meny() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trykk ein tast på hustelefonen for å velje eit valg");
        System.out.println("1: slett tabellen");
        System.out.println("2: legg til ein sopp");
        System.out.println("3: Søk etter ansatt på ansatt-ID");
        System.out.println("4: Søk etter ansatt på brukernavn (initialer)");
        System.out.println("5: Utlisting av alle ansatte");
        System.out.println("6: Oppdater en ansatt sin stilling og/ eller lønn");
        System.out.println("7: Legg inn en ny ansatt");

        String temp = switch (scanner.nextInt()) {
            case 1 -> "Omg";
            case 2 -> "Omg!";
            case 3 -> "Omg!!";
            case 4 -> "Omg!!!";
            case 5 -> "Omg!!!!";
            case 6 -> "Omg!!!!!";
            case 7 -> "Omg!!!!!1";
            default -> "invalid input";
        };
        System.out.println(temp);
    }


}