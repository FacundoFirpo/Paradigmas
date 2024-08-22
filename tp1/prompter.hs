module Prompter ( Prompter, nuevoP, archivosR, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem
import Control.Monad (when)
import Distribution.Compat.Lens (_1)
import System.Info (arch)

data Prompter = Pro FileSystem [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
nuevoP fs = Pro fs [] 0

archivosR :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
archivosR (Pro fs _ _) = fs

departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
departamentosP (Pro _ depts _) = depts

iteradorP :: Prompter -> Int                           -- dado un prompter retorna su iterador
iteradorP (Pro _ _ it) = it

configurarP :: Prompter -> [Departamento] -> Prompter  -- Prepara el prompter para emitir los anuncios en los departementos indicados
configurarP prompt depts | not (null [dept| dept <- depts, notElem dept (departamentosF (archivosR prompt))]) = error "el file system del prompter no abarca alguno de los departamentos indicados"
                         | otherwise = Pro (archivosR prompt) depts 0

anunciosP :: Prompter -> [Nombre]                      -- entrega la lista de nombres de anuncios configurados
anunciosP prompt = map nombreA (anunciosF (archivosR prompt))

showP :: Prompter -> Anuncio                           -- muestra el anuncio actual 
showP prompt =  anunciosF (archivosR prompt) !! iteradorP prompt

avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
avanzarP prompt = Pro (archivosR prompt) (departamentosP prompt) (iteradorP prompt + 1)

duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados
duracionP prompt = sum (map duracionA (anunciosF(archivosR prompt)))