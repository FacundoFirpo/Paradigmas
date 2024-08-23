import Tipos
import Anuncio
import FileSystem
import Prompter
-- test anuncio

anun1 = nuevoA "anuncio1" 10
anun2 = nuevoA "anuncio2" 20

anun1dept1 = agregarA "dept1" anun1
anun1depts12 = agregarA "dept2" anun1dept1

testA = [nombreA anun1 == "anuncio1",
         duracionA anun1 == 10,
         departamentosA anun1 == [],
         departamentosA anun1dept1 == ["dept1"],
         departamentosA anun1depts12 == ["dept2", "dept1"],
         departamentosA (sacarA "dept1" anun1depts12) == ["dept2"],
         departamentosA (sacarA "dept1" anun1dept1) == [],
         aplicaA ["dept1"] anun1dept1 == True,
         aplicaA ["dept2"] anun1dept1 == False,
         aplicaA ["dept1", "dept2"] anun1dept1 == True,
         aplicaA ["dept1", "dept2"] anun1depts12 == True,
         aplicaA ["dept1", "dept2"] anun1 == False]



-- test fileSystem

fs1 = nuevoF
fs2 = agregarAnuncioF anun1dept1 fs1
fs3 = agregarAnuncioF anun1depts12 fs2

fs1d1 = agregarDepartamentoF "dept1" fs1
fs1d12 = agregarDepartamentoF "dept2" fs1d1


testFS = [departamentosF fs1 == [],
          departamentosF fs1d1 == ["dept1"],
          anunciosF fs1 == [],
          anunciosF fs2 == [anun1dept1],
          anunciosF fs3 == [anun1depts12, anun1dept1],
          anunciosParaF ["dept1"] fs3 == [anun1depts12, anun1dept1],
          anunciosParaF ["dept2"] fs3 == [anun1depts12],
          anunciosParaF ["dept1", "dept2"] fs3 == [anun1depts12, anun1dept1],
          anunciosParaF ["dept3"] fs3 == [],
          sacarAnuncioF anun1depts12 fs3 == fs2,
          sacarDepartamentoF "dept2" fs1d12 == fs1d1]


-- test prompter

prompt1 = nuevoP fs3
prompt2 = configurarP prompt1 ["dept1"]
prompt3 = configurarP prompt2 ["dept2"]


