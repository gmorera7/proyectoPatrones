package edu.gpc.facade;

import edu.gpc.controller.util.Database;
import java.sql.*;
import javax.persistence.NoResultException;
  
public class UserFacade {      
     public static boolean login(String usuario, String clave) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select usuario, clave from usuario where usuario= ? and clave= ? and estado= 1");
            ps.setString(1, usuario);
            ps.setString(2, clave);
  
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                System.out.println(rs.getString("usuario"));
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }   

}
