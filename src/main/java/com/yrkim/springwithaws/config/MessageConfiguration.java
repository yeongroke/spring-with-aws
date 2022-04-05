package com.yrkim.springwithaws.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@NoArgsConstructor
@Configuration
public class MessageConfiguration implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("locale");
        localeResolver.setDefaultLocale(Locale.KOREAN);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean({"messageSource"})
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource(){
            @Override
            protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
                return ResourceBundle.getBundle(basename, locale);
            }
        };
        ms.setBasename(this.env.getProperty("spring.messages.basename"));
        ms.setDefaultEncoding(this.env.getProperty("spring.messages.encoding"));
        ms.setAlwaysUseMessageFormat(true);
        ms.setUseCodeAsDefaultMessage(true);
        ms.setFallbackToSystemLocale(true);
        return ms;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor(final MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource, Locale.KOREAN);
    }
}
