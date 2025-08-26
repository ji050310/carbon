package com.carbon.common.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.carbon.common.exception.CommonBizException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring-boot-plus代码生成器入口类
 *
 * @author Li Jun
 * @since 2021-06-10
 */
public class CodeGenerator {

    private static final String USER_NAME = "carbon_user";
    private static final String PASSWORD = "71&0925!jh#kd";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://123.60.111.33:3306/carbon?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";

    private static final String PARENT_PACKAGE = "com.carbon";
    private static final String SUPER_ENTITY = PARENT_PACKAGE + ".domain.common.BaseEntity";
    private static final String[] SUPER_ENTITY_COMMON_COLUMNS = new String[]{"creator_id","updated_id","updated_time","created_time"};
    private static final String SUPER_SERVICE = PARENT_PACKAGE + ".common.service.BaseService";
    private static final String SUPER_SERVICE_IMPL = PARENT_PACKAGE + ".common.service.BaseServiceImpl";

    private static final String PROJECT_PACKAGE_PATH = "com/carbon/";



    // ########################### 配置部分 start ############################
    // 主键数据库列名称
    private static final String PK_ID_COLUMN_NAME = "id";
    // 代码生成策略 true：All/false:SIMPLE
    private static final boolean GENERATOR_STRATEGY = true;
    // 分页列表查询是否排序 true：有排序参数/false：无
    private static final boolean PAGE_LIST_ORDER = false;
    // ############################ 配置部分 end ############################


    /**
     * 生成代码
     * @param moduleName 模块名，carbon-system
     * @param tableName 表名 carbon_methodology
     * @param author 创建人名称，对应生成文件的作者
     */
    public static void start(@NotNull String moduleName,@NotNull String tableName, String author) {
        String module = moduleName.split("-")[1];
        String superController = PARENT_PACKAGE +"."+ module + ".common.BaseController";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String modulePath;
        String projectPath = System.getProperty("user.dir");
        if (projectPath.contains(moduleName)){
            modulePath = projectPath;
        }else {
            modulePath = projectPath+"/"+moduleName;
        }

        gc.setOutputDir(modulePath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setSwagger2(true);               // 启用swagger注解
        gc.setIdType(IdType.AUTO);     // 主键类型:ID_WORKER
        gc.setServiceName("%sService");     // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setFileOverride(true);           // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DRIVER_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module);
        pc.setParent(PARENT_PACKAGE);
        //pc.setController("controller");

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("customField", "Hello " + this.getConfig().getGlobalConfig().getAuthor());
                // 查询参数包路径
                map.put("queryParamPath", PARENT_PACKAGE + "." + pc.getModuleName() + ".param." + underlineToPascal(tableName) + "QueryParam");
                // 查询参数共公包路径
                map.put("queryParamCommonPath", PARENT_PACKAGE + ".domain.common." + "QueryParam");
                // 响应结果包路径
                map.put("queryVoPath", PARENT_PACKAGE + "." + pc.getModuleName() + ".vo." + underlineToPascal(tableName) + "QueryVo");
                // 实体包路径
                map.put("entityPath", PARENT_PACKAGE + "." + pc.getModuleName() + ".entity." + underlineToPascal(tableName));
                // 实体对象名称
                map.put("entityObjectName", underlineToCamel(tableName));
                // service对象名称
                map.put("serviceObjectName", underlineToCamel(tableName) + "Service");
                // mapper对象名称
                map.put("mapperObjectName", underlineToCamel(tableName) + "Mapper");
                // 主键ID列名
                map.put("pkIdColumnName", PK_ID_COLUMN_NAME);
                // 主键ID驼峰名称
                map.put("pkIdCamelName", underlineToCamel(PK_ID_COLUMN_NAME));
                // 导入分页类
                map.put("paging", PARENT_PACKAGE + ".common.api.Paging");
                // 导入排序枚举
                map.put("orderEnum", PARENT_PACKAGE + ".common.enums.OrderEnum");
                // 分页列表查询是否排序
                map.put("pageListOrder", PAGE_LIST_ORDER);
                map.put("projectPackage", PARENT_PACKAGE);
                // 导入排序查询参数类
                map.put("orderQueryParamPath", PARENT_PACKAGE + StringPool.DOT + "domain.common." + "OrderQueryParam");
                // 代码生成策略
                map.put("generatorStrategy", GENERATOR_STRATEGY);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return modulePath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 自定义queryParam模板
        focList.add(new FileOutConfig("/templates/queryParam.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return modulePath + "/src/main/java/" + PROJECT_PACKAGE_PATH + pc.getModuleName() + "/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
            }
        });

        // 自定义entity模板
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return modulePath + "/src/main/java/" + PROJECT_PACKAGE_PATH + pc.getModuleName() + "/entity/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // 自定义queryVo模板
        focList.add(new FileOutConfig("/templates/queryVo.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return modulePath + "/src/main/java/" + PROJECT_PACKAGE_PATH + pc.getModuleName() + "/vo/" + tableInfo.getEntityName() + "QueryVo" + StringPool.DOT_JAVA;
            }
        });

        // 自定义rest模板
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return modulePath + "/src/main/java/" + PROJECT_PACKAGE_PATH + pc.getModuleName() +"/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });


        // 自定义service模板
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return modulePath + "/src/main/java/" + PROJECT_PACKAGE_PATH  + pc.getModuleName() +"/service/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null).setController(null).setEntity(null).setService(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(SUPER_ENTITY);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(superController);
        strategy.setSuperServiceClass(SUPER_SERVICE);
        strategy.setSuperServiceImplClass(SUPER_SERVICE_IMPL);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns(SUPER_ENTITY_COMMON_COLUMNS);
        strategy.setControllerMappingHyphenStyle(true);
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    public static String underlineToCamel(String underline) {
        if (StrUtil.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    public static String underlineToPascal(String underline) {
        if (StrUtil.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

}
