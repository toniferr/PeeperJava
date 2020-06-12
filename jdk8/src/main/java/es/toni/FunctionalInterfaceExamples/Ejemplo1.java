package es.toni.FunctionalInterfaceExamples;

//Puede agregar fácilmente métodos predeterminados a la interfaz funcional siempre que
//solo haya una declaración de método abstracto
@FunctionalInterface
public interface Ejemplo1 {
    String method(String string);
    default void defaultMethod() {}
}

//Las interfaces funcionales pueden ser extendidas por otras interfaces funcionales
//si sus métodos abstractos tienen la misma firma

/**
@FunctionalInterface
public interface Ejemplo1Extended extends Ejemplo2, Ejemplo3 {}

@FunctionalInterface
public interface Ejemplo2 {
    String method();
    default void defaultEjemplo2() {}
}

@FunctionalInterface
public interface Ejemplo3 {
    String method();
    default void defaultEjemplo3() {}
}
*/

//Al igual que con las interfaces regulares, extender diferentes interfaces funcionales
//con el mismo método predeterminado puede ser problemático. Por ejemplo, suponga que
//las interfaces Ejemplo2 y Ejemplo3 tienen un método predeterminado defaultCommon().
//En este caso, obtendrá un error en tiempo de compilación

//Para solucionar esto, el método defaultCommon() debe anularse en la interfaz Ejemplo1 . Por supuesto,
//puede proporcionar una implementación personalizada de este método. Pero si desea utilizar una de las
//implementaciones de las interfaces principales (por ejemplo, desde la interfaz Ejemplo2), agregue la
//siguiente línea de código al cuerpo del método defaultCommon() :

/**
Ejemplo2.super.defaultCommon();
*/