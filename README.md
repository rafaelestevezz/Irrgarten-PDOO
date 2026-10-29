# Irrgarten — PDOO

En este repositorio voy a ir subiendo las prácticas de Programación y Diseño Orientado a Objetos. El proyecto consiste en desarrollar poco a poco un juego de laberinto llamado Irrgarten, aplicando los mismos conceptos en Java y en Ruby.

La idea es ir ampliando las dos versiones conforme avancemos en la asignatura y guardar aquí los cambios de cada práctica.

## Organización

- `java/`: versión en Java, con el proyecto de NetBeans y el código dentro de `src/irrgarten/`.
- `ruby/`: versión en Ruby, con las clases y el archivo de pruebas `test_p1.rb`.

En ambas versiones hay clases para representar los jugadores, los monstruos, las armas, los escudos, el laberinto y el estado de la partida. También se incluyen los dados y los tipos utilizados para las direcciones y los personajes.

## Estado actual

El proyecto está en desarrollo. Ya están las primeras clases y las pruebas de la práctica 1, además de parte de la lógica del juego y del laberinto. Algunos métodos están pendientes para las siguientes prácticas, por lo que todavía no se puede jugar una partida completa.

## Ejecutar las pruebas de Java

Podemos abrir la carpeta `java/` como proyecto en NetBeans y ejecutar `irrgarten.TestP1`. La configuración actual de NetBeans utiliza Java 26.

También podemos compilar y ejecutar las pruebas desde PowerShell, entrando en la carpeta `java/`:

```powershell
javac -encoding UTF-8 -d build/classes src/irrgarten/*.java
java -cp build/classes irrgarten.TestP1
```

Esta ejecución por consola se ha comprobado con JDK 25. Las pruebas muestran ejemplos de los tipos, las armas, los escudos, el estado del juego y los dados.

## Ejecutar las pruebas de Ruby

Con Ruby instalado, entramos en la carpeta `ruby/` y ejecutamos:

```powershell
ruby test_p1.rb
```

No hace falta instalar gemas adicionales para estas pruebas. Como algunas utilizan valores aleatorios, los resultados pueden cambiar en cada ejecución.

## Seguir actualizando el proyecto

Para las siguientes prácticas, trabajaremos sobre las carpetas `java/` y `ruby/` de este repositorio. Después podemos revisar y subir los cambios desde el editor o desde una terminal situada en la carpeta principal:

```powershell
git status
git add java ruby README.md .gitignore
git commit -m "Avanza en la siguiente práctica de Irrgarten"
git push
```

Los archivos generados al compilar y la configuración privada de NetBeans se excluyen mediante `.gitignore`.
