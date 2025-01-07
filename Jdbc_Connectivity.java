package pract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_Connectivity {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fun", "root", "root");
			System.out.println("done");
			Scanner s=new Scanner(System.in);
			System.out.println("option 1:insertion");
			System.out.println("option 2:deletion");
			System.out.println("option 3: updation");
			System.out.println("option 4:retrieve all record");
			String k=s.next();
			switch (k) {
			case "1": {
				String qry="insert into players values(?,?,?)";
				PreparedStatement ps=con.prepareStatement(qry);
				System.out.println("enter no of records inserted");
				int n=s.nextInt();
				for(int i=1;i<=n;i++) {
					System.out.println("enter the player id");
					int id=s.nextInt();
					ps.setInt(1,id);
					System.out.println("enter the player name");
					String name=s.next();
					ps.setString(2, name);
					System.out.println("enter the player score");
					int score=s.nextInt();
					ps.setInt(3, score);
					ps.executeUpdate();
				}
				System.out.println("records inserted sucessfull");
				break;
				
			}
			case "2":{
				String qry="delete from players where player_id=?";
				PreparedStatement ps=con.prepareStatement(qry);
				System.out.println("enter the id to be delted");
				int id=s.nextInt();
				ps.setInt(1, id);
				ps.executeUpdate();
				System.out.println("records deleted sucessfull");
				break;
				
			}
			case "3":{
				String qry="update players set player_id=?,player_name=? where player_score=?";
				PreparedStatement ps=con.prepareStatement(qry);
				int ni=s.nextInt();
				for(int i=1;i<=ni;i++) {
					System.out.println("enter the id");
					int id=s.nextInt();
					ps.setInt(1, id);
					System.out.println("enter the name");
					String n=s.next();
					ps.setString(2, n);
					System.out.println("enter the score");
					int score=s.nextInt();
					ps.setInt(3, score);
				}
				System.out.println("records updated");
				break;
				
			}
			case "4":{
				String qry="select * from players";
				PreparedStatement ps=con.prepareStatement(qry);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + k);
			}


		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}			
}
