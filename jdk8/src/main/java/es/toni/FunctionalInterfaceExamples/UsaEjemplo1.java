package es.toni.FunctionalInterfaceExamples;

import java.util.function.Function;

public class UsaEjemplo1 {

    private String value = "Enclosing scope value";

    public static String add(String string, Ejemplo1 ejemplo1) {
        return ejemplo1.method(string);
    }

    /**
    public static String add(String string, Function<String, String> fn) {
        return fn.apply(string);
    }
    */

    public String scopeExperiment() {
        Ejemplo1 ejemplo1IC = new Ejemplo1() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return this.value;
            }
        };
        String resultIC = ejemplo1IC.method("");

        Ejemplo1 ejemplo1Lambda = parameter -> {
            String value = "Lambda value";
            return this.value;
        };
        String resultLambda = ejemplo1Lambda.method("");

        return "Results: resultIC = " + resultIC +
                ", resultLambda = " + resultLambda;
    }

    //Si ejecuta el método scopeExperiment() , obtendrá el siguiente resultado:
    //Resultados: resultIC = Inner class value, resultLambda = Enclosing scope value
    //
    //Como puede ver, llamando a this.value en IC, puede acceder a una variable local desde su instancia.
    //Pero en el caso de la lambda, la llamada this.value le da acceso al valor variable que se define en
    //la clase UsaEjemplo1, pero no al valor variable definido dentro del cuerpo de la lambda.
}
