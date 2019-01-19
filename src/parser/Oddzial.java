package parser;

public class Oddzial {
	
	public void setTerytTerc(String terytTerc) {
		this.terytTerc = terytTerc.replace("|", " ");
	}
	
	public void setTerytSimc(String terytSimc) {
		this.terytSimc = terytSimc.replace("|", " ");
	}
	
	public void setTerytUlic(String terytUlic) {
		this.terytUlic = terytUlic.replace("|", " ");
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
				this.terytTerc + "|" +
				this.terytSimc + "|" +
				this.terytUlic + "|" +
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
	
	private String terytTerc = "";
	private String terytSimc = "";
	private String terytUlic = "";
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
