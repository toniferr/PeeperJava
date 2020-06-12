package es.toni.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ZonaHoraria {

    private LocalTime localTime;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    public ZonaHoraria(LocalTime localTime, LocalDate localDate, LocalDateTime localDateTime) {
        this.localTime = localTime;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }
    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
    public LocalDate getLocalDate() {
        return localDate;
    }
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ZonaHoraria { " +
                " localTime= " + localTime +
                ", localDate= " + localDate +
                ", localDateTime= " + localDateTime +
                " }";
    }
}
