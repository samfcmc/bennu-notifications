package org.fenixedu.bennu.notifications.backend.api.param.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;

//@Provider
public class NotificationInfoConverterProvider implements ParamConverter<NotificationInfo>, ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        return ((ParamConverter<T>) new NotificationInfoConverterProvider());
    }

    @Override
    public NotificationInfo fromString(String value) {
        return Master.getInstance().getNotification(Authenticate.getUser().getUsername(), value);
    }

    @Override
    public String toString(NotificationInfo value) {
        return value.getId();
    }

}
