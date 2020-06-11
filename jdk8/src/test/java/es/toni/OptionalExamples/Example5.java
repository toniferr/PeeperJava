package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;

import static es.toni.OptionalExamples.Modem.priceIsInRange1;
import static es.toni.OptionalExamples.Modem.priceIsInRange2;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//RETORNO CONDICIONAL CON filter()
public class Example5 {

    //Podemos ejecutar una prueba en línea en nuestro valor envuelto con el método de filtro
    //Toma un predicado como argumento y devuelve un objeto Opcional
    //Si el valor ajustado pasa la prueba por el predicado, entonces el Opcional se devuelve tal cual
    //Sin embargo, si el predicado devuelve falso , devolverá un Opcional vacío
    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        Integer year = 2020;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2020 = yearOptional.filter(y -> y == 2020).isPresent();
        assertTrue(is2020);
        boolean is2021 = yearOptional.filter(y -> y == 2021).isPresent();
        assertFalse(is2021);
    }

    //Ejemplo en clase es.toni.OptionalExamples.Modem
    //está en rango si su precio está entre 10 y 15 (inclusive)
    @Test
    public void whenFiltersWithoutOptional_thenCorrect() {
        assertTrue(priceIsInRange1(new Modem(10.0)));
        assertFalse(priceIsInRange1(new Modem(9.9)));
        assertFalse(priceIsInRange1(new Modem(null)));
        assertFalse(priceIsInRange1(new Modem(15.5)));
        assertFalse(priceIsInRange1(null));
    }

    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        assertTrue(priceIsInRange2(new Modem(10.0)));
        assertFalse(priceIsInRange2(new Modem(9.9)));
        assertFalse(priceIsInRange2(new Modem(null)));
        assertFalse(priceIsInRange2(new Modem(15.5)));
        assertFalse(priceIsInRange2(null));
    }

}
