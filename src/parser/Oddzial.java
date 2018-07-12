package parser;

public class Oddzial {
	
	public void setTerc(String terc) {
		this.terc = terc.replace("|", " ");
	}
	
	public void setSimc(String simc) {
		this.simc = simc.replace("|", " ");
	}
	
	public void setUlic(String ulic) {
		this.ulic = ulic.replace("|", " ");
	}
	
	public void setId(String id) {
		this.identyfikator = id.replace("|", " ");
	}
	
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc.replace("|", " ");
	}
	
	public void setUlica (String ulica) {
		this.ulica = ulica.replace("|", " ");
	}
	
	public void setBudynek(String budynek) {
		this.budynek = budynek.replace("|", " ");
	}
	
	public void setLokal(String lokal) {
		this.lokal = lokal.replace("|", " ");
	}
	
	public void setKodPocztowy(String kod) {
		this.kodPocztowy = kod.replace("|", " ");
	}
	
	public void setPoczta(String poczta) {
		this.poczta = poczta.replace("|", " ");
	}
	
	public void setPowiat(String powiat) {
		this.powiat = powiat.replace("|", " ");
	}
	
	public void setGmina(String gmina) {
		this.gmina = gmina.replace("|", " ");
	}
	
	public void setWojewodztwo(String woj) {
		this.wojewodztwo = woj.replace("|", " ");
	}
	
	public void setOpis(String opis) {
		this.opis = opis.replace("|", " ");
	}
	
	@Override
	public String toString() {
		return this.identyfikator + "|" +
				this.terc + "|" +
				this.simc + "|" +
				this.ulic + "|" +
				this.miejscowosc + "|" +
				this.ulica + "|" +
				this.budynek + "|" +
				this.lokal + "|" +
				this.kodPocztowy + "|" +
				this.poczta + "|" +
				this.powiat + "|" +
				this.gmina + "|" +
				this.wojewodztwo + "|" +
				this.opis;
	}
	
	private String terc = "";
	private String simc = "";
	private String ulic = "";
	private String identyfikator = "";
	private String miejscowosc = "";
	private String ulica = "";
	private String budynek = "";
	private String lokal = "";
	private String kodPocztowy = "";
	private String poczta = "";
	private String powiat = "";
	private String gmina = "";
	private String wojewodztwo = "";
	private String opis = "";

}
