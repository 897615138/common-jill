/*
 * Copyright (c) 2016. 杭州端点网络科技有限公司.  All rights reserved.
 */

package jill;

import com.google.common.base.Throwables;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


@Slf4j
@ControllerAdvice
public class ExceptionResolver {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public void OpErrorHandler(RuntimeException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (e instanceof IllegalArgumentException) {
            Locale locale = LocaleContextHolder.getLocale();
            log.debug("IllegalArgumentException happened,locale={}, cause={}", locale, Throwables.getStackTraceAsString(e));
            String message = messageSource.getMessage(e.getMessage(), null, e.getMessage(), locale);
            response.setContentType(MediaType.JSON_UTF_8.toString());
            response.setStatus(500);
//            try (PrintWriter out = response.getWriter()) {
//                out.print(JsonMapper.nonDefaultMapper().toJson(ErrorResponse.fail(e.getMessage(), message)));
//            }
//        } else if (e instanceof UserNotLoginException) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            try (PrintWriter out = response.getWriter()) {
//                out.print(JsonMapper.nonDefaultMapper().toJson(new JsonResponseException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase())));
//            }
//        } else if (e instanceof JsonResponseException) {
//            Locale locale = LocaleContextHolder.getLocale();
//            log.debug("JsonResponseException happened,locale={}, cause={}", locale, Throwables.getStackTraceAsString(e));
//            String message = messageSource.getMessage(e.getMessage(), null, e.getMessage(), locale);
//            response.setContentType(MediaType.JSON_UTF_8.toString());
//            response.setStatus(500);
//            try (PrintWriter out = response.getWriter()) {
//                out.print(JsonMapper.nonDefaultMapper().toJson(ErrorResponse.fail(e.getMessage(), message)));
//            }
//        } else if (e instanceof EventApiException) {
//            Locale locale = LocaleContextHolder.getLocale();
//            log.debug("EventApiException happened,locale={}, cause={}", locale, Throwables.getStackTraceAsString(e));
//            String message = messageSource.getMessage(e.getMessage(), null, e.getMessage(), locale);
//            response.setContentType(MediaType.JSON_UTF_8.toString());
//            response.setStatus(500);
//            try (PrintWriter out = response.getWriter()) {
//                out.print(JsonMapper.nonDefaultMapper().toJson(ErrorResponse.fail(e.getMessage(), message)));
//            }
        } else {
            log.error("RuntimeException happened, cause={}", Throwables.getStackTraceAsString(e));
        }
    }
}



