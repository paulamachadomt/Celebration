// package ads.db.projetofinal.projetofinal.dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.ArrayList;

// import ads.db.projetofinal.projetofinal.dao.jdbc.Conexao;
// import ads.db.projetofinal.projetofinal.model.Evento;
// import ads.db.projetofinal.projetofinal.model.Pessoa;

// public class EventoDAO {

//     public boolean cadastroEvento(Evento evento){
//         boolean resultado = false;
//         try {
//             Connection conexao = Conexao.getConexao();
//             String comandoSQL = "INSERT INTO evento VALUES (?, ?, ?, ?)";
//             PreparedStatement statement = conexao.prepareStatement(comandoSQL);
//             statement.setInt(1, evento.getSenha());
//             statement.setString(2, evento.getLocal());
//             statement.setDate(3, evento.getData());
//             statement.setString(4, evento.getDescricao());
//             if (statement.executeUpdate() >= 1) {
//                 resultado = true;
//             }
//             statement.close();
//             conexao.close();
//         } catch (Exception e) {
//             System.out.println("Erro ao cadastrar evento \n" + e);
//         }
//         return resultado;
//     }

//     public Evento selectCodEvento(Integer codigo){
//         Evento evento = null;
//         try {
//             Connection conexao = Conexao.getConexao();
//             String comandoSQL = "SELECT * FROM evento WHERE codigo = ?";
//             PreparedStatement statement = conexao.prepareStatement(comandoSQL);
//             statement.setInt(1, codigo);
//             ResultSet resultadoSelect = statement.executeQuery();
//             while (resultadoSelect.next()) {
//                 evento = new EventoDAO(resultadoSelect.getInt("codigo"), resultadoSelect.getString("nome"));
//             }
//             resultadoSelect.close();
//             statement.close();
//             conexao.close();
//         } catch (Exception e) {
//             System.out.println("Erro ao localizar pessoa por CPF \n" + e);
//         }
//         return evento;

//     }





    
// }