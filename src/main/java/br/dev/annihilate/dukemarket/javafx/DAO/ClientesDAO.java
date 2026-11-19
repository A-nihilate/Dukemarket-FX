/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.annihilate.dukemarket.javafx.DAO;

import br.dev.annihilate.dukemarket.javafx.model.Clientes;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dia
 */
public class ClientesDAO {

    private static final String SQL_INSERT = "INSERT INTO produto (codBarras,"
            + "descricao, qtd, valorCompra, valorVenda)"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM produto";
    private static final String SQL_SELECT_ID = "SELECT * FROM produto "
            + "WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE produto SET codBarras = ?, "
            + "descricao = ?, qtd = ?, valorCompra = ?, valorVenda = ? "
            + "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";

    public void create(Clientes c) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, c.getCPF());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getCidade());
            stmt.setString(5, c.getuF());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);

        }

    }

    public List<Clientes> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes c = null;
        List<Clientes> listaProdutos = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            listaProdutos = new ArrayList<>();

            while (rs.next()) {
                c = new Clientes();
                c.setId(rs.getInt("id"));
                c.setCPF(rs.getString("CPF"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setuF(rs.getString("uF"));
                c.setDataCadastro(rs.getString("dataCadastro"));
                listaProdutos.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaProdutos;

    }

    public Clientes getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes c = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Clientes();
                c.setId(rs.getInt("id"));
                c.setCPF(rs.getString("CPF"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setuF(rs.getString("uF"));
                c.setDataCadastro(rs.getString("dataCadastro"));
            }
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return c;

    }

    public void update(Clientes c) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, c.getCPF());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getCidade());
            stmt.setString(5, c.getuF());
            stmt.setInt(6, c.getId());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }

    }

}
