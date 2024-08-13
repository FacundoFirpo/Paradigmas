module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agreagarA, sacarA, aplicaA )
  where

import Tipos
import Control.Monad.RWS (MonadWriter(pass))

data Anuncio = Anu Nombre [ Departamento ] Duracion deriving (Eq, Show, Ord)

nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio
nuevoA nombre = Anu nombre []

nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
nombreA (Anu nombre _ _) = nombre

duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
duracionA (Anu _ _ dur) = dur

departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
departamentosA (Anu _ depts _) = depts

agreagarA :: Departamento -> Anuncio -> Anuncio -- permite asignar un departamento a un anuncio
agreagarA dept anun | elem dept (departamentosA anun) = anun
                    | otherwise = dept : departamentosA

sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio


aplicaA :: [ Departamento ] -> Anuncio -> Bool  -- responde si un anuncio debe emitirse para alguno de los departamentos consultados
aplicaA depts anun =  length [y| y <- departamentosA anun, elem y depts] \= 0