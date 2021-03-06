package sentify;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class ArtistaDAO extends Artista{
    
    private Connection con;
    
    
    public ArtistaDAO(Connection c) {
      con = c;
    }
    
    /**
     * Fuzioni per la ricerca e la manipolazione dell'utente.
     */
    public ArrayList<Artista> getArtistaeByNome(String Nome) {
      
      ResultSet result;
      Artista A;
      ArrayList<Artista> art = new ArrayList<Artista>();
      
      String query = "select * from artista where nome=?";
      
      try {
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,Nome);
        result = pst.executeQuery();
        
          while(result.next()) {
            A = new Artista();
            A.setNome(result.getString(1));
            A.setBiografia(result.getString(2));
            A.setNumFollowers(result.getInt(6));
            art.add(A);
          }
          return art;
        
      } catch (SQLException e) {e.printStackTrace(); return null;}

    }
    
    

    public Artista LoginDAO(String username, String password) {
      ResultSet result;
      Artista A;
      
      String query = "select * from artista where username=? and password=?";
      
      try {
        
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,username);
        pst.setString(2, password);
        result = pst.executeQuery();
        
          A = new Artista();
          while(result.next()) {
        	  A.setNome(result.getString(1));
              A.setNumFollowers(result.getInt("numFollower"));
              A.setBiografia(result.getString(2));
              A.setUsername(result.getString(8));
              A.setPassword(result.getString(9)); 
          }
          return A;
      
      } catch (SQLException e) {
        e.printStackTrace(); 
        
        return null;
        }
      

      
      

    }
  }