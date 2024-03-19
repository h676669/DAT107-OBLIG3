package Prosjekt;

public class Main {
    public static void main(String[] args) {

        AnsattDAO ansattDAO = new AnsattDAO();

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
    }
}