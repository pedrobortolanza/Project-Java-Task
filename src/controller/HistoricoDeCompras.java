package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Compra;

public abstract class HistoricoDeCompras {

    private static final String ARQUIVO = "historicoCompras.txt";

    public static void salvarCompra(Compra compra) throws IOException {

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(compra.toString());

        }

    }
    public static ArrayList<Compra> listarCompras() throws IOException, Exception {

        ArrayList<Compra> listaCompras = new ArrayList<>();

        try (FileReader fr = new FileReader(ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

                String linha;
                while ((linha = br.readLine()) != null) {
                    
                    Compra compra = Compra.fromString(linha);
                    listaCompras.add(compra);
                }
        } 

        if (listaCompras.isEmpty()) {
            throw new Exception("\nNão há produtos cadastrados");
        }

        return listaCompras;

    }
}
