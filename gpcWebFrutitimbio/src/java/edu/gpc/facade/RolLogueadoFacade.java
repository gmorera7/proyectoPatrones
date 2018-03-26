package edu.gpc.facade;

import edu.gpc.controller.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RolLogueadoFacade {

    public static boolean usuarioLogueado(String usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean resultBoolean = false;
        try {
             
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "SELECT r.id FROM usuario u INNER JOIN rol r WHERE u.rolid = r.id AND u.usuario = ?");
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) // found
            { 
               
                String result = rs.getString("id");

                System.out.println("Result:::: " + result);
                System.out.println(rs.getString("id"));
                if (result.equals("1")) {
                    return resultBoolean= false;
                } else {
                    return resultBoolean = true;
                }                
            }return resultBoolean;
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
}
