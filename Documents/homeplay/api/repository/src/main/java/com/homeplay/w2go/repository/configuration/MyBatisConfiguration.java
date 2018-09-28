package om.homeplay.w2go.repository.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.homeplay.w2go.repository")
public class MyBatisConfiguration {

    private DataSource dataSource;

    @Autowired

    public MyBatisConfiguration(@Qualifier("dataSource") DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}
