package storing.vo;

import java.util.Date;

public class MarcarDoacao {
	private String idDoador;
	private String hospDoacao;
	private Date dataDoacao; 

	
	public String getIdDoador() {
		return idDoador;
	}


	public void setIdDoador(String idDoador) {
		this.idDoador = idDoador;
	}


	public String getHospDoacao() {
		return hospDoacao;
	}


	public void setHospDoacao(String hospDoacao) {
		this.hospDoacao = hospDoacao;
	}


	public Date getDataDoacao() {
		return dataDoacao;
	}


	public void setDataDoacao(Date dataDoacao) {
		this.dataDoacao = dataDoacao;
	}


	public MarcarDoacao() {
		// TODO Auto-generated constructor stub
	}

}
