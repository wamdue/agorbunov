package ru.job4j.mapping.carshop.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created on 06.02.18.
 * Like web xml, configuration of container.
 * @author Wamdue
 * @version 1.0
 */
public class CarShopWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Get root class.
     * @return - class.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class, WebSecurityConfig.class};
    }

    /**
     * Get web config class.
     * @return - class.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    /**
     * Get servlet mappings.
     * @return - mapping.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"*.do"};
    }

    /**
     * Get servlet filters.
     * @return - filters.
     */
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new ServletFilter()};
//    }
}
