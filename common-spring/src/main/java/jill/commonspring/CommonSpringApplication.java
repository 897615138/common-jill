package jill.commonspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//		(exclude = DataSourceAutoConfiguration.class)
@MapperScan("jill/commonspring/mapper")
public class CommonSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSpringApplication.class, args);
	}

}
