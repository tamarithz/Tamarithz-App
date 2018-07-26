DROP TABLE usuario IF EXISTS;
DROP TABLE authorities IF EXISTS;

DROP TABLE empresa IF EXISTS;
DROP TABLE empleado IF EXISTS;
DROP TABLE report IF EXISTS;

DROP TABLE activos_tipo IF EXISTS;
DROP TABLE activos_subtipo IF EXISTS;
DROP TABLE amenazas_tipo IF EXISTS;
DROP TABLE amenazas_subtipo IF EXISTS;
DROP TABLE salvaguardas_tipo IF EXISTS;
DROP TABLE salvaguardas_subtipo IF EXISTS;

/*USUARIO y ROLES*/
CREATE TABLE usuario (
	id         INTEGER IDENTITY PRIMARY KEY, 
	email VARCHAR(45), 
	password VARCHAR(60), 
	enabled TINYINT DEFAULT 1
);

CREATE INDEX usuario_email ON usuario (email);

CREATE TABLE authorities (
	id INTEGER IDENTITY PRIMARY KEY, 
	usuario INTEGER, 
	authority VARCHAR(45)
);
	
CREATE INDEX usuario_authority ON authorities (usuario);

ALTER TABLE authorities 
	ADD CONSTRAINT fk_authorities_usuario 
	FOREIGN KEY (usuario)
	REFERENCES usuario (id);

/*CREDENCIALES DE ADMINISTRADOR*/
/*INSERT INTO usuario (email, password, enabled) VALUES('admin@tamarithz.com', 'admin', 1);

INSERT INTO authorities (usuario, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities (usuario, authority) VALUES(1, 'ROLE_ADMIN');*/

/*EMPRESA*/
CREATE TABLE empresa (
  id         INTEGER IDENTITY PRIMARY KEY,
  nombre VARCHAR(30),
  cif  VARCHAR(30),
  direccion VARCHAR(100),
  ciudad VARCHAR(30),
  email VARCHAR(30)
);
CREATE INDEX cif_empresa ON empresa (cif);

/*ACTIVOS EMPRESA*/

CREATE TABLE activos_empresa (
	id INTEGER IDENTITY PRIMARY KEY,
	id_empresa INTEGER,
	id_activo INTEGER,
	id_empleado INTEGER
);

CREATE INDEX id_empresa_activos_empresa ON activos_empresa(id_empresa);

ALTER TABLE activos_empresa 
	ADD CONSTRAINT fk_activos_empresa_empresa 
	FOREIGN KEY (id_empresa)
	REFERENCES empresa (id);

/*EMPLEADO*/

CREATE TABLE empleado (
	id INTEGER IDENTITY PRIMARY KEY,
	id_empresa INTEGER,
	nombre VARCHAR(45),
	identificador VARCHAR(45),
	apellidos VARCHAR(45),
	email VARCHAR(45)
);

CREATE INDEX dni_empleado ON empleado(identificador);

ALTER TABLE empleado 
	ADD CONSTRAINT fk_empleado_empresa 
	FOREIGN KEY (id_empresa)
	REFERENCES empresa (id);


/*ACTIVOS*/

CREATE TABLE activos_tipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(100)
);

CREATE INDEX nombre_activos_tipo ON activos_tipo(nombre);

CREATE TABLE activos_subtipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(100),
	id_activo INTEGER
);

CREATE INDEX nombre_activos_subtipo ON activos_subtipo(nombre);

ALTER TABLE activos_subtipo 
	ADD CONSTRAINT fk_activos_subtipo_activos_tipo 
	FOREIGN KEY (id_activo)
	REFERENCES activos_tipo (id);

/*AMENAZAS*/

CREATE TABLE amenazas_tipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(100)
);

CREATE INDEX nombre_amenazas_tipo ON amenazas_tipo(nombre);

CREATE TABLE amenazas_subtipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(100),
	id_amenaza INTEGER
);

CREATE INDEX nombre_amenazas_subtipo ON amenazas_subtipo(nombre);

ALTER TABLE amenazas_subtipo 
	ADD CONSTRAINT fk_amenazas_subtipo_amenazas_tipo 
	FOREIGN KEY (id_amenaza)
	REFERENCES amenazas_tipo (id);

/*SALVAGUARDAS*/

CREATE TABLE salvaguardas_tipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(100)
);

CREATE INDEX nombre_salvaguardas_tipo ON salvaguardas_tipo(nombre);

CREATE TABLE salvaguardas_subtipo (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	desc VARCHAR(255),
	id_salvaguarda INTEGER
);

CREATE INDEX nombre_salvaguardas_subtipo ON salvaguardas_subtipo(nombre);

ALTER TABLE salvaguardas_subtipo 
	ADD CONSTRAINT fk_salvaguardas_subtipo_salvaguardas_tipo 
	FOREIGN KEY (id_salvaguarda)
	REFERENCES salvaguardas_tipo (id);
/*INFORMES*/
	
CREATE TABLE report (
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(45),
	identificador VARCHAR(45),
	fecha DATE
);

CREATE INDEX identificador_report ON report (identificador);
