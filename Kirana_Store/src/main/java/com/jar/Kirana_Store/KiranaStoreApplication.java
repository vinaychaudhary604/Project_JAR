package com.jar.Kirana_Store;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.annotation.ComponentScan;  

@SpringBootApplication
@ComponentScan(basePackages = "com.jar.Kirana_Store")
public class KiranaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaStoreApplication.class, args);
	}

}
