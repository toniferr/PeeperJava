package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

//VALOR PREDETERMINADO CON orElse() y orElseGet()
public class Example3 {

    //El método orElse() devuelve el valor envuelto si está presente y su argumento de lo contrario
    @Test
    public void whenOrElseWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("toni");
        assertEquals("toni", name);
    }

    //En vez de como orElse() y tomar un valor para devolver si el valor Opcional no está presente,
    // toma una interfaz funcional del proveedor que se invoca y devuelve el valor de la invocación
    @Test
    public void whenOrElseGetWorks_thenCorrect() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "toni");
        assertEquals("toni", name);
    }

    //ejemplo usando orElse() y orElseGet()
    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        String text = null;

        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }

    //En el ejemplo anterior, ajustamos un texto nulo dentro de un objeto Opcional e
    // intentamos obtener el valor ajustado usando cada uno de los dos enfoques.
    // El efecto secundario es el siguiente:
    //Getting default value...
    //Getting default value...
    //Se llama al método getMyDefault () en cada caso. Sucede que cuando el valor envuelto no está presente,
    // tanto orElse () como orElseGet () funcionan exactamente de la misma manera

    //Ahora ejecutemos otra prueba donde el valor está presente e idealmente,
    // el valor predeterminado ni siquiera debería crearse

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }

    //ya no estamos ajustando un valor nulo y el resto del código sigue siendo el mismo. Ahora echemos un
    // vistazo al efecto secundario de ejecutar este código:
    //Using orElseGet:
    //Using orElse:
    //Getting default value...
    //Tenga en cuenta que cuando usa orElseGet () para recuperar el valor envuelto,
    // el método getMyDefault () ni siquiera se invoca ya que el valor contenido está presente.
    //Sin embargo, cuando se usa orElse () , independientemente de si el valor ajustado está presente o no,
    // se crea el objeto predeterminado. Entonces, en este caso, acabamos de crear un objeto redundante que nunca se usa
    //En este ejemplo simple, no hay un costo significativo para crear un objeto predeterminado,
    // ya que la JVM sabe cómo lidiar con eso. Sin embargo, cuando un método como getMyDefault ()
    // tiene que hacer una llamada al servicio web o incluso consultar una base de datos, el costo se vuelve muy obvio
}
