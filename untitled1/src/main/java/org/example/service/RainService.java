package org.example.service;
import org.example.model.RainRegister;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Month;

public class RainService {

        private List<RainRegister> registros;

        public RainService(List<RainRegister> registros) {
            this.registros = registros;
        }

        public Map<Month, Double> totalPorMes(int ano) {
            Map<Month, Double> totais = new TreeMap<>();

            for (RainRegister r : registros) {
                if (r.getDate().getYear() == ano) {
                    Month mes = r.getDate().getMonth();
                    totais.put(mes, totais.getOrDefault(mes, 0.0) + r.getPrecipitation());
                }
            }
            return totais;
        }

        public RainRegister diaMaiorPrecipitacao(int ano) {
            return registros.stream()
                    .filter(r -> r.getDate().getYear() == ano)
                    .max(Comparator.comparingDouble(RainRegister::getPrecipitation))
                    .orElse(null);
        }

        public RainRegister diaMenorPrecipitacao(int ano) {
            return registros.stream()
                    .filter(r -> r.getDate().getYear() == ano)
                    .min(Comparator.comparingDouble(RainRegister::getPrecipitation))
                    .orElse(null);
        }


        public Month mesMaiorPrecipitacao(int ano) {
            Map<Month, Double> totais = totalPorMes(ano);
            return totais.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }

        public Month mesMenorPrecipitacao(int ano) {
            Map<Month, Double> totais = totalPorMes(ano);
            return totais.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);
        }

        public double mediaPrecipitacaoAno(int ano) {
            return registros.stream()
                    .filter(r -> r.getDate().getYear() == ano)
                    .mapToDouble(RainRegister::getPrecipitation)
                    .average()
                    .orElse(0.0);
        }


        public Map<Month, Double> mediaPorMes(int ano) {
            Map<Month, Double> medias = new TreeMap<>();
            Map<Month, Double> totais = totalPorMes(ano);


            Map<Month, Long> contagemDias = new TreeMap<>();
            for (RainRegister r : registros) {
                if (r.getDate().getYear() == ano) {
                    Month mes = r.getDate().getMonth();
                    contagemDias.put(mes, contagemDias.getOrDefault(mes, 0L) + 1);
                }
            }

            for (Month mes : totais.keySet()) {
                double media = totais.get(mes) / contagemDias.get(mes);
                medias.put(mes, media);
            }

            return medias;
        }

        public List<RainRegister> top10DiasChuvosos(int ano) {
            return registros.stream()
                    .filter(r -> r.getDate().getYear() == ano)
                    .sorted(Comparator.comparingDouble(RainRegister::getPrecipitation).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
        }
    }

