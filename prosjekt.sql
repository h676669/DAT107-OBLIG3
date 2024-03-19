DROP SCHEMA IF EXISTS Dat107Oblig3 CASCADE;
CREATE SCHEMA Dat107Oblig3;
SET search_path TO Dat107Oblig3;

create TABLE Ansatt
(
    ansatt_id       SERIAL PRIMARY KEY,
    brukernavn      VARCHAR(4) UNIQUE NOT NULL,
    fornavn         VARCHAR(255)      NOT NULL,
    etternavn       VARCHAR(255)      NOT NULL,
    ansettelsesdato DATE              NOT NULL,
    stilling        VARCHAR(255)      NOT NULL,
    manedslonn      NUMERIC(10, 2)    NOT NULL,
    avdeling_id     INT               NOT NULL
);
CREATE TABLE Avdeling
(
    avdeling_id   SERIAL PRIMARY KEY,
    avdelingsnavn VARCHAR(255) NOT NULL,
    le_boss_id    INT          NOT NULL,
    FOREIGN KEY (le_boss_id) REFERENCES Ansatt(ansatt_id)
);
INSERT INTO
    Ansatt(brukernavn,fornavn,etternavn,ansettelsesdato,stilling,manedslonn,avdeling_id)
VALUES
    ('evs','Edvard','Vindenes Steenslid','2012-08-24','Programmeringsmyrder',4206969.69,1),
    ('omgp','Ole Mathis Gudmund','Persen','2019-06-13','Nordlending',2697340.53,1),
    ('lcs','Lars-Christian','Selland','2001-03-01','Kodeoptimaliserer',360442.67,1),
    ('mk','Morten','kvamme','2024-02-29','CEO',3256400.10,1);


INSERT INTO
    Avdeling(avdelingsnavn, le_boss_id)
VALUES
    ('Nord-Norge',2);

SELECT * FROM Ansatt;

CREATE TABLE Prosjekt
(
    p_id          SERIAL PRIMARY KEY,
    p_navn        VARCHAR(255) NOT NULL,
    p_beskrivelse TEXT         NOT NULL
)