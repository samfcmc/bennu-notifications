package org.fenixedu.bennu.notifications.backend.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final String referer = requestContext.getHeaderString("Origin");
        responseContext.getHeaders().add("Access-Control-Allow-Origin", referer);
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", true);
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "Accept, Cache-Control, Pragma, Origin, Authorization, Content-Type");
    }

}
