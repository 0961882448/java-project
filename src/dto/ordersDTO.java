package dto;

import java.sql.Connection;
import java.sql.Time;
import java.time.LocalDate;


public class ordersDTO {
	private int id;
	private int idnv;
	private LocalDate ngayOrder;
	private Time timeOrder;
	private Double priceOrder;
	@SuppressWarnings("unused")
	private String time;
	@SuppressWarnings("unused")
	private Connection conn;
	
	public ordersDTO(Connection conn) {
		this.conn = conn;
	}
	public ordersDTO(int idnv, LocalDate ngayOrder, String time, Double priceOrder) {
		super();
		this.idnv = idnv;
		this.ngayOrder = ngayOrder;
		this.time = time;
		this.priceOrder = priceOrder;
	}
	
	public ordersDTO(int idnv, LocalDate ngayOrder, Time timeOrder, Double priceOrder) {
		super();
		this.idnv = idnv;
		this.ngayOrder = ngayOrder;
		this.timeOrder = timeOrder;
		this.priceOrder = priceOrder;
	}
	
	
	
	public ordersDTO(int id, int idnv, LocalDate ngayOrder, Time timeOrder, Double priceOrder) {
		super();
		this.id = id;
		this.idnv = idnv;
		this.ngayOrder = ngayOrder;
		this.timeOrder = timeOrder;
		this.priceOrder = priceOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdnv() {
		return idnv;
	}
	public void setIdnv(int idnv) {
		this.idnv = idnv;
	}
	public LocalDate getNgayOrDate() {
		return ngayOrder;
	}
	public void setNgayOrDate(LocalDate ngayOrDate) {
		this.ngayOrder = ngayOrDate;
	}
	public Time getTimeOrder() {
		return timeOrder;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setTimeOrder(Time timeOrder) {
		this.timeOrder = timeOrder;
	}
	public Double getPriceOrder() {
		return priceOrder;
	}
	public void setPriceOrder(Double priceOrder) {
		this.priceOrder = priceOrder;
	}
	
	
}
