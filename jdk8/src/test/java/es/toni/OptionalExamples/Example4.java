package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

//EXCEPCIONES CON orElseThrow ()
//VALOR DE RETORNO CON get()
public class Example4 {

    //En lugar de devolver un valor predeterminado cuando el valor ajustado no está presente, arroja una excepción
    @Test(expected = IllegalArgumentException.class)
    public void whenOrElseThrowWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(
                IllegalArgumentException::new);
    }

    //El enfoque final para recuperar el valor envuelto es el método get()
    @Test
    public void givenOptional_whenGetsValue_thenCorrect() {
        Optional<String> opt = Optional.of("toni");
        String name = opt.get();
        assertEquals("toni", name);
    }

    //Sin embargo, a diferencia de los tres enfoques anteriores,
    // get() solo puede devolver un valor si el objeto envuelto no es nulo;
    // de lo contrario, arroja una excepción de tal elemento


    @Test(expected = NoSuchElementException.class)
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
        Optional<String> opt = Optional.ofNullable(null);
        String name = opt.get();
    }
    //Este es el principal defecto del método get()
    //Idealmente, Opcional debería ayudarnos a evitar tales excepciones imprevistas
}
