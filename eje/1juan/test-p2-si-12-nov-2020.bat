rem comienza la prueba del control de la Practica 2 de SI curso 2020-21
rem Version 12 de noviembre de 2020
cd ..
call P2_si2020
pause
call P2_si2020 -h
pause
call P2_si2020 -f
pause
call P2_si2020 -f ./1juan/config-corregir-vatsyayana-12noviembre.txt


cd ./1juan/finales


rem Estas no tienen que se iguales 100%
Fc quijote-v-recuperado_juan.txt quijote-v-recuperado.txt
Fc quijote-p-recuperado_juan.txt quijote-p-recuperado.txt

cd ../..




pause 	OJO QUE ES EL FINAL
pause
