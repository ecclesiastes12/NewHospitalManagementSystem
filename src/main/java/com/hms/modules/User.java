package com.hms.modules;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@JsonPropertyOrder({"id","first_name","last_name","maiden_name","username","email","date_of_birth","phone_number"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("first_name")
	@Column(length = 45)
	@Length(min = 2, max = 45)
	private String firstName;
	
	@JsonProperty("last_name")
	@Column(length = 45)
	@Length(min = 2, max = 45)
	private String lastName;
	
	@Column(length = 45)
	@Length(min = 2, max = 45)
	@JsonProperty("maiden_name")
	private String maidenName;
	
	@Column(name="username", length = 45)
	@Length(min = 2, max = 45)
	private String userName;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable = false, length = 128)
	private String password;
	
	@JsonProperty("date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column(length = 14)
	@JsonProperty("phone_number")
	private String phoneNumber;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
//    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
//    @JsonBackReference
//    private Role role;
	
	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	Set<Role> roles = new HashSet();

	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, String maidenName, String userName, String email,
			String password, LocalDate dateOfBirth, String phoneNumer, Set<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.maidenName = maidenName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumer;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPhoneNumer() {
		return phoneNumber;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumber = phoneNumer;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	//method to add role to user
//	public void addRole(Role role) {
//		this.roles.add(role);
//	}
	
	public void addRole(Role role) {
	    if (role != null) {
	        this.roles.add(role);
	    }
	}


	
		//builder methods
	public User id(Long id) {
		setId(id);
		return this;
	}
	public User firstName(String firstName) {
		setFirstName(firstName);
		return this;
	}
	
	public User lastName(String lastName) {
		setLastName(lastName);
		return this;
	}
	
	public User maidenName(String maidenName) {
		setMaidenName(maidenName);
		return this;
	}
	
	public User userName(String userName) {
		setUsername(userName);
		return this;
	}
	
	public User password(String password) {
		setPassword(password);
		return this;
	}
	
	
	public User email(String email) {
		setEmail(email);
		return this;
	}
	
	public User dateOfBirth(LocalDate dob) {
		setDateOfBirth(dob);
		return this;
	}
	
	public User phoneNumber(String phoneNumber) {
		setPhoneNumer(phoneNumber);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
//	public User roles(Role role) {
//	    if (this.roles == null) {
//	        this.roles = new HashSet<>();
//	    }
//	    this.roles.add(role.getRoleId());
//	    return this;
//	}

	
	
	
	
}
