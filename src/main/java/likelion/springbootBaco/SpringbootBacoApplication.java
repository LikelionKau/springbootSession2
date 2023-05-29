package likelion.springbootBaco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBacoApplication.class, args);
	}

//	@Bean
//	Hibernate5JakartaModule hibernate5JakartaModule() {
//		Hibernate5JakartaModule hibernate5JakartaModule = new Hinernate5JakartaModule();
//		hibernate5JakartaModule.configure(Hibernate5JakarteModule.Feature.FORCE_LAZY_LOADING, true);
//		return hibernate5JakartaModule;
//	}

}
