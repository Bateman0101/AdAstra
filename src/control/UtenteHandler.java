package control;

import dao.DataSource;
import dao.UtenteDao;
import entity.Utente;
import exceptions.NoUserException;

import java.sql.Connection;

public class UtenteHandler {

    public boolean isUserPresent(String id, String password) throws NoUserException{


        UtenteDao dao = new UtenteDao();

        DataSource dS = new DataSource();
        Connection c = dS.getConnection();

            if (dao.isUserPresent(id, password, c)){
                return true;
            }
            else{
                throw new NoUserException("user not present!");
            }

    }

    public void insertNewUser(String id, String nome, String cognome,
                              String email, String password, String tipo) throws NoUserException{

        if (password.length() < 6 || id.length() < 6)
            return;

        UtenteDao dao = new UtenteDao();

        DataSource dS = new DataSource();
        Connection c = dS.getConnection();


        if (!dao.isUserPresent(id, password, c)){

            dao.insertNewUser(id, nome, cognome, email, password, tipo, c);
        }
        else{

            throw new NoUserException("user already present");
        }

    }

    public String getTipo(String id, String password) throws NoUserException{

        UtenteDao dao = new UtenteDao();

        DataSource dS = new DataSource();
        Connection c = dS.getConnection();

        Utente u = dao.getUser(id, password, c);

        return u.getTipo();
    }
}
