package Prosjekt;
import java.util.ArrayList;
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
        System.out.println("Trykk ein tast på hustelefonen for å velje eit valg");
        System.out.println("1: Søk etter ansatt på ansatt-ID");
        System.out.println("2: Søk etter ansatt på brukernavn (initialer)");
        System.out.println("3: Utlisting av alle ansatte");
        System.out.println("4: Oppdater en ansatt sin stilling og/ eller lønn");
        System.out.println("5: Legg inn en ny ansatt");
        System.out.println("6: Søk etter avdeling på avdeling-ID");
        System.out.println("7: Utlisting av alle ansatte på en avdeling inkl. utheving av hvem som er sjef");
        System.out.println("8: Oppdatere hvilken avdeling en ansatt jobber på. Man kan ikke bytte avdeling hvis man er sjef!");
        System.out.println("9: Legg inn en ny avdeling(!)");
        System.out.println("10: Legge inn et nytt prosjekt");
        System.out.println("11: Registrere prosjektdeltagelse (ansatt med rolle i prosjekt)");
        System.out.println("12: Føre timer for en ansatt på et prosjekt");
        System.out.println("13: Utskrift av info om prosjekt, inkl. liste av deltagere med rolle og timer, og totalt timetall for prosjektet");

        String invalidTekst = "Invalid, prøv igjen";
        int valg = -1;
        // Tvinger input til å være et tall mellom 1 og ?
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
                System.out.println("Skriv inn ansatt ID");
                int id;
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Skriv ett heltall!");
                }
                id = scanner.nextInt();
                sokEtterAnsattID(ansattDAO, id);
            }
            case 2 -> {
                System.out.println("Skriv inn ansattes initialer");
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
                        System.out.println("Skriv inn ny stilling");
                        ansattDAO.oppdaterAnsatt(id, scanner.next());
                    }
                    case 2 -> {
                        System.out.println("Skriv inn ny lønn");
                        ansattDAO.oppdaterAnsatt(id, scanner.nextDouble());
                    }
                    case 3 -> {
                        System.out.println("Skriv inn ny stilling og deretter lønn");
                        ansattDAO.oppdaterAnsatt(id, scanner.next(), scanner.nextDouble());
                    }
                }
            }
            case 5 -> {
                System.out.println("Pray forgive me sensei, for this employee is but hardcoded.");
                Ansatt nyAnsatt = new Ansatt("Geir", "Trolldeig", "Stein", "Konduktør", 529.30, avdelingDAO.finnAvdelingMedId(1));
                ansattDAO.leggTilNyAnsatt(nyAnsatt);
                System.out.println(nyAnsatt + " har blitt opprettet");
            }
            case 6 -> {
                System.out.println("Skriv inn avdeling ID:");
                sokAvdeling(avdelingDAO, scanner.nextInt());
            }
            case 7 -> {
                System.out.println("Skriv inn avdeling ID:");
                int id = scanner.nextInt();
                for (Ansatt a : avdelingDAO.finnAvdelingMedId(id).getAvdelingAnsatt()) {
                    if (a.getAnsatt_id() == avdelingDAO.finnAvdelingMedId(id).getLe_boss_id()) {
                        System.out.println("THE BOSS HIMSELF: xX" + a + "Xx");
                    } else {
                        System.out.println(a);
                    }
                }
            }
            case 8 -> {
                System.out.println("Skriv inn ansatt ID:");
                int id = scanner.nextInt();
                System.out.println("Skriv inn ny avdeling Id");
                int nyAvdeling = scanner.nextInt();
                ansattDAO.oppdaterAnsatt(id, nyAvdeling);
                avdelingDAO.leggTilAnsatt(ansattDAO.finnAnsattMedId(id),nyAvdeling);
            }
            case 9 -> {
                System.out.println("Skriv inn ønsket navn på ny avdeling: ");
                scanner.next();
                String nyAvdeling = scanner.nextLine();
                for (Avdeling a : avdelingDAO.finnAlleAvdelinger()) {
                    if (a != null) {
                        if (!Objects.equals(a.getAvdelingsnavn(), nyAvdeling)) {
                            avdelingDAO.lagreNyAvdeling(nyAvdeling);
                            break;
                        } else {
                            System.out.println("Avdeling eksisterer allerede");
                        }
                    }
                }
            }
            case 10 -> {
                System.out.println();
                ProsjektDAO prosjektDAO = new ProsjektDAO();
                prosjektDAO.lagreNyttProsjekt("test", "Geir", new ArrayList<>());
            }
            case 11 -> {
                //TODO
//                Ansatt testAnsatt = new Ansatt("rmd", "Ronald", "McDonald", "The Burger King", 420.0 , avdelingDAO.finnAvdelingMedId(2), 1);
//                ProsjektDeltagelseDAO prosjektDeltagelseDAO = new ProsjektDeltagelseDAO();
//                prosjektDeltagelseDAO.lagreNyProsjektDeltagelse("Brosteinlegger", testAnsatt, "???");
            }
            case 12 -> {
                //TODO
            }
            case 13 -> {
                //TODO
            }

            default -> {
                System.out.println("?");
            }
        }
        avdelingDAO.close();
        ansattDAO.close();
        System.out.println();
        System.out.println("Trykk ein tast (enter) for å avslutta");
        scanner.nextLine();
        scanner.nextLine(); // dette er ikke en feil

    }

    private static void sokEtterAnsattID(AnsattDAO ansattDAO, int id) {
        try {
            ansattDAO = new AnsattDAO();
            Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
            if (ansatt != null) {
                System.out.println("Found ansatt: " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
            } else {
                System.out.println("Ansatt with ID " + id + " not found.");
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
                System.out.println("Found ansatt: " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
            } else {
                System.out.println("Ansatt with initials " + initialer + " not found.");
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
                System.out.println("Found avdeling: " + avdeling.getAvdelingsnavn());
            } else {
                System.out.println("Avdeling with ID " + id + " not found.");
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