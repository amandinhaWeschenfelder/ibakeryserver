/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 02/03/2022
 */
package view;

import factory.Conector;
import controller.TrataCliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class ClassePrincipal {
    public static void main(String[] args) {
        Connection con = Conector.getConnection();

        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor inicializado. Aguardando conexão...");
            ConectaServidor conectaServidor
                    = new ConectaServidor(servidor, con);
            System.out.println("Classe principal executada: "
                    + conectaServidor.toString());
        } catch (IOException e) {
            System.out.println("Erro na classe principal (main): "
                    + e.getMessage() + ".");
        }
    }
}

class ConectaServidor extends Thread {

    private final ServerSocket servidor;
    private int idUnico; // n° para identificar o cliente que está conectando
    private final Connection con;

    public ConectaServidor(ServerSocket servidor, Connection con) {
        this.servidor = servidor;
        this.con = con;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket cliente = this.servidor.accept();
                System.out.println("Um novo cliente conectou: " + cliente.toString() + ".");

                // Criando um objeto de streaming para receber e enviar os dados
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                idUnico++; // incrementanto a conexão deste cliente.
                System.out.println("Iniciando uma nova Thread para o Cliente: " + idUnico + ".");
                TrataCliente trataCliente = new TrataCliente(in, out, idUnico);
            }
        } catch (IOException e) {
            System.out.println("ERRO na classe Principal (run): " + e.getMessage() + ".");
        }
    }
}