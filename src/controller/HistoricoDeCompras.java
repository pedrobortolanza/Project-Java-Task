package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Compra;

public abstract class HistoricoDeCompras {

    private static final String ARQUIVO = "historicoCompras.txt";

    public static void salvarCompra(Compra compra) throws IOException {

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(compra.toString());

        }

    }
    
    public static String listarCompras() throws IOException, Exception {
        StringBuilder sb = new StringBuilder();
    
        try (FileReader fr = new FileReader(ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {
    
            String linha;
    
            while ((linha = br.readLine()) != null) {
                sb.append(linha);
                sb.append("\n");
            }
        }
    
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
    
        return sb.toString();
    }
    
}
