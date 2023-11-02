package com.vow.mybatis;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: vow
 * @date: 2023/8/31 8:47
 * @description:
 */
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(Reader reader) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            Configuration configuration = parseConfiguration(document.getRootElement());
            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Configuration parseConfiguration(Element root) {
        Configuration configuration = new Configuration();
        configuration.setDatasource(datasource(root.selectNodes("//dataSource")));
        configuration.setConnection(connection(configuration.datasource));
        configuration.setMapperElement(mapperElement(root.selectNodes("mappers")));
        return configuration;
    }

    private Map<String, String> datasource(List<Element> list) {
        Map<String, String> datasource = new HashMap<>(4);
        Element element = list.get(0);
        List content = element.content();
        for (Object o : content) {
            Element e = (Element) o;
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            datasource.put(name, value);
        }
        return datasource;
    }

    private Connection connection(Map<String, String> datasource) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(datasource.get("url"), datasource.get("username"), datasource.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, XNode> mapperElement(List<Element> list) {
        Map<String, XNode> map = new HashMap<>();

        Element element = list.get(0);
        List content = element.content();
        for (Object o : content) {
            Element e = (Element) o;
            String resource = e.attributeValue("resource");

            try {
                Reader reader = Resources.getResourceAsReader(resource);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(new InputSource(reader));
                Element root = document.getRootElement();
                // 命名空间
                String namespace = root.attributeValue("namespace");

                // SELECT
                List<Element> selectNodes = root.selectNodes("select");
                for (Element node : selectNodes) {
                    String id = node.attributeValue("id");
                    String parameterType = node.attributeValue("parameterType");
                    String resultType = node.attributeValue("resultType");
                    String sql = node.getText();

                    // ? 匹配
                    Map<Integer, String> parameter = new HashMap<>();
                    Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                    Matcher matcher = pattern.matcher(sql);
                    for (int i = 1; matcher.find(); i++) {
                        String g1 = matcher.group(1);
                        String g2 = matcher.group(2);
                        parameter.put(i, g2);
                        sql = sql.replace(g1, "?");
                    }

                    XNode xNode = new XNode();
                    xNode.setNamespace(namespace);
                    xNode.setId(id);
                    xNode.setSql(sql);
                    xNode.setParameterType(parameterType);
                    xNode.setParameter(parameter);
                    xNode.setResultType(resultType);

                    map.put(namespace + "." + id, xNode);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return map;
    }
}
