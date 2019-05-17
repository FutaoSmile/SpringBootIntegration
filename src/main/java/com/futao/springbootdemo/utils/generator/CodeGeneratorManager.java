package com.futao.springbootdemo.utils.generator;

import com.futao.springbootdemo.utils.DateTools;
import com.lazyer.foundation.foundation.exception.ApplicationException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author futao
 * Created on 2019-03-08.
 */
public class CodeGeneratorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorManager.class);
    /**
     * 项目基础路径
     */
    private static final String PROJECT_BASE_PATH = System.getProperty("user.dir") + "/src/main/java/com/futao/springbootdemo";
    /**
     * 模板文件路径
     */
    private static final String TEMPLATE_FILE_PATH = PROJECT_BASE_PATH + "/utils/generator/template";
    /**
     * Entity生成路径
     */
    private static final String ENTITY_PATH = PROJECT_BASE_PATH + "/model/entity";
    /**
     * Controller路径
     */
    private static final String CONTROLLER_PATH = PROJECT_BASE_PATH + "/controller/business";
    /**
     * Service实现类生成路径
     */
    private static final String SERVICE_IMPL_PATH = PROJECT_BASE_PATH + "/service/impl";
    /**
     * Service接口生成路径
     */
    private static final String SERVICE_FACE_PATH = PROJECT_BASE_PATH + "/service";
    /**
     * Dao Mapper路径
     */
    private static final String DAO_PATH = PROJECT_BASE_PATH + "/dao";
    /**
     * Mybatis mapper xml文件路径
     */
    private static final String MYBATIS_MAPPER_XML_PATH = System.getProperty("user.dir") + "/src/main/resources/mybatis/mapper";


    private static Configuration configuration = null;

    private CodeGeneratorManager() {
    }

    /**
     * Freemarker 模板环境配置
     *
     * @return
     * @throws IOException
     */
    private Configuration initFreemarkerConfiguration() {
        Configuration cfg;
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        } catch (IOException e) {
            throw new RuntimeException("Freemarker 模板环境初始化异常!", e);
        }
        return cfg;
    }

    /**
     * 获取模板文件
     *
     * @return
     */
    private Template getTemplate(String templateName) {
        if (configuration == null) {
            configuration = initFreemarkerConfiguration();
        }
        try {
            return configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
            throw ApplicationException.ae(e.getMessage());
        }
    }

    /**
     * 生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
        CodeGeneratorManager codeGeneratorManager = new CodeGeneratorManager();

        Map<String, Object> dataMap = new HashMap<>(6);
        /*配置类名*/
        dataMap.put("className", "Review");
        /*配置类描述*/
        dataMap.put("classDesc", "评论");
        /*配置@author*/
        dataMap.put("authorName", "futao");


        dataMap.put("createDate", DateTools.dateToString(new Date(), DateTools.yyyyMMdd));
        dataMap.put("controllerPackagePath", getPackagePath(CONTROLLER_PATH));
        dataMap.put("entityPackagePath", getPackagePath(ENTITY_PATH));
        dataMap.put("serviceFacePackagePath", getPackagePath(SERVICE_FACE_PATH));
        dataMap.put("serviceImplPackagePath", getPackagePath(SERVICE_IMPL_PATH));
        dataMap.put("daoPackagePath", getPackagePath(DAO_PATH));

        Template entityTemplate = codeGeneratorManager.getTemplate("EntityTemplate.ftl");
        Template controllerTemplate = codeGeneratorManager.getTemplate("ControllerTemplate.ftl");
        Template serviceFaceTemplate = codeGeneratorManager.getTemplate("ServiceFaceTemplate.ftl");
        Template serviceImplTemplate = codeGeneratorManager.getTemplate("ServiceImplTemplate.ftl");
        Template daoTemplate = codeGeneratorManager.getTemplate("DaoTemplate.ftl");
        Template mapperTemplate = codeGeneratorManager.getTemplate("MapperTemplate.ftl");

        File entityFile = new File(ENTITY_PATH + "/" + dataMap.get("className") + ".java");
        File controllerFile = new File(CONTROLLER_PATH + "/" + dataMap.get("className") + "Controller.java");
        File serviceFaceFile = new File(SERVICE_FACE_PATH + "/" + dataMap.get("className") + "Service.java");
        File serviceImplFile = new File(SERVICE_IMPL_PATH + "/" + dataMap.get("className") + "ServiceImpl.java");
        File daoFile = new File(DAO_PATH + "/" + dataMap.get("className") + "Dao.java");
        File mapperFile = new File(MYBATIS_MAPPER_XML_PATH + "/" + dataMap.get("className") + "Mapper.xml");


        try {
            entityTemplate.process(dataMap, new FileWriter(entityFile));
            System.out.println(">>>> 实体类【" + dataMap.get("className") + "】生成成功");
            controllerTemplate.process(dataMap, new FileWriter(controllerFile));
            System.out.println(">>>> 控制器类【" + dataMap.get("className") + "Controller】生成成功");
            serviceFaceTemplate.process(dataMap, new FileWriter(serviceFaceFile));
            System.out.println(">>>> Service接口【" + dataMap.get("className") + "Service】生成成功");

            serviceImplTemplate.process(dataMap, new FileWriter(serviceImplFile));
            System.out.println(">>>> Service实现类【" + dataMap.get("className") + "ServiceImpl】生成成功");

            daoTemplate.process(dataMap, new FileWriter(daoFile));
            System.out.println(">>>> Dao接口【" + dataMap.get("className") + "Dao】生成成功");

            mapperTemplate.process(dataMap, new FileWriter(mapperFile));
            System.out.println(">>>> Mapper文件【" + dataMap.get("className") + "Mapper】生成成功");

            System.out.println(StringUtils.repeat("=", 20) + "执行完毕" + StringUtils.repeat("=", 20));
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据文件系统路径获取包路径
     *
     * @param fileSysPath 文件系统路径
     * @return
     */
    private static String getPackagePath(String fileSysPath) {
        return fileSysPath.substring(fileSysPath.indexOf("com")).replace("/", ".");
    }

}
