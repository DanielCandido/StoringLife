/**
 * 
 */
package storing.controller.dto;

import java.util.List;

import storing.vo.Doador;
import storing.vo.Medico;

/**
 * @author Daniel Candido
 *
 */
public class DtoMedico extends DtoAbstract {

	
	/**
	 * 
	 */
	private Medico medico;
	private List<Medico> lista;
	
	
	//Contrutor
	public DtoMedico(boolean pOK,String pMensagem) {
		super (pOK, pMensagem);
	}
	public DtoMedico(boolean pOK, String pMensagem, Medico pMedico) {
		super(pOK, pMensagem);
		medico = pMedico;
	}
	   public DtoMedico(boolean pOk, String pMensagem, List<Medico> pLista)
	    {
	        super(pOk, pMensagem);
	        lista = pLista;
	    }


	//Metodos de acesso
	public Medico getMedico() {
		return medico;
	}


	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public List<Medico> getLista() {
		return lista;
	}


	public void setLista(List<Medico> lista) {
		this.lista = lista;
	}



}
