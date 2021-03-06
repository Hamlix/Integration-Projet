package mediator;

import java.sql.*;

public class Extractor_SQL {
    private String url;
    private String user;
    private String password;
    Connection connexion = null;

    public Extractor_SQL(){
        this.url = "jdbc:mysql://localhost/projetidcm2";
        this.user = "root";
        this.password = "MDP2Paris";
    }
    public boolean connexion_BDD(){
        try {
            if (connexion != null && !connexion.isClosed() )
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
                connexion = DriverManager.getConnection( url, user, password );
                return true;
        } catch ( SQLException e ) {
            return false;
        }

    }

    public boolean deconnexion_BDD(){

        try {
            if (connexion != null) {
                connexion.close();
                return false;
            }else
                return true;
        }catch ( SQLException e){
            return true;
            }
    }

    public int[] requete_1(){
        /* Le nombre d'heures n'existe pas dans cette source
        Donc on ne peut pas faire de requête dans la BDD
         */
        return null;
    }

    public int requete_2(){
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery("SELECT count(*) nbre FROM etudiant WHERE provenance = 'fr';");
            return resultat.getInt("nbre");
        }catch (SQLException e){
            return 0;
        }
    }

    public int[] requete_3(){
        int res[] = new int[3];
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultatCM = statement.executeQuery("SELECT count(*) nbre FROM cours WHERE type = \"CM\";");
            ResultSet resultatTD = statement.executeQuery("SELECT count(*) nbre FROM cours WHERE type = \"TD\";");
            ResultSet resultatTP = statement.executeQuery("SELECT count(*) nbre FROM cours WHERE type = \"TP\";");
            res[0] = resultatCM.getInt("nbre");
            res[1] = resultatTD.getInt("nbre");
            res[2] = resultatTP.getInt("nbre");
            return res;
        }catch (SQLException e){
            return null;
        }
    }

}
