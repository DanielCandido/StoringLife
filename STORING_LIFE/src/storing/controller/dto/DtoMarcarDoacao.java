/**
 * 
 */
package storing.controller.dto;

import storing.vo.MarcarDoacao;

/**
 * @author Daniel Candido
 *
 */
public class DtoMarcarDoacao extends DtoAbstract {
	
	private MarcarDoacao marcardoacao;

		/*Construtores de classe*/
	
	public DtoMarcarDoacao(boolean pOK, String pMensagem) {
		super(pOK, pMensagem);
	}

	public DtoMarcarDoacao(boolean pOK, String pMensagem, MarcarDoacao pMarcardoacao) {
		super(pOK, pMensagem);
		setMarcardoacao(pMarcardoacao);
	}

	public MarcarDoacao getMarcardoacao() {
		return marcardoacao;
	}

	public void setMarcardoacao(MarcarDoacao marcardoacao) {
		this.marcardoacao = marcardoacao;
	}

	    /* metodos de acesso*/
	 

}
