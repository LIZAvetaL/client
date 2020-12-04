package Server.Model;

import java.io.Serializable;
import java.util.List;


public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_user;
    private String login;
    private String password;

    public UsersEntity(){}
    public UsersEntity( String login, String password){
        this.password=password;
        this.login=login;
    }

    public int getId_user() {
        return id_user;
    }

    public void setIdUser(int idUser) {
        this.id_user = idUser;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
