--------------------------------------------------------
-- TAREA PROG10: CREACIÓN DE LOS TIPOS Y TABLAS
--------------------------------------------------------

--------------------------------------------------------
--  DDL for Type TIPO_MUEBLE
--------------------------------------------------------
  

CREATE OR REPLACE TYPE TIPO_MUEBLE AS OBJECT( 
    IDMUEBLE       	NUMBER(4),
    DESCRIPMUEBLE      	VARCHAR2(50),
    PRECIOUNITARIO  	NUMBER(8,2),
    UNIDADESEXISTENTES  NUMBER(4)
)

/
--------------------------------------------------------
--  DDL for Type TIPO_VENTA
--------------------------------------------------------

CREATE OR REPLACE TYPE TIPO_VENTA AS OBJECT(
    IDVENTA	 	NUMBER(6),
    NOMBRECLI	 	VARCHAR2(30),
    UNIDADESVEND 	NUMBER(4),
    MUEBLE	 	REF TIPO_MUEBLE -- Mueble que se ha vendido	
)

/

--------------------------------------------------------
--   Tabla MUEBLES
--------------------------------------------------------
-- CREAR TABLA MUEBLES_OBJ
CREATE TABLE MUEBLES_OBJ OF TIPO_MUEBLE(
    IDMUEBLE PRIMARY KEY);

--------------------------------------------------------
--  Tabla VENTAS
--------------------------------------------------------
-- Crear tabla VENTAS_OBJ
CREATE TABLE VENTAS_OBJ OF TIPO_VENTA(
    IDVENTA PRIMARY KEY);
    
