package org.jsp.event.responsestructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseStructure<T> {
	
	
	
	private int httpStatus;
	private String message;
	private T body;
	
	

}
