package com.api.daos;

import com.api.entities.Discord;
import com.api.entities.Materia;
import com.api.entities.Turma;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class TurmaDao {

    private ConexaoMySQL conexao;

    public Turma adicionar(Turma turma) {
        conexao = new ConexaoMySQL();

        String sql = "INSERT INTO turma VALUES(null, ?, ?, ?);";
        try{
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, turma.getNome());
            st.setLong(2, turma.getMateria().getIdMateria());
            st.setLong(3, turma.getDiscord().getIdDiscord());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                turma = this.buscarPorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conexao.closeConnection();
        }
        return turma;
    }

    public void editar(Turma turma) {
        conexao = new ConexaoMySQL();

        String sql = "UPDATE turma SET nome=?, id_materia=?, id_discord=? WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setString(1, turma.getNome());
            st.setLong(2, turma.getMateria().getIdMateria());
            st.setLong(3, turma.getDiscord().getIdDiscord());
            st.setLong(4, turma.getIdTurma());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public void excluir(long idTurma) {
        conexao = new ConexaoMySQL();
        String sql = "DELETE FROM turma WHERE id_turma=?;";

        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
    }

    public Turma buscarPorId(long idTurma) {
        conexao = new ConexaoMySQL();
        Turma turma = null;
        String sql = "SELECT * FROM turma WHERE id_turma=?;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            st.setLong(1, idTurma);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                turma = new Turma();
                turma.setIdTurma(rs.getLong("id_turma"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.closeConnection();
        }
        return turma;
    }

    public List<Turma> buscar() {
        conexao = new ConexaoMySQL();
        List<Turma> listTurma = new ArrayList();
        String sql = "SELECT * FROM turma;";
        try {
            PreparedStatement st = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Turma turma = new Turma();
                turma.setIdTurma(rs.getLong("id_administrador"));
                turma.setNome(rs.getString("nome"));
                Materia materia = new MateriaDao().buscarPorId(rs.getLong("id_materia"));
                turma.setMateria(materia);
                Discord discord = new DiscordDao().buscarPorId(rs.getLong("id_discord"));
                turma.setDiscord(discord);
                listTurma.add(turma);
            }
        } catch(SQLException e) {
        }
        return listTurma;
    }
}
