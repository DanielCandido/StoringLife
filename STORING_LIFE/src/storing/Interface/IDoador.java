/**
 * 
 */
package storing.Interface;

import java.util.Date;
import java.util.List;

import storing.vo.Doador;
import storing.vo.MarcarDoacao;

/**
 * @author Daniel Candido e Willian Dos Santos
 *
 */
public interface IDoador {
	/**
	 * 
	 * @param doador
	 * @return
	 */
	
	public Doador cadatrarDoador(Doador pdoador);

	public Doador atualiarDoador(Doador pdoador);

	boolean exclirDoador(String pCpf);

	public Doador listarDoador(String pCpf);

	public List<Doador> listarDoadores();

	public boolean fazerLogin (String cpfDoador, String senhaDoador);
	
	public MarcarDoacao marcardoacao(MarcarDoacao pDoacao);
}
