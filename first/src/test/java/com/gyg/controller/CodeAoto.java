package com.gyg.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeAoto {

	@Test
	public void create() {
		// 代码生成器
		com.baomidou.mybatisplus.generator.AutoGenerator mpg = new com.baomidou.mybatisplus.generator.AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("gyg");
		gc.setIdType(IdType.AUTO); //主键策略
		gc.setOpen(false); //生成后是否打开资源管理器
		gc.setFileOverride(false); //重新生成时文件是否覆盖
		gc.setServiceName("%sService");    //去掉Service接口的首字母I
		gc.setOpen(false);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/gyg_user?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setDbType(DbType.MYSQL);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName("user");
		pc.setParent("com.gyg");
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setService("service");
		pc.setMapper("mapper");
		mpg.setPackageInfo(pc);


		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//		strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
//		strategy.setSuperEntityColumns("id");
		strategy.setInclude("user");
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.execute();
	}

}