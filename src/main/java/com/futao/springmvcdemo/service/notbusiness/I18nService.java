package com.futao.springmvcdemo.service.notbusiness;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-01-15.
 */
@Service
public class I18nService {

    private static final Logger LOGGER = LoggerFactory.getLogger(I18nService.class);

    @Resource
    private MessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            LOGGER.error("获取国际化资源{}失败" + e.getMessage(), code, e);
            throw LogicException.le(ErrorMessage.I18N_RESOURCE_NOT_FOUND, new String[]{code});
        }
    }
}
