/**
 * 
 */
package storing.controller.dto;

import java.util.List;

import storing.vo.Hospital;

/**
 * @author Aluno
 *
 */
public class DtoHospital extends DtoAbstract {

	/**
	 * @param pOK
	 * @param pMensagem
	 */
	/*atributos normais */
	private Hospital hospital;
	private List<Hospital> lista;

		/*Construtores de classe*/
	
	public DtoHospital(boolean pOK, String pMensagem) {
		super(pOK, pMensagem);
	}

	public DtoHospital(boolean pOK, String pMensagem, Hospital pHospital) {
		super(pOK, pMensagem);
		hospital = pHospital;
	}
	   public DtoHospital(boolean pOk, String pMensagem, List<Hospital> pLista)
	    {
	        super(pOk, pMensagem);
	        lista = pLista;
	    }

	   
	   /* metodos de acesso*/
	   public Hospital getHospital()
	    {
	        return hospital;
	    }

	    public void setHospital(Hospital pHospital)
	    {
	        hospital = pHospital;
	    }

	    public List<Hospital> getLista()
	    {
	        return lista;
	    }

	    public void setLista(List<Hospital> pLista)
	    {
	        lista = pLista;
	    }
	
}
