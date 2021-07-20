package elxsi.assessment.models;

/**
 * @author bhaskarkumar
 *
 */
public class CustomerDto {

	private Integer id;
	private String name;
	private String email;
	private Boolean status;
	private Double phone;

	public CustomerDto() {

	}

	public CustomerDto(Integer id, String name, String email, Boolean status, Double phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getPhone() {
		return phone;
	}

	public void setPhone(Double phone) {
		this.phone = phone;
	}

	

}
