package dto;

import java.time.LocalDate;

public class nguyenlieuDTO {
	private int id;
	private String tenNguyenLieu;
	private double giaNguyenLieu;
	private int soluong;
	private LocalDate ngayNhap;
	
	
	public nguyenlieuDTO(String tenNguyenLieu, double giaNguyenLieu, int soluong, LocalDate ngayNhap) {
		super();
		this.tenNguyenLieu = tenNguyenLieu;
		this.giaNguyenLieu = giaNguyenLieu;
		this.soluong = soluong;
		this.ngayNhap = ngayNhap;
	}
	public nguyenlieuDTO(int id, String tenNguyenLieu, double giaNguyenLieu, int soluong, LocalDate ngayNhap) {
		super();
		this.id = id;
		this.tenNguyenLieu = tenNguyenLieu;
		this.giaNguyenLieu = giaNguyenLieu;
		this.soluong = soluong;
		this.ngayNhap = ngayNhap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenNguyenLieu() {
		return tenNguyenLieu;
	}
	public void setTenNguyenLieu(String tenNguyenLieu) {
		this.tenNguyenLieu = tenNguyenLieu;
	}
	public double getGiaNguyenLieu() {
		return giaNguyenLieu;
	}
	public void setGiaNguyenLieu(double giaNguyenLieu) {
		this.giaNguyenLieu = giaNguyenLieu;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public LocalDate getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	

}
