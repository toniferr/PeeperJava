package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

//TRANSFORMANDO VALOR CON map()
public class Example6 {

    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "toni";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional
                .map(String::length)
                .orElse(0);
        assertEquals(4, len);
    }

    //ajustamos una lista de cadenas dentro de un objeto Opcional y usamos su método de map para
    //realizar una acción en la lista contenida. La acción que realizamos es recuperar el tamaño de la lista.
    //El método de mapa devuelve el resultado del cálculo envuelto dentro de Opcional.
    //Luego tenemos que llamar a un método apropiado en el Opcional devuelto para recuperar su valor.

    //Observe que el método de filtro simplemente realiza una comprobación del valor y devuelve un valor booleano.
    //Por otro lado, el método de mapa toma el valor existente, realiza un cálculo utilizando este valor
    //y devuelve el resultado del cálculo envuelto en un objeto Opcional



    //Supongamos que queremos verificar la exactitud de una contraseña ingresada por un usuario
    //podemos limpiar la contraseña usando una transformación de mapa (String::trim)
    // y verificar su corrección usando un filtro
    @Test
    public void givenOptional_whenMapWorksWithFilter_thenCorrect() {

        String password = " password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(
                pass -> pass.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword = passOpt
                .map(String::trim)
                .filter(pass -> pass.equals("password"))
                .isPresent();
        assertTrue(correctPassword);
    }
}
