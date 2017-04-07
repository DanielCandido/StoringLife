/**
 * 
 */
package storing.controle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import storing.controller.dto.DtoDoador;
import storing.controller.dto.DtoHospital;
import storing.controller.dto.DtoMarcarDoacao;
import storing.controller.dto.DtoMedico;
import storing.model.DoadorDAO;
import storing.model.HospitalDAO;
import storing.model.MedicoDAO;
import storing.vo.Doador;
import storing.vo.Hospital;
import storing.vo.MarcarDoacao;
import storing.vo.Medico;

/**
 * Classe para controle 
 * @author Aluno
 *
 */
public class Controle {
	/* Atributos normais */
	
	private MedicoDAO  medicoDAO = new MedicoDAO();
	private HospitalDAO hospitalDAO = new HospitalDAO();
	private DoadorDAO doadorDAO = new DoadorDAO();
	private MarcarDoacao marcadoacao;
	MarcarDoacao marcardoacao = new MarcarDoacao();
	Doador doador = new Doador();
	Hospital hospital = new Hospital();
	Medico medico = new Medico();
	/*pegando a data e hora do sistema*/
	/*private Date dataAtual = new Date();*/
	SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
	
		
	/**
	 * metodo que controla o cadastro de doadores
	 * @param pdoador
	 * @return
	 */
	public DtoDoador cadatrarDoador(Doador pdoador) {
		doadorDAO = new DoadorDAO();
		/* if verificando se doador nao é nulo */
		Doador doador = new Doador();
		if (pdoador == null)
			return new DtoDoador(false, "Cpf ja cadastrado");

		pdoador.setDataCadastro(new Date());
		/* Chamando a camada de persistencia */
		Doador tdoador = doadorDAO.cadatrarDoador(pdoador);

		/* Verificando se gouve algum erro na criação */
		if (tdoador == null)

			/* Retorno para caso tenha ocorrido algum erro */
			return new DtoDoador(false, "Cpf ja cadastrado");
		/* Retorno para caso tenha sido inserido com sucesso o doador */
		else{
		return new DtoDoador(true, "Doador inserido com sucesso");
	}
		
	}

	/**
	 * metodo que controla o listar
	 * @param pcpf
	 * @return
	 */
	public DtoDoador listarDoador (String pcpf){
		/*If para verificar se o cpf digitado correspondea um cpf eistente*/
		doador = new Doador();
		doadorDAO = new DoadorDAO();
		
		Doador retornaDoador = doadorDAO.listarDoador(pcpf);
	
		if (pcpf == null)
			return new DtoDoador(false, "cpf inavlido");
		if (retornaDoador == null)
		{
		return new DtoDoador(false, "Doador não encontrado");
		
		}
		else {
			return new DtoDoador(true, "Doador encontrado com sucesso" + retornaDoador);
		}
	}

	/**
	 * Metodo que controla o atualizar DOador
	 * @param pdoador
	 * @return
	 */
	public DtoDoador atualiarDoador(Doador pdoador) {
		doadorDAO = new DoadorDAO();
		doador = new Doador();
		/* retorno caso tente atualizar um doador nao existente */
		if (pdoador == null)
			return new DtoDoador(false, "tentativa de atualizar um doador não existente");
		/* chamando camada de persistencia */
		Doador tdoador = doadorDAO.atualiarDoador(pdoador);

		/* retorno caso ocorra um erro na tentativa de atualizar doador */
		if (tdoador == null)
			return new DtoDoador(false, "tentativa de atualizar falhou");
		/* retirno caso a atualização esteja correta */
	
		else{
		return new DtoDoador(true, "Doador Atualizado com sucesso");
		}
	}

	/**
	 * Metodo de excluir doadores
	 * @param pCpf
	 * @return
	 */
	public DtoDoador exclirDoador (String pCpf){
		doador = new Doador();
		doadorDAO = new DoadorDAO();
		System.out.println(pCpf);
		boolean excluiDoador = doadorDAO.exclirDoador(pCpf);
		
		if (excluiDoador == false)
			return new DtoDoador (false, "Doador nao encontrado");
			
		boolean gdoador = doadorDAO.exclirDoador(pCpf);
		if(gdoador == false)
			return new DtoDoador(false,"Doador não localizado");
		if(!doadorDAO.exclirDoador(pCpf))
			return new DtoDoador (false, "erro ao tentar ecluir o Doador");
		
		else{
		return new DtoDoador (true, "Doador Excluido com sucesso");
	}
	}
		/**
		 * Metodo para excluir Medicos
		 * @param pCrm
		 * @return
		 */
		public DtoMedico exclirMedico (String pCrm){
			//inciando metodo
			medico = new Medico();
			//inciiando dao
			medicoDAO = new MedicoDAO();
			System.out.println(pCrm);
			boolean exclirMedico = medicoDAO.exclirMedico(pCrm);
			//verificando se medico existe
			if (exclirMedico == false)
				//retornando mensagem
				return new DtoMedico (false, "Medico nao encontrado");
				
			boolean gmedico = medicoDAO.exclirMedico(pCrm);
			if(gmedico == false)
				//retornando mensagem
				return new DtoMedico(false,"Medico não localizado");
			if(!medicoDAO.exclirMedico(pCrm))
				return new DtoMedico (false, "erro ao tentar ecluir o Doador");
			
			else{
			return new DtoMedico (true, "Medico Excluido com sucesso");
		}
	}
		
	public List<Hospital> listarHospitais(){
		hospital = new Hospital();
		List<Hospital> tlista = hospitalDAO.listarHospitais();
		
		return tlista;
	}
	
		/**
		 * Metodo para listar doadores
		 * @return
		 */
	public List<Doador> listarDoadores() {
	doador = new Doador();
		List<Doador> tlista = doadorDAO.listarDoadores();

		return tlista;
		}
	public DtoDoador fazerLogin(String cpfDoador, String senhaDoador){
		doadorDAO = new DoadorDAO();
		doador = new Doador();
		boolean pfazerLogin = doadorDAO.fazerLogin(cpfDoador, senhaDoador);
		if(cpfDoador != doador.getCpf())
			return new DtoDoador(false, "CPF invalido");
		if(senhaDoador != doador.getSenhaDoador())
			return new DtoDoador(false, "Senha invalida");
		
		if (pfazerLogin == false)
			return new DtoDoador(true, "Login realizado");
		else {
			return new DtoDoador (false, "Login ou senha invalido");
		}
	}
	public DtoMarcarDoacao marcarDoacao(MarcarDoacao pDoacao){
		doadorDAO = new DoadorDAO();
		setMarcadoacao(new MarcarDoacao());
		MarcarDoacao pmarcarDoacao = doadorDAO.marcardoacao(pDoacao);
		
		if (pmarcarDoacao == null)
			return new DtoMarcarDoacao (false, "Não foi possivel marcar a Doação");
		if (pDoacao == null)
			return new DtoMarcarDoacao (false, "Não foi possivel marcar a Doação");
		else{
			return new DtoMarcarDoacao (true, "Doação marcada com sucesso");
		}
	}
	
	/* Metodo para criar um hospital */
	public DtoHospital cadastrarHospital(Hospital pHospital) {
		hospitalDAO = new HospitalDAO();
		/* if verificando se hospital nao é nulo */
		 hospital = new Hospital();
		if (pHospital == null)
			return new DtoHospital(false, "Problema ao tentar inserir um hospital nulo");

		
		/* Chamando a camada de persistencia */
		Hospital hHospital = hospitalDAO.cadastrarHospital(pHospital); 

		/* Verificando se gouve algum erro na criação */
		if (hHospital == null)

			/* Retorno para caso tenha ocorrido algum erro */
			return new DtoHospital(false, "problemas ao tentar inserir um hospital nulo");
		/* Retorno para caso tenha sido inserido com sucesso o hospital */
		else{
		return new DtoHospital(true, "Hospital inserido com sucesso");
	}
}
	
	/* Metodo para criar um medico */
	public DtoMedico cadastrarMedico(Medico pMedico) {
		medicoDAO = new MedicoDAO();
		/* if verificando se medico nao é nulo */
		 medico = new Medico();
		if (pMedico == null)
			return new DtoMedico(false, "Problema ao tentar inserir um medico nulo");

		
		/* Chamando a camada de persistencia */
		Medico hMedico = medicoDAO.cadastrarMedico(pMedico);

		/* Verificando se houve algum erro na criação */
		if (hMedico == null)

			/* Retorno para caso tenha ocorrido algum erro */
			return new DtoMedico(false, "problemas ao tentar inserir um medico nulo");
		/* Retorno para caso tenha sido inserido com sucesso o medico */
		else{
		return new DtoMedico(true, "Medico inserido com sucesso");
	}
}
	public DtoHospital fazerLoginHosp (String cnpjHospital, String senhaHospital){
		hospitalDAO = new HospitalDAO();
		hospital = new Hospital();
		boolean pfazerLogin = hospitalDAO.fazerLoginHosp(cnpjHospital, senhaHospital);
		
		if (pfazerLogin == false)
			return new DtoHospital(true, "Login realizado");
		else {
			return new DtoHospital (false, "Login ou senha invalido");
	}
}

	public MarcarDoacao getMarcadoacao() {
		return marcadoacao;
	}

	public void setMarcadoacao(MarcarDoacao marcadoacao) {
		this.marcadoacao = marcadoacao;
	}

}
