# Irrgarten — PDOO

Proyecto académico de **Programación y Diseño Orientado a Objetos (PDOO)** de la Universidad de Granada. Consiste en el desarrollo progresivo de **Irrgarten**, un juego de laberinto por turnos implementado en **Java y Ruby**.

Ambas versiones comparten la lógica del juego y permiten trabajar los conceptos de orientación a objetos en dos lenguajes con características diferentes.

## Descripción del juego

Los jugadores se desplazan por un laberinto con obstáculos, monstruos y una casilla de salida. El objetivo es llegar a la salida para finalizar la partida.

Durante el recorrido se gestionan movimientos válidos, combates por turnos, armas, escudos y recompensas. El sistema también contempla la pérdida de combates, la muerte de personajes y la posibilidad de resurrección.

Tras resucitar, los jugadores se convierten en FuzzyPlayer, un tipo de jugador cuyo movimiento, ataque y defensa incorporan un componente aleatorio.

## Diseño del proyecto

Las responsabilidades se distribuyen entre distintas clases:

- **`Game`**: coordina los turnos, los combates, las recompensas y la finalización de la partida.
- **`Labyrinth`**: representa el tablero y gestiona obstáculos, posiciones y movimientos.
- **`LabyrinthCharacter`**: generaliza los atributos y comportamientos comunes de los personajes.
- **`Player` y `Monster`**: modelan los personajes y su comportamiento en combate.
- **`FuzzyPlayer`**: especializa el comportamiento de los jugadores tras resucitar.
- **`CombatElement`**: reúne el comportamiento común de armas y escudos.
- **`Weapon` y `Shield`**: representan el equipamiento de los jugadores.
- **`CardDeck`, `WeaponCardDeck` y `ShieldCardDeck`**: gestionan las barajas de equipamiento en Java.
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

El repositorio conserva el desarrollo incremental de la asignatura.

| Práctica | Contenido | Estado |
|---|---|---|
| P1 | Conceptos básicos de programación orientada a objetos | Completada |
| P2 | Diseño estructural e implementación del modelo | Completada |
| P3 | Diseño dinámico, lógica del juego e interfaz de texto | Completada |
| P4 | Herencia, clases abstractas, polimorfismo y clases genéricas | Completada |
| P5 | Interfaz gráfica y patrón MVC | Pendiente |

En la práctica 4 se ha refactorizado el modelo mediante jerarquías de herencia, eliminando código duplicado e incorporando un nuevo tipo de jugador. También se han introducido clases genéricas para gestionar las barajas de armas y escudos en Java.

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