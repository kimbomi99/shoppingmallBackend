package com.example.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
@ConfigurationProperties("com.example")
public class JwtProp {

	//com.example.secret-key
	private String secretKey;


}
