/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere;

/**
 *
 * @author edwin
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.dozer.DozerConverter;

@SuppressWarnings("rawtypes")
public class EnumAndStringConverter extends DozerConverter<String, Enum> {

	public EnumAndStringConverter() {
		super(String.class, Enum.class);
	}

	@Override
	public String convertFrom(Enum src, String dst) {

		if (src == null) {
			return null;
		}

		return getString(src);
	}

	@Override
	public Enum convertTo(String src, Enum dst) {

		if (src == null) {
			return null;
		}

		return getEnum(src, dst);
	}

	private String getString(Enum<?> src) {

		String value = src.name();

		return value;
	}

	private Enum getEnum(String src, Enum dst) {

		Object enumeration = null;

		Method[] ms = dst.getDeclaringClass().getMethods();

		for (Method m : ms) {

			if (m.getName().equalsIgnoreCase("valueOf")) {

				try {
					enumeration = m.invoke(dst.getDeclaringClass(), src);
				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
					throw new IllegalArgumentException(e);
				}

				return (Enum) enumeration;
			}

		}

		return null;

	}

}
