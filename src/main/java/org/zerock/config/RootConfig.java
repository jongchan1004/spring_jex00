package org.zerock.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"org.zerock.sample"})
@MapperScan(basePackages = {"org.zerock.mapper"})
public class RootConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		//hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		//hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:XE");
		
		hikariConfig.setUsername("book_ex");
		hikariConfig.setPassword("book_ex");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	
//	@Bean
//	public HikariConfig hikariConfig() {
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
//		hikariConfig.setUsername("book_ex");
//		hikariConfig.setPassword("book_ex");
//		
//		return hikariConfig;
//	}
//	
//	@Bean(destroyMethod = "close")
//	public DataSource dataSource(HikariConfig hikariConfig) { //injection
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//		
//		return dataSource;
//	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		
		return bean;
	}
	
}
