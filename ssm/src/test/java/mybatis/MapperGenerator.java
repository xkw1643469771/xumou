package mybatis;

import mybatis.generator.config.MyBatisConfig;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MapperGenerator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        /*//指定 逆向工程配置文件
        String resource = ClassLoader.getSystemClassLoader().getResource("").getPath();
        File rootFile = new File(resource).getParentFile().getParentFile();
        System.out.println(rootFile);
        Configuration c = new Configuration();
        Context con = new Context(ModelType.FLAT);
        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
        jdbcConfig.setDriverClass("org.h2.Driver");
        jdbcConfig.setConnectionURL("jdbc:h2:d:/h2/ssm");
        jdbcConfig.setUserId("root");
        jdbcConfig.setPassword("root");
        con.setJdbcConnectionConfiguration(jdbcConfig);
        SqlMapGeneratorConfiguration sqlMap = new SqlMapGeneratorConfiguration();
        sqlMap.setTargetPackage("mapper");
        sqlMap.setTargetProject("/src/main/resources");
        con.setSqlMapGeneratorConfiguration(sqlMap);
        c.addContext(con);*/
        File configFile = new File("ssm/src/test/resources/generatorconfig.xml");
        System.out.println(configFile.getAbsolutePath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = MyBatisConfig.getMybatisConfig();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}