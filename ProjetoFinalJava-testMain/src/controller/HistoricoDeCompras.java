package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Compra;

public abstract class HistoricoDeCompras {

    private static List<Compra> listaCompras = new ArrayList<>();

    private static final String ARQUIVO = "historicoCompras.txt";

    public static void salvarCompra(Compra compra) throws IOException {

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
                BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(compra.getValorTotal() + "," +
                    compra.getCarrinho() + "," +
                    compra.getDataHoraCompra() + "\n");

        }

    }

    public static List<Compra> listarCompras() throws IOException, Exception {

        List<Compra> compras = new ArrayList<>();

        try (FileReader fr = new FileReader(ARQUIVO);
                BufferedReader br = new BufferedReader(fr)) {

                    String linha;
            while ((linha = br.readLine()) != null) {

                for (Compra compra : listaCompras) {
                    if (compra instanceof Compra) {
                        compras.add(compra);
                    }

                }
            }
            if (compras.isEmpty()) {
                throw new Exception("\nNão há compras cadastradas");
            }
        }
        return compras;
    }

    protected abstract void salvarCompra();
}