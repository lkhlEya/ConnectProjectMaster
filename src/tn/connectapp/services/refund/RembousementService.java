/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.refund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.entities.refund.Remboursement;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author i_dkh
 */
public class RembousementService {

    private final Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public RembousementService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterRemboursement(Remboursement r) throws SQLException {

        String sql = "INSERT INTO remboursement (date, nom, club, email, ville, telephone, demande, etatdemande, id_user) VALUES "
                + "(current_date,'" + r.getNom() + "','" + r.getClub() + "','" + r.getEmail() + "','" + r.getVille() + "',"
                + r.getTelephone() + ",'" + r.getDemande() + "','" + r.getEtatdemande() + "' ," + r.getIdUser() + " )        ";

        Statement ste = cnx.createStatement();

        ste.executeUpdate(sql);
        System.out.println("INSERTION OK!!");
    }

 public List<Remboursement> findAll() throws SQLException {
        List<Remboursement> remboursements = new ArrayList<>();

        String sql = "SELECT * FROM remboursement";

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);

        while (rs.next()) {

            Remboursement r = new Remboursement();

            r.setId(rs.getInt("Id"));
            r.setNom(rs.getString("nom"));
            r.setClub(rs.getString("club"));
            r.setVille(rs.getString("ville"));
            r.setEmail(rs.getString("email"));
            r.setTelephone(rs.getInt("telephone"));
            r.setEtatdemande(rs.getString("etatdemande"));
            r.setDemande(rs.getString("demande"));
            r.setDate(rs.getDate("date"));

            remboursements.add(r);
        }
        return remboursements;
    }

   public List<Remboursement> findNotEnAttente() throws SQLException {
        List<Remboursement> remboursements = new ArrayList<>();

        String sql = "SELECT * FROM remboursement where etatdemande !='En attente'";

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);

        while (rs.next()) {

            Remboursement r = new Remboursement();

            r.setId(rs.getInt("Id"));
            r.setNom(rs.getString("nom"));
            r.setClub(rs.getString("club"));
            r.setVille(rs.getString("ville"));
            r.setEmail(rs.getString("email"));
            r.setTelephone(rs.getInt("telephone"));
            r.setEtatdemande(rs.getString("etatdemande"));
            r.setDemande(rs.getString("demande"));
            r.setDate(rs.getDate("date"));

            remboursements.add(r);
        }
        return remboursements;
    }
 
  public List<Remboursement> findEnAttente() throws SQLException {
        List<Remboursement> remboursements = new ArrayList<>();

        String sql = "SELECT * FROM remboursement where etatdemande='En attente'";

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);

        while (rs.next()) {

            Remboursement r = new Remboursement();

            r.setId(rs.getInt("Id"));
            r.setNom(rs.getString("nom"));
            r.setClub(rs.getString("club"));
            r.setVille(rs.getString("ville"));
            r.setEmail(rs.getString("email"));
            r.setTelephone(rs.getInt("telephone"));
            r.setEtatdemande(rs.getString("etatdemande"));
            r.setDemande(rs.getString("demande"));
            r.setDate(rs.getDate("date"));

            remboursements.add(r);
        }
        return remboursements;
    }
 
    public List<Remboursement> findByUser(Integer id) throws SQLException {
        List<Remboursement> remboursements = new ArrayList<>();

        String sql = "SELECT * FROM remboursement where id_user = " + id;

        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);

        while (rs.next()) {

            Remboursement r = new Remboursement();

            r.setId(rs.getInt("Id"));
            r.setNom(rs.getString("nom"));
            r.setClub(rs.getString("club"));
            r.setVille(rs.getString("ville"));
            r.setEmail(rs.getString("email"));
            r.setTelephone(rs.getInt("telephone"));
            r.setEtatdemande(rs.getString("etatdemande"));
            r.setDemande(rs.getString("demande"));
            r.setDate(rs.getDate("date"));

            remboursements.add(r);
        }
        return remboursements;
    }

    public void delete(Integer id) throws SQLException {

        String sql = "delete from remboursement where id=" + id;
        Statement ste = cnx.createStatement();
        ste.executeUpdate(sql);
    }

    public void update(Remboursement remboursement) throws SQLException {
        String sql = "update remboursement set nom=?, club=?, ville=?, email=?, telephone=?, demande=?, etatdemande=? where id=?";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setString(1, remboursement.getNom());
        statement.setString(2, remboursement.getClub());
        statement.setString(3, remboursement.getVille());
        statement.setString(4, remboursement.getEmail());
        statement.setInt(5, remboursement.getTelephone());
        statement.setString(6, remboursement.getDemande());
        statement.setString(7, remboursement.getEtatdemande());
        statement.setInt(8, remboursement.getId());
        statement.executeUpdate();
    }
}
