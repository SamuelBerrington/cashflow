package cashflow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
public class DemoApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
