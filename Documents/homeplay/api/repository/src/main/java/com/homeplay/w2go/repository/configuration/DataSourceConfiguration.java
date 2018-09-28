package om.homeplay.w2go.repository.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfiguration {


    @Value("${w2go.postgresql.datasource.url}")
    private String jdbcUrl;
    @Value("${w2go.postgresql.datasource.user-name}")
    private String jdbcUserName;
    @Value("${w2go.postgresql.datasource.password}")
    private String jdbcPassword;

//    @Profile("postgres-remote")
    @Bean("dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://35.240.141.73:5432/knongdai_db");
        System.out.println("jdbc"+ jdbcUrl);
        driverManagerDataSource.setUsername("knongdai");
        driverManagerDataSource.setPassword("knongdai!@#");
        return driverManagerDataSource;
    }
}
