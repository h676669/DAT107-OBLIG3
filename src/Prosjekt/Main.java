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
        System.out.println("3: legg til to soppar");

        switch (scanner.nextInt()) {
            case 1: {
                System.out.println("du valgte feil");
                break;
            }
            case 2: {
                System.out.println("du valgte feil!");
                break;
            }
            case 3: {
                System.out.println("prøv igjen seinare");
            }
        }
    }


}