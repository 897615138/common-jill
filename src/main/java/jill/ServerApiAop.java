package jill;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Aspect
@Component
@Order(0)
@Slf4j
@RequiredArgsConstructor
public class ServerApiAop {

    private static final String COUNTRY_KEY = "country";
    private static final String LANGUAGE_KEY = "language";
    private final MessageSource messageSource;

    @Pointcut(value = "within(jill..*)")
    public void apiPointcut() {
    }

    @Around(value = "apiPointcut()")
    public Object doApi(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();

        } catch (Throwable e) {
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage(e.getMessage(), null, e.getMessage(), locale);
            if (StrUtil.isNotBlank(message)) {
                return message;
            } else {
                return messageSource.getMessage("server.error", null, e.getMessage(), locale);
            }
        }
    }

}
