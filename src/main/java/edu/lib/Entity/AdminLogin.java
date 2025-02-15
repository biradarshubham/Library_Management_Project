package edu.lib.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class AdminLogin {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNo;
	private String password;
	private String confirmPassword;
}
