DROP SCHEMA IF EXISTS Dat107Oblig3 CASCADE;
CREATE SCHEMA Dat107Oblig3;
SET search_path TO Dat107Oblig3;

CREATE TABLE Ansatt
(
    ansatt_id       SERIAL PRIMARY KEY,
    brukernavn      VARCHAR(4) UNIQUE NOT NULL,
    fornavn         TEXT              NOT NULL,
    etternavn       TEXT              NOT NULL,
    ansettelsesdato DATE              NOT NULL,
    stilling        TEXT              NOT NULL,
    manedslonn      NUMERIC(10, 2)    NOT NULL,
    avdeling_id     INT               NOT NULL
    /*  CONSTRAINT fk_avdeling
          FOREIGN KEY (avdeling_id)
              REFERENCES Avdeling (avdeling_id)
              ON DELETE RESTRICT/*Hindrer sletting av avdeling om det finnes en ansatt der*/

          */
);
CREATE TABLE Avdeling
(
    avdeling_id   SERIAL PRIMARY KEY,
    avdeling_navn TEXT NOT NULL,
    le_boss_id    INT  NOT NULL
    /*
    FOREIGN KEY (le_boss_id) REFERENCES Ansatt (ansatt_id)

     */
);
INSERT INTO Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, manedslonn, avdeling_id)
VALUES ('evs', 'Edvard', 'Vindenes Steenslid', '2012-08-24', 'Programmeringsmyrder', 4206969.69, 1),
       ('omgp', 'Ole Mathis Gudmund', 'Persen', '2019-06-13', 'Nordlending', 2697340.53, 1),
       ('lcs', 'Lars-Christian', 'Selland', '2001-03-01', 'Kodeoptimaliserer', 360442.67, 1),
       ('mk', 'Morten', 'kvamme', '2024-02-29', 'CEO', 32564000.10, 1);


INSERT INTO Avdeling(avdeling_navn, le_boss_id)
VALUES ('BERGEN', 1),
       ('Nord-Norge', 2),
       ('Rogaland', 3);
SELECT *
FROM Ansatt;

CREATE TABLE Prosjekt
(
    prosjekt_id          SERIAL PRIMARY KEY,
    prosjekt_navn        TEXT NOT NULL,
    prosjekt_beskrivelse TEXT NOT NULL,
    prosjekt_ansatt      TEXT NOT NULL
)

ALTER TABLE "Avdeling" ADD CONSTRAINT "Avdeling_fk1" FOREIGN KEY ("navn") REFERENCES "Ansatt"("fornavn");
ALTER TABLE "Avdeling" ADD CONSTRAINT "Avdeling_fk2" FOREIGN KEY ("sjef") REFERENCES "Ansatt"("ansatt_id");
ALTER TABLE "Prosjekt" ADD CONSTRAINT "Prosjekt_fk3" FOREIGN KEY ("ansatt") REFERENCES "Ansatt"("ansatt_id");