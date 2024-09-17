package com.thc.realspr.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.thc.realspr.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

    // SqlSessionFactory를 설정하는 메서드
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // DataSource를 설정 (데이터베이스 연결을 위해)
        sqlSessionFactoryBean.setDataSource(dataSource);

        // DTO 패키지 경로 설정
        sqlSessionFactoryBean.setTypeAliasesPackage("com.thc.realspr.dto");

        // MyBatis XML 매퍼 파일 경로 설정
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));

        // SqlSessionFactoryBean 객체에서 SqlSessionFactory를 생성하여 반환
        return sqlSessionFactoryBean.getObject();
    }
}
