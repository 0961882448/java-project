package dto;

public class banDTO {
	private int id;
	private String status;
	
	
	public banDTO(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public banDTO(String status) {
		super();
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
