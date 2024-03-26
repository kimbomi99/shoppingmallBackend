package com.example.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {

	@Autowired
	private JwtProp jwtProp;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {

		String username=request.getUsername();
		String password=request.getPassword();

		log.info("username : "+username);
		log.info("password : "+password);

		//사용자 권한
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_USER");
		roles.add("ROLE_ADMIN");

		byte[] signingKey = jwtProp.getSecretKey().getBytes();

		//토큰 생성
		String jwt=Jwts.builder().signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512 )
				.header().add("typ", SecurityConstants.TOKEN_TYPE)
				.and().expiration(new Date( System.currentTimeMillis()+ 1000*60*60*24*5 ))
				.claim("uid", username)
				.claim("rol", roles)
				.compact();

		log.info(jwt);

		return new ResponseEntity<String>(jwt, HttpStatus.OK);



	}
	//토큰 해석
	@GetMapping("/user/info")
	public ResponseEntity<?> userInfo(@RequestHeader(name="Authorization") String header){
		log.info("===== header =====");
		log.info("Authorization : " + header);

		//Authorization : Bearer ${jwt}
		String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");

		byte[] signingKey= jwtProp.getSecretKey().getBytes();

		//토큰 해석
		Jws<Claims> parsedToken = Jwts.parser().verifyWith( Keys.hmacShaKeyFor(signingKey) )
				.build().parseSignedClaims(jwt);

		log.info("paresedToken : " + parsedToken);

		//uid : user
		String username = parsedToken.getPayload().get("uid").toString();
		log.info("username : "+ username);

		//rol : [ROLE_USER, ROLE_ADMIN]
		Claims claims = parsedToken.getPayload();
		Object roles = claims.get("rol");
		log.info("roles : "+ roles);


		return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
	}
}
