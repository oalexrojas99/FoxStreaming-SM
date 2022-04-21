drop database FoxDB;

CREATE DATABASE FoxDB;

use FoxDB;

CREATE TABLE Cliente (
	clienteId int auto_increment NOT NULL primary key, 
	nombreCompleto varchar(50) NOT NULL,
    nombreUsuario varchar(30) NOT NULL,
    celular varchar(9) NOT NULL
);

CREATE TABLE TipoServicioStreaming (
	tipoServicioId int auto_increment NOT NULL primary key,
    nombre varchar(30) NOT NULL,
    url_logo varchar(35),
    maxUsuarios tinyint
);

CREATE TABLE Plan (
	planId int auto_increment NOT NULL primary key,
    tipoServicioId int NOT NULL,
    tiempoSuscripcionMeses tinyint NOT NULL,
    costo decimal(5, 2) NOT NULL
);

ALTER TABLE Plan
ADD CONSTRAINT fk_plan_tipoServicioStreaming FOREIGN KEY (tipoServicioId) 
	REFERENCES TipoServicioStreaming (tipoServicioId) ON DELETE CASCADE;

CREATE TABLE Cuenta (
	cuentaId int auto_increment NOT NULL primary key,
    planId int NOT NULL,
    correo varchar(50) NOT NULL, 
    contrasena varchar(50) NOT NULL, 
    estaActiva bit NOT NULL,
    numActualUsuarios tinyint NOT NULL
);

ALTER TABLE Cuenta
ADD CONSTRAINT fk_cuenta_plan FOREIGN KEY (planId) 
	REFERENCES Plan (planId) ON DELETE CASCADE;

CREATE TABLE Suscripcion (
	suscripcionId int auto_increment NOT NULL primary key,
    clienteId int NOT NULL,
    cuentaId int NOT NULL,
    fechaInicio date,
    estaEnVigencia bit,
    pagoEfectuado decimal(5, 2)
);

ALTER TABLE Suscripcion
ADD CONSTRAINT fk_suscripcion_cliente FOREIGN KEY (clienteId) REFERENCES Cliente (clienteId) ON DELETE CASCADE,
ADD CONSTRAINT fk_suscripcion_cuenta FOREIGN KEY (cuentaId) REFERENCES Cuenta (cuentaId) ON DELETE CASCADE;

/*TRIGGERS*/
/*1. Crear trigger para cuando se genera una suscripcion, la cantidad de usuarios asociada a la cuenta se incremente en 1*/
DELIMITER //
CREATE TRIGGER ins_incrementarUsuariosCuenta AFTER INSERT ON Suscripcion
FOR EACH ROW
BEGIN
	UPDATE Cuenta SET numActualUsuarios = numActualUsuarios + 1 WHERE cuentaId = NEW.cuentaId;
END; //
delimiter ;

DELIMITER //
CREATE TRIGGER upd_disminuirUsuariosCuenta1 AFTER UPDATE ON Suscripcion
FOR EACH ROW
BEGIN
	IF NEW.estaEnVigencia = 0 THEN
		UPDATE Cuenta SET numActualUsuarios = numActualUsuarios - 1 WHERE cuentaId = NEW.cuentaId;
	ELSEIF NEW.estaEnVigencia = 1 THEN
		UPDATE Cuenta SET numActualUsuarios = numActualUsuarios + 1 WHERE cuentaId = NEW.cuentaId;
    END IF;
END; //
delimiter ;

-- Agregando registros en Cliente
INSERT INTO Cliente (nombreCompleto, nombreUsuario, celular) VALUES ('Alexander Rojas', 'Alex', '972839402'),
('Oscar Salazar', 'Oscar', '970499860'), ('Alejandro Perez', 'Alejandro', '951295457'), ('Bianca Romero', 'Bianca', '958898045'),
('Marcus Fernandez', 'Mark', '952302452'), ('Xavier', 'Xavi', '985402366'), ('Fiorella M.', 'Fiorella', '912302659'),
('Brenda Huaman', 'Brenda', '956214200'), ('Leandro Jimenez', 'Lean', '956321202'), ('Hector Huaripata', 'Hector', '981542374');

-- Agregando registros en ServicioStreaming
INSERT INTO TipoServicioStreaming (nombre, maxUsuarios) VALUES ('Netflix', 6), ('Spotify Premium', 6), ('HBO Max', 6), 
('Disney plus', 4), ('Star plus', 6), ('Amazon Prime Video', 5), ('YouTube Premium', 6), ('Paramount plus', 8),
('Crunchyroll', 6);

-- Agregando registros en Plan
INSERT INTO Plan (tipoServicioId, tiempoSuscripcionMeses, costo) 
VALUES (1, 1, 6), (1, 2, 10.5), (1, 3, 16.5), (1, 4, 29.5), -- Netflix
	   (2, 1, 20), (2, 2, 25), (2, 3, 30), (2, 4, 35), -- Spotify Premium
       (3, 1, 14), (3, 2, 19.5), (3, 3, 25), -- HBO Max
       (4, 1, 12.5), (4, 2, 20), (4, 3, 26), (4, 4, 32), (4, 6, 40), -- Disney plus
       (5, 1, 9), (5, 2, 15.5); -- Star plus
       
INSERT INTO Cuenta (planId, correo, contrasena, estaActiva, numActualUsuarios) 
VALUES (1, 'asdatr_ed@9ss8.net', 'contrasena1', 1, 0), (1, 'e32eef@dd9.com.pe', 'contrasena2', 1, 0), (1, 'asdas@838s.com', 'contrasena3', 1, 0), -- Asociadas a Netflix, para plan de 1 mes
	   (2, 'ew34448_ddvf@se4455.com', '4848', 1, 0), (2, 'ccccc_ssss@666.com.pe', '956d', 1, 0), (1, 'aaaaa_vvvv@asde.com', 'd566', 1, 0), -- Asociadas a Netflix, para plan de 2 meses
       (5, 'aaawww@dsadc.com', 'fox9855', 1, 0), (5, 'csadtsss@7ff.com.pe', 'dda', 1, 0), (5, 'oaskfv@asde.com', '6699', 1, 0), -- Asociadas a Spotify Premium, para plan de 1 mes
       (6, 'errrtt@asds.com', '4848', 1, 0), (6, 'dasd.dasd@sdf.com.pe', '9696', 1, 0), (6, 'tfvsdf.gg8@4rfe.com', '11777', 1, 0), -- Asociadas a Spotify Premium, para plan de 2 meses
       (8, 'rrttttr@se4455.com', '0558', 1, 0), (8, 'assaaa@rrtt.com.pe', '1999', 1, 0), (8, 'vvccsada@asde.com', 'aaaa', 1, 0), -- Asociadas a Spotify Premium, para plan de 4 meses
       (9, 'errrtt@asds.com', '4848', 1, 0), (9, 'dasd.dasd@sdf.com.pe', '9696', 1, 0), (9, 'tfvsdf.gg8@4rfe.com', '11777', 1, 0), -- Asociadas a HBO Max, para plan de 1 mes
       (10, 'e8ds9s@fd.com', '4848', 1, 0), (10, 'jdhh.dasd@d.com.pe', 'fffs', 1, 0), (10, 'dafgtdstt@4rfe.com', '695969', 1, 0), -- Asociadas a HBO Max, para plan de 2 meses
       (11, 'erdstt@rrsds.com', '4848', 1, 0), (11, 'dfdd.dasfd@ff5.com.pe', 'dh5d', 1, 0), (11, 'fda.ggd8@af.com', '777', 1, 0), -- Asociadas a HBO Max, para plan de 3 meses
       (12, 't5sager', 'sg', 1, 0), (12, '6etgh', 'dh5d', 1, 0), (12, 'fda.ggd8@af.com', '4frgg', 1, 0), -- Asociadas a Disney plus, para plan de 1 mes
       (13, 'ertw455tre', '5we5', 1, 0), (13, 'dffgstsfpe', 'rr55', 1, 0), (13, 'fsfsfg', '5e3r', 1, 0), (13, 'sd44', 'sds dds', 1, 0); -- Asociadas a Disney plus, para plan de 2 meses


-- Agregando registros en Suscripcion
INSERT INTO Suscripcion (clienteId, cuentaId, fechaInicio, estaEnVigencia, pagoEfectuado) 
VALUES (1, 1, '2022-02-26', 1, 15), (1, 20, '2022-03-04', 1, 22), (1, 22, '2022-04-02', 1, 35.5), -- Cliente 1 tiene 2 cuentas en HBO Max 1 cuenta en Netflix
	(2, 30, '2022-03-10', 1, 17.5), (2, 2, '2022-03-31', 1, 22), (2, 9, '2022-01-22', 1, 31), -- Cliente 2 tiene 1 cuentas en Disney Plus, 1 en Netflix y una en Spotify
	(3, 3, '2022-01-19', 1, 17), (3, 20, '2022-03-04', 1, 15.4), (3, 16, '2022-04-15', 1, 24.5), -- Cliente 3 tiene 1 cuenta en netflix y 1 en HBO Max
	(4, 25, '2022-01-11', 1, 15), (4, 26, '2022-04-01', 1, 11), (4, 1, '2022-03-10', 1, 29.9), (4, 2, '2022-02-10', 1, 28.5), -- Cliente 4 tiene 2 cuentas en Disney Plus y 2 cuentas en Netflix
	(5, 7, '2022-03-08', 1, 10), -- Cliente 5 tiene 1 cuenta en Spotify Premium
	(6, 16, '2022-02-26', 1, 15), (6, 25, '2022-03-04', 1, 19), -- Cliente 6 tiene 1 cuentas en HBO Max y 1 cuenta en Disney Plus
	(7, 7, '2022-02-27', 1, 15), (7, 8, '2022-03-04', 1, 23), (7, 2, '2022-02-04', 1, 22); -- Cliente 7 tiene 2 cuentas en Spotify y 1 cuenta en Netflix

-- Consultas
/*SELECT P.planId, C.cuentaId, TS.nombre FROM Plan P INNER JOIN Cuenta C ON P.planId = C.planId
INNER JOIN TipoServicioStreaming TS ON TS.tipoServicioId =  P.tipoServicioId;

SELECT COUNT(*) FROM Cliente C, Suscripcion S WHERE C.clienteId = S.clienteId  AND S.clienteId = 7;
SELECT * FROM Cuenta;

SELECT P.*, TS.nombre FROM Plan P
INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId
WHERE P.tipoServicioId = 1;

SELECT C.* FROM Cuenta C
INNER JOIN Plan P ON C.planId = P.planId
INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId
WHERE C.numActualUsuarios < TS.maxUsuarios AND C.planId = 10 AND C.estaActiva = 1;

SELECT COUNT(C.cuentaId) FROM Cuenta C
INNER JOIN Suscripcion S ON C.cuentaId = S.cuentaId
WHERE S.estaEnVigencia = 1 AND C.cuentaId = 1;

SELECT S.*, TS.nombre, C.correo, C.contrasena, DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH), CL.nombreCompleto FROM Suscripcion S 
INNER JOIN Cliente CL ON S.clienteId = CL.clienteId
INNER JOIN Cuenta C ON S.cuentaId = C.cuentaId
INNER JOIN Plan P ON C.planId = P.planId
INNER JOIN TipoServicioStreaming TS ON P.tipoServicioId = TS.tipoServicioId
WHERE DATE_ADD(S.fechaInicio, INTERVAL P.tiempoSuscripcionMeses MONTH) < DATE_ADD(CURRENT_DATE(), INTERVAL 15 DAY)
AND S.estaEnVigencia = 1;*/