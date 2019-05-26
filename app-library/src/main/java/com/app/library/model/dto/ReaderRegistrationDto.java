package com.app.library.model.dto;

import com.app.library.model.Address;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReaderRegistrationDto {

	@NotNull
	private Address address;

	@NotBlank
	@Length(max = 64)
	private String firstName;

	@NotBlank
	@Length(max = 64)
	private String surname;

	@NotBlank
	@Length(max = 11, min = 11)
	private String pesel;

	@NotBlank
	@Size(min = 1, max = 128)
	private String password;

	@NotBlank
	@Size(min = 1, max = 128)
	private String confirmPassword;

	@Email
	@NotBlank
	@Size(min = 1, max = 64)
	private String email;

	public Address getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getPesel() {
		return pesel;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public static class Builder {

		private Address address;
		private String firstName;
		private String surname;
		private String pesel;
		private String password;
		private String confirmPassword;
		private String email;

		public Builder setAddress(Address address) {
			this.address = address;

			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;

			return this;
		}

		public Builder setSurname(String surname) {
			this.surname = surname;

			return this;
		}

		public Builder setPesel(String pesel) {
			this.pesel = pesel;

			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;

			return this;
		}

		public Builder setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;

			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;

			return this;
		}

		public ReaderRegistrationDto build() {
			return new ReaderRegistrationDto(address, firstName, surname, pesel, password, confirmPassword, email);
		}
	}

	private ReaderRegistrationDto(@NotNull Address address, @NotBlank @Length(max = 64) String firstName, @NotBlank @Length(max = 64) String surname,
								 @NotBlank @Length(max = 11, min = 11) String pesel, @NotBlank @Size(min = 1, max = 128) String password,
								 @NotBlank @Size(min = 1, max = 128) String confirmPassword, @Email @NotBlank @Size(min = 1, max = 64) String email) {
		this.address = address;
		this.firstName = firstName;
		this.surname = surname;
		this.pesel = pesel;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
}
