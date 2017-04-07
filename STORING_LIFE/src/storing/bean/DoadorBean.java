/**
 * 
 */
package storing.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import storing.controle.Controle;
import storing.controller.dto.DtoDoador;
import storing.controller.dto.DtoHospital;
import storing.controller.dto.DtoMarcarDoacao;
import storing.controller.dto.DtoMedico;
import storing.model.DoadorDAO;
import storing.model.HospitalDAO;
import storing.util.SessionUtils;
import storing.vo.Cidade;
import storing.vo.Doador;
import storing.vo.Hospital;
import storing.vo.LoginDoador;
import storing.vo.MarcarDoacao;
import storing.vo.Medico;

@Named("doadorBean")
@SessionScoped
/**
 * @author Aluno
 *
 */
public class DoadorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -92101457439998016L;
	/**
	 * 
	 */
	// Atributos Globais
	private Doador doador;
	private Hospital hospital;
	private Medico medico;
	private Controle medicocontrole;
	private Controle doadorcontrole;
	private Controle hospitalcontrole;
	private Controle marcardoacaocontrole;
	private DoadorDAO doadorDAO;
	private HospitalDAO hospitalDAO;
	private MarcarDoacao marcadoacao;
	private List<Doador> listaDoador;
	private List<Cidade> listaCidade;
	private boolean setStatusCnpj;
	private boolean setStatuslistarHospital;
	private boolean statuslistarDoador;
	private boolean statusCadastrar;
	private boolean statusAtualizar;
	private boolean statusExcluir;
	private boolean statusCpf;
	private boolean fazerLogin;
	private boolean fazerLoginHosp;
	private boolean statusCnpjHospital;
	private String cpfLogin;
	private String senhaLogin;
	private String cnpjLogin;
	private String senhaHospLogin;
	
	
	public DoadorBean() {
		doador = new Doador();
		doadorcontrole = new Controle();
		marcadoacao = new MarcarDoacao();
		hospitalcontrole = new Controle();
		hospital = new Hospital();
		medico = new Medico();
		medicocontrole = new Controle();
		marcardoacaocontrole= new Controle();
		doadorDAO = new DoadorDAO();
		hospitalDAO = new HospitalDAO();
	}

	public void acertarBotoesManutencao() {

		// Acertando o status dos botoes
		setStatusCnpj = true;
		setStatuslistarHospital = true;
		statusCpf = true;
		statuslistarDoador = true;
		statusCadastrar = true;
		statusAtualizar = false;
		statusExcluir = false;
		statusCnpjHospital = false;
	}

	// public void DoadorBean(){
	//
	// Doadorcontrole = new Controle();
	//
	// doador = new Doador();
	//
	// DtoDoador doadordto = Doadorcontrole.listarDoadores();
	//
	// if(doadordto.taOK()) {
	//
	// }
	// }

	public void acertaBotoens() {
		setStatusCnpj = true;
		setStatuslistarHospital = true;
		statusCpf = true;
		statuslistarDoador = true;
		statusCadastrar = true;
		statusAtualizar = false;
		statusExcluir = false;
	}

	/**
	 * metodo para consultar
	 * 
	 * @return
	 */
	public String ConsultarDoador() {
		FacesContext contexto = FacesContext.getCurrentInstance();

		String tCpf = doador.getCpf();

		doador = new Doador();
		doador.setCpf(tCpf);

		doadorcontrole = new Controle();
		DtoDoador doadordto = doadorcontrole.listarDoador(tCpf);

		if (doadordto.taOK()) {
			doador = doadordto.getDoador();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, doadordto.getpMensagem(), null));
			acertaBotoens();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, doadordto.getpMensagem(), null));
		}
		return "CadastrarDoadores";
	}

	/**
	 * Metodo para cadastrar
	 * 
	 * @return
	 */
	public String cadastrarDoador() {

		FacesContext contexto = FacesContext.getCurrentInstance();

		doadorcontrole = new Controle();
		DtoDoador doadordto = doadorcontrole.cadatrarDoador(doador);

		if (!contexto.getMessageList().isEmpty()) {
			return "LoginDoador";
		}
		if (doadordto.taOK()) {
			doador = doadordto.getDoador();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, doadordto.getpMensagem(), null));
			doadorcontrole = new Controle();
			statusCpf = true;
			statuslistarDoador = true;
			statusCadastrar = true;
			statusAtualizar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, doadordto.getpMensagem(), null));
		}
		doador = new Doador();
		return "LoginDoador";
	}

	/**
	 * Metodo cadastrarHospital
	 * 
	 * @return
	 */
	public String cadastrarHospital() {

		FacesContext contexto = FacesContext.getCurrentInstance();

		hospitalcontrole = new Controle();
		DtoHospital hospitaldto = hospitalcontrole.cadastrarHospital(hospital);

		if (!contexto.getMessageList().isEmpty()) {
			return "LoginHospital";
		}
		if (hospitaldto.taOK()) {
			hospital = hospitaldto.getHospital();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, hospitaldto.getpMensagem(), null));
			setStatusCnpj = true;
			setStatuslistarHospital = true;
			statusCpf = true;
			statuslistarDoador = true;
			statusCadastrar = true;
			statusAtualizar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, hospitaldto.getpMensagem(), null));
		}
		hospital = new Hospital();
		return "LoginHospital";

	}

	/**
	 * Metodo para cadastrar Medicos
	 * 
	 * @return
	 */
	public String cadastrarMedico() {

		FacesContext contexto = FacesContext.getCurrentInstance();

		medicocontrole = new Controle();
		DtoMedico medicoDTO = medicocontrole.cadastrarMedico(medico);

		if (!contexto.getMessageList().isEmpty()) {
			return "MenuHospital";
		}
		if (medicoDTO.taOK()) {
			medico = medicoDTO.getMedico();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, medicoDTO.getpMensagem(), null));
			setStatusCnpj = true;
			setStatuslistarHospital = true;
			statusCpf = true;
			statuslistarDoador = true;
			statusCadastrar = true;
			statusAtualizar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, medicoDTO.getpMensagem(), null));
		}
		medico = new Medico();
		return "LoginHospital";

	}

	/**
	 * Metodo para atualizar doador
	 * 
	 * @return
	 */

	public String AtualizarDoador() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		DtoDoador doadordto = doadorcontrole.atualiarDoador(doador);
		if (doadordto.taOK()) {
			doador = doadordto.getDoador();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, doadordto.getpMensagem(), null));

			statusCpf = true;
			statuslistarDoador = true;
			statusCadastrar = true;
			statusAtualizar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, doadordto.getpMensagem(), null));
		}

		return "LoginDoador";
	}

	/**
	 * Metodo para excluir Doador
	 * 
	 * @return
	 */
	public String exclirDoador() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		DtoDoador doadordto = doadorcontrole.exclirDoador(doador.getCpf());
		if (doadordto.taOK()) {
			doador = doadordto.getDoador();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, doadordto.getpMensagem(), null));
			restaurarStatusBotoes();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, doadordto.getpMensagem(), null));
		}

		return "CadastrarDoadores";
	}

	/**
	 * Metodo para excluir Medicos
	 * 
	 * @return
	 */

	public String exclirMedico() {

		FacesContext contexto = FacesContext.getCurrentInstance();
		DtoMedico dtomedico = medicocontrole.exclirMedico(medico.getCrmMedico());
		if (dtomedico.taOK()) {
			medico = dtomedico.getMedico();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, dtomedico.getpMensagem(), null));
			restaurarStatusBotoes();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dtomedico.getpMensagem(), null));
		}

		return "CadastrarMedicos";
	}

	/**
	 * Metodo para fazer Login
	 * 	
	 * @return
	 */
	public String fazerLogin(){
		/*DtoDoador logar = doadorcontrole.fazerLogin(getSenhaDoador(),getCpf());*/
		
		boolean validar = doadorDAO.fazerLogin(cpfLogin, senhaLogin);	
		System.out.println("Chegou  2");
				if(validar){
					System.out.println("Entrou validar");
					
					HttpSession session =  SessionUtils.getSession();
					session.setAttribute("CPFDOADOR", cpfLogin);
					return "MenuDoador";
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF ou senha incorreto", null));
					return "LoginDoador";
				}
	}
	/**
	 * Metodo para fazer logout
	 */
	public String fazerLogout(){
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "LoginDoador";
	}
	public String fazerLogoutHosp(){
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "LoginHospital";
	}
	
	/**
	 * Metodo para fazer Hospital
	 * 
	 * @return
	 */
	public String fazerLoginHosp() {
		System.out.println(cnpjLogin);
		System.out.println(senhaHospLogin);
		boolean validar = hospitalDAO.fazerLoginHosp(cnpjLogin, senhaHospLogin);
		System.out.println("Chegou  2 Hospital");
				if(validar){
					System.out.println("Entrou validar");
					
					HttpSession session =  SessionUtils.getSession();
					session.setAttribute("CNPJHOSP", cnpjLogin);
					return "MenuHospital";
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CNPJ ou senha incorreto", null));
					return "LoginHospital";
				}
	}

	/**
	 * Metodo para marcar doação
	 * 
	 * @return
	 */
	public String marcarDoacao() {

		System.out.println("TYRJSADBJNA");
		FacesContext contexto = FacesContext.getCurrentInstance();
		DtoMarcarDoacao marcardoacaodto = marcardoacaocontrole.marcarDoacao(marcadoacao);

		if (!contexto.getMessageList().isEmpty()) {
			return "MenuDoador";
		}
		if (marcardoacaodto.taOK()) {
			marcadoacao = marcardoacaodto.getMarcardoacao();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, marcardoacaodto.getpMensagem(), null));
			doadorcontrole = new Controle();
			statusCpf = true;
			statuslistarDoador = true;
			statusCadastrar = true;
			statusAtualizar = false;
			statusExcluir = false;
		} else {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, marcardoacaodto.getpMensagem(), null));
		}
		marcadoacao = new MarcarDoacao();
		return "MenuDoador";
	}

	public String Limpar() {

		System.out.println("Cadastro Contato - Excluir - Entrada - " + doador);

		doador = new Doador();

		restaurarStatusBotoes();

		System.out.println("Cadastro Contato - Excluir - Saï¿½da   - " + doador);
		return "CadastrarDoadores";
	}

	/*
	 * public void encerrar(){ doadorcontrole = new Controle();
	 * if(doadorcontrole.fazerLogin(!fazerLogin) != fazerLogin); }
	 */
	private void restaurarStatusBotoes() {
		// Acertando o status dos botoes
		statusCpf = false;
		statuslistarDoador = false;
		statusCadastrar = false;
		statusAtualizar = true;
		statusExcluir = true;
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Campo Obrigatorio"));
	}

	public void warning() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Impossivel inserir, Campo nulo"));
	}
	

	/**
	 * Metodos de get e set
	 * 
	 * @return
	 */
	public Controle getDoadorcontrole() {
		return doadorcontrole;
	}

	public void setDoadorcontrole(Controle doadorcontrole) {
		this.doadorcontrole = doadorcontrole;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public boolean isStatuslistarDoador() {
		return statuslistarDoador;
	}

	public void setStatuslistarDoador(boolean statuslistarDoador) {
		this.statuslistarDoador = statuslistarDoador;
	}

	public boolean isStatusCadastrar() {
		return statusCadastrar;
	}

	public void setStatusCadastrar(boolean statusCadastrar) {
		this.statusCadastrar = statusCadastrar;
	}

	public boolean isStatusAtualizar() {
		return statusAtualizar;
	}

	public void setStatusAtualizar(boolean statusAtualizar) {
		this.statusAtualizar = statusAtualizar;
	}

	public boolean isStatusExcluir() {
		return statusExcluir;
	}

	public void setStatusExcluir(boolean statusExcluir) {
		this.statusExcluir = statusExcluir;
	}

	public boolean isStatusCpf() {
		return statusCpf;
	}

	public void setStatusCpf(boolean statusCpf) {
		this.statusCpf = statusCpf;
	}

	public String getCpf() {
		return doador.getCpf();
	}

	public String getSenhaDoador() {
		return doador.getSenhaDoador();
	}

	public MarcarDoacao getMarcadoacao() {
		return marcadoacao;
	}

	public void setMarcadoacao(MarcarDoacao marcadoacao) {
		this.marcadoacao = marcadoacao;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Controle getHospitalcontrole() {
		return hospitalcontrole;
	}

	public void setHospitalcontrole(Controle hospitalcontrole) {
		this.hospitalcontrole = hospitalcontrole;
	}

	public boolean isSetStatusCnpj() {
		return setStatusCnpj;
	}

	public void setSetStatusCnpj(boolean setStatusCnpj) {
		this.setStatusCnpj = setStatusCnpj;
	}

	public boolean isSetStatuslistarHospital() {
		return setStatuslistarHospital;
	}

	public void setSetStatuslistarHospital(boolean setStatuslistarHospital) {
		this.setStatuslistarHospital = setStatuslistarHospital;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Controle getMedicocontrole() {
		return medicocontrole;
	}

	public void setMedicocontrole(Controle medicocontrole) {
		this.medicocontrole = medicocontrole;
	}

	public List<Doador> getListaDoador() {
		return listaDoador;
	}

	public void setListaDoador(List<Doador> listaDoador) {
		this.listaDoador = listaDoador;
	}

	public boolean isFazerLogin() {
		return fazerLogin;
	}

	public void setFazerLogin(boolean fazerLogin) {
		this.fazerLogin = fazerLogin;
	}

	public boolean isFazerLoginHosp() {
		return fazerLoginHosp;
	}

	public void setFazerLoginHosp(boolean fazerLoginHosp) {
		this.fazerLoginHosp = fazerLoginHosp;
	}

	public boolean isStatusCnpjHospital() {
		return statusCnpjHospital;
	}

	public void setStatusCnpjHospital(boolean statusCnpjHospital) {
		this.statusCnpjHospital = statusCnpjHospital;
	}

	public Controle getMarcardoacaocontrole() {
		return marcardoacaocontrole;
	}

	public void setMarcardoacaocontrole(Controle marcardoacaocontrole) {
		this.marcardoacaocontrole = marcardoacaocontrole;
	}

	public String getCpfLogin() {
		return cpfLogin;
	}

	public void setCpfLogin(String cpfLogin) {
		this.cpfLogin = cpfLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public String getCnpjLogin() {
		return cnpjLogin;
	}

	public void setCnpjLogin(String cnpjLogin) {
		this.cnpjLogin = cnpjLogin;
	}

	public String getSenhaHospLogin() {
		return senhaHospLogin;
	}

	public void setSenhaHospLogin(String senhaHospLogin) {
		this.senhaHospLogin = senhaHospLogin;
	}

	public HospitalDAO getHospitalDAO() {
		return hospitalDAO;
	}

	public void setHospitalDAO(HospitalDAO hospitalDAO) {
		this.hospitalDAO = hospitalDAO;
	}

}
