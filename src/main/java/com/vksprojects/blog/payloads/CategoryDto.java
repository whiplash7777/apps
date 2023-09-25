package com.vksprojects.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	private Integer categoryId;
	
	@NotBlank(message = "please provide title")
	private String categoryTitle;
	
	
	@NotBlank(message = "description cant be blank")
	private String categoryDescription;

}
