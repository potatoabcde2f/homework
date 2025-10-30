package com.my.blog.website;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/** 核心应用启动类，博客网站的主入口，使用Spring Boot框架
 * 继承SpringBootServletInitializer以支持war包部署到外部容器
 */
@MapperScan("com.my.blog.website.dao") // 扫描指定包路径下的MyBatis Mapper接口
@SpringBootApplication // 标记为Spring Boot应用，包含@Configuration、@EnableAutoConfiguration、@ComponentScan
@EnableTransactionManagement // 启用注解式事务管理
public class CoreApplication extends SpringBootServletInitializer {

    /**配置Spring应用构建器，用于支持外部Servlet容器部署
     * @param builder Spring应用构建器， @return 配置好的Spring应用构建器
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    /** 配置Druid数据源Bean
     * 使用@ConfigurationProperties将application.properties中spring.datasource前缀的配置注入到数据源
     * initMethod和destroyMethod指定初始化和销毁时调用的方法
     * @return Druid数据源实例
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**配置MyBatis的SqlSessionFactory
     * 用于创建SqlSession实例，是MyBatis的核心接口
     * @return SqlSessionFactory实例
     * @throws Exception 可能抛出的异常
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        // 资源模式解析器，用于解析类路径下的资源文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource());
        // 设置Mapper XML文件的位置，支持通配符匹配
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    /**配置事务管理器
     * 用于管理数据库事务，确保数据一致性
     * @return 平台事务管理器实例
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        // 基于数据源的事务管理器
        return new DataSourceTransactionManager(dataSource());
    }

    /**应用主入口方法
     * 启动Spring Boot应用
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}