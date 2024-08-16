module Prompter ( Prompter, nuevoP, archivosR, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem
import FileSystem (anunciosF)
import Control.Monad (when)

data Prompter = Pro FileSystem  [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
nuevo P fs = Pro fs []

archivosR :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
archivosR (Pro fs _) = fs

departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
departamentosP (Pro _ depts) = depts

configurarP :: Prompter -> [Departamento] -> Prompter  -- Prepara el prompter para emitir los anuncios en los departementos indicados
configurarP prompt depts | null [dept| dept <- depts, notElem dept (departamentosP prompt)] = prompt
                         | otherwise = Pro () (depts)

anunciosP :: Prompter ->  [Nombre]                      -- entrega la lista de nombres de anuncios configurados
anunciosP prompt = map nombreA (anunciosF (archivosR prompt))

showP :: Prompter -> Anuncio                           -- muestra el anuncio actual 
showP prompt = head (anunciosF (archivosR prompt))

avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
avanzarP prompt = Pro (FS (departamentosF fs) (tail anuns ++ [head anuns])) (departamentosP prompt)
    where fs = archivosR prompt; anuns = anunciosF fs

duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados
duracionP prompt = sum (map duracionA (anunciosF(archivosR prompt)))