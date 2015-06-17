/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.lasalle.av2.DAO;

import br.lasalle.av2.BO.Usuario;

import br.lasalle.av2.CONNECTION.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salgado
 */
public class UsuarioDAO {
    // conexao com banco de dados
    private Connection connection;
    
    public UsuarioDAO()
    {
        this.connection = new ConnectionFactory().getConnection();
    }
    
        public boolean autenticar(String login, String senha)
    {
        String sql = "select * from usuario where login='" + login + "' and senha=" + senha;
        boolean valorretorno = false;
        
        PreparedStatement query;
        try {
            query = connection.prepareStatement(sql);
                    ResultSet rs =  query.executeQuery();
        
        if(rs.next()) {
            valorretorno = true;
        }       
            else
        {
            valorretorno = false;
        }
            
         rs.close();
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valorretorno;
        }


    public boolean inserir(Usuario usuario)
    {
        String sql = "insert into usuario " + 
                " (nome, senha) values (?, ?)";
        
        try{
            //prepared statment para inserir
            PreparedStatement query = connection.prepareStatement(sql);
                
            //define os valores dos parametros
            query.setString(1, usuario.getLogin() ); // nome
        
            query.setInt(2, usuario.getSenha() ); // matricula
            //executa
            query.execute();
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
     public boolean altera(Usuario usuario) {
     String sql = "update usuario set senha=? where login=?";
     try {
            //prepared statment para inserir
            PreparedStatement query = connection.prepareStatement(sql);
                
            //define os valores dos parametros
            
                               
            query.setInt(1, usuario.getSenha() ); // endereco
            //executa
            query.execute();
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
 
     }
         
     public boolean remove(Usuario usuario) {
     try {
         PreparedStatement query = connection
                 .prepareStatement("delete" +  "from usuario where login=?");
         query.setString(1, usuario.getLogin());
         query.execute();
         query.close();
     } catch (SQLException ex) {
         Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
     }
     return true;
 }

    public ArrayList<Usuario> ler()
    {
        String sql = "select * from usuario ";
        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
        try{
            //prepared statment para inserir
            PreparedStatement query = connection.prepareStatement(sql);
            
            ResultSet rs =  query.executeQuery();
            
            while(rs.next())
            {
                //le os valores dos parametros
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getInt("senha"));
                
                listUsuario.add(usuario);
            }
    
            //fecha
            rs.close();
            query.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listUsuario;
    }

}


