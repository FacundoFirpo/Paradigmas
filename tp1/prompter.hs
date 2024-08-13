module Prompter ( Prompter, nuevoP, archivosR, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem

data Prompter = Pro FileSystem  [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
nuevo P fs = Pro fs []

archivosR :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
archivosR (Pro fs _) = fs

departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
departamentosP (Pro _ depts) = depts

configurarP :: Prompter -> [Departamento] -> Prompter  -- Prepara el prompter para emitir los anuncios en los departementos indicados
-- usar anunciosParaF

nunciosP :: Prompter ->  [Nombre]                      -- entrega la lista de nombres de anuncios configurados


showP :: Prompter -> Anuncio                           -- muestra el anuncio actual 


avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio


duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados 