package com.futao.springmvcdemo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.model.system.ErrorMessageFields;
import com.futao.springmvcdemo.service.StatisticService;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author futao
 * Created on 2018-12-18.
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    /**
     * 获取程序中定义的错误码
     *
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public List<ErrorMessageFields> getErrorMessages() throws IllegalAccessException {
        Field[] fields = ErrorMessage.class.getFields();
        List<ErrorMessageFields> errorMessageFieldsList = new ArrayList<>(fields.length);
        for (Field it : fields) {
            errorMessageFieldsList.add(new ErrorMessageFields(it.getName(), it.get(ErrorMessage.class).toString()));
        }
        return errorMessageFieldsList;
    }

    /**
     * 获取程序中的枚举值
     *
     * @return
     */
    @Override
    public Map<String, JSONArray> enumList() {
        Reflections reflections = new Reflections("com.futao.springmvcdemo.model.enums");
        Set<Class<? extends Enum>> classSet = reflections.getSubTypesOf(Enum.class);
        Map<String, JSONArray> map = new HashMap<>(classSet.size());
        classSet.forEach(enumClass -> {
            JSONArray jsonArray = new JSONArray();
            map.put(enumClass.getSimpleName(), jsonArray);
            Arrays.stream(enumClass.getEnumConstants()).forEach(enumField -> {
                JSONObject kv = new JSONObject();
                jsonArray.add(new JSONObject().fluentPut(enumField.name(), kv));
                Arrays.stream(enumField.getClass().getDeclaredFields())
                        .filter(allField -> (!allField.isEnumConstant()) && !"$VALUES".equals(allField.getName()))
                        .forEach(field -> {
                            try {
                                field.setAccessible(true);
                                kv.fluentPut(field.getName(), field.get(enumField));

                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        });
            });

        });
        return map;
    }
}
