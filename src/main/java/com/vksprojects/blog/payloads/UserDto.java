package com.vksprojects.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	
	@NotEmpty
	@Size(min = 4, message = "name must be mre than 4 character")
	private String name;
	
	@NotEmpty
	@Size(min = 4, max = 15, message = "must in between 4 - 15 character")
	private String password;
	
	@Email(message = "email is not valid")
	private String email;
	
	@NotEmpty
	private String about;
	

}
