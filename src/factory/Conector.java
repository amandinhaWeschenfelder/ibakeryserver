/**
NOME: Luana Kuntz e Amanda Weschenfelder
TURMA: INF4AT
DATA: 23/02/2022
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {

    private static Connection con;

    public static Connection getConnection() {
        String banco = "";
        try {
            String url = "jdbc:mysql://localhost:3306/"; // Caminho do banco e porta
            banco = "Padaria"; // nome da base
            String timeserver = "?serverTimezone=America/Sao_Paulo";
            String usuario = "root"; // username  
            String senha = "12345678"; // senha  

            con = DriverManager.getConnection(url + banco + timeserver, usuario, senha);
            return con;            

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados '" + banco + "'.\n"
                    + "Mensagem de erro: " + e.getErrorCode() + " - Mensagem: " + e.getMessage());
            return null;
        }
    }    
}
