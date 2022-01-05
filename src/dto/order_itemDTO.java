package dto;

public class order_itemDTO {
	private int id;
	private int idOreder;
	private int idMenu;
	private int soLuong;
	private Double discount;
	private int idb;
	
	
	
	public order_itemDTO(int id, int idOreder, int idMenu, int soLuong, Double discount, int idb) {
		super();
		this.id = id;
		this.idOreder = idOreder;
		this.idMenu = idMenu;
		this.soLuong = soLuong;
		this.discount = discount;
		this.idb = idb;
	}
	public order_itemDTO(int idOreder, int idMenu, int soLuong, Double discount, int idb) {
		super();
		this.idOreder = idOreder;
		this.idMenu = idMenu;
		this.soLuong = soLuong;
		this.discount = discount;
		this.idb = idb;
	}
	public int getIdb() {
		return idb;
	}
	public void setIdb(int idb) {
		this.idb = idb;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdOreder() {
		return idOreder;
	}
	public void setIdOreder(int idOreder) {
		this.idOreder = idOreder;
	}
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
}
