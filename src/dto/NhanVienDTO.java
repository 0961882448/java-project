package dto;

import java.time.LocalDate;

public class NhanVienDTO {
	private int maNV;
	private String hoTen;
	private LocalDate ngaySinh;
	private String user;
	private String pass;
	private String quyen;
	private int luong;
	
	
	
	public NhanVienDTO(String hoTen, LocalDate ngaySinh, String user, String pass, String quyen, int luong) {
		super();
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.user = user;
		this.pass = pass;
		this.quyen = quyen;
		this.luong = luong;
	}
	public NhanVienDTO(int maNV, String hoTen, LocalDate ngaySinh, String user, String pass, String quyen, int luong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.user = user;
		this.pass = pass;
		this.quyen = quyen;
		this.luong = luong;
	}
	
	

	public NhanVienDTO(int maNV, String hoTen, LocalDate ngaySinh, String quyen, int luong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.quyen = quyen;
		this.luong = luong;
	}
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
		this.luong = luong;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	
}
