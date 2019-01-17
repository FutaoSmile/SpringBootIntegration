package com.futao.springmvcdemo.controller.business;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.annotation.Role;
import com.futao.springmvcdemo.annotation.impl.interceptor.RequestLogInterceptor;
import com.futao.springmvcdemo.annotation.listener.OnlineHttpSessionListener;
import com.futao.springmvcdemo.controller.SentinelAnn;
import com.futao.springmvcdemo.model.entity.ApiControllerDescription;
import com.futao.springmvcdemo.model.entity.SingleValueResult;
import com.futao.springmvcdemo.model.entity.SystemInformation;
import com.futao.springmvcdemo.model.enums.UserRoleEnum;
import com.futao.springmvcdemo.model.system.ErrorMessageFields;
import com.futao.springmvcdemo.service.StatisticService;
import com.futao.springmvcdemo.service.notbusiness.I18nService;
import com.futao.springmvcdemo.service.notbusiness.SentinelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * Created on 2018/10/11.
 * 统计
 */
@Api("统计接口")
@RestController
@RequestMapping(path = "statistic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StatisticController {

    @Resource
    private OnlineHttpSessionListener onlineHttpSessionListener;

    @Resource
    private RequestLogInterceptor requestLogInterceptor;

    @Resource
    private StatisticService statisticService;

    /**
     * 获取当前在线人数
     *
     * @return
     */
    @ApiOperation("统计当前在线人数")
    @Role(UserRoleEnum.ADMIN)
    @GetMapping("onlinePeopleQuantity")
    public SingleValueResult onlinePeopleQuantity() {
        return new SingleValueResult(onlineHttpSessionListener.getOnlinePeopleQuantity().get());
    }

    /**
     * 统计接口的请求次数
     *
     * @return
     */
    @ApiOperation("统计接口的请求次数")
    @GetMapping("apiRequest")
    public ConcurrentHashMap<String, AtomicInteger> apiRequest() {
        return requestLogInterceptor.getApiRequestStatistic();
    }

    /**
     * 统计所有的错误码
     *
     * @return
     */
    @SentinelAnn(resource = "niubi")
    @ApiOperation("统计所有的错误码")
    @GetMapping("errorMessages")
    public List<ErrorMessageFields> errorMessages() throws IllegalAccessException {
        List<ErrorMessageFields> errorMessages = statisticService.getErrorMessages();
        System.out.println(JSONObject.toJSONString(errorMessages));
        return errorMessages;
    }

    /**
     * 统计所有的枚举类信息
     *
     * @return
     */
    @ApiOperation("统计所有的枚举类信息")
    @GetMapping("enumList")
    @SentinelResource(value = "STATISTIC_ENUM")
    public Map<String, JSONArray> enumList() {
        return statisticService.enumList();
    }


    /**
     * 统计系统中所有的api
     *
     * @return
     */
    @ApiOperation("统计系统中所有的api")
    @GetMapping(path = "apiList")
    @SentinelResource(value = "STATISTIC_API_LIST", blockHandler = "handler", blockHandlerClass = {SentinelService.class})
    public ArrayList<ApiControllerDescription> apiList() throws InterruptedException {
        System.out.println(I18nService.getMessage("welcome"));
        System.out.println(I18nService.getMessage("test"));
        return statisticService.apiList();
    }


    @Resource
    private SystemInformation systemInformation;

    /**
     * 系统版本信息
     *
     * @return
     */
    @ApiOperation("系统版本信息")
    @GetMapping("systemInformation")
    public SystemInformation systemInformation() {
        return systemInformation;
    }
}
