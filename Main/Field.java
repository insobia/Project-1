package Main;

import java.util.Scanner;

/**
 * A Field class used for user input in various banking operations.
 *
 * @param <T> Type of the field (e.g., String, Integer, Double, etc.).
 * @param <E> Threshold type for validation.
 */
public class Field<T, E> {
    private static final Scanner scanner = new Scanner(System.in);

    private final Class<T> fieldType;
    private final String fieldName;
    private T fieldValue;
    private final E threshold;
    private final FieldValidator<T, E> fieldValidator;

    public Field(String fieldName, Class<T> fieldType, E threshold, FieldValidator<T, E> validator) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.threshold = threshold;
        this.fieldValidator = validator;
    }

    /**
     * Get the input value of this field.
     */
    public T getFieldValue() {
        return this.fieldValue;
    }

    /**
     * Set the value for this field.
     *
     * @param phrase Prompt message shown to the user.
     */
    public void setFieldValue(String phrase) {
        this.setFieldValue(phrase, true);
    }

    /**
     * Set the value for this field with validation.
     *
     * @param phrase      Prompt message.
     * @param inlineInput If true, reads only one word; if false, reads a full line.
     */
    @SuppressWarnings("unchecked")
    public void setFieldValue(String phrase, boolean inlineInput) {
        String tempVal;
        int attempts = 3;

        while (attempts-- > 0) {
            try {
                tempVal = prompt(phrase, inlineInput);


                if (fieldType == Double.class) {
                    this.fieldValue = (T) Double.valueOf(tempVal);
                } else if (fieldType == Integer.class) {
                    this.fieldValue = (T) Integer.valueOf(tempVal);
                } else if (fieldType == Boolean.class) {
                    this.fieldValue = (T) Boolean.valueOf(tempVal);
                } else if (fieldType == Float.class) {
                    this.fieldValue = (T) Float.valueOf(tempVal);
                } else if (fieldType == Long.class) {
                    this.fieldValue = (T) Long.valueOf(tempVal);
                } else {
                    this.fieldValue = fieldType.cast(tempVal);
                }

            } catch (ClassCastException | NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a valid " + fieldType.getSimpleName() + " value.");
                continue;
            }


            if (this.fieldValue != null) {
                String validationResult = this.fieldValidator.validate(this.fieldValue, threshold);
                if (validationResult == null) break;
                System.out.println(validationResult);
            } else {
                System.out.println("Invalid input given!");
            }
        }


        if (attempts < 0) {
            System.out.println("Too many invalid attempts. Cancelling input.");
            this.fieldValue = null;
        }
    }

    /**
     * Handles user input.
     *
     * @param phrase      Prompt to display.
     * @param inlineInput If true, reads only one word; if false, reads a full line.
     * @return The user input.
     */
    private static String prompt(String phrase, boolean inlineInput) {
        System.out.print(phrase + " ");
        return inlineInput ? scanner.next() : scanner.nextLine();
    }


    public static class IntegerFieldValidator implements FieldValidator<Integer, Integer> {
        @Override
        public String validate(Integer value, Integer threshold) {
            return (value < threshold) ? "Value must be at least: " + threshold : null;
        }
    }

    public static class DoubleFieldValidator implements FieldValidator<Double, Double> {
        @Override
        public String validate(Double value, Double threshold) {
            return (value < threshold) ? "Value must be at least: " + threshold : null;
        }
    }

    public static class StringFieldValidator implements FieldValidator<String, String> {
        @Override
        public String validate(String value, String threshold) {
            return (value.isEmpty()) ? "Field cannot be empty!" : null;
        }
    }

    public static class StringFieldLengthValidator implements FieldValidator<String, Integer> {
        @Override
        public String validate(String value, Integer threshold) {
            return (value.length() < threshold) ? "Must be at least " + threshold + " characters long" : null;
        }
    }
}

