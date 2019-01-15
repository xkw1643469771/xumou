package mybatis.generator.config;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.ModelType;

/**
 *
 */
public class MyBatisConfig {

    public static Configuration getMybatisConfig() {
        Configuration config = new Configuration();
        Context context = new Context(ModelType.FLAT);
        JDBCConnectionConfiguration jdbcConifg = new JDBCConnectionConfiguration();
        context.setJdbcConnectionConfiguration(jdbcConifg);
        config.addContext(context);
        return config;
    }
}