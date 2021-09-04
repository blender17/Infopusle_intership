import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class Mapper {

	enum AccessLevel {
		PRIVATE, PUBLIC;
	}

	private boolean fieldNamesByAnnotation;
	private boolean caseSensitive;
	private AccessLevel accessLevel;

	public Mapper() {
		//Default configuration
		fieldNamesByAnnotation = false;
		caseSensitive = false;
		accessLevel = AccessLevel.PRIVATE;

	}

	public void fieldNamesByAnnotation(boolean fieldNamesByAnnotation) {
		this.fieldNamesByAnnotation = fieldNamesByAnnotation;
	}

	public void caseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void accessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public <S, D> D map(S source, Class<D> destination) {
		if (source == null) {
			return null;
		}

		D dstObj = getInstance(destination);
		Field[] sourceFields = getFields(source);
		Field[] destinationFields = getFields(dstObj);
		mapFields(sourceFields, destinationFields, source, dstObj);

		return dstObj;
	}

	private <S, D> void mapFields(Field[] source, Field[] destination, S srcInst, D dstInst) {
		for (Field sourceField : source) {
			for (Field destinationField: destination) {
				if (isFieldEquals(sourceField, destinationField)) {
					setField(sourceField, destinationField, srcInst, dstInst);
				}
			}
		}
	}

	private boolean isFieldEquals(Field sourceField, Field destinationField) {
		if (fieldNamesByAnnotation) {
			Optional<Column> dstAnnotation = Optional.ofNullable(destinationField.getAnnotation(Column.class));
			Optional<Column> srcAnnotation = Optional.ofNullable(sourceField.getAnnotation(Column.class));
			String srcFieldName = (srcAnnotation.isPresent() ? srcAnnotation.get().value() : sourceField.getName());
			String dstFieldName = (dstAnnotation.isPresent() ? dstAnnotation.get().value() : destinationField.getName());

			if (caseSensitive) {
				return srcFieldName.equals(dstFieldName) & sourceField.getType().equals(destinationField.getType());
			} else {
				return srcFieldName.equalsIgnoreCase(dstFieldName) & sourceField.getType().equals(destinationField.getType());
			}
		} else {
			if (caseSensitive) {
				return sourceField.getName().equals(destinationField.getName());
			} else {
				return sourceField.getName().equalsIgnoreCase(destinationField.getName());
			}
		}
	}

	private <S, D> void setField(Field srcField, Field dstField, S srcInst, D dstInst) {
		try {
			dstField.set(dstInst, srcField.get(srcInst));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private <S> Field[] getFields(S source) {
		Field[] fields = null;
		switch (accessLevel) {
			case PRIVATE -> {
				fields = source.getClass().getDeclaredFields();
				Arrays.stream(fields).forEach(field -> field.setAccessible(true));
			}
			case PUBLIC -> fields = source.getClass().getFields();
		}
		return fields;
	}

	private <D> D getInstance(Class<D> destination) {
		D dstObj = null;
		try {
			Constructor<D> constructor = destination.getDeclaredConstructor();
			if (!constructor.canAccess(null)) {
				constructor.setAccessible(true);
			}
			dstObj = constructor.newInstance();
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return dstObj;
	}

}
