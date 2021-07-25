package tn.connectapp.services.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.entities.club.Post;
import tn.connectapp.utils.commun.MyConnection;




public class PostService {

	private Connection cnx;

	public PostService() {
		cnx = MyConnection.getInstance().getCnx();
	}
	
	public void createPost(Post p) throws SQLException {

		try {
			String req = "INSERT INTO Post (post_name,	creation_user,	creation_date,	status,description) "
					+ "values ('" + p.getPostName() + "'," + p.getCreationUser()
					+ ",'" + p.getCreationDate() + "','" + p.getStatus() + "','" + p.getDescription() + "');";

			Statement ste = cnx.createStatement();
			ste.executeUpdate(req);

			System.out.println("Values Inserted");

		} catch (SQLException e) {
			System.out.println("Problem while Inserting in Post "+e.getMessage());
		}
	}
	
	public void updatePost(Post updateEntity) throws SQLException {

		try {

			String req = "UPDATE Post SET post_name = ? ,creation_user = ? , "
					+ "creation_date = ? , status = ? , description = ? "
					+ " where post_id = ? ;";

			PreparedStatement pre = cnx.prepareStatement(req);

			pre.setString(1, updateEntity.getPostName());
			pre.setLong(2, updateEntity.getCreationUser());
			pre.setDate(3, updateEntity.getCreationDate());
			pre.setString(4, updateEntity.getStatus());
			pre.setString(5, updateEntity.getDescription());
			pre.setLong(6, updateEntity.getPostId());

			pre.executeUpdate();

			System.out.println("Values Updated");

		} catch (SQLException e) {
			System.out.println("Problem while Updating post "+e.getMessage());
		}
	}
	
	public boolean deletePost(Long idPost) throws SQLException {

		try {
			String req = "UPDATE Post SET status = 'HEXP' WHERE id_Post = ? ;";

			PreparedStatement pre = cnx.prepareStatement(req);

			pre.setLong(1, idPost);

			if (pre.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Problem While Deleting from Post" + e.getMessage());
		}

		return false;
	}
	
	public List<Post> ReadListPost() throws SQLException {
		List<Post> posts = new ArrayList<>();

		try {
			String sql = "SELECT p.*,(select concat(u.FirstName,' ',u.LastName)"
                                + " From user u where p.creation_user = u.id_user) user_name FROM Post p;"
;
			Statement ste = cnx.createStatement();
			ResultSet rs = ste.executeQuery(sql);
			while (rs.next()) {

				Post post = new Post();
				post.setPostId(rs.getLong("post_id"));
				post.setPostName(rs.getString("post_name"));
				post.setStatus(rs.getString("status"));
				post.setCreationDate(rs.getDate("creation_date"));
				post.setCreationUser(rs.getLong("creation_user"));
				post.setDescription(rs.getString("description"));
                            	post.setCreationUserName(rs.getString("user_name"));


				posts.add(post);

			}
		} catch (SQLException e) {
			System.out.println("Problem while Selecting from Post" + e.getMessage());
		}

		return posts;
	}
	
	public Post ReadPost(Long idPost) throws SQLException {
		Post post = new Post();
		try {
			String sql = "SELECT p.*,(select concat(u.FirstName,' ',u.LastName)"
                                + " From user u where p.creation_user = u.id_user) user_name "
                                + "FROM Post p WHERE post_id = " + idPost + ";";
			Statement ste = cnx.createStatement();
			ResultSet rs = ste.executeQuery(sql);
			if (rs.first()) {
                            	post.setCreationUserName(rs.getString("user_name"));
				post.setPostId(rs.getLong("post_id"));
				post.setPostName(rs.getString("post_name"));
				post.setStatus(rs.getString("status"));
				post.setCreationDate(rs.getDate("creation_date"));
				post.setCreationUser(rs.getLong("creation_user"));
				post.setDescription(rs.getString("description"));

			}

		} catch (SQLException e) {
			System.out.println("Problem while Selecting from ClubHistory "+e.getMessage());

		}

		return post;
	}
}
