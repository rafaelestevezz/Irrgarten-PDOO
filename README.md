# Irrgarten — PDOO

Proyecto académico de **Programación y Diseño Orientado a Objetos (PDOO)** de la Universidad de Granada. Consiste en el desarrollo progresivo de **Irrgarten**, un juego de laberinto por turnos implementado en **Java y Ruby**.

Ambas versiones comparten la lógica del juego y permiten trabajar los conceptos de orientación a objetos en dos lenguajes con características diferentes.

## Descripción del juego

Los jugadores se desplazan por un laberinto con obstáculos, monstruos y una casilla de salida. El objetivo es llegar a la salida para finalizar la partida.

Durante el recorrido se gestionan movimientos válidos, combates por turnos, armas, escudos y recompensas. El sistema también contempla la pérdida de combates, la muerte de personajes y la posibilidad de resurrección. Parte de los atributos y acontecimientos de la partida se determinan mediante métodos aleatorios de la clase `Dice`.

## Diseño del proyecto

Las responsabilidades se distribuyen entre distintas clases:

- **`Game`**: coordina los turnos, los combates, las recompensas y la finalización de la partida.
- **`Labyrinth`**: representa el tablero y gestiona obstáculos, posiciones y movimientos.
- **`Player` y `Monster`**: modelan los personajes y su comportamiento en combate.
- **`Weapon` y `Shield`**: representan el equipamiento de los jugadores.
- **`Dice`**: centraliza los cálculos y decisiones aleatorias.
- **`GameState`**: reúne la información que se muestra durante la partida.
- **`TextUI`, `Controller` y `Main`**: proporcionan la interfaz por consola, la coordinación con el modelo y el punto de entrada.

## Estructura del repositorio

```text
Irrgarten-PDOO/
├── java/
│   ├── src/irrgarten/
│   │   ├── UI/TextUI.java
│   │   ├── controller/Controller.java
│   │   ├── main/Main.java
│   │   └── ...clases del juego
│   └── ...archivos del proyecto NetBeans
├── ruby/
│   ├── UI/textUI.rb
│   ├── controller/controller.rb
│   ├── main/main.rb
│   └── ...clases del juego
└── README.md
```

## Evolución

El repositorio conserva el desarrollo incremental de la asignatura. Las primeras prácticas establecieron las clases y operaciones básicas del modelo; hasta la **práctica 3** se ha incorporado la lógica de movimiento, combate, recompensas y resurrección, junto con una interfaz de texto que permite jugar partidas completas.

Las implementaciones de Java y Ruby evolucionan en paralelo, respetando las particularidades de cada lenguaje.

## Ejecución

**Java:** abrir `java/` como proyecto en NetBeans y ejecutar la clase principal `irrgarten.main.Main`. El movimiento se introduce con **W, A, S y D**, seguido de Enter.

**Ruby:** desde la carpeta `ruby/`, ejecutar:

```bash
ruby main/main.rb
```

La interfaz Ruby utiliza las **flechas del teclado**. La lectura directa de teclas se ha probado en una terminal de Ubuntu mediante WSL.

---

Proyecto académico en evolución, desarrollado como parte de la asignatura PDOO.
