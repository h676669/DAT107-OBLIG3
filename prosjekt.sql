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
    ('evs','Edvard','Steenslid',2012-08-24,'Programmeringsmyrder',4206969.69
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