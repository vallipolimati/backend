package com.tritern.evozspecial;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.tritern.evozspecial", "com.tritern.evozspecial.entity.EvozEntity" })
//@ComponentScan({"com.tritern.evozspecial.entity.EvozEntity"})

public class EvozspecialApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvozspecialApplication.class, args);
	}

	@Component
	class CorsFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,

				final FilterChain filterChain) throws ServletException, IOException {

			response.addHeader("Access-Control-Allow-Origin", "*");

			response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");

			response.addHeader("Access-Control-Allow-Headers",

					"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

			response.addHeader("Access-Control-Expose-Headers",

					"Access-Control-Allow-Origin, Access-Control-Allow-Credentials");

			response.addHeader("Access-Control-Allow-Credentials", "true");

			response.addIntHeader("Access-Control-Max-Age", 10);

			filterChain.doFilter(request, response);

		}

	}

}
