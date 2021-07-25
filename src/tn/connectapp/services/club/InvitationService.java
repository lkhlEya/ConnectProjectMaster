package tn.connectapp.services.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.utils.commun.MyConnection;
import tn.connectapp.entities.club.Category;
import tn.connectapp.utils.commun.InputControl;
import tn.connectapp.entities.club.Club;
import tn.connectapp.entities.club.Invitation;


public class InvitationService {

    private Connection cnx;

    public InvitationService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void createInvitation(Invitation i) throws SQLException {

        try {
            String req = "INSERT INTO invitation (status, creation_date, club_id, member_id, post_id,acceptance_flag) values ('"
                    + i.getStatus() + "','" + i.getCreationDate() + "','" + i.getClubId() + "','" + i.getMemberId() + "','"
                    + i.getPostId() + "','" + i.getAcceptanceFlag() + "');";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);

            System.out.println("Values Inserted");

        } catch (SQLException e) {
            System.out.println("Problem while Inserting in invitation" + e.getMessage());
        }
    }

    public void updateInvitation(Invitation updateEntity) throws SQLException {

        try {

            String req = "UPDATE Invitation SET status = ? ,creation_date = ? , "
                    + "club_id = ? , member_id = ? , post_id = ? "
                    + " , acceptance_flag = ?  where invitation_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, updateEntity.getStatus());
            pre.setDate(2, updateEntity.getCreationDate());
            pre.setLong(3, updateEntity.getClubId());
            pre.setLong(4, updateEntity.getMemberId());
            pre.setLong(5, updateEntity.getPostId());
            pre.setString(6, updateEntity.getAcceptanceFlag());
            pre.setLong(7, updateEntity.getInvitationId());

            pre.executeUpdate();

            System.out.println("Values Updated");

        } catch (SQLException e) {
            System.out.println("Problem while Updating Invitation " + e.getMessage());
        }
    }

    public boolean deleteInvitation(Long idInvitation) throws SQLException {

        try {
            String req = "UPDATE Invitation SET status = 'HEXP' WHERE invitation_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idInvitation);

            if (pre.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem While Deleting from Invitation" + e.getMessage());
        }

        return false;
    }

    public List<Invitation> ReadListInvitation() throws SQLException {
        List<Invitation> invitations = new ArrayList<>();

        try {
            String sql = "SELECT i.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where i.member_id = u.id_user) user_name ,"
                    + "(select name From club c where c.id_club = i.club_id) club_name"
                    + " FROM Invitation i; ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Invitation invitation = new Invitation();
                invitation.setStatus(rs.getString("status"));
                invitation.setCreationDate(rs.getDate("creation_date"));
                invitation.setClubId(rs.getLong("club_id"));
                invitation.setMemberId(rs.getLong("member_id"));
                invitation.setPostId(rs.getLong("post_id"));
                invitation.setAcceptanceFlag(rs.getString("acceptance_flag"));
                invitation.setInvitationId(rs.getLong("invitation_id"));
                invitation.setMemberName(rs.getString("user_name"));
                invitation.setClubName(rs.getString("club_name"));

                invitations.add(invitation);

            }
        } catch (SQLException e) {
            System.out.println("Problem while Selecting from Invitation" + e.getMessage());
        }

        return invitations;
    }

    public Invitation ReadInvitation(Long idInvitation) throws SQLException {
        Invitation invitation = new Invitation();
        try {
            String sql = "SELECT i.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where i.member_id = u.id_user) user_name ,"
                    + "(select name From club c where c.id_club = i.club_id) club_name"
                    + " FROM Invitation i WHERE invitation_id = " + idInvitation + ";";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.first()) {
                invitation.setStatus(rs.getString("status"));
                invitation.setCreationDate(rs.getDate("creation_date"));
                invitation.setClubId(rs.getLong("club_id"));
                invitation.setMemberId(rs.getLong("member_id"));
                invitation.setPostId(rs.getLong("post_id"));
                invitation.setAcceptanceFlag(rs.getString("acceptance_flag"));
                invitation.setInvitationId(rs.getLong("invitation_id"));
                invitation.setMemberName(rs.getString("user_name"));
                invitation.setClubName(rs.getString("club_name"));
            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from ClubHistory" + e.getMessage());

        }

        return invitation;
    }
}
