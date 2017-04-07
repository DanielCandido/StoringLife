/**
 * 
 */
package storing.controller.dto;

import java.util.List;

import storing.vo.Doador;

/**
 * @author Aluno
 *
 */
public class DtoDoador extends DtoAbstract {

	/**
	 * @param pOK
	 * @param pMensagem
	 */
	/*atributos normais */
	private Doador doador;
	private List<Doador> lista;

		/*Construtores de classe*/
	
	public DtoDoador(boolean pOK, String pMensagem) {
		super(pOK, pMensagem);
	}

	public DtoDoador(boolean pOK, String pMensagem, Doador pdoador) {
		super(pOK, pMensagem);
		doador = pdoador;
	}
	   public DtoDoador(boolean pOk, String pMensagem, List<Doador> pLista)
	    {
	        super(pOk, pMensagem);
	        lista = pLista;
	    }

	   
	   /* metodos de acesso*/
	   public Doador getDoador()
	    {
	        return doador;
	    }

	    public void setDoador(Doador pdoador)
	    {
	        doador = pdoador;
	    }

	    public List<Doador> getLista()
	    {
	        return lista;
	    }

	    public void setLista(List<Doador> pLista)
	    {
	        lista = pLista;
	    }
	
}
