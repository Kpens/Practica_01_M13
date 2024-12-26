-- Insert a temporada
INSERT INTO Temporada (anny) VALUES (2024);
INSERT INTO Temporada (anny) VALUES (2025);

INSERT INTO usuari (login, nom_usu, password) VALUES ('usuari', 'usuari','usuari'); 

INSERT INTO usuari (login, nom_usu, password) VALUES ('Alba', 'Alba','123456789'); 

-- Insert categories
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Benjami', 7, 8);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Alevi', 9, 11);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Infantil', 12, 13);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Cadet', 14, 15);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Juvenil', 16, 17);
INSERT INTO Categoria (nom, edat_min, edat_max) VALUES ('Senior', 18, 101);


-- Insert equips
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Masculi', 'H', 2024, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Femeni', 'D', 2024, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Mixte', 'M', 2024, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Masculi', 'H', 2025, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Femeni', 'D', 2025, 1);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Benjami Mixte', 'M', 2025, 1);


INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Masculi', 'H', 2024, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Femeni', 'D', 2024, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Mixte', 'M', 2024, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Masculi', 'H', 2025, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Femeni', 'D', 2025, 2);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Alevi Mixte', 'M', 2025, 2);

INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Masculi', 'H', 2024, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Femeni', 'D', 2024, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Mixte', 'M', 2024, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Masculi', 'H', 2025, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Femeni', 'D', 2025, 3);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Infantil Mixte', 'M', 2025, 3);


INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Masculi', 'H', 2024, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Femeni', 'D', 2024, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Mixte', 'M', 2024, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Masculi', 'H', 2025, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Femeni', 'D', 2025, 4);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Cadet Mixte', 'M', 2025, 4);


INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Masculi', 'H', 2024, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Femeni', 'D', 2024, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Mixte', 'M', 2024, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Masculi', 'H', 2025, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Femeni', 'D', 2025, 5);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Juvenil Mixte', 'M', 2025, 5);

INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Masculi', 'H', 2024, 6);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Femeni', 'D', 2024, 6);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Mixte', 'M', 2024, 6);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Masculi', 'H', 2025, 6);
INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES ('Senior Femeni', 'D', 2025, 6);

-- Jugadors per a l'equip Benjami Femeni (edats 7-8)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Lara', 'Blanco', 'D', TO_DATE('2017-06-10', 'YYYY-MM-DD'), '97958762Z', 'ES4320804469304329649513', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 1', 'C:\jugadors\97958762Z.png'); -- 8

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Clara', 'Sanz', 'D', TO_DATE('2017-08-15', 'YYYY-MM-DD'), '26773311T', 'ES0500753882573669176662', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 2', 'C:\jugadors\26773311T.png'); -- 8

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Sara', 'Vidal', 'D', TO_DATE('2017-07-20', 'YYYY-MM-DD'), '00258888T', 'ES3914656485153417253568', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 3', 'C:\jugadors\00258888T.png'); -- 9

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Mia', 'Torres', 'D', TO_DATE('2017-01-05', 'YYYY-MM-DD'), '16797653V', 'ES5820383375769942562248', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 4', 'C:\jugadors\16797653V.png'); -- 9

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Nora', 'Morales', 'D', TO_DATE('2017-03-30', 'YYYY-MM-DD'), '76258289H', 'ES2014657779511731483325', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 5', 'C:\jugadors\76258289H.png'); -- 9


-- Jugadors per a l'equip Infantil Mixte (edats 12-13)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Oscar', 'Garcia', 'H', TO_DATE('2013-04-10', 'YYYY-MM-DD'), '54495074R', 'ES5114651661786979337188', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\54495074R.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Lucas', 'Cruz', 'H', TO_DATE('2013-10-05', 'YYYY-MM-DD'), '67045241B', 'ES8600818378813269557467', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 7', 'C:\jugadors\67045241B.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Noah', 'Hernandez', 'H', TO_DATE('2012-02-15', 'YYYY-MM-DD'), '55186663A', 'ES6920385493112987519953', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 8', 'C:\jugadors\55186663A.png'); -- 12

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Luca', 'Ramirez', 'D', TO_DATE('2012-05-22', 'YYYY-MM-DD'), '39300316D', 'ES7001286421647716485634', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 9', 'C:\jugadors\39300316D.png'); -- 12

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Elena', 'Fernandez', 'D', TO_DATE('2012-09-18', 'YYYY-MM-DD'), '01979086M', 'ES2001286329978731196623', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 10', 'C:\jugadors\01979086M.png'); -- 12

-- Jugadors per a l'equip Cadet Masculi (edats 14-15)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Alex', 'Perez', 'H', TO_DATE('2010-11-30', 'YYYY-MM-DD'), '12368881X', 'ES1620808385304282793513', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 11', 'C:\jugadors\12368881X.png'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Mateo', 'Serrano', 'H', TO_DATE('2010-05-25', 'YYYY-MM-DD'), '95433426D', 'ES3021003152038879392137', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 12', 'C:\jugadors\95433426D.png'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Dani', 'Moreno', 'H', TO_DATE('2010-02-10', 'YYYY-MM-DD'), '48855508N', 'ES3904878825242142952965', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 13', 'C:\jugadors\48855508N.png'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Adrian', 'Navarro', 'H', TO_DATE('2010-08-14', 'YYYY-MM-DD'), '10243043Q', 'ES5020954776828486169896', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 14', 'C:\jugadors\10243043Q.png'); -- 15

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Toni', 'Camps', 'H', TO_DATE('2011-12-02', 'YYYY-MM-DD'), '75853280Q', 'ES2414653622698262596722', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 15', 'C:\jugadors\75853280Q.png'); -- 15

-- Jugadors per a l'equip Juvenil Masculi (edats 16-17)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Xavi', 'Bermidez', 'H', TO_DATE('2009-01-11', 'YYYY-MM-DD'), '33902640L', 'ES4004878815514117979794', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 16', 'C:\jugadors\33902640L.png'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Ruben', 'Lopez', 'H', TO_DATE('2009-04-15', 'YYYY-MM-DD'), '46028777G', 'ES1121006845875313856193', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 17', 'C:\jugadors\46028777G.png'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Pablo', 'Roca', 'H', TO_DATE('2009-10-20', 'YYYY-MM-DD'), '82842739M', 'ES4120801565318977228727', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 18', 'C:\jugadors\82842739M.png'); -- 17

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Victor', 'Martinez', 'H', TO_DATE('2008-07-30', 'YYYY-MM-DD'), '33556994V', 'ES6500757416515123666369', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 19', 'C:\jugadors\33556994V.png'); -- 18

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Liam', 'Salas', 'H', TO_DATE('2008-09-25', 'YYYY-MM-DD'), '58793407W', 'ES3314659116104762296934', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 20', 'C:\jugadors\58793407W.png'); -- 18

-- Jugadors per a l'equip Senior Femeni (edats 18-21)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Elisa', 'Dominguez', 'D', TO_DATE('2005-03-22', 'YYYY-MM-DD'), '68399800P', 'ES1001287177038478246641', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 21', 'C:\jugadors\68399800P.png'); -- 21

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Julia', 'Gonzalez', 'D', TO_DATE('2005-10-10', 'YYYY-MM-DD'), '36506272M', 'ES1020951336999497428946', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 22', 'C:\jugadors\36506272M.png'); -- 22

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Laura', 'Vidal', 'D', TO_DATE('2004-05-18', 'YYYY-MM-DD'), '48556386M', 'ES6301826642179435461978', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 23', 'C:\jugadors\48556386M.png'); -- 22

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Carmen', 'Sanchez', 'D', TO_DATE('2004-02-14', 'YYYY-MM-DD'), '69516791G', 'ES2100814775139974536543', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 24', 'C:\jugadors\69516791G.png'); -- 19

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Valeria', 'Romero', 'D', TO_DATE('2005-11-30', 'YYYY-MM-DD'), '30846170L', 'ES3004877216677372671331', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 25', 'C:\jugadors\30846170L.png'); -- 20


-- Jugadors per a l'equip Alevi Mixte (edats 9-11)
INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Maria', 'Lopez', 'H', TO_DATE('2015-04-10', 'YYYY-MM-DD'), '29279928P', 'ES5701825898971944371121', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\29279928P.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Joana', 'Dominguez', 'D', TO_DATE('2014-10-02', 'YYYY-MM-DD'), '32519365X', 'ES3320809667088282627132', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\32519365X.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Rita', 'Sanchez', 'D', TO_DATE('2015-01-13', 'YYYY-MM-DD'), '39278726Q', 'ES8804879153174777817157', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 6', 'C:\jugadors\39278726Q.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Sebastian', 'Pozo', 'H', TO_DATE('2014-11-29', 'YYYY-MM-DD'), '13131877G', 'ES9604872959564874388281', '08700', 'Igualada', 'Barcelona', 'Espanya', 2024, 'C/ carrer 6', 'C:\jugadors\13131877G.png'); -- 13

INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto)
VALUES ('Manolo', 'Seguro', 'H', TO_DATE('2014-11-11', 'YYYY-MM-DD'), '92544371F', 'ES2304876461548981867482', '08700', 'Igualada', 'Barcelona', 'Espanya', 2025, 'C/ carrer 6', 'C:\jugadors\92544371F.png'); -- 13



-- Assign jugadors to equips
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 1, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 2, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 3, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 4, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (2, 5, 'S'); 
-- Assign jugadors to equips
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 1, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 2, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 3, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 4, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (5, 5, 'S'); 

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (15, 6, 'N'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (15, 7, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (15, 8, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (15, 9, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (15, 10, 'S');

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (18, 6, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (18, 7, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (18, 8, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (18, 9, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (18, 10, 'S');


INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (19, 11, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (19, 12, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (19, 13, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (19, 14, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (19, 15, 'N');

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (22, 11, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (22, 12, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (22, 13, 'S');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (22, 14, 'N');
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (22, 15, 'N');

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (25, 16, 'N'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (25, 17, 'N'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (25, 18, 'N'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (25, 19, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (25, 20, 'S'); 

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (28, 16, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (28, 17, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (28, 18, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (28, 19, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (28, 20, 'S'); 


INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (32, 21, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (32, 22, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (32, 23, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (32, 24, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (32, 25, 'S');  

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (35, 21, 'N');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (35, 22, 'N');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (35, 23, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (35, 24, 'S');  
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (35, 25, 'N');  


INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (9, 26, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (9, 27, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (9, 28, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (9, 29, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (9, 30, 'S');  

INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (12, 26, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (12, 27, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (12, 28, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (12, 29, 'S'); 
INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (12, 30, 'S');  

COMMIT;