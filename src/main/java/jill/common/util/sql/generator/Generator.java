package jill.common.util.sql.generator;

import jill.common.util.FilesUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jill W
 * @date 2020/12/14
 */
@Slf4j
public class Generator {


    public static void main(String[] args) {
        generateByConfigXml("/mybatis-generator/generatorConfig.xml");
    }

    public static void generateByConfigXml(String name) {
        List<String> warnings = new ArrayList<>();
        // File configFile = new File(Generator.class.getResource("/mybatis-generator/generatorConfig.xml").getPath());
        File configFile = FilesUtil.getResource(name);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        //读取配置文件
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException | XMLParserException e) {
            e.printStackTrace();
            log.error("generateByConfigXml.config.parser.fail");
        }
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = null;
        //生成生成器对象
        try {
            if (config != null) {
                myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            if (myBatisGenerator != null) {
                myBatisGenerator.generate(null);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        log.info("generate.end");
    }
}
