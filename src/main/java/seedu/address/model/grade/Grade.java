package seedu.address.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;
/**
 * Represents a Student's Grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS = "Grades should be of the format Test: Grade "
            + "and adhere to the following constraints:\n"
            + "1. The Test name should not be empty"
            + "2. This is followed by a ': ' and then a grade. \n"
            + "The grade must:\n"
            + "    - represent the percentage gotten in the test rounded to the nearest whole number\n"
            + "    - be between [0, 100]\n";

    /*
     * The first character of the test name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Grade should be an integer ranging from 0-100, representing the percentage.
     */
    public static final String TEST_NAME_VALIDATION_REGEX = ".+?";
    public static final String GRADE_VALIDATION_REGEX = "(100|[1-9]?[0-9])$";

    public final String testAndGrade;
    public final String testName;
    public final String grade;

    /**
     * Constructs an {@code Grade}.
     *
     * @param testAndGrade A valid testAndGrade.
     */
    public Grade(String testAndGrade) {
        requireNonNull(testAndGrade);
        String[] parts = testAndGrade.split(":", 2);
        checkArgument(parts.length == 2, MESSAGE_CONSTRAINTS);
        checkArgument(parts[0].trim().matches(TEST_NAME_VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
        checkArgument(parts[1].trim().matches(GRADE_VALIDATION_REGEX), MESSAGE_CONSTRAINTS);
        this.testName = parts[0].trim();
        this.grade = parts[1].trim();
        this.testAndGrade = testAndGrade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        String[] parts = test.split(":", 2);
        if (parts.length != 2) {
            return false;
        }
        return parts[0].trim().matches(TEST_NAME_VALIDATION_REGEX)
                && parts[1].trim().matches(GRADE_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return testName.equals(otherGrade.testName) && grade.equals(otherGrade.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, grade);
    }

    public String toString() {
        return testName + ": " + grade;
    }

    public String getTestAndGrade() {
        return testAndGrade;
    }
}
