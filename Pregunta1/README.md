## Pregunta 1

<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/a.png">
</h1>

# Fase 1

Pregunta:

Sigue la lógica comercial para un vuelo comercial y traduce eso escribiendo una prueba llamada
AirportTest. ¿Cuáles son los resultados de las pruebas con cobertura obtenidad?
¿Puedes indicar algunas conclusiones de lo anterior, necesitamos refactorizar?

Respuesta:

En las pruebas implementadas no hay cobertura del 100%, esto debido a que la clase `Flight` en los
métodos `addPassenger` y `addPassenger` tienen una excepción, donde si el atributo `flightType` no es 
del tipo de vuelo `economy` o `business` se muestra la excepción. Una posible refactorización para evitar esta excepción donde se 
puede crear un tipo de vuelo diferente a los que se pide es crear clases distintas de los tipos de vuelos
que se necesita, de esta forma se evita usar el atributo `flightType`.

<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f1.png">
</h1>


<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f1-1.png">
</h1>


# Fase 2
Preguntas:
- La refactorización se logrará manteniendo la clase Flight base y para cada tipo condicional,
agregando una clase separada para extender Flight. Debes cambiar addPassenger y
removePassenger a métodos abstractos y delegar su implementación a subclases. El campo
flightType ya no es significativo y se eliminará.
- Implementa una clase EconomyFlight que amplía Flight e implementa los métodos abstractos
heredados addPassenger y removePassenger.
- Implementa una clase BusinessFlight que amplía Flight e implementa los métodos abstractos
heredados addPassenger y removePassenger.

La refactorización y los cambios de la API se propagan a la implementacion de las pruebas.¿Cómo?.

¿Cuál es el código de cobertura ahora?. ¿Ayudó la refactorización a mejor la calidad de código?

Respuesta:

En la fase 1 la cobertura del codigo no alcanzaba el 100% debido al atributo `flightType`, pero ahora que se hace uso de la herencia 
de la clase abstracta `Flight`, el cual tiene como métodos abstractos `addPassenger` y `addPassenger` se implementan en las clases 
`EconomyFlight` y `BusinessFlight`, esto ayuda en las pruebas debido que nos centramos en hacer las pruebas en los pasajeros. La 
refactorización mejoro la calidad del código debido a que hora el cobertura para las pruebas implementadas es del 100% 

<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f2.png">
</h1>
<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f2-1.png">
</h1>


# Fase 3

Pregunta:

Utiliza la clase AirportTest refactorizada antes de pasar al trabajo para el vuelo premium en el código
desarrollado como mejora a tus resultados. Ver el codigo entregado en la evaluación.
Ejecuta las pruebas.

Respuesta:

Luego de hacer la refactorización de la clase `AirportTest` y el uso de anotacion `@Nested` 
se tiene lo siguiente para un vuelo economico:

```
public class AirportTest {

    @DisplayName("Vuelo Economico")
    @Nested
    public class EconomyFlightTests{
        EconomyFlight economyFlight ;
        Passenger normalPassenger;
        Passenger vipPassenger;

        @BeforeEach
        void setUp(){
            economyFlight = new EconomyFlight("1");
            normalPassenger = new Passenger("Luis", false);
            vipPassenger = new Passenger("Ana", true);
        }

        @Nested
        @DisplayName("Cuando hay un pasajero Regular")
        public class RegularPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero regular en el vuelo",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(normalPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Luis", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(normalPassenger)),
                        () -> assertEquals(0, economyFlight.getPassengers().size())
                        );
            }
        }

        @Nested
        @DisplayName("Cuando hay un pasajero VIP")
        public class VipPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo pero no eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero VIP en el vuelo",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(vipPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Ana", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(vipPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size())
                );
            }
        }
    }
.
.
.
}
```
De igual forma se tiene las pruebas para un vuelo de negocios en la clase `AirportTest`.
<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f3.png">
</h1>

# Fase 4

Pregunta:

Realiza la implementación de la clase PremiumFlight y su lógica. Debes crear PremiumFlight como una
subclase de Flight y sobreescribir los métodos addPassenger y removePassenger, pero actúan como
stubs: no hacen nada y simplemente devuelven false. El estilo TDD de trabajo implica crear primero las
pruebas y luego la lógica de negocios.
Ahora implementa las pruebas de acuerdo con la lógica comercial de vuelos premium:
- Se agrega un pasajero a un vuelo premium, solo si es pasajeros VIP
- Cualquier tipo de pasajero puede ser eliminado de un vuelo premium.

Después de escribir las pruebas, realiza la ejecución.
Comprueba que la cobertura del código es del 100%.

Respuesta:

Luego de implementar la clase `PremiumFlight` la prueba para esta clase es la siguiente:

```
public class AirportTest {
.
.
.
    @DisplayName("Vuelo Premium")
    @Nested
    public class PremiumFlightTests{
        PremiumFlight premiumFlight ;
        Passenger normalPassenger;
        Passenger vipPassenger;

        @BeforeEach
        void setUp(){
            premiumFlight = new PremiumFlight("3");
            normalPassenger = new Passenger("Luis", false);
            vipPassenger = new Passenger("Ana", true);
        }

        @Nested
        @DisplayName("Cuando hay un pasajero Regular")
        public class RegularPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero regular en el vuelo",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(false, premiumFlight.addPassenger(normalPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(normalPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("Cuando hay un pasajero VIP")
        public class VipPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero VIP en el vuelo",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(true, premiumFlight.addPassenger(vipPassenger)),
                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertEquals("Ana", premiumFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(true, premiumFlight.removePassenger(vipPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }
    }
}
```
<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f4.png">
</h1>
Luego de ejecutar las pruebas, la cobertura del código es del 100%.

# Fase 5

Pregunta:

Ocasionalmente, a propósito o por error, el mismo pasajero se ha agregado a un vuelo más de una vez.
Esto ha causado problemas con la gestión de asientos y estas situaciones deben evitarse. Tu como
desarrollado necesitas asegurarte de que cada vez que alguien intente agregar un pasajero, si el
pasajero se ha agregado previamente al vuelo, la solicitud debe ser rechazada.

Esta es una nueva lógica comercial y debes implementarla al estilo TDD.

Para garantizar la unicidad de los pasajeros en un vuelo, cambia la estructura de la lista de pasajeros a
un conjunto. Entonces, hace una refactorización de código que también se propagará a través de las
pruebas.

¿Cómo cambia la clase de vuelo en este contexto?.

Luego crea una nueva prueba para verificar que un pasajero solo se puede agregar una vez a un vuelo

¿Consigues una mejor cobertura de código?

Respuesta:

Dado que ahora se quiere garantizar la unicidad de los pasajeros en la clase abstracta `Flight` se cambia el atributo: 
```
private List<Passenger> passengers;
```
por 
```
private Set<Passenger> passengers;
```
Dado que las clases `PremiumFlight`, `EconomyFlight` y `BusunessFlight` extienden de clase abstracta `Flight` se hace una unica prueba 
donde se verifica que un pasajero solo se puede registrar una vez a un vuelo economico:

```
@DisplayName("Un pasajero solo puede agregarse una vez a un vuelo")
@Test
public void testPassengerOnlyCheckInOnceOnAFlight(){
    EconomyFlight economyFlight = new EconomyFlight("1");

    Passenger Luis = new Passenger("Luis", false);

    economyFlight.addPassenger(Luis);
    economyFlight.addPassenger(Luis);
    economyFlight.addPassenger(Luis);

    assertAll("Verifica que se agrego solo una vez el pasajero en el vuelo",
            () -> assertEquals("Luis", ((Passenger) economyFlight.getPassengers().toArray()[0]).getName()),
            () -> assertEquals(1, economyFlight.getPassengers().size())
    );

}
```
<h1 align="center">
  <img src="https://raw.githubusercontent.com/owamns/ExamenFinal-3S2/main/Pregunta1/files/f5.png">
</h1>
La cobertura del código es del 100%.
