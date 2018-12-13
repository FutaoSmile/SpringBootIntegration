package com.futao.springmvcdemo.annotation.impl.interceptor;

import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.utils.TimeUtilsKt;
import org.joda.time.DateTime;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * @author futao
 * Created on 2018/9/18-15:15.
 */
@SpringBootConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private SignInterceptor signInterceptor;
    @Resource
    private LoginUserInterceptor loginUserInterceptor;
    @Resource
    private RequestLogInterceptor requestLogInterceptor;

    /**
     * 添加拦截器
     * addInterceptor()的顺序需要严格按照程序的执行的顺序
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLogInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**");
        //  "/**"和"/*"是有区别的
        registry.addInterceptor(signInterceptor).addPathPatterns("/**");
    }

    /**
     * Helps with configuring HandlerMappings path matching options such as trailing slash match,
     * suffix registration, path matcher and path helper.
     * Configured path matcher and path helper instances are shared for:
     * <ul>
     * <li>RequestMappings</li>
     * <li>ViewControllerMappings</li>
     * <li>ResourcesMappings</li>
     * </ul>
     *
     * @param configurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    /**
     * Configure content negotiation options.
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    /**
     * Configure asynchronous request handling options.
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**
     * Configure a handler to delegate unhandled requests by forwarding to the
     * Servlet container's "default" servlet. A common use case for this is when
     * the DispatcherServlet is mapped to "/" thus overriding the
     * Servlet container's default handling of static resources.
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    /**
     * 不起作用，无效
     * <p>
     * Add Converter and {@link Formatter}s in addition to the ones
     * registered by default.
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        Formatter<Timestamp> timestampFormatter = new Formatter<Timestamp>() {
            @Override
            public Timestamp parse(String text, Locale locale) throws ParseException {
                System.out.println("parse 时间转换器");
                DateTime dateTime = TimeUtilsKt.toDateTime(text);
                return TimeUtilsKt.toTimestamp(dateTime);
            }

            @Override
            public String print(Timestamp object, Locale locale) {
                System.out.println("print 时间转换器");
                return null;
            }
        };
        registry.addFormatter(timestampFormatter);
        registry.addFormatterForFieldType(Timestamp.class, timestampFormatter);

        Formatter<User> userFormatter = new Formatter<User>() {
            @Override
            public User parse(String text, Locale locale) throws ParseException {
                System.out.println("=================");
                return null;
            }

            @Override
            public String print(User object, Locale locale) {
                System.out.println("-------------");
                return null;
            }
        };
        registry.addFormatter(userFormatter);
    }

    /**
     * 添加静态资源映射
     * <p>
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     * <p>
     * 配置了该资源解析器之后会导致swagger 404 ，需要加上对/swagger-ui.html的映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

    }

    /**
     * Configure cross origin requests processing.
     *
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * Configure view resolvers to translate String-based view names returned from
     * controllers into concrete {@link View}
     * implementations to perform rendering with.
     *
     * @param registry
     * @since 4.1
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    /**
     * Add resolvers to support custom controller method argument types.
     * <p>This does not override the built-in support for resolving handler
     * method arguments. To customize the built-in support for argument
     * resolution, configure RequestMappingHandlerAdapter directly.
     *
     * @param resolvers initially an empty list
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    /**
     * Add handlers to support custom controller method return value types.
     * <p>Using this option does not override the built-in support for handling
     * return values. To customize the built-in support for handling return
     * values, configure RequestMappingHandlerAdapter directly.
     *
     * @param handlers initially an empty list
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    /**
     * Configure the {@link HttpMessageConverter}s to use for reading or writing
     * to the body of the request or response. If no converters are added, a
     * default list of converters is registered.
     * <p><strong>Note</strong> that adding converters to the list, turns off
     * default converter registration. To simply add a converter without impacting
     * default registration, consider using the method
     * {@link #extendMessageConverters(List)} instead.
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * A hook for extending or modifying the list of converters after it has been
     * configured. This may be useful for example to allow default converters to
     * be registered and then insert a custom converter through this method.
     *
     * @param converters the list of configured converters to extend.
     * @since 4.1.3
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * Configure exception resolvers.
     * <p>The given list starts out empty. If it is left empty, the framework
     * configures a default set of resolvers, see
     * {@link WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)}.
     * Or if any exception resolvers are added to the list, then the application
     * effectively takes over and must provide, fully initialized, exception
     * resolvers.
     * <p>Alternatively you can use
     * {@link #extendHandlerExceptionResolvers(List)} which allows you to extend
     * or modify the list of exception resolvers configured by default.
     *
     * @param resolvers initially an empty list
     * @see #extendHandlerExceptionResolvers(List)
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /**
     * Extending or modify the list of exception resolvers configured by default.
     * This can be useful for inserting a custom exception resolver without
     * interfering with default ones.
     *
     * @param resolvers the list of configured resolvers to extend
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List)
     * @since 4.3
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /**
     * Provide a custom {@link Validator} instead of the one created by default.
     * The default implementation, assuming JSR-303 is on the classpath, is:
     * {@link OptionalValidatorFactoryBean}.
     * Leave the return value as {@code null} to keep the default.
     */
    @Override
    public Validator getValidator() {
        return null;
    }

    /**
     * Provide a custom {@link MessageCodesResolver} for building message codes
     * from data binding and validation error codes. Leave the return value as
     * {@code null} to keep the default.
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
