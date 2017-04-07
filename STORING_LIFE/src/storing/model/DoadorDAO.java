/**
 * 
 */
package storing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import storing.Interface.IDoador;
import storing.vo.Doador;
import storing.vo.Hospital;
import storing.vo.MarcarDoacao;

/**
 * @author Daniel Candido
 *
 */
public class DoadorDAO implements IDoador {

	public DoadorDAO() {

	}

	Connection myConnection = null;
	private Doador doador;

	@Override
	public Doador cadatrarDoador(Doador pdoador) {
		Doador tdoador = null;

		try {

			AcessoDAO acessoDAO = new AcessoDAO();

			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection.prepareStatement("INSERT INTO CADASTRODOADOR(CPFDOADOR,NOMEDOADOR,"
					+ "DTNASCIMENTO, TIPOSANGUE, EMAILDOADOR, SEXO , TELDOADOR, CELDOADOR, ENDERECODOADOR,"
					+ " ESTADODOADOR, CIDADEDOADOR, BAIRRO, CEP, DATACADASTRO," + "SENHADOADOR)" + "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstm.setString(1, pdoador.getCpf());
			pstm.setString(2, pdoador.getNome());
			pstm.setDate(3, new java.sql.Date(pdoador.getDtNascimento().getTime()));
			pstm.setString(4, pdoador.getTipoSangue());
			pstm.setString(5, pdoador.getEmail());
			pstm.setString(6, pdoador.getSexo());
			pstm.setString(7, pdoador.getTelDoador());
			pstm.setString(8, pdoador.getCelDoador());
			pstm.setString(9, pdoador.getEnderecoDoador());
			pstm.setString(10, pdoador.getEstadoDoador());
			pstm.setString(11, pdoador.getCidadeDoador());
			pstm.setString(12, pdoador.getBairro());
			pstm.setString(13, pdoador.getCep());
			pstm.setDate(14, new java.sql.Date(pdoador.getDataCadastro().getTime()));
			pstm.setString(15, pdoador.getSenhaDoador());

			int tQtdeReg = pstm.executeUpdate();

			// Verificando se um registro foi incluido
			if (tQtdeReg == 1) {
				// Copiando o contato para o retorno
				tdoador = pdoador;

			}

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
		return tdoador;
	}

	@SuppressWarnings("finally")
	@Override
	// metodo para atualizar doador
	public Doador atualiarDoador(Doador pdoador) {
		// criando um doador nulo
		Doador tdoador = null;
		try {
			// inicializando a classe de acesso ao banco
			AcessoDAO acessoDAO = new AcessoDAO();
			// abrindo conexao
			myConnection = acessoDAO.abrirConexao();
			// passando o comando sql
			PreparedStatement pstm = myConnection
					.prepareStatement("UPDATE CADASTRODOADOR SET" + "(CPFDOADOR,NOMEDOADOR,"
							+ "DTNASCIMENTO, TIPOSANGUE, EMAILDOADOR, SEXO , TELDOADOR, CELDOADOR, ENDERECODOADOR,"
							+ " ESTADODOADOR, CIDADEDOADOR, BAIRRO, CEPDOADOR, DATACADASTRO," + "SENHADOADOR)"
							+ "VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" + "WHERE CPFDOADOR = ?");

			// passando os atributos de doador
			pstm.setString(1, pdoador.getCpf());
			pstm.setString(2, pdoador.getNome());
			pstm.setDate(3, new java.sql.Date(pdoador.getDtNascimento().getTime()));
			pstm.setString(4, pdoador.getTipoSangue());
			pstm.setString(5, pdoador.getEmail());
			pstm.setString(6, pdoador.getSexo());
			pstm.setString(7, pdoador.getTelDoador());
			pstm.setString(8, pdoador.getCelDoador());
			pstm.setString(9, pdoador.getEnderecoDoador());
			pstm.setString(10, pdoador.getEstadoDoador());
			pstm.setString(11, pdoador.getCidadeDoador());
			pstm.setString(12, pdoador.getBairro());
			pstm.setString(13, pdoador.getCep());
			pstm.setDate(14, new java.sql.Date(pdoador.getDataCadastro().getTime()));
			pstm.setString(15, pdoador.getSenhaDoador());
			pstm.executeUpdate();
			int tQtdeReg = pstm.executeUpdate();

			// Verificando se um registro foi incluido
			if (tQtdeReg == 1) {
				// Copiando o contato para o retorno
				tdoador = pdoador;
			}

			// Liberando os recursos JDBC
			pstm.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// encerrando a conexao
			try {
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}

			return tdoador;

		}
	}

	@Override
	// metodo para excluir doador
	public boolean exclirDoador(String pCpf) {
		// criando um doador null
		Doador tdoador = null;
		// inicializando um doador
		doador = new Doador();
		try {
			// inicializando classe de acesso ao banco
			AcessoDAO acessoDAO = new AcessoDAO();
			// abrindo conexao
			myConnection = acessoDAO.abrirConexao();
			// passando o comando sql
			PreparedStatement pst = myConnection.prepareStatement("DELETE CADASTRODOADOR WHERE CPFDOADOR = ?");
			// passando os parametros
			pst.setString(1, pCpf);
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

	@Override
	// metodo para listar apenas um doador
	public Doador listarDoador(String pCpf) {
		// criando um doador nulo
		Doador tdoador = null;
		try {
			// iniciando a classe de conexao com o banco
			AcessoDAO acessoDAO = new AcessoDAO();
			// abrindo conexao
			myConnection = acessoDAO.abrirConexao();
			// passando comando sql
			PreparedStatement pstm = myConnection
					.prepareStatement("SELECT * FROM CADASTRODOADOR" + "WHERE CPFDOADOR =?");
			// verificando se o resultado é mair que 0
			if (pstm.getMaxRows() > 0) {
				System.out.println("achei um doador");
				// criar doador para retornar
				doador = new Doador();
				// seta os dados do doador
				pstm.setString(1, doador.getCpf());
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					doador.setNome(rs.getString("NOMEDOADOR"));
					doador.setCpf(rs.getString("CPFDOADOR"));
					doador.setTipoSangue(rs.getString("TIPOSANGUE"));
					((List<Doador>) doador).add(doador);
					// criando o novo doador
					doador = new Doador();
					int tQtdeReg = pstm.executeUpdate();
					if (tQtdeReg == 1) {
						// Copiando o contato para o retorno
						tdoador = doador;
					} else {
						System.out.println("doador não encontrado");
					}

					// Liberando os recursos JDBC
					pstm.close();
				}

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {// encerrando conexao
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tdoador;
	}

	@Override
	// metodo que lista todos os doadores
	public List<Doador> listarDoadores() {
		Connection myConnection = null;
		// criando uma lista para guardar os doadores
		List<Doador> doadores = new ArrayList<>();
		// iniciando um doador
		doador = new Doador();

		try {
			// iniciando a classe de conexao com o banco
			AcessoDAO acessoDAO = new AcessoDAO();
			// abrindo conexao
			myConnection = acessoDAO.abrirConexao();
			// passando comando sql
			PreparedStatement pstm = myConnection
					.prepareStatement("SELECT * FROM CADASTRODOADOR ORDER BY UPPER(NOMEDOADOR)");
			// criando um result set passando os dados que retornaram
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				doador.setNome(rs.getString("NOMEDOADOR"));
				doador.setCpf(rs.getString("CPFDOADOR"));
				doador.setTipoSangue(rs.getString("TIPOSANGUE"));
				doadores.add(doador);
				// criando o novo doador
				doador = new Doador();

			}
			// retornando a lista de doadores
			return doadores;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// encerrando conexao com o banco
			try {
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return doadores;
	}

/**
 * Metodo DAO que inseri no banco as doações marcadas
 */
	public MarcarDoacao marcardoacao(MarcarDoacao pDoacao) {
		MarcarDoacao iDoacao = null;

		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection.prepareStatement(
					"INSERT INTO MARCARDOACAO (IDDOADOR,HOSPDOACAO,DATADOACAO) VALUES (?,?,?)");

			pstm.setString(1, pDoacao.getIdDoador());
			pstm.setString(2, pDoacao.getHospDoacao());
			pstm.setDate(3, new java.sql.Date(pDoacao.getDataDoacao().getTime()));
			pstm.executeUpdate();


			// Liberando os recursos JDBC
			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pDoacao;
	}

/*	@SuppressWarnings("unchecked")
	public List<Hospital> listarHospitais() {

		Connection myConnection = null;
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection.prepareStatement("SELECT FROM CADASTROHOSPITAIS");
			pstm.executeQuery();
			while (((ResultSet) pstm).next()) {
				MarcarDoacao md = new MarcarDoacao();
				pstm.setString(1, md.getIdDoador());
				pstm.setString(2, md.getHospDoacao());
				pstm.setDate(3, new java.sql.Date(md.getDataDoacao().getTime()));
				listarHospitais().addAll((Collection<? extends Hospital>) md);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listarHospitais();

	}
*/
	public boolean fazerLogin(String cpfDoador, String senhaDoador) {
		System.out.println(cpfDoador);
		System.out.println(senhaDoador);
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection
					.prepareStatement("SELECT CPFDOADOR, SENHADOADOR FROM CADASTRODOADOR WHERE CPFDOADOR = ? AND SENHADOADOR = ?");
			pstm.setString(1, cpfDoador);
			pstm.setString(2, senhaDoador);
			
			ResultSet retorno = pstm.executeQuery();

			if (retorno.next()) {
				System.out.println("Achou Usuário " + cpfDoador);
				return true;
			}
			
			// Liberando os recursos JDBC
			pstm.close();
			return false;

		} catch (Exception e) {
			System.out.println("Login error");
			e.printStackTrace();
		} finally {
			
			try {
				if (myConnection != null && !myConnection.isClosed())
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

/*	@Override
	public boolean marcarExame(String idDoador, String hosp, Date dtmarca) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
