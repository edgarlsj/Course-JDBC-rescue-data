package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {

        Connection conn= null;//cria uma variável do tipo Connection
        Statement st= null;//cria uma variável do tipo Statement
        ResultSet rs= null;//cria uma variável do tipo ResultSet

        try {
            conn = DB.getConnection();//estabelece a conexão com o banco de dados

            st = conn.createStatement();//cria um objeto do tipo Statement

            rs= st.executeQuery("select* from coursejdbc.department");//executa a query no banco de dados

            while (rs.next()){//enquanto houver resultados
                System.out.println(rs.getInt("Id") + " , " + rs.getString("Name"));//imprime os resultados da query
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);//fecha o statement
            DB.closeResultSet(rs);//fecha o resultSet
            DB.closeConnection();//fecha a conexão
        }


    }
}