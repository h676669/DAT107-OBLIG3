package Prosjekt;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        meny();
    }

    public static void meny() {
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Trykk en tast på hustelefonen for å velge et valg:");
        System.out.println("1: Søk etter ansatt på ansatt-ID.");
        System.out.println("2: Søk etter ansatt på brukernavn (initialer).");
        System.out.println("3: Utlisting av alle ansatte.");
        System.out.println("4: Oppdater en ansatt sin stilling og/ eller lønn.");
        System.out.println("5: Legg inn en ny ansatt.");
        System.out.println("6: Søk etter avdeling på avdeling-ID.");
        System.out.println("7: Utlisting av alle ansatte på en avdeling inkl. utheving av hvem som er sjef.");
        System.out.println("8: Oppdatere hvilken avdeling en ansatt jobber på. Man kan ikke bytte avdeling hvis man er sjef!");
        System.out.println("9: Legg inn en ny avdeling(!).");
        System.out.println("10: Legge inn et nytt prosjekt.");
        System.out.println("11: Registrere prosjektdeltagelse (ansatt med rolle i prosjekt).");
        System.out.println("12: Føre timer for en ansatt på et prosjekt.");
        System.out.println("13: Utskrift av info om prosjekt, inkl. liste av deltagere med rolle og timer, og totalt timetall for prosjektet.");

        String invalidTekst = "Ugyldig, prøv igjen.";
        int valg = -1;
        // Tvinger input til å være et tall mellom 1 og 13
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println(invalidTekst);
            }
            valg = scanner.nextInt();
            if (valg < 1 || valg > 13) {
                System.out.println(invalidTekst);
            }
        } while (valg < 1 || valg > 13);

        // metodekall her
        switch (valg) {
            case 1 -> {
                System.out.println("Skriv inn ansatt ID:");
                int id;
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Skriv ett heltall!");
                }
                id = scanner.nextInt();
                sokEtterAnsattID(ansattDAO, id);
            }
            case 2 -> {
                System.out.println("Skriv inn ansattes initialer:");
                String initial = scanner.next();
                sokEtterAnsattInitial(ansattDAO, initial);
            }
            case 3 -> {
                try {
                    for (Ansatt a : ansattDAO.finnAlleAnsatt()) {
                        System.out.println(a.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 4 -> {
                System.out.println("Skriv inn ansatt ID:");
                int id = scanner.nextInt();
                System.out.println(ansattDAO.finnAnsattMedId(id));
                System.out.println();
                System.out.println("Oppdater:");
                System.out.println("1: Stilling");
                System.out.println("2: Lønn");
                System.out.println("3: Stilling og lønn");
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Skriv inn ny stilling:");
                        ansattDAO.oppdaterAnsatt(id, scanner.next());
                    }
                    case 2 -> {
                        System.out.println("Skriv inn ny lønn:");
                        ansattDAO.oppdaterAnsatt(id, scanner.nextDouble());
                    }
                    case 3 -> {
                        System.out.println("Skriv inn ny stilling og deretter lønn.");
                        System.out.println("Stilling:");
                        scanner.nextLine();
                        String stilling = scanner.nextLine();
                        System.out.println("Lønn:");
                        double lonn = scanner.nextDouble();
                        ansattDAO.oppdaterAnsatt(id, stilling, lonn);
                    }
                }
            }
            case 5 -> {
                System.out.println("Skriv inn følgende: ");
                scanner.nextLine();
                System.out.println("Brukernavn(maks 4 bokstaver):");
                String brukernavn = scanner.nextLine();
                System.out.println("Fornavn:");
                String fornavn = scanner.nextLine();
                System.out.println("Etternavn:");
                String etternavn = scanner.nextLine();
                System.out.println("Stilling:");
                String stilling = scanner.nextLine();
                System.out.println("Lønn:");
                double lonn = scanner.nextDouble();

                Ansatt nyAnsatt = new Ansatt(brukernavn, fornavn, etternavn, stilling, lonn, avdelingDAO.finnAvdelingMedId(1));
                ansattDAO.leggTilNyAnsatt(nyAnsatt);
                System.out.println(nyAnsatt + " har blitt opprettet.");
            }
            case 6 -> {
                System.out.println("Skriv inn avdeling ID: ");
                sokAvdeling(avdelingDAO, scanner.nextInt());
            }
            case 7 -> {
                System.out.println("Skriv inn avdeling ID: ");
                int id = scanner.nextInt();
                for (Ansatt a : avdelingDAO.finnAvdelingMedId(id).getAvdelingAnsatt()) {
                    if (a.getAnsatt_id() == avdelingDAO.finnAvdelingMedId(id).getLe_boss_id()) {
                        System.out.println("THE BOSS HIMSELF: xX" + a + "Xx ");
                    } else {
                        System.out.println(a);
                    }
                }
            }
            case 8 -> {
                System.out.println("Skriv inn ansatt ID: ");
                int id = scanner.nextInt();
                System.out.println("Skriv inn ny avdeling ID: ");
                int nyAvdeling = scanner.nextInt();
                ansattDAO.oppdaterAnsatt(id, nyAvdeling);
                avdelingDAO.leggTilAnsatt(ansattDAO.finnAnsattMedId(id), nyAvdeling);
            }
            case 9 -> {
                System.out.println("Skriv inn ønsket navn på ny avdeling: ");
                scanner.nextLine();
                String nyAvdeling = scanner.nextLine();
                for (Avdeling a : avdelingDAO.finnAlleAvdelinger()) {
                    if (a != null) {
                        if (!Objects.equals(a.getAvdelingsnavn(), nyAvdeling)) {
                            avdelingDAO.lagreNyAvdeling(nyAvdeling);
                            break;
                        } else {
                            System.out.println("Avdeling eksisterer allerede.");
                        }
                    }
                }
            }
            case 10 -> {
                System.out.println();
                ProsjektDAO prosjektDAO = new ProsjektDAO();
                prosjektDAO.lagreNyttProsjekt("test", "Geir");
            }
            case 11 -> {
                ProsjektDAO prosjektDAO = new ProsjektDAO();
                //Prosjekt testProsjekt = new Prosjekt();
                //Ansatt testAnsatt = new Ansatt("rmd", "Ronald", "McDonald", "The Burger King", 420.0 , avdelingDAO.finnAvdelingMedId(2));

                ProsjektDeltagelseDAO prosjektDeltagelseDAO = new ProsjektDeltagelseDAO();
                prosjektDeltagelseDAO.lagreNyProsjektDeltagelse("Brosteinlegger", ansattDAO.finnAnsattMedId(1), prosjektDAO.finnProsjektMedId(1));
            }
            case 12 -> {
                ProsjektDeltagelseDAO prosjektDeltagelseDAO = new ProsjektDeltagelseDAO();
                prosjektDeltagelseDAO.leggTilTimer(1, 100);
            }
            case 13 -> {
                ProsjektDAO prosjektDAO = new ProsjektDAO();
                System.out.println("Skriv inn prosjekt ID: ");
                int pID = scanner.nextInt();

                int sumTimer = 0;
                for (ProsjektDeltagelse p : prosjektDAO.finnProsjektMedId(pID).getProsjektListe()) {
                    sumTimer += p.getTimer();
                    System.out.println(p.getAnsatt() + ".");
                    System.out.println(p.getP_rolle() + ".");
                }
                System.out.println("Totalt antall timer: " + sumTimer + ".");
            }

            default -> {
                System.out.println("?");
            }
        }
        avdelingDAO.close();
        ansattDAO.close();
        System.out.println();
        System.out.println("Trykk en tast (enter) for å avslutte.");
        scanner.nextLine();
        scanner.nextLine(); // dette er ikke en feil

    }

    private static void sokEtterAnsattID(AnsattDAO ansattDAO, int id) {
        try {
            ansattDAO = new AnsattDAO();
            Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
            if (ansatt != null) {
                System.out.println("Fant ansatt: " + ansatt.getFornavn() + " " + ansatt.getEtternavn() + ".");
            } else {
                System.out.println("Ansatt med ID " + id + " ikke funnet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ansattDAO.close();
        }
    }

    private static void sokEtterAnsattInitial(AnsattDAO ansattDAO, String initialer) {
        try {
            ansattDAO = new AnsattDAO();
            Ansatt ansatt = ansattDAO.finnAnsattMedBrukernavn(initialer);
            if (ansatt != null) {
                System.out.println("Fant ansatt: " + ansatt.getFornavn() + " " + ansatt.getEtternavn() + ".");
            } else {
                System.out.println("Ansatt med initialer " + initialer + " ikke funnet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ansattDAO.close();
        }
    }

    private static void sokAvdeling(AvdelingDAO avdelingDAO, int id) {
        try {
            avdelingDAO = new AvdelingDAO();
            Avdeling avdeling = avdelingDAO.finnAvdelingMedId(id);
            if (avdeling != null) {
                System.out.println("Fant avdeling: " + avdeling.getAvdelingsnavn() + ".");
            } else {
                System.out.println("Avdeling med ID " + id + " ikke funnet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            avdelingDAO.close();
        }
    }

    /*
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⠷⠒⠛⠉⠛⠓⠦⣤⣶⣳⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣠⠖⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣻⠽⠀⢀⣠⡔⣦⣄⠀⠰⣟⢻⡁⠉⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀
⠸⠉⠙⠲⢷⣄⡈⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡿⠁⣠⠖⠋⠘⣇⡇⠈⠳⡄⠘⢷⣷⠀⠀⢹⡄⠀⠀⠀⠀⠀⠀⠀
⠨⠷⣶⡦⢄⡈⠑⢦⡈⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡿⢡⡼⣓⠁⠀⠀⢿⣿⠒⠢⢽⡄⠀⠸⣧⠀⠀⢻⡀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠉⠙⠒⠯⢤⣽⡀⠉⠲⣄⠀⠀⠀⠀⠀⠀⠀⡼⢹⠇⣾⠻⣁⡟⠀⠀⠀⠻⢾⣉⡾⣷⠠⢐⣿⡆⠂⠀⢳⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠦⡀⠑⢄⠀⠀⢀⣀⡼⢁⣾⣿⡏⠀⠀⢠⠤⣤⣀⡀⠀⠉⠀⢸⣼⣿⣿⣷⠸⡀⠈⢧⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⠳⣾⣿⣿⣷⣼⡭⣿⣧⡀⠀⡼⠀⠀⢸⠀⠀⠀⢀⣼⣿⣿⠟⢻⡀⣧⠀⠘⣇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣇⠀⠀⢸⠀⣀⣠⣾⣿⡟⠁⠀⠈⡇⢸⡄⠀⢹⡆⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⡿⠉⣛⣿⣯⠿⣻⡿⠛⢿⣿⠓⠀⠀⠀⣇⠈⣇⠀⠈⣿⡀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠉⣽⡹⣏⣟⣻⣿⣷⢴⡿⢩⣿⡞⠹⡄⠀⠀⣹⣇⠀⠀⠀⣟⠀⢿⠀⢃⠸⣧⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⢰⣯⢳⡝⣦⢻⣿⢏⡾⠁⢸⠀⠷⢶⠿⣿⣿⣿⣿⠀⠀⢀⣏⠰⠌⠀⠸⡐⡽⡄⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⣾⢧⡻⣜⡧⠟⠛⣾⠁⢀⡿⠒⠎⢦⢹⣿⣿⣿⣿⣧⠀⢰⡇⠨⢀⡐⠀⡇⣝⣳⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠃⢀⡿⣧⣛⣾⢿⡄⢀⣽⣠⣏⠀⠢⠄⡈⢺⣿⣿⣿⣿⣿⡆⣸⠃⠀⡀⢧⠀⢸⢰⠽⡆
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢠⠊⣹⢧⢯⡽⣮⢿⣿⣿⣿⣿⣧⣀⣤⣴⣿⣿⣯⣿⢿⣿⣿⡿⠀⠁⡀⢸⠆⢸⣹⢚⣷
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣯⡏⠀⣿⣛⡮⣗⠿⣦⣽⠛⠿⣿⣿⣿⣿⣿⢻⣿⣷⣿⣿⣿⣿⢃⡀⠁⡀⢸⢣⠸⡜⣷⢺
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⠀⢸⢸⡿⡼⣭⡻⣵⡿⣯⣙⢚⣧⣴⠒⣋⣡⣴⣟⠳⠴⢿⣿⣾⠀⠠⠀⡼⢣⢸⡕⣻⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⢸⡇⠏⣷⡳⡽⢶⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠘⠀⣯⡄⠀⠄⣏⣛⢦⠽⣱⣟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡘⣿⣄⢹⣳⡽⠋⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠹⣶⠀⢸⢣⡝⢮⡹⣥⡟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣼⡹⢾⠏⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠙⢧⣜⡳⣚⢧⢳⡽⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⠂⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⢹⢶⡙⣮⠟⠁⠀
⠀⠀kyo🍀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⠿⢛⡿⠿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⢸⡧⠟⠁⠀⠀⠀
⣿⣿⠿⠿⠛⠉⠉⠹⠟⠙⠟⠫⠹⢿⣿⡏⠉⠉⠉⠹⠏⠿⣿⣩⠉⠉⣉⠛⢻⣿⣯⡉⠉⠉⠉⠉⠉⠉⠉⠛⠻⣿⣿⣿⣿⣮⡉⠉⠉⠉⠋⠋⠙⠉⠋⠉⠉⠋⠙⠛⠛⠉⣹⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⡇⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠤⠸⣿⣧⠀⠀⠀⠀⠀⠀⢹⣷⡄⠀⠉⠀⠀⢻⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣯⡻⣿⣿⣶⣤⣀⣀⠀⠀⠀⠀⠀⠀⠈⠂⠀⠀⠐⠟⢱⡿⠏⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⢹⣿⠀⠀⠀⠀⠀⠀⠀⢹⣿⣦⡀⠀⠀⠀⣿⣿⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⡝⣿⣿⣯⡝⠛⠿⣶⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⢀⣐⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⢸⣯⢻⣷⡀⠀⠀⠈⣿⡄⢻⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠙⣷⡈⢿⣿⠞⢿⣶⣦⣍⡻⢿⣦⣄⠀⠀⢄⣴⠰⠄⠄⢛⣴⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⢸⣿⡄⠹⣿⡄⠀⠀⠸⣿⠀⠈⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠸⣷⠈⢿⣆⠈⠻⣿⣿⣿⣶⣌⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀⢸⣧⣦⠀⠈⢿⣆⠀⠁⢿⣇⠀⠀⢿⣷⡄⠀⠀⠀⠀⠀⠀⠀⢻⣇⠈⢿⡇⠀⠙⢿⣿⣿⣿⣿⣦⣙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⣿⡇⢻⡆⠀⠈⢿⣦⠀⠘⣿⣤⣤⣤⣼⣿⡄⠀⠀⠀⠀⠀⠀⠈⣿⡄⠘⣿⡄⠀⠀⠻⣿⣿⣿⣿⣿⣷⣝⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⢸⣿⠀⠘⣣⣤⣶⣾⣿⣧⠀⢿⡏⠉⠉⠉⠙⣿⡄⠀⠀⠀⠀⠀⠀⢸⣇⠀⠘⣿⣄⠀⠀⠙⣿⣿⣿⣿⣿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠀⠀⠀⠀⣸⣧⣶⡿⠟⠋⠉⠀⠀⠹⣧⡸⣧⠀⠀⠀⠀⢘⣿⡄⠀⠀⠀⠀⠀⠘⣿⡀⠀⢹⣿⣧⡄⠀⠈⢿⣿⣿⣿⣿⣿⣿⣞⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⡄⠀⠀⠀⠀⣼⣿⡀⠀⠀⠀⠀⠀⢻⡇⠀⠈⣿⣿⣿⣦⡀⠀⠻⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⣸⡏⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠙⣿⣧⣴⣖⣐⣼⡏⢘⣿⡀⠀⠀⠀⠀⢸⡇⠀⠀⢸⣿⣿⣿⣿⣄⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⢠⡿⠁⠀⠀⠀⠀⢠⡶⠋⠀⣠⣶⣿⠿⡿⠿⠿⠿⠿⣿⣿⣿⣧⠀⠀⠀⠀⢸⣷⠀⠀⠈⣿⡻⣿⣿⣿⣦⡀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠀⢃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⣿⠃⠀⠀⠀⠀⣴⡟⢁⣴⣿⡿⢋⣡⣶⣿⣿⣷⣶⣦⣄⠉⠻⣿⡆⠀⠀⠀⠘⣿⠀⠀⠀⢻⣇⢻⣿⣿⣿⣿⣄⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣠⣿⣷⣿⢿⣿⣿⣿⣿⣿⣿
⢸⣆⠈⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⣾⠇⠀⠀⠀⠀⠸⠋⣰⣿⣿⣟⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠘⣿⡀⠀⠀⠀⣿⠀⠀⠀⢸⣿⣔⡉⠙⢿⣿⣿⣧⡈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡙⢿⢿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⢻⡆⠈⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⠀⣼⡟⠀⠀⠀⠀⠀⠀⢠⣿⣿⠏⠁⠿⠿⣿⣿⡿⠋⠉⠙⢿⣿⣿⣿⣆⢹⣧⠀⠀⢠⣿⠀⠀⠀⠈⣿⣿⣷⠀⠀⢻⣿⣿⣿⣆⢻⣿⣿⣿⣿⣿⣿⠛⣿⣿⣿⡋⢱⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣶⣾⢿⣆⠈⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣰⡟⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⠀⠀⠀⠀⠀⢻⣇⠀⠀⠀⠀⢻⡿⢻⣿⡄⣿⠀⠀⢸⡟⠀⠀⠀⠀⣿⣼⣿⡆⠀⠸⣿⣿⣿⣿⣦⡹⣿⣿⣿⣿⣿⣇⠘⢿⣿⣧⢀⣉⢉⠻⣿⡿⡻⣿⣿⣿⣿
⣿⡁⠈⢿⡄⠘⣧⡀⠀⠀⠀⠀⠀⠀⠀⡟⣸⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⠁⠀⠀⣤⣤⣾⣿⣦⡈⠃⠀⣸⡇⠈⠛⣿⣿⡇⠀⢸⡇⠀⠀⠀⢠⣿⣿⣿⣿⣦⠀⣿⣿⣿⣿⣿⣷⡜⣿⣿⣿⣿⣿⣷⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⢿⣧⠀⠈⢿⣆⠘⣷⡀⠀⠀⠀⠀⠀⢀⣴⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠇⠀⠀⠙⣿⣿⡏⠉⠻⠷⠾⠟⠃⢀⣾⠟⢿⡇⢀⣿⠇⠀⠀⠀⢸⣿⡿⠛⠛⠃⠀⣿⡟⣿⣿⣿⣿⣿⣮⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠈⢿⡆⠀⠀⢻⣦⠈⢿⠀⠀⠀⠀⢠⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣄⠀⠀⠀⠀⠀⣠⡾⠃⠀⢸⡇⢸⣿⠀⠀⠀⠀⣾⣿⣿⠀⠀⠀⢀⣿⣿⡜⣿⣿⣿⣿⣿⣷⣻⣿⣿⣿⣿⣿⣷⣴⣶⣦⣤⣤⣽⣿⣿⣿⣿⣟
⠀⠸⣿⡄⠀⠀⢹⣷⣸⣿⣷⡀⠀⢾⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢤⣤⡀⢙⣷⣤⣤⡶⠟⠋⠀⠀⠀⢸⡇⣾⣿⠀⠀⠀⢀⣿⣿⣿⠀⠀⣠⣾⣿⣿⣿⡜⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⢠⣾⣿⣀⢘⣿⡿⢿⣿⣿⣧⡀⣸⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⣾⣷⣿⣿⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⠀⠈⠰⣿⣿⣿⣿⡃⠈⠻⣿⣿⡷⠋⠻⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⢻⣿⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠉⠀⠀⠈⠉⠻⢿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣆⠀⠀⢻⣿⣿⣿⡇⠀⣀⣙⣿⣟⠀⠀⢸⣟⢻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡟⢸⡟⠀⠀⢸⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣷⣀⠸⣿⣿⣿⣿⠀⠙⢿⣿⡟⢷⣦⣼⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡿⠁⣸⡇⠀⢰⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣦⣻⣿⣿⣷⣄⠀⠈⢻⣧⡀⠉⢉⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡇⠀⣼⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡿⠶⣿⣿⠿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⠀⣼⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⡏⣰⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⣡⣿⣷⣿⣿⣿⣿⣿⠿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⢟⣴⣿⣿⣿⣿⣿⣿⣿⡿⠀⢿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⠟⠁⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⢿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠃⠀⢻⣟⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡶⢶⣶⣤⣤⣀⣀⣀⡀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠸⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠋⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⢾⡷⠿⣿⣟⣿⣿⣿⠿⠿⠿⠟⠫⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⢿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⣰⣿⣷⡀⠀⠀⣾⣿⣿⣿⣿⣿⡆⠠⠹⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣶⣾⣿⣿⣿⣿⠰⣧⡀⢚⢀⠔⠂⣙⠻⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡆⠀⠀⠀⠀⠀⣴⡟⠁⣿⣿⠀⢸⡿⠛⢿⣿⣿⠋⢀⣤⣤⣽⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡈⠙⠒⠤⠼⠤⠶⣸⣾⣿⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣧⠀⠀⠀⢠⣾⠟⠀⠀⢸⣿⣷⣿⠇⠀⠀⠈⣿⣐⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣟⠿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣄⡀⠀⠈⠁⠀⠀⠀⣾⣿⡿⣿⡆⠀⠀⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠯⠄⠘⡻⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⣺⣿⣿⠀⢸⣿⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⠙⠀⠀⠈⡅⠹⣿⣿⣧⡀⠀⡠⠀⠀⢀⠀⢷⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⠀⠀⠀⠀⠀⣰⣿⣿⣏⠀⢸⣿⣶⡶⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣗⣄⡁⡐⠂⠀⠀⣿⣿⣿⣿⣮⣶⣽⣯⣽⣮⣬⣥⣦⡆⢄⠀⠁⠀⠀⠀⠀⠀⠀⠐⠀⣿⣿⣿⣿⠀⠀⠀⢀⣾⡿⣽⣿⣿⠀⠈⢿⣿⠃⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣤⣀⠀⢹⣷⡙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣴⣷⣠⣠⣤⣠⣀⣄⣀⣻⣿⣿⣿⠀⠀⣠⣾⠟⣴⣿⣿⠇⣠⡀⢸⣿⠈⣁⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣷⣄⠀⠀⠘⠿⢛⣭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣤⣾⠟⣡⣾⣿⣿⣟⣀⣼⣧⣾⡿⢤⣼⣧⣈⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⣁⣀⣀⣿⡟⢻⣿⣿⣿⣿⣿⠷⢮⣽⣦⣔⢠⡍⠙⠿⣿⣛⡛⣯⣭⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣷⣶⣷⣾⣶⣿⣦⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀
     */
}