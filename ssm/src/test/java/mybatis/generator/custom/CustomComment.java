package mybatis.generator.custom;

import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.JDBCConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 自定义注解生成
 */
public class CustomComment extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String comment = "";
        try {
            ConnectionFactory connectionFactory = new JDBCConnectionFactory(introspectedColumn.getContext().getJdbcConnectionConfiguration());
            Connection connection = connectionFactory.getConnection();
            String sql = "SELECT T.COMMENTS FROM ALL_COL_COMMENTS T WHERE T.TABLE_NAME = ? AND T.COLUMN_NAME = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, introspectedTable.getTableConfiguration().getTableName());
            ps.setString(2, introspectedColumn.getActualColumnName());
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                comment = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder javaDoc = new StringBuilder();
        javaDoc.append("/** ");
        javaDoc.append(comment.trim());
        javaDoc.append(" */");
        field.addJavaDocLine(javaDoc.toString());
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String className = topLevelClass.getType().getShortName();
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n");
        sb.append(" * @方法说明 自动生成的模型类, 和表 ").append(tableName).append(" 的字段一一对应.\n");
        sb.append(" * 此表仅用于逆向生成, 不要修改, 以免修改的内容被覆盖. 如果要扩展字段, 在扩展类 ").append(className).append("Ext 中添加.\n");
        sb.append(" * @data ").append(format.format(Calendar.getInstance().getTime())).append("\n");
        sb.append(" * @auther mybatis generator \n");
        sb.append(" */");
        topLevelClass.addJavaDocLine(sb.toString());
    }

}
