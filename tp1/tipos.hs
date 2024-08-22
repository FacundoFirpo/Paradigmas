-- Prompter

-- En una conocida tienda de departamentos el area de promocion y marketing decide contratar un nuevo servicio.
-- El mismo consiste de un sistema que permite definir y reproducir todos los anuncios de video de la compañia.
-- Luego de definir en el sistema de archivos de la aplicacion los anuncios con su duracion y departamentos a los que corresponde...
-- ...se configura el prompter para el piso o sector en el que va a emitir los anuncios y las areas alojadas en esa zona.

-- Para sostener este modelo se cuenta con las siguientes entidades:

module Tipos
    where

type Duracion = Int
type Departamento = String
type Nombre = String