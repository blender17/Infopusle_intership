package model;

import java.time.LocalDate;
import java.util.Objects;

/*
	Example POJO without Lombok
*/


public abstract class User {

	Long id;
	String firstName;
	String middleName;
	String lastName;
	LocalDate birthDate;
	Character gender;
	String status;
	String address;

	public User(Long id,
	            String firstName,
	            String middleName,
	            String lastName,
	            LocalDate birthDate,
	            Character gender,
	            String status,
	            String address) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.status = status;
		this.address = address;
	}

	public User() {
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", middleName='" + middleName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate=" + birthDate +
				", gender=" + gender +
				", status='" + status + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof User user)) {
			return false;
		}

		return id.equals(user.id)
				&& firstName.equals(user.firstName)
				&& middleName.equals(user.middleName)
				&& lastName.equals(user.lastName)
				&& birthDate.equals(user.birthDate)
				&& gender.equals(user.gender)
				&& status.equals(user.status)
				&& address.equals(user.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, middleName, lastName, birthDate, gender, status, address);
	}


}
