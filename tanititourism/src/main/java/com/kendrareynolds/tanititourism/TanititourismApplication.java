package com.kendrareynolds.tanititourism;

import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TanititourismApplication {

	public static void main(String[] args) {




		SpringApplication.run(TanititourismApplication.class, args);
	}

}
