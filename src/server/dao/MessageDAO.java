package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.jdbc.ConnectionFactory;
import server.model.Mensagem;

public class MessageDAO {
	
	public static List<Mensagem> selectAll() {
		List<Mensagem> messageList = new ArrayList<>();
		String sql = "SELECT * FROM chatlog.t_mensagems";
        try (Connection conn = ConnectionFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
        	
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nickname");
                String textoMsg = rs.getString("mensagem");
                Date data = rs.getDate("data");

                messageList.add(new Mensagem(id, nome, textoMsg, data));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        System.out.println("DEBUG: selectAll() -> " + messageList);
        return messageList;
    }
	
	public static void save(Mensagem mensagem) {

        String sql = "INSERT INTO `chatlog`.`t_mensagems` (`nickname`, `mensagem`, `data`) "
        		+ "VALUES (?, ?, ?);";

        try (Connection conn = ConnectionFactory.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mensagem.getNome());
            ps.setString(2, mensagem.getTexto());
            ps.setDate(3, new java.sql.Date(mensagem.getData().getTime()));
            ps.executeUpdate();
            
            System.out.println("DEBUG: save() -> registro salvo com sucesso");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
