module FileSystem ( FileSystem, nuevoF, departamentosF, anunciosF, agregarAnuncioF, sacarAnuncioF, agregarDepartamentoF, sacarDepartamentoF, anunciosParaF )
    where

import Tipos
import Anuncio

data FileSystem = FS [Departamento] [Anuncio] deriving (Eq, Show)

nuevoF :: FileSystem                                              -- permite obtener un nuevo FileSystem
nuevoF = FS [] []

departamentosF :: FileSystem -> [ Departamento ]                  -- dado un FileSystem retorna los departamentos que incluye
departamentosF (FS depts _) = depts

anunciosF :: FileSystem -> [ Anuncio ]                            -- dado un FileSystem retorna los anuncios que incluye
anunciosF (FS _ anuns) = anuns

agregarAnuncioF :: Anuncio -> FileSystem -> FileSystem            -- permite agregar un anuncio  
agregarAnuncioF anun fs | elem anun (anunciosF fs) = fs
                        | otherwise = anun : anunciosF fs

sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio


agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento
agregarAnuncioF dept fs | elem dept (departamentosF fs) = fs
                        | otherwise = dept : departamentosF fs

sacarDepartamentoF :: Departamento -> FileSystem -> FileSystem    -- permite eliminar un departamento


anunciosParaF :: [Departamento] -> FileSystem -> [Anuncio]        -- entrega los anuncios a emitir para un conjunto de departamentos