package org.springframework.data.gremlin.schema.property.accessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * A concrete {@link AbstractGremlinFieldPropertyAccessor} for basic Fields.
 *
 * @author Gman
 */
public class GremlinDynamicMapPropertyAccessor<V> extends AbstractGremlinFieldPropertyAccessor<V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GremlinDynamicMapPropertyAccessor.class);

    public GremlinDynamicMapPropertyAccessor(Field field) {
        super(field);
    }

    public GremlinDynamicMapPropertyAccessor(Field field, GremlinDynamicMapPropertyAccessor parentAccessor) {
        super(field, parentAccessor);
    }

    @Override
    public V get(Object object) {
        try {

            object = getEmbeddedObject(object, false);
            V result = null;
            if (object != null) {
                result = (V) field.get(object);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object object, V val) {

        try {
            object = getEmbeddedObject(object, true);
            if (object != null) {
                field.set(object, val);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GremlinFieldPropertyAccessor{");
        sb.append("field=").append(field);
        sb.append(", embeddedAccessor=").append(embeddedAccessor);
        sb.append('}');
        return sb.toString();
    }
}
