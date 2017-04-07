package storing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import storing.Interface.IMedico;
import storing.vo.Doador;
import storing.vo.Medico;

public class MedicoDAO implements IMedico {

	public MedicoDAO() {

	}

	Connection myConnection = null;
	private Medico medico;

	@Override
	public Medico cadastrarMedico(Medico imedico) {

		Medico tMedico = null;

		try {

			AcessoDAO acessoDAO = new AcessoDAO();

			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection.prepareStatement("INSERT INTO CADASTROMEDICO (CRMMEDICO,NOMEMEDICO,"
					+ "HOSPATUACAO, SENHAMEDICO, DIRMEDICO)" + "VALUES" + "(?,?,?,?,?)");

			pstm.setString(1, imedico.getCrmMedico());
			pstm.setString(2, imedico.getNomeMedico());
			pstm.setString(3, imedico.getHospAtuacao());
			pstm.setString(4, imedico.getSenhaMedico());
			pstm.setString(5, imedico.getDirMedico());
			pstm.executeUpdate();


			// Liberando os recursos JDBC
			pstm.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tMedico;
	}
	
	// metodo para excluir doador
		public boolean exclirMedico(String pCrm) {
			// criando um doador null
			Medico tmedico = null;
			// inicializando um doador
			medico = new Medico();
			try {
				// inicializando classe de acesso ao banco
				AcessoDAO acessoDAO = new AcessoDAO();
				// abrindo conexao
				myConnection = acessoDAO.abrirConexao();
				// passando o comando sql
				PreparedStatement pst = myConnection.prepareStatement("DELETE CADASTRODOADOR WHERE CRMMEDICO = ?");
				// passando os parametros
				pst.setString(1, pCrm);
				// executando a query
				ResultSet rs = pst.executeQuery();
				// encerrando o metodo
				pst.close();
				return true;

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return true;
			} finally {
				// fechando a conexao com o banco
				try {
					if (myConnection != null && !myConnection.isClosed())
						myConnection.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
