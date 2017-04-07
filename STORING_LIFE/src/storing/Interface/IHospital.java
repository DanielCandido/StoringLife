package storing.Interface;


import storing.vo.Hospital;

public interface IHospital {
	
	public Hospital cadastrarHospital(Hospital pHopsital);
	public boolean fazerLoginHosp (String cnpjHodpital, String senhaHodpita);
}
