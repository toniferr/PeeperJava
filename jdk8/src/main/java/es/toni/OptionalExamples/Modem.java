package es.toni.OptionalExamples;

import java.util.Optional;

//Ejemplo
public class Modem {
    private Double price;

    public Modem(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static boolean priceIsInRange1(Modem modem) {
        boolean isInRange = false;

        if (modem != null && modem.getPrice() != null
                && (modem.getPrice() >= 10
                && modem.getPrice() <= 15)) {

            isInRange = true;
        }
        return isInRange;
    }

    public static boolean priceIsInRange2(Modem modem2) {
        return Optional.ofNullable(modem2)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }
    //La llamada al map simplemente se usa para transformar un valor en otro valor.
    //Tenga en cuenta que esta operación no modifica el valor original.
    //El enfoque anterior promete verificar el rango de precios, pero tiene que hacer más que eso
    //para defenderse de su fragilidad inherente. Por lo tanto, podemos usar el método de filtro
    //para reemplazar sentencias if innecesarias y rechazar valores no deseados
}

