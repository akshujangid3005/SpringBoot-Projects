package com.akshay.blog.payloads;



import org.hibernate.validator.constraints.NotEmpty;

import jakarta.validation.constraints.Email;
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
	@Size(min=4,max=15, message = "User name must be min 4 and max 15 characters !!")
	private String name;
	
	@Email(message = "email address not valid ")
	private String email;
	
	@NotEmpty
	@Size(min=5,message ="password min 5 character" )
	private String password;
	
	@NotEmpty
	private String about;
	
}
