package Prosjekt;

import jakarta.persistence.*;

import javax.xml.validation.Schema;

@Entity
@Table(schema = "Dat107Oblig3")

public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int avdelingID;
}
