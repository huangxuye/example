package com.wxuy.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wxuy.example.mapper.tLog", sqlSessionFactoryRef = "TLogSqlSessionFactory")
public class TLogDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean(name = "TLogDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.tlog")
    public DataSource getSecondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "TLogSqlSessionFactory")
    public SqlSessionFactory tLogSqlSessionFactory(@Qualifier("TLogDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //mybatis需要设置spring boot vsf
        bean.setVfs(SpringBootVFS.class);
        bean.setDataSource(datasource);
        bean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/tLog/*.xml"));
        return bean.getObject();// 设置mybatis的xml所在位置
    }

    @Bean("TLogSqlSessionTemplate")
    public SqlSessionTemplate tLogSqlSessionTemplate(
            @Qualifier("TLogSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
