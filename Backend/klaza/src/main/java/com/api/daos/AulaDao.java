package com.api.daos;

import com.api.util.ConversaoDeData;
import com.api.entities.Aula;
import com.api.entities.Materia;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class AulaDao {

    ConexaoMySQL conexao;

    public Aula adicionar(Aula aula) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO aula VALUES(null, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, aula.getNome());
            st.setLong(2, aula.getMateria().getIdMateria());
            st.setString(3, aula.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(aula.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setString(5, aula.getLink());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                aula = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return aula;
    }

    public void editar(Aula aula) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE aula SET nome=?, materia=?, descricao=?, inicio=?, link=? WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, aula.getNome());
            st.setLong(2, aula.getMateria().getIdMateria());
            st.setString(3, aula.getDescricao());
            Timestamp timestampInicio = ConversaoDeData.localDateTimeToTimestamp(aula.getInicio());
            st.setTimestamp(4, timestampInicio);
            st.setString(5, aula.getLink());
            st.setLong(5, aula.getIdAula());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idAula) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM aula WHERE id_aula=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Aula buscarPorId(long idAula) {
        conexao = new ConexaoMySQL();
        Aula aula = null;
        String sql = "SELECT * FROM aula WHERE id_aula=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idAula);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                aula = new Aula();
                aula.setIdAula(rs.getLong("id_aula"));
                aula.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                aula.setMateria(materia);
                aula.setDescricao(rs.getString("aula"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                aula.setInicio(ldtInicio);
                aula.setLink(rs.getString("link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return aula;
    }

    public List<Aula> buscar() {
        conexao = new ConexaoMySQL();
        List<Aula> listAula = new ArrayList();
        String sql = "SELECT * FROM aula;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Aula aula = new Aula();
                aula.setIdAula(rs.getLong("id_aula"));
                aula.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                aula.setMateria(materia);
                aula.setDescricao(rs.getString("aula"));
                LocalDateTime ldtInicio = ConversaoDeData.timestampToLocalDateTime(rs.getTimestamp("inicio"));
                aula.setInicio(ldtInicio);
                aula.setLink(rs.getString("link"));
                listAula.add(aula);
            }
        } catch(SQLException e) {
        }
        return listAula;
    }
}
