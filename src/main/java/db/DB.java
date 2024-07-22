package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null; // atributo para armazenar a conexão com o banco de dados


    // Método para carregar as propriedades do arquivo db.properties
    private static Properties loadProperties() {//
        try (FileInputStream fs = new FileInputStream("db.properties")) // instancia um objeto FileInputStream para ler o arquivo db.properties
        {
            Properties props = new Properties();// instancia um objeto Properties para armazenar as propriedades do arquivo db.properties
            props.load(fs);// carrega as propriedades do arquivo db.properties
            return props; // retorna as propriedades do arquivo db.properties
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }

    }

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        if (conn == null) { // se a conexão ainda não foi instanciada
            try {
                Properties props = loadProperties(); // carrega as propriedades do arquivo db.properties
                String url = props.getProperty("dburl");// recupera a propriedade dburl do arquivo db.properties
                conn = DriverManager.getConnection(url, props);// estabelece a conexão com o banco de dados
                System.out.println("Conexão estabelecida com sucesso!!");

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection() {
        if (conn != null) {//verifica se a conexão foi instanciada antes de fechar
            try {
                conn.close();//fecha a conexão
            } catch (SQLException e) {//trata exceção de fechamento da conexão
                throw new DbException(e.getMessage());//lança uma exceção personalizada
            }

        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
