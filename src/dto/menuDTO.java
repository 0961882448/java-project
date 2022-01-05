package dto;

public class menuDTO {
	private int id;
	private String tenMon;
	private Double gia;
	
	
	public menuDTO(Integer id, String tenMon, Double gia) {
		super();
		this.id = id;
		this.tenMon = tenMon;
		this.gia = gia;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public menuDTO(String tenMon, Double gia) {
		super();
		this.tenMon = tenMon;
		this.gia = gia;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public Double getGia() {
		return gia;
	}
	public void setGia(Double gia) {
		this.gia = gia;
	}
	
}
