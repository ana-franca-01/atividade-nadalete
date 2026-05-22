package org.example.repository;
import org.example.model.RainRegister;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class RainRepository {

        public List<RainRegister> carregarDados(String caminhoArquivo) {

            List<RainRegister> registros = new ArrayList<>();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String linha;

                br.readLine();

                while ((linha = br.readLine()) != null) {

                    String[] partes = linha.split(";");

                    if (partes.length >= 4) {

                        double precipitation =
                                Double.parseDouble(partes[1].trim().replace(",", "."));

                        LocalDate date =
                                LocalDate.parse(partes[2].trim(), formato);

                        registros.add(new RainRegister(date, precipitation));
                    }
                }

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }

            return registros;
        }
    }

