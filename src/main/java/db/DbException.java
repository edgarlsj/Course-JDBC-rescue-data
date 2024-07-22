package db;

public class DbException extends RuntimeException{
    // Classe para tratar exceções personalizadas do banco de dados (DB) que herda de RuntimeException

    public DbException (String msg){
        super(msg);
    }


}
