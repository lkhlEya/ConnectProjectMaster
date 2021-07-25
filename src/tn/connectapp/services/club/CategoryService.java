package tn.connectapp.services.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.entities.club.Category;
import tn.connectapp.utils.commun.InputControl;
import tn.connectapp.utils.commun.MyConnection;

public class CategoryService {

    private Connection cnx;

    public CategoryService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void createCategory(Category c) throws SQLException {

        try {
            String req = "INSERT INTO category (category_name,creation_date, creation_user,	"
                    + "status, description) values ('" + c.getCategoryName() + "','" + c.getCreationDate() + "','"
                    + c.getCreationUser() + "','" + c.getStatus() + "','" + c.getDescription() + "');";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);

            System.out.println("Values Inserted");

        } catch (SQLException e) {
            System.out.println("Problem while Inserting in category" + e.getMessage());
        }
    }

    public void updateCategory(Category updateEntity) throws SQLException {

        try {

            String req = "UPDATE category SET category_name = ? , "
                    + "creation_date = ? , creation_user = ? ,status = ?"
                    + "  , description = ? where  category_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, updateEntity.getCategoryName());
            pre.setDate(2, updateEntity.getCreationDate());
            pre.setLong(3, updateEntity.getCreationUser());
            pre.setString(4, updateEntity.getStatus());
            pre.setString(5, updateEntity.getDescription());
            pre.setLong(6, updateEntity.getCategoryId());

            pre.executeUpdate();

            System.out.println("Values Updated");

        } catch (SQLException e) {
            System.out.println("Problem while Updating category" + e.getMessage());
        }
    }

    public boolean deleteCategory(Long idCategory) throws SQLException {

        try {
            String req = "UPDATE Category SET status = 'HEXP' WHERE category_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idCategory);

            if (pre.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem While Deleting from Category" + e.getMessage());
        }

        return false;
    }

    public boolean validateCategory(Long idCategory) throws SQLException {

        try {
            String req = "UPDATE Category SET status = 'EXPL' WHERE category_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idCategory);

            if (pre.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem While Validating from Category" + e.getMessage());
        }

        return false;
    }

    public List<Category> ReadListCategory(String status) throws SQLException {
        List<Category> categories = new ArrayList<>();

        try {
            String sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where c.creation_user = u.id_user) user_name FROM Category c";
            if (!InputControl.isNull(status)) {
                sql = sql + " WHERE status = '" + status + "'";
            }
            sql += ";";
            System.out.println(sql);
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Category category = new Category();
                category.setCategoryId(rs.getLong("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCreationDate(rs.getDate("creation_date"));
                category.setCreationUser(rs.getLong("creation_user"));
                category.setStatus(rs.getString("status"));
                category.setDescription(rs.getString("description"));
                category.setCreationUserName(rs.getString("user_name"));

                categories.add(category);

            }
        } catch (SQLException e) {
            System.out.println("Problem while Selecting from Category" + e.getMessage());
        }

        return categories;
    }

    public Category ReadCategoryByReference(Long idCategory, String categoryName) throws SQLException {
        Category category = new Category();
        String sql = new String();
        try {

            if (idCategory != null) {
                sql = "SELECT c.*,(select concat(u.FirstName,' ',u.LastName)"
                        + " From user u where c.creation_user = u.id_user) user_name FROM Category c WHERE category_id = " + idCategory + ";";
            } else if (!categoryName.isEmpty()) {
                sql = "SELECT * FROM Category WHERE category_name = '" + categoryName + "';";
            }

            if (!sql.isEmpty()) {
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(sql);
                if (rs.first()) {
                    category.setCategoryId(rs.getLong("category_id"));
                    category.setCategoryName(rs.getString("category_name"));
                    category.setCreationDate(rs.getDate("creation_date"));
                    category.setCreationUser(rs.getLong("creation_user"));
                    category.setStatus(rs.getString("status"));
                    category.setDescription(rs.getString("description"));
                    category.setCreationUserName(rs.getString("user_name"));

                }
            }

        } catch (SQLException e) {
            System.out.println("Problem while Selecting from ClubHistory" + e.getMessage());

        }

        return category;
    }
}
