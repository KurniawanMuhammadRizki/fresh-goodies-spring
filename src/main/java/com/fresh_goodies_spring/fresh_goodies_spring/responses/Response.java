package com.fresh_goodies_spring.fresh_goodies_spring.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class Response<T> {
    private int statusCode;
    private String message;
    boolean success = false;
    private T data;

    public Response(int statCode, String statusDesc){
        statusCode = statCode;
        message = statusDesc;

        if(statCode == HttpStatus.OK.value()){
            success = true;
        }
    }


}
