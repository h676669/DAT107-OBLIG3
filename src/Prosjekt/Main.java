package Prosjekt;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        meny();

    }


    //TODO alt
    public static void meny() {
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Trykk ein tast på hustelefonen for å velje eit valg");
        System.out.println("1: Søk etter ansatt på ansatt-ID");
        System.out.println("2: Søk etter ansatt på brukernavn (initialer)");
        System.out.println("3: Utlisting av alle ansatte");
        System.out.println("4: Oppdater en ansatt sin stilling og/ eller lønn");
        System.out.println("5: Legg inn en ny ansatt");

        String invalidTekst = "Invalid, prøv igjen";

        int valg = -1;
        // Tvinger input til å være et tall mellom 1 og 5
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println(invalidTekst);
            }
            valg = scanner.nextInt();
            if (valg < 1 || valg > 5) {
                System.out.println(invalidTekst);
            }
        } while (valg < 1 || valg > 5);

        // metodekall her
        switch (valg) {
            case 1 -> {
                System.out.println("Skriv inn ansatt ID");
                int id;
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Skriv ett heltall!");
                }
                id = scanner.nextInt();
                sokEtterAnsatt(ansattDAO, id);
            }
            case 2 -> {
                System.out.println("Skriv inn avdeling ID");
                int id;
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Skriv ett heltall!");
                }
                id = scanner.nextInt();
                sokAvdeling(avdelingDAO, id);
            }
            case 3 -> {
                ansattDAO = new AnsattDAO();
                try {
                    for (Ansatt a : ansattDAO.finnAlleAnsatt()) {
                        System.out.println(a.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    avdelingDAO.close();
                }
            }
            case 4 -> System.out.println("todo");
            case 5 -> System.out.println("todo");
            default -> System.out.println("todo");
        }
        System.out.println();
        System.out.println("Trykk ein tast for å avslutta");
        scanner.nextLine();
        scanner.nextLine(); // dette er ikke en feil

    }
    private static void sokEtterAnsatt (AnsattDAO ansattDAO,int id){
        try {
            ansattDAO = new AnsattDAO();
            int ansattId = id;
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
    }

    private static void sokAvdeling (AvdelingDAO avdelingDAO,int id){
        try {
            avdelingDAO = new AvdelingDAO();
            int avdelingID = id;
            Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdelingID);

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
}