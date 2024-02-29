package com.vow.mybatis.builder;

import com.vow.mybatis.mapping.ResultMap;
import com.vow.mybatis.mapping.ResultMapping;

import java.util.List;

/**
 * @author: vow
 * @date: 2024/2/29 14:36
 * @description:
 */
public class ResultMapResolver {

    private final MapperBuilderAssistant assistant;
    private String id;
    private Class<?> type;
    private List<ResultMapping> resultMappings;

    public ResultMapResolver(MapperBuilderAssistant assistant, String id, Class<?> type, List<ResultMapping> resultMappings) {
        this.assistant = assistant;
        this.id = id;
        this.type = type;
        this.resultMappings = resultMappings;
    }

    public ResultMap resolve() {
        return assistant.addResultMap(this.id, this.type, this.resultMappings);
    }
}
