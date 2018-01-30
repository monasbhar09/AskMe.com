package com.neu.askme.filter;

import java.io.IOException;
import java.text.Normalizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSFilter implements Filter {

    class XSSRequestWrapper extends HttpServletRequestWrapper {

        XSSRequestWrapper(HttpServletRequest httpServletRequest) {
            super(httpServletRequest);
        }

        @Override
        public String[] getParameterValues(String parameter) {

            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }
            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                encodedValues[i] = cleanXSS(values[i]);
            }
            return encodedValues;
        }

        @Override
        public String getParameter(String parameter) {
            String value = super.getParameter(parameter);
            if (value == null) {
                return null;
            }
            return cleanXSS(value);
        }

        private String cleanXSS(String value) {
            String updatedValue = null;

            if (value != null) {
                updatedValue = Normalizer.normalize(value, Normalizer.Form.NFD);

                updatedValue = updatedValue.replaceAll("<", "").replaceAll(">", "");
                updatedValue = updatedValue.replaceAll("\\;", "").replaceAll("\"", "").replaceAll("\'", "");

                updatedValue = updatedValue.replaceAll("eval\\((.*)\\)", "");
                updatedValue = updatedValue.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
                updatedValue = updatedValue.replaceAll("/^[a-z0-9 ]$/i", "");

            }

            return updatedValue.trim();
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        fc.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }
}
