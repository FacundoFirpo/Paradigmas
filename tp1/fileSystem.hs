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
                        | otherwise = FS (departamentosF fs) (anun : anunciosF fs)

sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio
sacarAnuncioF anun fs | notElem anun (anunciosF fs) = error "el anuncio no esta en el file system"
                      | otherwise = FS (departamentosF fs) nAnuns
                    where nAnuns = [y| y <- anunciosF fs, y /= anun]

agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento
agregarDepartamentoF dept fs | elem dept (departamentosF fs) = fs
                             | otherwise = FS (dept : departamentosF fs) (anunciosF fs)

sacarDepartamentoF :: Departamento -> FileSystem -> FileSystem    -- permite eliminar un departamento
sacarDepartamentoF dept fs | notElem dept (departamentosF fs) = error "el departamento no esta en el file system"
                           | otherwise = FS nDepts (anunciosF fs)
                        where nDepts = [y| y <- departamentosF fs, y /= dept]

anunciosParaF :: [Departamento] -> FileSystem -> [Anuncio]        -- entrega los anuncios a emitir para un conjunto de departamentos
anunciosParaF depts fs = [anun| anun <- anunciosF fs, aplicaA depts anun]