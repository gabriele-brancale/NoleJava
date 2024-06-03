CREATE TABLE IMBARCAZIONE(
    TARGA VARCHAR(5) PRIMARY KEY,
    NOME VARCHAR(30) NOT NULL,
    TIPOLOGIA VARCHAR(30) NOT NULL,
    STATO VARCHAR(30) NOT NULL,
    CAPIENZA INT NOT NULL,
    COSTO FLOAT NOT NULL
);

CREATE TABLE CLIENTE_REGISTRATO(
    ID_CLIENTE INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(50) NOT NULL,
    COGNOME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(254) NOT NULL UNIQUE,
    PASSWORD VARCHAR(20) NOT NULL,
    DATA_DI_NASCITA DATE NOT NULL,
    NUMERO_PATENTE VARCHAR(10)
);

CREATE TABLE ACCESSORIO_OBBLIGATORIO (
    ID_ACCESSORIO_OBBLIGATORIO INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(50) NOT NULL,
    DESCRIZIONE VARCHAR(50) NOT NULL,
    PREZZO FLOAT NOT NULL
);

CREATE TABLE NOLEGGIO(
    ID_NOLEGGIO INT AUTO_INCREMENT PRIMARY KEY,
    DATA_INIZIO DATE NOT NULL,
    DATA_FINE DATE NOT NULL,
    ID_CLIENTE INT NOT NULL,
    TARGA VARCHAR(5) NOT NULL,
    ID_ACCESSORIO_OBBLIGATORIO INT NOT NULL,
    SKIPPER TINYINT NOT NULL,
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE_REGISTRATO(ID_CLIENTE) ON DELETE CASCADE,
    FOREIGN KEY (TARGA) REFERENCES IMBARCAZIONE(TARGA) ON DELETE CASCADE,
    FOREIGN KEY (ID_ACCESSORIO_OBBLIGATORIO) REFERENCES ACCESSORIO_OBBLIGATORIO(ID_ACCESSORIO_OBBLIGATORIO) ON DELETE CASCADE
);

CREATE TABLE ACCESSORIO_OPTIONAL (
    ID_ACCESSORIO_OPTIONAL INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(20) NOT NULL,
    DESCRIZIONE VARCHAR(50) NOT NULL,
    PREZZO FLOAT NOT NULL
);

CREATE TABLE NOLEGGIO_ACCESSORIO_OPTIONAL (

    ID_CLIENTE INT,
    ID_ACCESSORIO_OPTIONAL INT,
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE_REGISTRATO(ID_CLIENTE) ON DELETE CASCADE,
    FOREIGN KEY (ID_ACCESSORIO_OPTIONAL) REFERENCES ACCESSORIO_OPTIONAL(ID_ACCESSORIO_OPTIONAL) ON DELETE CASCADE

);

INSERT INTO CLIENTE_REGISTRATO VALUES (NULL, 'Mario', 'Rossi', 'mariorossi@gmail.com', 'pippo2002', '2004-05-22', '0123456789');
INSERT INTO CLIENTE_REGISTRATO VALUES (NULL, 'Roberto', 'Mengoni', 'robertomengoni@gmail.com', 'pippo2004', '2004-07-22',  '0123456780');

INSERT INTO IMBARCAZIONE VALUES ('NA123', 'La Bellissima', 'vela', 'IN USO', 4, 30 );
INSERT INTO IMBARCAZIONE VALUES ('PA123', 'Amanda', 'motore', 'IN USO', 6, 50 );

INSERT INTO ACCESSORIO_OBBLIGATORIO VALUES (NULL, 'servizio assicurativo', 'servizio assicurativo descrizione', 50);
INSERT INTO ACCESSORIO_OPTIONAL VALUES (NULL, 'motore tender', 'motore tender descrizione', 30);

INSERT INTO ACCESSORIO_OBBLIGATORIO VALUES (NULL, 'servizio assicurativo 2', 'servizio assicurativo 2 descrizione', 50);
INSERT INTO ACCESSORIO_OPTIONAL VALUES (NULL, 'motore tender 2', 'motore tender 2 descrizione', 30);

INSERT INTO NOLEGGIO VALUES (NULL, '2024-02-10', '2024-02-20', 1, 'PA123', 1, TRUE);






