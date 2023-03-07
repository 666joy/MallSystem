package cn.edu.scnu.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
@Configuration
@ConfigurationProperties("spring.datasource")
public class DataSourceInitConfig {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private Integer maxActive; //最大连接数
	private Integer maxIdle;   //最大空闲连接数
	private Integer initialSize; //初始化连接数量
	private Integer minIdle;   //最大空闲数
	//自定义创建的DruidDatasource对象返回给框架使用
	@Bean
	@Primary//当容器中存在多个同类对象时,以Primary所在的优先级最高
	public DataSource initDruidDataSource(){
		DruidDataSource datasource=new DruidDataSource();
		datasource.setDriverClassName(driverClassName);
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		//连接池初始化参数
		datasource.setInitialSize(initialSize);//5
		datasource.setMaxActive(maxActive);//200
		datasource.setMaxIdle(maxIdle);//8
		datasource.setMinIdle(minIdle);//3
		return datasource;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	public Integer getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	
}
