package com.tor.generator;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

    /*生成位置*/
    private final static String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\main\\java\\";
    private final static String PAGE = "com.tor.project";

    /*ORACLE*/

    /*private final static DbType DBTYPE = DbType.ORACLE;
    private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@192.168.1.2:1521:RLSBDB";
    private final static String USER_NAME = "spzpjkdba_jinhua";
    private final static String PASSWORD = "spzpjkdba_jinhua";*/

    /*MYSQL*/

    private final static DbType DBTYPE = DbType.MYSQL;
    private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://47.103.194.192:3306/ifast-new2?useUnicode=true&useSSL=false&characterEncoding=utf8&autoReconnect=true";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "root";

    /*要生成的表*/
    /*private final static String TABLE_NAME = "YW_YS";*/

    public static void main(String[] args) throws SQLException {
        while (true){
            generate();
        }
    }

    public static void generate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要生成的表名:");
        String tableName = sc.next();
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("Tzx") // 作者
                .setOutputDir(OUTPUT_DIR) // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                // IEmployeeService
                .setBaseResultMap(true)//生成基本的resultMap
                .setBaseColumnList(true);//生成基本的SQL片段

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DBTYPE)  // 设置数据库类型
                .setDriverName(DRIVER_NAME)
                .setUrl(URL)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD);

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setTablePrefix("YW_")
                .setInclude(tableName);  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(PAGE)
                .setMapper("mapper")//dao
                .setService("service")//servcie
                .setController("controller")//controller
                .setEntity("entity")
                .setXml("mapper");//mapper.xml

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}