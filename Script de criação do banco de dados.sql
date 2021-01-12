DROP DATABASE IF EXISTS artistas_e_albuns;
CREATE DATABASE artistas_e_albuns;
USE artistas_e_albuns;
CREATE TABLE usuarioJWT(
usuario varchar(100) NOT NULL UNIQUE,
senha varchar(100) NOT NULL,
PRIMARY KEY (usuario)
);
CREATE TABLE artista(
artistaUID bigint AUTO_INCREMENT,
nome varchar(100) NOT NULL,
PRIMARY KEY (artistaUID)
);
CREATE TABLE album(
albumUID bigint AUTO_INCREMENT,
artistaUID bigint NOT NULL,
nome varchar(100) NOT NULL,
PRIMARY KEY (albumUID),
FOREIGN KEY (artistaUID) REFERENCES artista(artistaUID)
);

INSERT INTO usuarioJWT (usuario, senha) VALUES ('user', '$2y$10$CCvx1ZKyFB4RPNks0yKfIOq6U/jr2yvRxJzTJNAS0EXjsvWuVQBC6');

INSERT INTO artista (nome) VALUES ('Serj tankian');
SET @ultimoArtistaUID = LAST_INSERT_ID();
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Harakiri');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Black Blooms');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'The Rough Dog');

INSERT INTO artista (nome) VALUES ('Mike Shinoda');
SET @ultimoArtistaUID = LAST_INSERT_ID();
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'The Rising Tied');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Post Traumatic');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Post Traumatic EP');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Where\'d You Go');

INSERT INTO artista (nome) VALUES ('Michel Teló');
SET @ultimoArtistaUID = LAST_INSERT_ID();
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Bem Sertanejo');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Bem Sertanejo - O Show (Ao Vivo)');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Bem Sertanejo - (1a Temporada) - EP');

INSERT INTO artista (nome) VALUES ('Guns N\' Roses');
SET @ultimoArtistaUID = LAST_INSERT_ID();
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Use Your IIIlusion I');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Use Your IIIlusion II');
INSERT INTO album (artistaUID, nome) VALUES (@ultimoArtistaUID, 'Greatest Hits');
