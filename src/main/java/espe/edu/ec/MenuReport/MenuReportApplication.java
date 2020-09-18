package espe.edu.ec.MenuReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class MenuReportApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MenuReportApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MenuReportApplication.class, args);
		System.out.println("MenuReport esta corriendo");
	}

}
