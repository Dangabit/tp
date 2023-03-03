package seedu.modtrek.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    private enum ValidTag {
        UNIVERSITY_LEVEL_REQUIREMENTS,
        COMPUTER_SCIENCE_FOUNDATION,
        COMPUTER_SCIENCE_BREADTH_AND_DEPTH,
        IT_PROFESSIONALISM,
        MATHEMATICS_AND_SCIENCES,
        UNRESTRICTED_ELECTIVES
    }

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be one of possible curriculum requirements";

    protected final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        try {
            String formattedString = test
                    .replace(" ", "_")
                    .toUpperCase();
            ValidTag.valueOf(formattedString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
