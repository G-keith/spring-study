package com.keith.common.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author keith
 * @version 1.0
 * @date 2020-02-17
 **/
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
//        topLevelClass.addImportedType("lombok.Builder");
//        topLevelClass.addImportedType("lombok.NoArgsConstructor");
//        topLevelClass.addImportedType("lombok.AllArgsConstructor");

        //添加domain的注解
        String remarks=introspectedTable.getRemarks();
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@ApiModel(description = \""+remarks+"\")");
//        topLevelClass.addAnnotation("@Builder");
//        topLevelClass.addAnnotation("@NoArgsConstructor");
//        topLevelClass.addAnnotation("@AllArgsConstructor");

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* @author keith");
        topLevelClass.addJavaDocLine("* @version 1.0");
        topLevelClass.addJavaDocLine("* @date " + date2Str(new Date()));
        topLevelClass.addJavaDocLine("*/");

        return true;
    }

    /**
     * 给实体字段添加swagger注释
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            addFieldJavaDoc(field, remarks);
            field.addJavaDocLine("@ApiModelProperty(value = \"" + remarks + "\" )");
        }
        addFieldJavaDoc(field,remarks);
        return true;
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

    /**
     * 给dao添加注释
     */
    @Override
    public boolean clientGenerated(Interface interfaze,IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType importedType1=new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
        FullyQualifiedJavaType importedType2=new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param");
        Set<FullyQualifiedJavaType> set=new HashSet<FullyQualifiedJavaType>();
        set.add(importedType1);
        set.add(importedType2);
        interfaze.addImportedTypes(set);
        interfaze.addAnnotation("@Repository");

        //todo Mapper添加作者时间
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" *  @author keith");
        interfaze.addJavaDocLine(" *  @version 1.0");
        interfaze.addJavaDocLine(" * @date " + date2Str(new Date()));
        interfaze.addJavaDocLine(" **/");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
