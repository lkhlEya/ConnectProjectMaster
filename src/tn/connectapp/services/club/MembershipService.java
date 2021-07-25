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
import tn.connectapp.entities.club.Membership;

public class MembershipService {

    private Connection cnx;

    public MembershipService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void createMembership(Membership m) throws SQLException {

        try {
            String req = "INSERT INTO Membership (post_id,club_id,status,membership_date,manager_id,member_id) values ('"
                    + m.getPostId() + "','" + m.getClubId() + "','" + m.getStatus() + "','" + m.getMembershipDate() + "','"
                    + m.getManagerId() + "','" + m.getMemberId() + "');";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);

            System.out.println("Values Inserted");

        } catch (SQLException e) {
            System.out.println("Problem while Inserting in Membership");
        }
    }

    public void updateMembership(Membership updateEntity) throws SQLException {

        try {

            String req = "UPDATE Membership SET post_id = ? ,club_id = ? , "
                    + "status = ? , membership_date = ? , manager_id = ? "
                    + " , member_id = ?  where membership_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, updateEntity.getPostId());
            pre.setLong(2, updateEntity.getClubId());
            pre.setString(3, updateEntity.getStatus());
            pre.setDate(4, updateEntity.getMembershipDate());
            pre.setLong(5, updateEntity.getManagerId());
            pre.setLong(6, updateEntity.getMemberId());
            pre.setLong(7, updateEntity.getMembershipId());

            pre.executeUpdate();

            System.out.println("Values Updated");

        } catch (SQLException e) {
            System.out.println("Problem while Updating membership");
        }
    }

    public boolean deleteMembership(Long idMembership) throws SQLException {

        try {
            String req = "UPDATE Membership SET status = 'HEXP' WHERE Membership_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idMembership);

            if (pre.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem While Deleting from Membership" + e.getMessage());
        }

        return false;
    }

    public List<Membership> ReadListMembership() throws SQLException {
        List<Membership> memberships = new ArrayList<>();

        try {
            String sql = "SELECT m.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where m.manager_id = u.id_user) manager_name,"
                    + "(select concat(u1.FirstName,' ',u1.LastName)"
                    + " From user u1 where m.member_id = u1.id_user) member_name,"
                    + "(select name From club c where c.id_club = m.club_id) club_name,"
                    + "(select post_name From post p where p.post_id = m.post_id) post_name"
                    + "  FROM Membership m; ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Membership membership = new Membership();
                membership.setPostId(rs.getLong("post_id"));
                membership.setClubId(rs.getLong("club_id"));
                membership.setStatus(rs.getString("status"));
                membership.setMemberId(rs.getLong("member_id"));
                membership.setMembershipDate(rs.getDate("membership_date"));
                membership.setManagerId(rs.getLong("manager_id"));
                membership.setMembershipId(rs.getLong("membership_id"));
                membership.setClubName(rs.getString("club_name"));
                membership.setManagerName(rs.getString("manager_name"));
                membership.setMemberName(rs.getString("member_name"));
                membership.setPostName(rs.getString("post_name"));

                memberships.add(membership);

            }
        } catch (SQLException e) {
            System.out.println("Problem while Selecting from Membership" + e.getMessage());
        }

        return memberships;
    }

    public Membership ReadMembership(Long idMembership) throws SQLException {
        Membership membership = new Membership();
        try {
            String sql = "SELECT * FROM Membership WHERE membership_id = " + idMembership + ";";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.first()) {
                membership.setPostId(rs.getLong("post_id"));
                membership.setClubId(rs.getLong("club_id"));
                membership.setStatus(rs.getString("status"));
                membership.setMemberId(rs.getLong("member_id"));
                membership.setMembershipDate(rs.getDate("membership_date"));
                membership.setManagerId(rs.getLong("manager_id"));
                membership.setMembershipId(rs.getLong("membership_id"));

            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from ClubHistory " + e.getMessage());

        }

        return membership;
    }

    public List<Membership> ReadMembersClub(Long idClub) throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Membership WHERE club_id = " + idClub + ";";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Membership membership = new Membership();
                membership.setPostId(rs.getLong("post_id"));
                membership.setClubId(rs.getLong("club_id"));
                membership.setStatus(rs.getString("status"));
                membership.setMemberId(rs.getLong("member_id"));
                membership.setMembershipDate(rs.getDate("membership_date"));
                membership.setManagerId(rs.getLong("manager_id"));
                membership.setMembershipId(rs.getLong("membership_id"));

                memberships.add(membership);

            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from ClubHistory");

        }

        return memberships;
    }

    public List<Membership> SearchMembershipByCriterian(Membership criterianEntity, String status) throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM membership WHERE 1";
        if (null == criterianEntity) {
            sql += "AND status = 'EXPL';";
        } else {
            sql = (criterianEntity.getClubId() == null) ? sql + ""
                    : sql + " AND club_id = '" + criterianEntity.getClubId() + "'";
            sql = InputControl.isNull(criterianEntity.getClubName()) ? sql + ""
                    : sql + " AND club_id in (select club_id from club where name = '"
                    + criterianEntity.getClubName() + "') ";
            sql = InputControl.isNull(criterianEntity.getPostName()) ? sql + ""
                    : sql + " AND post_id in ( select post_id from post where post_name = '"
                    + criterianEntity.getPostName() + "')";
            sql = (criterianEntity.getPostId() == null) ? sql + ""
                    : sql + " AND post_id = '" + criterianEntity.getPostId() + "'";
            sql = criterianEntity.getManagerId() == null ? sql + ""
                    : sql + " AND manager_id = '" + criterianEntity.getManagerId() + "'";
            sql = InputControl.isNull(criterianEntity.getManagerName()) ? sql + ""
                    : sql + " AND manager_id in (select id_user from user where replace('"
                    + criterianEntity.getManagerName() + "',' ','') = replace(concat(FirstName,LastName),' ',''))";
            sql = criterianEntity.getMembershipId() == null ? sql + ""
                    : sql + " AND membership_id = " + criterianEntity.getMembershipId();
            sql = criterianEntity.getMemberId() == null ? sql + ""
                    : sql + " AND member_id = " + criterianEntity.getMemberId();
            sql = criterianEntity.getMembershipDate() == null ? sql + ""
                    : sql + " AND membership_date = '" + criterianEntity.getMembershipDate() + "'";
            sql = InputControl.isNull(criterianEntity.getMemberName()) ? sql + ""
                    : sql + " AND member_id in (select id_user from user where replace('"
                    + criterianEntity.getMemberName() + "',' ','') = replace(concat(FirstName,LastName),' ',''))";
            sql = InputControl.isNull(status) ? sql + " AND status = 'EXPL';" : sql + " AND status = '" + status + "';";
            System.out.println(sql);

            try {
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(sql);
                while (rs.next()) {

                    Membership membership = new Membership();
                    membership.setPostId(rs.getLong("post_id"));
                    membership.setClubId(rs.getLong("club_id"));
                    membership.setStatus(rs.getString("status"));
                    membership.setMemberId(rs.getLong("member_id"));
                    membership.setMembershipDate(rs.getDate("membership_date"));
                    membership.setManagerId(rs.getLong("manager_id"));
                    membership.setMembershipId(rs.getLong("membership_id"));

                    memberships.add(membership);

                }

            } catch (SQLException e) {
                System.out.println("Problem while Selecting from ClubHistory");

            }

        }
        return memberships;

    }

}
