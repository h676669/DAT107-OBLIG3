DROP SCHEMA IF EXISTS Dat107Oblig3 CASCADE;
CREATE SCHEMA Dat107Oblig3;
SET search_path TO Dat107Oblig3;

create Table Ansatt
(
    ansatt_id       SERIAL PRIMARY KEY,
    brukernavn      VARCHAR(4) UNIQUE NOT NULL,
    fornavn         VARCHAR(255)      NOT NULL,
    etternavn       VARCHAR(255)      NOT NULL,
    ansettelsesdato DATE              NOT NULL,
    stilling        VARCHAR(255)      NOT NULL,
    manedslonn      NUMERIC(10, 2)    NOT NULL 
);
INSERT INTO
    Ansatt(brukernavn,fornavn,etternavn,ansettelsesdato,stilling,manedslonn)
    VALUES
    ('evs','Edvard','Vindenes Steenslid','2012-08-24','Programmeringsmyrder',4206969.69),
    ('omgp','Ole Mathis Gudmund','Persen','2019-06-13','Nordlending',2697340.53),
    ('lcs','Lars-Christian','Selland','2001-03-01','Kodeoptimaliserer',360442.67),
    ('mk','Morten','kvamme','2024-02-29','CEO',3256400.10
);
SELECT * FROM Ansatt;


 /*avdeling_id     INT               NOT NULL*/
/*CREATE TABLE Avdeling
(
    avdeling_id   SERIAL PRIMARY KEY,
    avdelingsnavn VARCHAR(255) NOT NULL,
    le_boss_id    INT          NOT NULL,
    FOREIGN KEY (le_boss_id) REFERENCES Ansatt(ansatt_id)
);
CREATE TABLE Prosjekt
(
    p_id          SERIAL PRIMARY KEY,
    p_navn        VARCHAR(255) NOT NULL,
    p_beskrivelse TEXT         NOT NULL
)*/