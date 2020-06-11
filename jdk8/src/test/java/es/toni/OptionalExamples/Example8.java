package es.toni.OptionalExamples;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

//ENCADENAMIENTO DE S OPCIONALES EN JAVA 8
public class Example8 {

    //A veces, es posible que necesitemos obtener el primer objeto opcional no vacío de varios s opcionales.
    // En tales casos, sería muy conveniente utilizar un método como orElseOptional () . Desafortunadamente,
    // dicha operación no es directamente compatible con Java 8.
    private Optional<String> getEmpty() {
        return Optional.empty();
    }

    private Optional<String> getHello() {
        return Optional.of("hello");
    }

    private Optional<String> getBye() {
        return Optional.of("bye");
    }

    private Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    //La desventaja de este enfoque es que todos nuestros métodos get siempre se ejecutan,
    // independientemente de dónde aparezca un Opcional no vacío en el Stream
    //Si queremos evaluar perezosamente los métodos pasados ​​a Stream.of (),
    // necesitamos usar la referencia del método y la interfaz del Proveedor
    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated() {
        Optional<String> found =
                Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
                        .map(Supplier::get)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst();

        assertEquals(getHello(), found);
    }

    //En caso de que necesitemos utilizar métodos que toman argumentos, tenemos que recurrir a expresiones lambda
    @Test
    public void givenTwoOptionalsReturnedByOneArgMethod_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(
                () -> createOptional("empty"),
                () -> createOptional("hello")
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(createOptional("hello"), found);
    }

    //A menudo, queremos devolver un valor predeterminado en caso de que todos los s opcionales
    // encadenados estén vacíos. Podemos hacerlo simplemente agregando una llamada a orElse () o orElseGet ()
    // como en el siguiente ejemplo

    @Test
    public void givenTwoEmptyOptionals_whenChaining_thenDefaultIsReturned() {
        String found = Stream.<Supplier<Optional<String>>>of(
                () -> createOptional("empty"),
                () -> createOptional("empty")
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> "default");

        assertEquals("default", found);
    }
}
