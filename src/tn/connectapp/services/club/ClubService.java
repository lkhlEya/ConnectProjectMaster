package tn.connectapp.services.club;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import tn.connectapp.utils.commun.MyConnection;
import tn.connectapp.entities.club.Category;
import tn.connectapp.entities.club.Club;
import tn.connectapp.utils.commun.InputControl;


public class ClubService {

    private Connection cnx;
    Date sysdate = new Date(System.currentTimeMillis());

    public ClubService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void createClub(Club c) throws SQLException {

        String req = "INSERT INTO CLUB (university, institue, description,category,"
                + "creation_date,email,phone_number,name,logo,creation_user,add_date)"
                + " values (?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, c.getUniversity());
            pre.setString(2, c.getInstitue());
            pre.setString(3, c.getDescription());
            pre.setString(4, c.getCategory());
            pre.setDate(5, c.getCreationDate());
            pre.setString(6, c.getEmail());
            pre.setLong(7, c.getPhoneNumber());
            pre.setString(8, c.getName());
            pre.setString(9, c.getLogo());
            pre.setLong(10, c.getCreationUser());
            pre.setDate(11, c.getAddDate());

            pre.executeUpdate();

            System.out.println("Values Inserted");

        } catch (Exception ex) {
            System.out.println("Problem " + ex.getMessage());
        }
    }

    public void updateClubStatus(Long idClub, String status) throws SQLException {

        try {
            String req = "UPDATE CLUB SET status = ? WHERE id_club = ? ;";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, status);
            pre.setLong(2, idClub);

            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem while Updating CLUB status");
        }
    }

    public void updateClub(Club updateEntity) throws SQLException {

        try {
            String req = "UPDATE CLUB SET university = ? ,institue = ? , "
                    + "description = ? , category = ? , creation_date = ? "
                    + " , email = ? , phone_number = ?, logo = ?  WHERE id_club = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, updateEntity.getUniversity());
            pre.setString(2, updateEntity.getInstitue());
            pre.setString(3, updateEntity.getDescription());
            pre.setString(4, updateEntity.getCategory());
            pre.setDate(5, updateEntity.getCreationDate());
            pre.setString(6, updateEntity.getEmail());
            pre.setLong(7, updateEntity.getPhoneNumber());
            pre.setString(8, updateEntity.getLogo());
            pre.setLong(9, updateEntity.getIdClub());

            pre.executeUpdate();

            System.out.println("Values Updated");

        } catch (SQLException e) {
            System.out.println("Problem while Updating CLUB" + e.getMessage());
        }
    }

    public boolean deleteClub(long idClub) throws SQLException {

        try {
            String req = "UPDATE CLUB SET status = 'HEXP' WHERE id_club = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idClub);

            if (pre.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem While Deleting from CLUB" + e.getMessage());
        }

        return false;
    }

    public List<Club> ReadListClub(String status) throws SQLException {
        List<Club> clubs = new ArrayList<>();

        try {
            String sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where c.creation_user = u.id_user) user_name FROM CLUB c ";
            if (!InputControl.isNull(status)) {
                sql = sql + "WHERE status = '" + status + "'";
            }
            sql += ";";
            System.out.println(sql);
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Club club = new Club();
                club.setIdClub(rs.getLong("id_club"));
                club.setName(rs.getString("name"));
                club.setUniversity(rs.getString("university"));
                club.setInstitue(rs.getString("institue"));
                club.setStatus(rs.getString("status"));
                club.setDescription(rs.getString("description"));
                club.setCategory(rs.getString("category"));
                club.setCreationDate(rs.getDate("creation_date"));
                club.setLogo(rs.getString("logo"));
                club.setPhoneNumber(rs.getLong("phone_number"));
                club.setEmail(rs.getString("email"));
                club.setCreationUserName(rs.getString("user_name"));

                clubs.add(club);

            }
        } catch (SQLException e) {
            System.out.println("Problem while Selecting from CLUB" + e.getMessage());

        }

        return clubs;
    }

    public Club ReadClub(Long idClub) throws SQLException {
        Club club = new Club();
        try {
            String sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where c.creation_user = u.id_user) user_name FROM CLUB c WHERE id_club = " + idClub + ";";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.first()) {
                club.setIdClub(rs.getLong("id_club"));
                club.setName(rs.getString("name"));
                club.setUniversity(rs.getString("university"));
                club.setInstitue(rs.getString("institue"));
                club.setStatus(rs.getString("status"));
                club.setDescription(rs.getString("description"));
                club.setCategory(rs.getString("category"));
                club.setCreationDate(rs.getDate("creation_date"));
                club.setLogo(rs.getString("logo"));
                club.setPhoneNumber(rs.getLong("phone_number"));
                club.setEmail(rs.getString("email"));
                club.setCreationUserName(rs.getString("user_name"));

            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from CLUB" + e.getMessage());

        }

        return club;
    }

    public List<Club> SearchClubByCriterian(Club criterianEntity, String status, Long nbminmem, Long nbmaxmem) throws SQLException {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                + " From user u where c.creation_user = u.id_user) user_name FROM CLUB c WHERE id_club = id_club ";
        if (null != criterianEntity) {
            sql = InputControl.isNull(criterianEntity.getInstitue()) ? sql + ""
                    : sql + " AND institue = '" + criterianEntity.getInstitue() + "'";
            sql = InputControl.isNull(criterianEntity.getUniversity()) ? sql + ""
                    : sql + " AND university = '" + criterianEntity.getUniversity() + "'";
            sql = InputControl.isNull(criterianEntity.getName()) ? sql + ""
                    : sql + " AND name = '" + criterianEntity.getName() + "'";
            sql = InputControl.isNull(criterianEntity.getCategory()) ? sql + ""
                    : sql + " AND category = '" + criterianEntity.getCategory() + "'";
            sql = criterianEntity.getCreationDate() == null ? sql + ""
                    : sql + " AND creation_date = '" + criterianEntity.getCreationDate() + "'";
            sql = InputControl.isNull(criterianEntity.getEmail()) ? sql + ""
                    : sql + " AND email = '" + criterianEntity.getEmail() + "'";
            sql = criterianEntity.getPhoneNumber() == null ? sql + ""
                    : sql + " AND phone_number = " + criterianEntity.getPhoneNumber();
            sql = InputControl.isNull(criterianEntity.getCreationUserName()) ? sql + ""
                    : sql + " AND creation_user in (select id_user from user where replace('"
                    + criterianEntity.getCreationUserName() + "',' ','') = replace(concat(FirstName,LastName),' ',''))";
            sql = criterianEntity.getPhoneNumber() == null ? sql + ""
                    : sql + " AND phone_number = " + criterianEntity.getPhoneNumber();
        }
        sql = nbminmem == null ? sql + ""
                : sql + " AND " + nbminmem + "<= (select count(*) from membership m where c.id_club = m.club_id"
                +  " AND m.status = 'EXPL' )";
        sql = nbmaxmem == null ? sql + ""
                : sql + " AND " + nbmaxmem + ">= (select count(*) from membership m where c.id_club = m.club_id"
                + " AND m.status = 'EXPL' )";

        sql = InputControl.isNull(status) ? sql + " AND status = 'EXPL';" : sql + " AND status = '" + status + "';";

        System.out.println(sql);

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Club club = new Club();
                club.setIdClub(rs.getLong("id_Club"));
                club.setName(rs.getString("name"));
                club.setUniversity(rs.getString("university"));
                club.setInstitue(rs.getString("institue"));
                club.setStatus(rs.getString("status"));
                club.setDescription(rs.getString("description"));
                club.setCreationDate(rs.getDate("creation_date"));
                club.setLogo(rs.getString("logo"));
                club.setPhoneNumber(rs.getLong("phone_number"));
                club.setEmail(rs.getString("email"));
                club.setCategory(rs.getString("category"));
                club.setCreationUserName(rs.getString("user_name"));

                clubs.add(club);
            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from CLUB" + e.getMessage());

        }

        return clubs;
    }

    public List<Club> SearchClubByKey(String Key, String status) throws SQLException {
        List<Club> clubs = new ArrayList<>();

        String sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                + " From user u where c.creation_user = u.id_user) user_name FROM CLUB c WHERE status = '" + status + "'";
        sql += "AND (institue like '%" + Key + "%'";
        sql += "OR university like '%" + Key + "%'";
        sql += "OR name like '%" + Key + "%'";
        sql += "OR category like '%" + Key + "%'";
        sql += "OR email like '%" + Key + "%'";
        sql += "OR phone_number like '%" + Key + "%'";
        sql += "OR description like '%" + Key + "%'";
        sql += "OR creation_date like '%" + Key + "%')";
        System.out.println(sql);
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Club club = new Club();
                club.setIdClub(rs.getLong("id_Club"));
                club.setName(rs.getString("name"));
                club.setUniversity(rs.getString("university"));
                club.setInstitue(rs.getString("institue"));
                club.setStatus(rs.getString("status"));
                club.setDescription(rs.getString("description"));
                club.setCreationDate(rs.getDate("creation_date"));
                club.setLogo(rs.getString("logo"));
                club.setPhoneNumber(rs.getLong("phone_number"));
                club.setEmail(rs.getString("email"));
                club.setCategory(rs.getString("category"));
                club.setCreationUserName(rs.getString("user_name"));

                clubs.add(club);
            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from CLUB" + e.getMessage());

        }

        return clubs;
    }
}
