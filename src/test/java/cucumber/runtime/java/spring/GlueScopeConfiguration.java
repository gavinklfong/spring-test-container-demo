package cucumber.runtime.java.spring;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

// https://github.com/cucumber/cucumber-jvm/issues/965

@TestConfiguration
public class GlueScopeConfiguration {
    @Bean
    public CustomScopeConfigurer glueScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
//        customScopeConfigurer.addScope("cucumber-glue", new GlueCodeScope());
        return customScopeConfigurer;
    }
}
