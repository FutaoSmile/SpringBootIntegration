package com.futao.springbootdemo.apigen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.apigen.model.ApiController;
import com.futao.springbootdemo.apigen.model.ApiInfo;
import com.futao.springbootdemo.apigen.model.ApiMethod;
import com.futao.springbootdemo.apigen.model.ApiParameter;
import com.futao.springbootdemo.foundation.configuration.HttpMessageConverterConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * swagger数据加载
 *
 * @author futao
 * Created on 2019-05-15.
 */
@Slf4j
public class DataLoader {

    private static HashMap<String, ArrayList<ApiMethod>> arrayListHashMap = new HashMap<>();

    private static Map<String, String> definition;

    /**
     * 开始加载
     *
     * @param result
     * @return
     */
    @SneakyThrows
    public static ApiInfo loadData(String result) {
        arrayListHashMap = new HashMap<>();
        definition = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(result);
        loadDefinition(jsonObject);
        loadPath(jsonObject);
        ApiInfo apiInfo = loadInfo(jsonObject);
        FileUtils.writeStringToFile(new File("./666.json"), JSON.toJSONString(apiInfo, HttpMessageConverterConfiguration.SERIALIZER_FEATURES), StandardCharsets.UTF_8);
        return apiInfo;

    }

    /**
     * 加载info信息
     *
     * @param jsonObject
     * @return
     */
    private static ApiInfo loadInfo(JSONObject jsonObject) {
        String basePath = jsonObject.getString("basePath");
        String host = jsonObject.getString("host");
        JSONObject info = jsonObject.getJSONObject("info");
        String contact = info.getString("contact");
        String description = info.getString("description");
        String license = info.getString("license");
        String termsOfService = info.getString("termsOfService");
        String title = info.getString("title");
        String version = info.getString("version");
        return new ApiInfo(basePath, host, version, title, termsOfService, license, description, contact, loadController(jsonObject));
    }

    /**
     * 加载controller
     *
     * @param jsonObject
     * @return
     */
    private static ArrayList<ApiController> loadController(JSONObject jsonObject) {
        JSONArray tags = jsonObject.getJSONArray("tags");
        ArrayList<ApiController> apiControllers = new ArrayList<>(tags.size());
        tags.forEach(object -> {
            JSONObject tag = (JSONObject) object;
            ApiController apiController = new ApiController();
            String tagName = tag.getString("name");
            apiController.setTagName(tagName);
            String tagDesc = tag.getString("description");
            apiController.setTagDesc(tagDesc);
            //加载paths
            apiController.setApiMethodList(arrayListHashMap.get(tagName));
            apiControllers.add(apiController);
        });
        return apiControllers;
    }


    /**
     * 加载path信息
     *
     * @param jsonObject
     */
    private static void loadPath(JSONObject jsonObject) {
        JSONObject paths = jsonObject.getJSONObject("paths");
        paths.forEach((path, pathBody) -> {

            JSONObject pathBodyObject = (JSONObject) pathBody;
            String oneSupportMethodName = getOneSupportMethodName(pathBodyObject);
            JSONObject pathBodyObjectJSONObject = pathBodyObject.getJSONObject(oneSupportMethodName);
            String tags = pathBodyObjectJSONObject.getJSONArray("tags").getString(0);
            ArrayList<ApiMethod> apiMethods = arrayListHashMap.get(tags);
            if (apiMethods == null) {
                ArrayList<ApiMethod> apiMethodArrayList = new ArrayList<>();
                apiMethodArrayList.add(new ApiMethod(
                        pathBodyObject.keySet(),
                        pathBodyObjectJSONObject.getString("summary"),
                        pathBodyObjectJSONObject.getJSONArray("parameters") == null ? new ApiParameter[0] : loadParameter(pathBodyObject.getJSONObject(getOneSupportMethodName(pathBodyObject)).getJSONArray("parameters")),
                        null,
                        path,
                        pathBodyObjectJSONObject.getJSONArray("consumes").toJavaList(String.class),
                        pathBodyObjectJSONObject.getJSONArray("produces").toJavaList(String.class)));
                arrayListHashMap.put(tags, apiMethodArrayList);
            } else {
                apiMethods.add(new ApiMethod(
                        pathBodyObject.keySet(),
                        pathBodyObjectJSONObject.getString("summary"),
                        pathBodyObjectJSONObject.getJSONArray("parameters") == null ? new ApiParameter[0] : loadParameter(pathBodyObject.getJSONObject(getOneSupportMethodName(pathBodyObject)).getJSONArray("parameters")),
                        null,
                        path,
                        pathBodyObjectJSONObject.getJSONArray("consumes").toJavaList(String.class),
                        pathBodyObjectJSONObject.getJSONArray("produces").toJavaList(String.class)));
            }
        });
    }

    /**
     * 加载请求参数
     *
     * @param jsonArray
     * @return
     */
    private static ApiParameter[] loadParameter(JSONArray jsonArray) {
        if (jsonArray != null) {
            ApiParameter[] apiParameters = new ApiParameter[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                String name = jsonArray.getJSONObject(i).getString("name");
                String description = jsonArray.getJSONObject(i).getString("description");
                Boolean required = jsonArray.getJSONObject(i).getBoolean("required");
                String type = jsonArray.getJSONObject(i).getString("type");
                JSONObject schema = jsonArray.getJSONObject(i).getJSONObject("schema");
                if (schema != null) {
//                    String $ref = schema.getString("$ref");
//                    String modelName = $ref.substring($ref.lastIndexOf("/"));
//                    type = modelName + ":" + definition.get(modelName);
                }
                apiParameters[i] = new ApiParameter(name, type, required, description);
            }
            return apiParameters;
        }
        return new ApiParameter[0];
    }


    /**
     * 加载定义信息
     *
     * @param jsonObject
     */
    private static void loadDefinition(JSONObject jsonObject) {
        JSONObject definitions = jsonObject.getJSONObject("definitions");
        Map<String, String> map = new HashMap<>(jsonObject.size());
        definitions.forEach((k, v) -> {
            JSONObject properties = ((JSONObject) v).getJSONObject("properties");
            if (properties != null) {
                JSONObject current = new JSONObject(properties.size());
                map.put(k, JSON.toJSONString(current, HttpMessageConverterConfiguration.SERIALIZER_FEATURES));
                properties.forEach((pro, desc) -> current.put(pro, ((JSONObject) desc).getString("type")));
            }
        });
        definition = map;
    }

    /**
     * 获取支持的一个method
     *
     * @param jsonObject
     * @return
     */
    private static String getOneSupportMethodName(JSONObject jsonObject) {
        String methodName = "";
        for (String s : jsonObject.keySet()) {
            methodName = s;
        }
        return methodName;
    }

}
