package com.keith.common.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * @author keith
 * @version 1.0
 * @date 2020-02-17
 **/
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments= StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 给字段添加swagger注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks=introspectedColumn.getRemarks();
        if(addRemarkComments&&StringUtility.stringHasValue(remarks)){
            addFieldJavaDoc(field, remarks);
            field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");
        }
        super.addFieldComment(field, introspectedTable, introspectedColumn);
    }

    /**
     * 给model的字段添加注释
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        field.addJavaDocLine("/**");
        //换行
        String[] remarkLines=remarks.split(System.getProperty("line.separator"));
        field.addJavaDocLine(" * "+remarkLines[0]);
        field.addJavaDocLine(" */");
    }
}
