
package dao;

import factory.Conexao;
import gui.MenuSecretario;
import gui.Login;
import gui.MenuAlunos;
import gui.MenuPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDAO {
    
    private Connection connection;
    private int id;
    private String username;
    private String curso;
    private String senha;
    private String perfil;
     private String email;
    private String telefone;
    private String cep;
    private String bairro;
    private String cpf;
    private String data_nascimento;
    private String unidade_federal;
    private Login gui;
    
    
    
    public UsuarioDAO(){ 
        this.connection = new Conexao().getConnection();
        
    }
    
    
    public void inserir(Usuario usuario){
    
        String sql = "INSERT INTO usuarios(username, senha, perfil, email, curso, cep, telefone, bairro, unidade_federal, data_nascimento, pai, mae, cpf)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, usuario.getUsername());
           stmt.setString(2, usuario.getSenha());
           stmt.setString(3, usuario.getPerfil());
           stmt.setString(4, usuario.getEmail());
           stmt.setString(5,usuario.getCurso());
           stmt.setString(6, usuario.getCep());
           stmt.setString(7, usuario.getTelefone());
           stmt.setString(8, usuario.getBairro());
           stmt.setString(9, usuario.getUnidade_Federal());
           stmt.setString(10, usuario.getData_nascimento());
           stmt.setString(11, usuario.getPai());
           stmt.setString(12, usuario.getMae());
           stmt.setInt(13, usuario.getCpf());
           
          
           stmt.execute();
           stmt.close();
           
           
           
       }
       catch(SQLException u){
           throw  new RuntimeException(u);
       } 
          
    }
     
    public Usuario consultar(Usuario usuario){
        
        Usuario consulta = new Usuario();
        String sql = "select id, username, senha, curso, perfil "
                + "from usuarios where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,String.valueOf(usuario.getId()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                consulta.setUsername(rs.getString(2));
                consulta.setSenha(rs.getString(3));
                consulta.setCurso(rs.getString(4));
                consulta.setPerfil(rs.getString(5));
                
            
            }
            else{
                consulta = null;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return(consulta);
    }
    
    
}
