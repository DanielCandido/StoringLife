/**
 * 
 */
package storing.controller.dto;

import java.util.Date;

/**
 * @author Aluno
 *
 */
public class DtoAbstract {
	 /*atributos normais*/
	private boolean   ok;
	private String    mensagem;
	
	/*construtores de classe*/
	public DtoAbstract(boolean pOK , String pMensagem){
		super ();
		ok = pOK;
		mensagem = pMensagem;
	}
	public boolean taOK(){
		return ok;
	}
	public void setOK(boolean pOK){
		ok = pOK;
	}
	public String getpMensagem(){
		return mensagem;
		
	}
	public void setMensagem(String pMensagem){
		mensagem = pMensagem;
	}
	public String dtNascimento(Date dtNasc){
		return mensagem;
		
	}




}
