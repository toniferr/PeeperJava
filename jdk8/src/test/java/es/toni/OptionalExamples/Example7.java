package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

//TRANSFORMANDO VALOR CON flatMap()
public class Example7 {

    //La diferencia es que el map transforma los valores solo cuando se desenvuelven,
    //mientras que flatMap toma un valor envuelto y lo desenvuelve antes de transformarlo.
    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Person person = new Person("toni", 33);
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper
                = personOptional.map(Person::getName);
        Optional<String> nameOptional
                = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("toni", name1);

        String name = personOptional
                .flatMap(Person::getName)
                .orElse("");
        assertEquals("toni", name);
    }

    // Aquí, estamos tratando de recuperar el atributo de nombre del objeto Persona para realizar una aserción
    // Observe cómo logramos esto con el método map() en la tercera declaración y luego observe cómo hacemos
    // lo mismo con el método flatMap() después.
    // La referencia del método Person::getName es similar a la llamada String::trim que teníamos en
    // la sección anterior para limpiar una contraseña.
    // La única diferencia es que getName() devuelve un Opcional en lugar de una Cadena como lo hizo
    // la operación trim(). Esto, junto con el hecho de que una transformación de mapa envuelve el
    // resultado en un objeto Opcional conduce a un Opcional anidado
    // Al usar el método map(), por lo tanto, necesitamos agregar una llamada adicional para recuperar
    // el valor antes de usar el valor transformado. De esta forma, se eliminará el contenedor opcional.
    // Esta operación se realiza implícitamente cuando se usa flatMap .
}
