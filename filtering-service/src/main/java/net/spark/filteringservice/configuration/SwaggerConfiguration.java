package net.spark.filteringservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

  @Value("${swagger.api.title}")
  private String title;

  @Value("${swagger.api.description}")
  private String description;

  @Value("${swagger.api.version}")
  private String version;

  @Value("${swagger.api.terms}")
  private String terms;

  @Value("${swagger.api.developer.name}")
  private String name;

  @Value("${swagger.api.developer.url}")
  private String url;

  @Value("${swagger.api.developer.email}")
  private String email;

  @Value("${swagger.api.license}")
  private String license;

  @Value("${swagger.api.licenseUrl}")
  private String licenseUrl;

  @Value("${swagger.api.basePackage}")
  private String basePackage;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(title)
        .description(description)
        .termsOfServiceUrl(terms)
        .contact(new Contact(name, url, email))
        .license(license)
        .licenseUrl(licenseUrl)
        .version(version)
        .build();
  }
}
