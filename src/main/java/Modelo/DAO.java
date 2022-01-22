package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Result;

public class DAO {

	private String Driver = "com.mysql.cj.jdbc.Driver";

	private String url = "jdbc:mysql://127.0.0.1:3306/contatos?useTimezone=true&serverTimezone=UTC";

	private String user = "root";

	private String password = " ";

	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(Driver);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contatos", "root", "");
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	 //método para fazer a conexão com banco de dados
	public void conectaBd() {

		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
   //método para adicionar os dados no banco de dados
 	public void adicionarDados(Basebens contato) {

		String sql = "INSERT INTO adicionar(nome,fone,email)values(?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement patm = con.prepareStatement(sql);
			patm.setString(1, contato.getNome());
			patm.setString(2, contato.getFone());
			patm.setString(3, contato.getEmail());

			patm.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//método para lista toda tabela de adicionar
	public  ArrayList<Basebens>listaDados() {

		String sql = "Select * from adicionar order by nome";
		ArrayList<Basebens> contatos = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement patm = con.prepareStatement(sql);

			ResultSet rst = patm.executeQuery();

			while (rst.next()) {

				String nome = rst.getString(1);
				String fone = rst.getString(2);
				String email = rst.getString(3);
				contatos.add(new Basebens(nome, fone, email));

			}

			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//método para visualizar os dados pelo atributo fone;
	public void visualizarPorFone(Basebens contato) {
		
		String sql="SELECT * FROM adicionar WHERE fone=?";
		try {
			
			Connection con =conectar();
			PreparedStatement patm = con.prepareStatement(sql);
			patm.setString(1, contato.getFone());
			ResultSet rst =patm.executeQuery();
			
			 while(rst.next()) {
				 
				 contato.setNome(rst.getString(1));
				 contato.setFone(rst.getString(2));
				 contato.setEmail(rst.getString(3));
			 }
			rst.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	 //método para alterar os dados da tabela  pelo atributo fone;
    public void alterarContato(Basebens contato) {
		
		String sql="UPDATE adicionar SET nome=?, email=? WHERE fone=?";
		try {
			
			Connection con =conectar();
			PreparedStatement patm = con.prepareStatement(sql);
			patm.setString(1, contato.getNome());
			patm.setString(2, contato.getEmail());
			patm.setString(3, contato.getFone());
			patm.executeUpdate();
	
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
    //Método para remover os dados da tabela adicionar pelo atributo fone;
 public void removerDados(Basebens contato) {
		
		String sql="delete from adicionar WHERE fone=?";
		try {
			
			Connection con =conectar();
			PreparedStatement patm = con.prepareStatement(sql);
			patm.setString(1, contato.getFone());
			patm.executeUpdate();
	
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
