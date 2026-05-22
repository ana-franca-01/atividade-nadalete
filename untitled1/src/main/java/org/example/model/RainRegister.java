package org.example.model;
import java.time.LocalDate;

public class RainRegister {

        private LocalDate date;     
        private double precipitation;

        public RainRegister(LocalDate date, double precipitation) {
            this.date = date;
            this.precipitation = precipitation;
        }

        public LocalDate getDate() {
            return date;
        }

        public double getPrecipitation() {
            return precipitation;
        }

        @Override
        public String toString() {
            return date + " -> " + precipitation + "mm";
        }
    }

