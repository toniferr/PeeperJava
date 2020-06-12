package es.toni;

import es.toni.FunctionalInterfaceExamples.Ejemplo1;
import es.toni.FunctionalInterfaceExamples.UsaEjemplo1;
import es.toni.LambdaStreamExamples.Alumno;
import es.toni.time.ZonaHoraria;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainJDK8 {

    public static void main(String[] args) {

        /***************************************JDK8***************************************/
        System.out.println("**********JDK8**********");
        /**Optional*/
        //Ver test.java.es.toni.OptionalExamples

        System.out.println("**********Functional Interface**********");
        Ejemplo1 ejemplo1 = parameter -> parameter + " from lambda";
        String result = UsaEjemplo1.add("Message ", ejemplo1);
        System.out.println(result);

        //Ejemplo1 no es más que una función que acepta un argumento y produce un resultado
        //Java 8 ya proporciona dicha interfaz en la función <T, R> del paquete java.util.function
        //Function<String, String> fn = parameter -> parameter + " from lambda";
        //String result = UsaEjemplo1.add("Message ", fn);
        //System.out.println(result);

        //Instanciar interfaces funcionales con expresiones lambda
        //Ejemplo1 ejemplo1 = parameter -> parameter + " from ejemplo1";
        //en vez de
        //Ejemplo1 ejemplo1PorClaseInterna = new Ejemplo1() {
        //    @Override
        //    public String method(String string) {
        //        return string + " from Ejemplo1";
        //    }
        //};

        System.out.println("**********Lambda expressions y Stream**********");
        List<Alumno> listaAlumnos = new ArrayList<>();

        listaAlumnos.add(new Alumno(1, "1717213183", "Javier Ignacio", "Molina Cano", "Java 8", 7, 28));
        listaAlumnos.add(new Alumno(2, "1717456218", "Lillian Eugenia", "Gómez Avarez", "Java 8", 10, 33));
        listaAlumnos.add(new Alumno(3, "1717328901", "Sixto Naranjoe", "Marín", "Java 8", 8.6, 15));
        listaAlumnos.add(new Alumno(4, "1717567128", "Gerardo Emilio", "Duque Gutiérrez", "Java 8", 10, 13));
        listaAlumnos.add(new Alumno(5, "1717902145", "Jhony Alberto", "Sáenz Hurtado", "Java 8", 9.5, 15));
        listaAlumnos.add(new Alumno(6, "1717678456", "Germán Antonio", "Lotero Upegui", "Java 8", 8, 34));
        listaAlumnos.add(new Alumno(7, "1102156732", "Oscar Darío", "Murillo González", "Java 8", 8, 32));
        listaAlumnos.add(new Alumno(8, "1103421907", "Augusto Osorno", "Palacio Martínez", "PHP", 9.5, 17));
        listaAlumnos.add(new Alumno(9, "1717297015", "César Oswaldo", "Alzate Agudelo", "Java 8", 8, 26));
        listaAlumnos.add(new Alumno(10, "1717912056", "Gloria Amparo", "González Castaño", "PHP", 10, 28));
        listaAlumnos.add(new Alumno(11, "1717912058", "Jorge León", "Ruiz Ruiz", "Python", 8, 22));
        listaAlumnos.add(new Alumno(12, "1717912985", "John Jairo", "Duque García", "Java Script", 9.4, 32));
        listaAlumnos.add(new Alumno(13, "1717913851", "Julio Cesar", "González Castaño", "C Sharp", 10, 22));
        listaAlumnos.add(new Alumno(14, "1717986531", "Gloria Amparo", "Rodas Monsalve", "Ruby", 7, 18));
        listaAlumnos.add(new Alumno(15, "1717975232", "Gabriel Jaime", "Jiménez Gómez", "Java Script", 10, 18));

        System.out.println("*** Lista de Alumnos ***");
        listaAlumnos.stream().forEach(a->System.out.println(a));
        listaAlumnos.stream().filter(a -> true).forEach(a -> System.out.println(a));

        System.out.println("\n*** Alumnos cuyo primer apellido empiezan con el caracter L u G ***");
        listaAlumnos.stream().
                filter(c -> c.getApellidos().charAt(0) == 'L' || c.getApellidos().charAt(0) == 'G')
                .forEach(c -> System.out.println(c));

        System.out.println("\n**** Número de Alumnos ***");
        System.out.println(listaAlumnos.stream().count());

        System.out.println("\n**** Alumnos con notas mayores a 9 y que el curso sea PHP ***");
        listaAlumnos.stream().filter(alumno -> alumno.getNota()>9 && alumno.getNombreCurso().equals("PHP"))
                .forEach(alumno -> System.out.println(alumno));

        System.out.println("\n**** Imprimir los 2 primeros Alumnos de la lista ***");
        listaAlumnos.stream().limit(2).forEach(a -> System.out.println(a));

        System.out.println("\n**** Imprimir el alumno con menor edad ***");
        System.out.println(listaAlumnos.stream().min((a1, a2) -> a1.getEdad() - a2.getEdad()));

        System.out.println("\n**** Imprimir el alumno con mayor edad ***");
        System.out.println(listaAlumnos.stream().max((a1, a2) -> a1.getEdad() - a2.getEdad()));

        System.out.println("\n**** Encontrar el primer Alumno***");
        System.out.println(listaAlumnos.stream().findFirst());

        System.out.println("\n**** Alumnos en los que los nombres de los cursos (lenguajes) que terminan en t ***");
        listaAlumnos.stream().filter(a -> a.getNombreCurso().endsWith("t")).forEach(System.out::println);

        System.out.println("\n**** Alumnos que tienen un curso en el que el nombre contienen la A ***");
        listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a")).forEach(System.out::println);

        System.out.println("\n**** Alumnos en que su tamaño de su nombre es mayor a 10 caracteres ***");
        listaAlumnos.stream().filter(a -> a.getNombres().length() > 10).forEach(System.out::println);

        System.out.println("\n**** Combinación de predicados ***");
        Predicate<Alumno> empiezaConJ = a -> a.getNombreCurso().startsWith("P");
        Predicate<Alumno> longitud = a -> a.getNombreCurso().length() <= 6;
        // Obtiene los alumnos en los cuales el nombre del curso empieza con el
        // caracter 'P' y la longitud sea <= a 6
        listaAlumnos.stream().filter(empiezaConJ.and(longitud)).forEach(System.out::println);

        //asignar la lista a una nueva lista
        List<Alumno> nuevaLista= listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a"))
                .collect(Collectors.toList());
        nuevaLista.forEach(System.out::println);


        System.out.println("**********Zona horaria**********");

        System.out.println("Método now");
        ZonaHoraria zonaHoraria = new ZonaHoraria(LocalTime.now(),LocalDate.now(),LocalDateTime.now());
        System.out.println(zonaHoraria);

        System.out.println("Método LocalDate");
        LocalDate.of(2020, 06, 12);
        LocalDate.parse("2020-06-12");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        DayOfWeek sunday = LocalDate.parse("2020-06-14").getDayOfWeek();
        int twelve = LocalDate.parse("2020-06-12").getDayOfMonth();
        boolean leapYear = LocalDate.now().isLeapYear();

        boolean notBefore = LocalDate.parse("2020-06-12")
                .isBefore(LocalDate.parse("2020-06-11"));
        boolean isAfter = LocalDate.parse("2020-06-12")
                .isAfter(LocalDate.parse("2020-06-11"));

        LocalDateTime beginningOfDay = LocalDate.parse("2020-06-12").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2020-06-12")
                .with(TemporalAdjusters.firstDayOfMonth());

        System.out.println("Método LocalTime");
        LocalTime thirteenThirty = LocalTime.parse("13:30");
        LocalTime thirteenThirty2 = LocalTime.of(13, 30);
        LocalTime fourteenThirty3 = LocalTime.parse("13:30").plus(1, ChronoUnit.HOURS);
        int thirteen = LocalTime.parse("13:30").getHour();
        boolean isbefore = LocalTime.parse("13:30").isBefore(LocalTime.parse("14:30"));
        //El tiempo máximo, mínimo y mediodía de un día se puede obtener mediante constantes en la clase LocalTime
        LocalTime maxTime = LocalTime.MAX;

        System.out.println("Método LocalDateTime");
        LocalDateTime.of(2020, Month.JUNE, 12, 13, 30);
        LocalDateTime.parse("2020-06-12T13:30:00");

        LocalDateTime.now().plusDays(1);
        LocalDateTime.now().minusHours(2);
        LocalDateTime.now().getMonth();

        System.out.println("Método ZonedDateTime");
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
        ZonedDateTime.parse("2020-06-12T13:30:00+01:00[Europe/Paris]");
        ZoneOffset offset = ZoneOffset.of("+02:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(LocalDateTime.now(), offset);

        System.out.println("Periodo");
        LocalDate initialDate = LocalDate.parse("2020-06-12");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        int five = Period.between(initialDate, finalDate).getDays();
        long cinco = ChronoUnit.DAYS.between(initialDate, finalDate);

        System.out.println("Duración");
        LocalTime initialTime = LocalTime.of(13, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        long thirty = Duration.between(initialTime, finalTime).getSeconds();
        long treinta = ChronoUnit.SECONDS.between(initialTime, finalTime);

        System.out.println("Fecha y Calendario");
        LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDateTime.ofInstant(Calendar.getInstance().getTime().toInstant(), ZoneId.systemDefault());
        LocalDateTime.ofEpochSecond(1465817690, 0, ZoneOffset.UTC);

        System.out.println("Formato fecha y hora");
        String localDateString = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        String localDateFormatter = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(localDateString);
        System.out.println(localDateFormatter);

        /***************************************JDK9***************************************/
    }
}