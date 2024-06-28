
--------------------------------------------------------
--   TABLAS PARA LA TAREA UT11
--------------------------------------------------------

--------------------------------------------------------
--   Tabla CATEGORIAS_R
--------------------------------------------------------
 
DROP TABLE MUEBLES CASCADE CONSTRAINTS;
CREATE TABLE  MUEBLES(
    IDMUEBLE       	NUMBER(4) CONSTRAINT PK_MUEBLES PRIMARY KEY,
    DESCRIPMUEBLE      	VARCHAR2(50),
    PRECIOUNITARIO  	NUMBER(8,2),
    UNIDADESEXISTENTES  NUMBER(4)
);

--------------------------------------------------------
--   Tabla ARTICULOS_R
--------------------------------------------------------
 
DROP TABLE VENTAS CASCADE CONSTRAINTS;
CREATE TABLE VENTAS(
    IDVENTA	 		NUMBER(6)CONSTRAINT PK_VENTAS PRIMARY KEY,
    NOMBRECLI	 	VARCHAR2(30),
    UNIDADESVEND 	NUMBER(4),
    IDMUEBLE	 	NUMBER(4) REFERENCES MUEBLES(IDMUEBLE)
);
