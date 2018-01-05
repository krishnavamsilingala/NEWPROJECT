package com.company.backend.initializer;

import com.company.backend.spring.MvcConfig;
import com.company.backend.spring.PersistenceJPAConfig;
import com.company.backend.spring.SecurityConfig;
import com.company.backend.spring.SocialConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class, SocialConfig.class, PersistenceJPAConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
