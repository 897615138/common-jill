package jill.commonspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//		(exclude = DataSourceAutoConfiguration.class)
public class CommonSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSpringApplication.class, args);
	}

}
