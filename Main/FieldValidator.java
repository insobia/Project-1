package Main;

/**
 * A generic interface for validating field input.
 *
 * @param <T> The data type of the field.
 * @param <E> The threshold type for validation.
 */
public interface FieldValidator<T, E> {
    /**
     * Validates the given value against a specified threshold.
     *
     * @param value The input value to validate.
     * @param threshold The validation threshold.
     * @return A validation message if invalid; otherwise, null.
     */
    String validate(T value, E threshold);

    /**
     * @param value The input value to validate.
     * @param minThreshold The minimum threshold.
     * @param maxThreshold The maximum threshold.
     * @return A validation message if invalid; otherwise, null.
     */
    default String validate(T value, E minThreshold, E maxThreshold) {
        String minCheck = validate(value, minThreshold);

        if (minCheck != null) {
            return "Value must be at least: " + minThreshold;
        }


        if (value instanceof Comparable<?> && ((Comparable<T>) value).compareTo((T) maxThreshold) > 0) {
            return "Value must be at most: " + maxThreshold;
        }

        return null;
    }
}