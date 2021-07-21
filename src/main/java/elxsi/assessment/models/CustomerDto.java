package elxsi.assessment.models;

import java.io.Serializable;

/**
 * @author bhaskarkumar
 *
 */
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Boolean status;
	private Long phone;

	public CustomerDto() {

	}

	public CustomerDto(Integer id, String name, String email, Boolean status, Long phone) {
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

}
