-- public.centromonitoraggio definition

-- Drop table

-- DROP TABLE public.centromonitoraggio;

CREATE TABLE public.centromonitoraggio (
	"name" varchar(255) NOT NULL,
	address varchar(255) NULL,
	CONSTRAINT centromonitoraggio_pk PRIMARY KEY (name)
);


-- public.coordinatemonitoraggio definition

-- Drop table

-- DROP TABLE public.coordinatemonitoraggio;

CREATE TABLE public.coordinatemonitoraggio (
	id int4 NULL,
	"name" varchar(255) NULL,
	name_ascii varchar(255) NULL,
	country_code varchar(255) NULL,
	country_name varchar(255) NULL,
	lat varchar NULL,
	lon varchar NULL,
	CONSTRAINT coordinatemonitoraggio_unique UNIQUE (id)
);


-- public.lavora definition

-- Drop table

-- DROP TABLE public.lavora;

CREATE TABLE public.lavora (
	id_coordinate int4 NOT NULL,
	nome_centro varchar(255) NOT NULL,
	CONSTRAINT lavora_pk PRIMARY KEY (id_coordinate, nome_centro),
	CONSTRAINT centro FOREIGN KEY (nome_centro) REFERENCES public.centromonitoraggio("name") ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT coordinate FOREIGN KEY (id_coordinate) REFERENCES public.coordinatemonitoraggio(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.operatoreregistrato definition

-- Drop table

-- DROP TABLE public.operatoreregistrato;

CREATE TABLE public.operatoreregistrato (
	nome varchar(255) NULL,
	cognome varchar(255) NULL,
	cf varchar(16) NOT NULL,
	mail varchar(255) NULL,
	nick varchar(255) NULL,
	"password" varchar(255) NULL,
	nome_centro varchar(255) NULL,
	CONSTRAINT operatoreregistrato_pk PRIMARY KEY (cf),
	CONSTRAINT centro FOREIGN KEY (nome_centro) REFERENCES public.centromonitoraggio("name") ON DELETE CASCADE ON UPDATE CASCADE
);


-- public.parametriclimatici definition

-- Drop table

-- DROP TABLE public.parametriclimatici;

CREATE TABLE public.parametriclimatici (
	idcitta int4 NOT NULL,
	nome_centro varchar(255) NOT NULL,
	"data" date NOT NULL,
	ora timestamp NOT NULL,
	vento varchar NOT NULL,
	umidita varchar NOT NULL,
	pressione varchar NOT NULL,
	temperatura varchar NOT NULL,
	precipitazioni varchar NOT NULL,
	altitudine varchar NOT NULL,
	massa varchar NOT NULL,
	CONSTRAINT parametriclimatici_pk PRIMARY KEY (idcitta, nome_centro, data, ora),
	CONSTRAINT centro FOREIGN KEY (nome_centro) REFERENCES public.centromonitoraggio("name") ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT citta FOREIGN KEY (idcitta) REFERENCES public.coordinatemonitoraggio(id) ON DELETE CASCADE ON UPDATE CASCADE
);