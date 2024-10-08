module Prompter ( Prompter, nuevoP, archivosR, departamentosP, iteradorP, configurarP, anunciosP, showP, avanzarP, duracionP )
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
configurarP prompt depts | null depts = error "no se puede configurar el prompter con un conjunto de departamentos vacio"
                         | not (null [dept| dept <- depts, notElem dept (departamentosF (archivosR prompt))]) = error "el file system del prompter no abarca alguno de los departamentos indicados"
                         | null (anunciosParaF depts (archivosR prompt)) = error "no hay anuncios para los departamentos indicados"
                         | otherwise = Pro (archivosR prompt) depts 0

anunciosP :: Prompter -> [Nombre]                      -- entrega la lista de nombres de anuncios configurados
anunciosP prompt = map nombreA (anunciosParaF (departamentosP prompt) (archivosR prompt))

showP :: Prompter -> Anuncio                           -- muestra el anuncio actual 
showP prompt = anunciosParaF (departamentosP prompt) (archivosR prompt) !! iteradorP prompt

avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
avanzarP prompt | (iteradorP prompt + 1) < length (anunciosParaF (departamentosP prompt) (archivosR prompt)) = Pro (archivosR prompt) (departamentosP prompt) (iteradorP prompt + 1)
                | otherwise = Pro (archivosR prompt) (departamentosP prompt) 0

duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados
duracionP prompt = sum (map duracionA (anunciosParaF (departamentosP prompt) (archivosR prompt)))