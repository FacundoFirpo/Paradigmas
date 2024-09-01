import Tipos
import Anuncio
import FileSystem
import Prompter
import Control.Exception
import System.IO.Unsafe
import GHC.RTS.Flags (GCFlags(allocLimitGrace))
import Prompter (iteradorP)

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

nombre1 = "anuncio1"
nombre2 = "anuncio2"
nombreFalso = "falsoAnuncio"
anun1 = nuevoA nombre1 10
anun2 = nuevoA nombre2 20
anunAsociadoDept1 = agregarA dept1 anun1
anunAsociadoDept2 = agregarA dept2 anun2
falseAnun = nuevoA nombreFalso 20
dept1 = "dept1"
dept2 = "dept2"
falsoDept = "falsoDept"
fs1 = nuevoF
fs2 = nuevoF
fs3 = agregarAnuncioF anunAsociadoDept1 (agregarAnuncioF anunAsociadoDept2 fs1)
fs4 = agregarDepartamentoF dept1 (agregarDepartamentoF dept2 fs3)
prompt1 = nuevoP fs1
prompt2 = nuevoP fs4
prompt1config = configurarP prompt2 [dept1, dept2]

tests = [nombreA anun1 == "anuncio1",
         duracionA anun1 == 10,
         departamentosA anun1 == [],
         departamentosA (agregarA dept1 anun1) == ["dept1"],
         departamentosA (agregarA dept1 (agregarA dept2 anun1)) == ["dept1", "dept2"],
         departamentosA (sacarA dept1 (agregarA dept2 (agregarA dept1 anun1))) == ["dept2"],
         testF(sacarA dept1 anun1),
         aplicaA [dept1] anun1 == False,
         aplicaA [dept1] (agregarA dept1 anun1) == True,
         aplicaA [dept1] (agregarA dept1 (agregarA dept2 anun1)) == True,
         aplicaA [dept1, dept2] (agregarA dept1 (agregarA dept2 anun1)) == True,
         
         anunciosF fs1 == [],
         anunciosF (agregarAnuncioF anun1 fs1) == [anun1],
         anunciosF (agregarAnuncioF anun1 (agregarAnuncioF anun2 fs1)) == [anun1, anun2],
         anunciosF (sacarAnuncioF anun1 (agregarAnuncioF anun2 (agregarAnuncioF anun1 fs1))) == [anun2],
         testF( sacarAnuncioF anun1 fs2),
         departamentosF fs1 == [],
         departamentosF (agregarDepartamentoF dept1 fs1) == [dept1],
         departamentosF (agregarDepartamentoF dept1 (agregarDepartamentoF dept2 fs1)) == [dept1, dept2],
         departamentosF (sacarDepartamentoF dept1 (agregarDepartamentoF dept1 (agregarDepartamentoF dept2 fs1))) == [dept2],
         testF ( sacarDepartamentoF dept2 fs2),
         anunciosF ( agregarDepartamentoF dept1 (sacarAnuncioF anun1 (agregarAnuncioF anun1 (agregarAnuncioF anun2 fs1)))) == [anun2],
         departamentosF ( agregarDepartamentoF dept1 (sacarAnuncioF anun1 (agregarAnuncioF anun1 (agregarAnuncioF anun2 fs1)))) == [dept1],
         anunciosParaF [dept1] fs4 == [anunAsociadoDept1],
         testF(anunciosParaF [] fs4),

         archivosR prompt1 == fs1,
         archivosR prompt1config == fs4,
         departamentosP prompt1 == [],
         departamentosP prompt1config == [dept1, dept2],
         iteradorP prompt1 == 0,
         iteradorP prompt1config == 0,
         testF(configurarP prompt1config []),
         testF(configurarP prompt1config [dept1, dept2, falsoDept]),
         testF(configurarP (nuevoP (agregarDepartamentoF falsoDept (archivosR prompt1config))) [falsoDept]),
         testF(anunciosP prompt1),
         anunciosP prompt1config == [nombreA anun1, nombreA anun2],
         testF(showP prompt1),
         showP prompt1config == anunAsociadoDept1,
         testF(showP (avanzarP prompt1)),
         showP (avanzarP prompt1config) == anunAsociadoDept2,
         iteradorP (avanzarP prompt1config) == 1,
         showP (avanzarP (configurarP (nuevoP (sacarAnuncioF anunAsociadoDept2 (archivosR prompt1config))) [dept1, dept2] )) == anunAsociadoDept1,
         testF(duracionP prompt1),
         duracionP prompt1config == 30,
         duracionP (avanzarP (configurarP (nuevoP (sacarAnuncioF anunAsociadoDept2 (archivosR prompt1config))) [dept1, dept2] )) == 10,

         showP (configurarP prompt2 [dept2]) == anunAsociadoDept2,
         iteradorP (configurarP prompt2 [dept2]) == 0,
         duracionP (configurarP prompt2 [dept2]) == 20
         ]