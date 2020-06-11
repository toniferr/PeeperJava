package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//COMPROBACION DE PRESENCIA DE VALOR: isPresent () y isEmpty ()
//ACCION CONDICIONAL CON ifPresent ()
public class Example2 {

    //El método isPresent() devuelve verdadero  si el valor envuelto no es nulo
    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("toni");
        assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }

    // a partir de Java 11, podemos hacer lo contrario con el método isEmpty
    // no se reconoce isEmpty (usamos jdk8)
    @Test
    public void givenAnEmptyOptional_thenIsEmptyBehavesAsExpected() {
        Optional<String> opt = Optional.of("Baeldung");
        assertFalse(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    //Opcional nos hace tratar con valores anulables explícitamente
    //como una forma de aplicar buenas prácticas de programación
    //En el siguiente ejemplos se usa una línea para envolver el objeto en un objeto Opcional
    // y la siguiente para realizar la validación implícita y ejecutar el código
    @Test
    public void givenOptional_whenIfPresentWorks_thenCorrect() {
        Optional<String> opt = Optional.of("toni");
        opt.ifPresent(name -> System.out.println(name.length()));
    }
}
