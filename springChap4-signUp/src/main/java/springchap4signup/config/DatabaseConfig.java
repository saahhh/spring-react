package springchap4signup.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

//데이터 베이스에 테이블이 없을 경우 테이블 생성
@Configuration

public class DatabaseConfig {
	
	// DataSourceInitializer 객체는 데이터베이스 초기화를 수행할 수 있는 데이터 소스와 populator를 설정해주는 것이 작성되어 있음
	// databasePopulator : 데이터베이스를 초기화하는데 사용
	// addScript : 데이터베이스를 초기화하고 추가할 스크립트 파일을 추가
	// sql/create-members-table.sql : sql에 추가할 DDL문을 작성해서 넣어줌
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}
	
	private ResourceDatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("sql/create-members-table.sql"));
		return populator;
	}
}
