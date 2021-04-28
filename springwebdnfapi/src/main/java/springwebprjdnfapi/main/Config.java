package springwebprjdnfapi.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springwebprjdnfapi.test.Api;

@Configuration
public class Config {
	
	@Bean
	public Test test() {
		return new Test();
	}
	
	@Bean
	public MemberRegistRequest favoriteOs() {
		return new MemberRegistRequest();
	}
	
	@Bean
	public Api api() {
		return new Api();
	}
}
