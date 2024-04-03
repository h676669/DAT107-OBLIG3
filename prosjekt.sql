DROP SCHEMA IF EXISTS Dat107Oblig3 CASCADE;
CREATE SCHEMA Dat107Oblig3;
SET search_path TO Dat107Oblig3;

CREATE TABLE Ansatt
(
    ansatt_id          SERIAL PRIMARY KEY,
    brukernavn         VARCHAR(4)     NOT NULL,
    fornavn            TEXT           NOT NULL,
    etternavn          TEXT           NOT NULL,
    ansettelsesdato    DATE           NOT NULL,
    stilling           TEXT           NOT NULL,
    manedslonn         NUMERIC(10, 2) NOT NULL,
    avdeling_id        INT            NOT NULL,
    prosjektdeltagelse INT
);

CREATE TABLE Avdeling
(
    avdeling_id     SERIAL PRIMARY KEY,
    avdeling_navn   TEXT NOT NULL,
    avdeling_ansatt INT,
    le_boss_id      INT  NOT NULL
);

CREATE TABLE Prosjekt
(
    prosjekt_id          SERIAL PRIMARY KEY,
    prosjekt_navn        TEXT NOT NULL,
    prosjekt_beskrivelse TEXT NOT NULL
);

CREATE TABLE ProsjektDeltagelse
(
    pd_id       SERIAL PRIMARY KEY,
    pd_ansatt   INT,
    pd_rolle    TEXT,
    pd_timer    INT,
    pd_prosjekt INT
);

INSERT INTO Avdeling(avdeling_navn, le_boss_id)
VALUES ('BERGEN', 1),
       ('Nord-Norge', 2),
       ('Rogaland', 3);

INSERT INTO Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, manedslonn, avdeling_id)
VALUES ('evs', 'Edvard', 'Vindenes Steenslid', '2012-08-24', 'Programmeringsmyrder', 4206969.69, 1),
       ('omgp', 'Ole Mathis Gudmund', 'Persen', '2019-06-13', 'Nordlending', 2697340.53, 2),
       ('lcs', 'Lars-Christian', 'Selland', '2001-03-01', 'Kodeoptimaliserer', 360442.67, 1),
       ('mk', 'Morten', 'Kvamme', '2024-02-29', 'CEO', 32564000.10, 1);

INSERT INTO Prosjekt(prosjekt_navn, prosjekt_beskrivelse)
VALUES ('Dat107Oblig 3', 'Ting og tang om bruk av java til å kople sammen databaser og sånn'),
       ('Dat107Oblig 2', 'Har du hørt om 3NF? Det har BCNF i hvertfall!');

INSERT INTO ProsjektDeltagelse(pd_ansatt, pd_rolle, pd_timer, pd_prosjekt)
VALUES (1, 'Kodemann', 69, 1),
       (4, 'Asgeir Borgemoen', 9001, 2);

ALTER TABLE Ansatt
    ADD CONSTRAINT fk_prosjekt FOREIGN KEY (prosjektdeltagelse) REFERENCES ProsjektDeltagelse (pd_id);
ALTER TABLE Ansatt
    ADD CONSTRAINT fk_avdeling FOREIGN KEY (avdeling_id) REFERENCES Avdeling (avdeling_id) ON DELETE RESTRICT;
ALTER TABLE Avdeling
    ADD CONSTRAINT Avdeling_fk1 FOREIGN KEY (avdeling_ansatt) REFERENCES Ansatt (ansatt_id);
ALTER TABLE Avdeling
    ADD CONSTRAINT Avdeling_fk2 FOREIGN KEY (le_boss_id) REFERENCES Ansatt (ansatt_id);
ALTER TABLE ProsjektDeltagelse
    ADD CONSTRAINT fk_pd_ansatt FOREIGN KEY (pd_ansatt) REFERENCES Ansatt (ansatt_id);
ALTER TABLE ProsjektDeltagelse
    ADD CONSTRAINT fk_pd_prosjekt FOREIGN KEY (pd_prosjekt) REFERENCES Prosjekt (prosjekt_id);


SELECT *
FROM Ansatt;
