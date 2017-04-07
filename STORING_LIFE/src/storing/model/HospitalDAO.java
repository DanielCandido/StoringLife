/**
 * 
 */
package storing.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import storing.vo.Hospital;

/**
 * @author aluno
 *
 */
public class HospitalDAO {

	/**
	 * 
	 */
	public HospitalDAO(){
		
	}
	
		Connection myConnection = null;
		private Hospital hospital;
	
	public Hospital cadastrarHospital(Hospital pHospital) {
		Hospital tHospital = null;

		try {

			AcessoDAO acessoDAO = new AcessoDAO();

			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection.prepareStatement("INSERT INTO CADASTROHOSPITAL(CNPJHOSPITAL, RAZAOSOCIAL,"
					+ "NOMEFANTASIA, TELHOSPITAL, ENDERECOHOSP,ESTADOHOSP, CIDADEHOSP, BAIRROHOSP, CEPHOSP,"
					+ " SENHAHOSP, EMAILHOSP)" + "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?)");

			pstm.setString(1, pHospital.getCnpjHospital());
			pstm.setString(2, pHospital.getRazaoSocialHosp());
			pstm.setString(3, pHospital.getNomeHospital());
			pstm.setString(4, pHospital.getTelefoneHospital());
			pstm.setString(5, pHospital.getEnderecoHospital());
			pstm.setString(6, pHospital.getEstadoHospital());
			pstm.setString(7, pHospital.getCidadeHospital());
			pstm.setString(8, pHospital.getBairroHospital());
			pstm.setString(9, pHospital.getCepHospital());
			pstm.setString(10, pHospital.getSenhaHospital());
			pstm.setString(11, pHospital.getEmailHospital());
			

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
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tHospital;
	}
	
	public List<Hospital> listarHospitais() {
		Connection myConnection = null;
		// criando uma lista para guardar os doadores
		List<Hospital> hospitais= new ArrayList<>();
		// iniciando um doador
		hospital = new Hospital();

		try {
			// iniciando a classe de conexao com o banco
			AcessoDAO acessoDAO = new AcessoDAO();
			// abrindo conexao
			myConnection = acessoDAO.abrirConexao();
			// passando comando sql
			PreparedStatement pstm = myConnection
					.prepareStatement("SELECT * FROM CADASTROHOSPITAL ORDER BY UPPER(RAZAOSOCIAL)");
			// criando um result set passando os dados que retornaram
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				hospital.setRazaoSocialHosp(rs.getString("RAZAOSOCIAL"));
				hospital.setTelefoneHospital(rs.getString("TELHOSPITAL"));
				hospital.setEnderecoHospital(rs.getString("ENDERECOHOSP"));
				hospital.setBairroHospital(rs.getString("BAIRROHOSP"));
				hospital.setCidadeHospital(rs.getString("CIDADEHOSP"));
				hospital.setEstadoHospital(rs.getString("ESTADOHOSP"));
				hospitais.add(hospital);
				// criando o novo doador
				hospital = new Hospital();

			}
			// retornando a lista de doadores
			return hospitais;

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
		return hospitais;
	}
	public boolean fazerLoginHosp(String cnpjHodpital, String senhaHodpital) {
		System.out.println(cnpjHodpital);
		System.out.println(senhaHodpital);
		try {
			AcessoDAO acessoDAO = new AcessoDAO();
			myConnection = acessoDAO.abrirConexao();
			PreparedStatement pstm = myConnection
					.prepareStatement("SELECT * FROM CADASTROHOSPITAL WHERE CNPJHOSPITAL = ? AND SENHAHOSP = ?");
			pstm.setString(1, cnpjHodpital);
			pstm.setString(2, senhaHodpital);

			ResultSet retorno = pstm.executeQuery();

			if (retorno.next()) {
				System.out.println("Retornou hospital " + cnpjHodpital);
				return true;
			}

			// Liberando os recursos JDBC
			pstm.close();
			return false;

		} catch (Exception e) {
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
	}
