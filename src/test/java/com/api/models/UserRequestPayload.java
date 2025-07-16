package com.api.models;


public class UserRequestPayload {
	private String name;
	private String username;
	private String email;
	private UserAddressPayload address;
	private String phone;
	private String website;
	private UserCompanyPayload company;
	
	public UserRequestPayload() {
		super();
	}
	
	public UserRequestPayload(String name, String username, String email, UserAddressPayload address, String phone, String website,
			UserCompanyPayload company) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAddressPayload getAddress() {
		return address;
	}

	public void setAddress(UserAddressPayload address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public UserCompanyPayload getCompany() {
		return company;
	}

	public void setCompany(UserCompanyPayload company) {
		this.company = company;
	}
	
	
	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", username=" + username + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", website=" + website + ", company=" + company + "]";
	}	
	
}
