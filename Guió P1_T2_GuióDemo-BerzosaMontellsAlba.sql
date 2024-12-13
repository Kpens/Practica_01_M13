-- Insert a temporada
INSERT INTO Temporada (anny) VALUES (2024);

INSERT INTO usuari (login, nom_usu, password) VALUES ('usuari', 'usuari','usuari'); 

-- Insert categories
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Benjami', 7, 8);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Alevi', 9, 11);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Infantil', 12, 13);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Cadet', 14, 15);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Juvenil', 16, 17);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Senior', 18, 21);


-- Insert equips
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Femeni', 'D', 2024, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Mixte', 'M', 2024, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Mixte', 'M', 2024, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Masculi', 'H', 2024, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Masculi', 'H', 2024, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Femeni', 'D', 2024, 6);

-- Jugadors per a l'equip Benjami Femeni (edats 7-8)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Lara', 'Blanco', 'D', TO_DATE('2016-06-10', 'YYYY-MM-DD'), 'ID01', 'ES9121000418450200051331', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 1', 'C:\jugadors\persona.jpg'); -- 8

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Clara', 'Sanz', 'D', TO_DATE('2016-08-15', 'YYYY-MM-DD'), 'ID02', 'ES9121000418450200051332', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 2', 'C:\jugadors\persona.jpg'); -- 8

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Sara', 'Vidal', 'D', TO_DATE('2016-07-20', 'YYYY-MM-DD'), 'ID03', 'ES9121000418450200051333', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 3', 'C:\jugadors\persona.jpg'); -- 9

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Mia', 'Torres', 'D', TO_DATE('2016-01-05', 'YYYY-MM-DD'), 'ID04', 'ES9121000418450200051334', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 4', 'C:\jugadors\persona.jpg'); -- 9

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Nora', 'Morales', 'D', TO_DATE('2016-03-30', 'YYYY-MM-DD'), 'ID05', 'ES9121000418450200051335', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 5', 'C:\jugadors\persona.jpg'); -- 9


-- Jugadors per a l'equip Infantil Mixte (edats 12-13)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Oscar', 'Garcia', 'H', TO_DATE('2011-04-10', 'YYYY-MM-DD'), 'ID06', 'ES9121000418450200051336', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Lucas', 'Cruz', 'H', TO_DATE('2011-10-05', 'YYYY-MM-DD'), 'ID07', 'ES9121000418450200051337', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 7', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Noah', 'Hernandez', 'H', TO_DATE('2012-02-15', 'YYYY-MM-DD'), 'ID08', 'ES9121000418450200051338', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 8', 'C:\jugadors\persona.jpg'); -- 12

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Luca', 'Ramirez', 'D', TO_DATE('2012-05-22', 'YYYY-MM-DD'), 'ID09', 'ES9121000418450200051339', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 9', 'C:\jugadors\persona.jpg'); -- 12

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Elena', 'Fernandez', 'D', TO_DATE('2012-09-18', 'YYYY-MM-DD'), 'ID10', 'ES9121000418450200051310', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 10', 'C:\jugadors\persona.jpg'); -- 12

-- Jugadors per a l'equip Cadet Masculi (edats 14-15)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Alex', 'Pérez', 'H', TO_DATE('2009-11-30', 'YYYY-MM-DD'), 'ID11', 'ES9121000418450200051311', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 11', 'C:\jugadors\persona.jpg'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Mateo', 'Serrano', 'H', TO_DATE('2009-05-25', 'YYYY-MM-DD'), 'ID12', 'ES9121000418450200051312', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 12', 'C:\jugadors\persona.jpg'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Dani', 'Moreno', 'H', TO_DATE('2009-02-10', 'YYYY-MM-DD'), 'ID13', 'ES9121000418450200051313', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 13', 'C:\jugadors\persona.jpg'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Adrià', 'Navarro', 'H', TO_DATE('2009-08-14', 'YYYY-MM-DD'), 'ID14', 'ES9121000418450200051314', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 14', 'C:\jugadors\persona.jpg'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Toni', 'Camps', 'H', TO_DATE('2009-12-02', 'YYYY-MM-DD'), 'ID15', 'ES9121000418450200051315', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 15', 'C:\jugadors\persona.jpg'); -- 15

-- Jugadors per a l'equip Juvenil Masculi (edats 16-17)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Xavi', 'Bermúdez', 'H', TO_DATE('2007-01-11', 'YYYY-MM-DD'), 'ID16', 'ES9121000418450200051316', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 16', 'C:\jugadors\persona.jpg'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Ruben', 'López', 'H', TO_DATE('2007-04-15', 'YYYY-MM-DD'), 'ID17', 'ES9121000418450200051317', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 17', 'C:\jugadors\persona.jpg'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Pablo', 'Roca', 'H', TO_DATE('2007-10-20', 'YYYY-MM-DD'), 'ID18', 'ES9121000418450200051318', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 18', 'C:\jugadors\persona.jpg'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Victor', 'Martinez', 'H', TO_DATE('2008-07-30', 'YYYY-MM-DD'), 'ID19', 'ES9121000418450200051319', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 19', 'C:\jugadors\persona.jpg'); -- 18

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Liam', 'Salas', 'H', TO_DATE('2008-09-25', 'YYYY-MM-DD'), 'ID20', 'ES9121000418450200051320', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 20', 'C:\jugadors\persona.jpg'); -- 18

-- Jugadors per a l'equip Senior Femeni (edats 18-21)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Elisa', 'Dominguez', 'D', TO_DATE('2003-03-22', 'YYYY-MM-DD'), 'ID21', 'ES9121000418450200051321', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 21', 'C:\jugadors\persona.jpg'); -- 21

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Julia', 'Gonzalez', 'D', TO_DATE('2003-10-10', 'YYYY-MM-DD'), 'ID22', 'ES9121000418450200051322', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 22', 'C:\jugadors\persona.jpg'); -- 22

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Laura', 'Vidal', 'D', TO_DATE('2004-05-18', 'YYYY-MM-DD'), 'ID23', 'ES9121000418450200051323', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 23', 'C:\jugadors\persona.jpg'); -- 22

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Carmen', 'Sanchez', 'D', TO_DATE('2004-02-14', 'YYYY-MM-DD'), 'ID24', 'ES9121000418450200051324', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 24', 'C:\jugadors\persona.jpg'); -- 19

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Valeria', 'Romero', 'D', TO_DATE('2003-11-30', 'YYYY-MM-DD'), 'ID25', 'ES9121000418450200051325', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 25', 'C:\jugadors\persona.jpg'); -- 20


-- Jugadors per a l'equip Alevi Mixte (edats 9-11)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Maria', 'Lopez', 'H', TO_DATE('2015-04-10', 'YYYY-MM-DD'), 'ID26', 'ES9121000418450200051326', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Joana', 'Dominguez', 'D', TO_DATE('2014-10-02', 'YYYY-MM-DD'), 'ID27', 'ES9121000418450200051327', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Rita', 'Sanchez', 'D', TO_DATE('2015-01-13', 'YYYY-MM-DD'), 'ID28', 'ES9121000418450200051328', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Sebastian', 'Pozo', 'H', TO_DATE('2014-11-29', 'YYYY-MM-DD'), 'ID29', 'ES9121000418450200051329', 08700, 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Manolo', 'Seguro', 'H', TO_DATE('2014-11-11', 'YYYY-MM-DD'), 'ID30', 'ES9121000418450200051330', 08700, 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 6', 'C:\jugadors\persona.jpg'); -- 13



-- Assign jugadors to equips
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (1, 1, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (1, 2, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (1, 3, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (1, 4, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (1, 5, 'S'); 

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (3, 6, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (3, 7, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (3, 8, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (3, 9, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (3, 10, 'S');


INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (4, 11, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (4, 12, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (4, 13, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (4, 14, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (4, 15, 'N');

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 16, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 17, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 18, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 19, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 20, 'S'); 

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (6, 21, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (6, 22, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (6, 23, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (6, 24, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (6, 25, 'S');  


INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 26, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 27, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 28, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 29, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 30, 'S');  

COMMIT


