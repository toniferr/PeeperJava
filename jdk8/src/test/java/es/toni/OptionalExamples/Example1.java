package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//CREACION OBJECTOS OPCIONALES
public class Example1 {

    //utilizamos el método isPresent () para verificar si hay un valor dentro del objeto Opcional
    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    //podemos crear un objeto opcional con el método estático  de of()
    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        String name = "toni";
        Optional<String> opt = Optional.of(name);
        assertTrue(opt.isPresent());
    }

    //el argumento pasado al método of() no puede ser nulo
    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
        String name = null;
        Optional.of(name);
    }

    //en caso de que esperemos algunos valores nulos, podemos usar el método ofNullable ()
    @Test
    public void givenNonNull_whenCreatesNullable_thenCorrect() {
        String name = "toni";
        Optional<String> opt = Optional.ofNullable(name);
        assertTrue(opt.isPresent());
    }

    //si pasamos una referencia nula, no arroja una excepción, sino que devuelve un objeto opcional vacío
    @Test
    public void givenNull_whenCreatesNullable_thenCorrect() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        assertFalse(opt.isPresent());
    }

}
