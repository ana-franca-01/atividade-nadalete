package org.example;
import org.example.model.RainRegister;
import org.example.repository.RainRepository;
import org.example.service.RainService;

import java.io.File;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(new File(".").getAbsolutePath());


        RainRepository repository = new RainRepository();
        List<RainRegister> dados = repository.carregarDados("src/main/java/org/example/PluviometriaFuncemeNormalizada_2026-05-22T22_44_21.csv");

        RainService service = new RainService(dados);

        int ano = 2025;


        System.out.println("=== TOTAL POR MÊS ===");
        Map<Month, Double> totais = service.totalPorMes(ano);
        totais.forEach((mes, total) ->
                System.out.printf("%s: %.2f mm%n", mes, total));

        System.out.println("\n=== DIA MAIOR PRECIPITAÇÃO ===");
        System.out.println(service.diaMaiorPrecipitacao(ano));

        System.out.println("\n=== DIA MENOR PRECIPITAÇÃO ===");
        System.out.println(service.diaMenorPrecipitacao(ano));

        System.out.println("\n=== MÊS MAIOR PRECIPITAÇÃO ===");
        System.out.println(service.mesMaiorPrecipitacao(ano));

        System.out.println("\n=== MÊS MENOR PRECIPITAÇÃO ===");
        System.out.println(service.mesMenorPrecipitacao(ano));

        System.out.printf("%n=== MÉDIA DO ANO ===%n%.2f mm%n",
                service.mediaPrecipitacaoAno(ano));

        System.out.println("\n=== MÉDIA POR MÊS ===");
        service.mediaPorMes(ano).forEach((mes, media) ->
                System.out.printf("%s: %.2f mm%n", mes, media));

        System.out.println("\n=== TOP 10 DIAS MAIS CHUVOSOS ===");
        service.top10DiasChuvosos(ano).forEach(System.out::println);
    }
}