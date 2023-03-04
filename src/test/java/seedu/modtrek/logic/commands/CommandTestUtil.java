package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_CODE_CS1101S = "CS1101S";
    public static final String VALID_CODE_MA2002 = "MA2002";
    public static final String VALID_CREDIT_CS1101S = "4";
    public static final String VALID_CREDIT_MA2002 = "8";
    public static final String VALID_SEMYEAR_CS1101S = "Y1S1";
    public static final String VALID_SEMYEAR_MA2002 = "Y1S2";
    public static final String VALID_GRADE_CS1101S = "A";
    public static final String VALID_GRADE_MA2002 = "A-";
    public static final String VALID_TAG_CS1101S = "COMPUTER SCIENCE FOUNDATION";
    public static final String VALID_TAG_MA2002 = "MATHEMATICS AND SCIENCES";

    public static final String CODE_DESC_CS1101S = " " + PREFIX_CODE + VALID_CODE_CS1101S;
    public static final String CODE_DESC_MA2002 = " " + PREFIX_CODE + VALID_CODE_MA2002;
    public static final String CREDIT_DESC_CS1101S = " " + PREFIX_CREDIT + VALID_CREDIT_CS1101S;
    public static final String CREDIT_DESC_MA2002 = " " + PREFIX_CREDIT + VALID_CREDIT_MA2002;
    public static final String SEMYEAR_DESC_CS1101S = " " + PREFIX_SEMYEAR + VALID_SEMYEAR_CS1101S;
    public static final String SEMYEAR_DESC_MA2002 = " " + PREFIX_SEMYEAR + VALID_SEMYEAR_MA2002;
    public static final String GRADE_DESC_CS1101S = " " + PREFIX_GRADE + VALID_GRADE_CS1101S;
    public static final String GRADE_DESC_MA2002 = " " + PREFIX_GRADE + VALID_GRADE_MA2002;
    public static final String TAG_DESC_MA2002 = " " + PREFIX_TAG + VALID_TAG_MA2002;
    public static final String TAG_DESC_CS1101S = " " + PREFIX_TAG + VALID_TAG_CS1101S;

    public static final String INVALID_CODE_DESC = " " + PREFIX_CODE + "CS1101S&"; // '&' not allowed in names
    public static final String INVALID_CREDIT_DESC = " " + PREFIX_CREDIT + "4MC"; // 'a' not allowed in phones
    public static final String INVALID_SEMYEAR_DESC = " " + PREFIX_SEMYEAR + "YEAR1SEM1"; // missing '@' symbol
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "COMPUTER SCIENCE *"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditModuleDescriptor DESC_CS1101S;
    public static final EditCommand.EditModuleDescriptor DESC_MA2002;

    static {
        DESC_CS1101S = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS1101S)
                .withCredit(VALID_CREDIT_CS1101S).withSemYear(VALID_SEMYEAR_CS1101S).withGrade(VALID_GRADE_CS1101S)
                .withTags(VALID_TAG_MA2002).build();
        DESC_MA2002 = new EditModuleDescriptorBuilder().withCode(VALID_CODE_MA2002)
                .withCredit(VALID_CREDIT_MA2002).withSemYear(VALID_SEMYEAR_MA2002).withGrade(VALID_GRADE_MA2002)
                .withTags(VALID_TAG_CS1101S, VALID_TAG_MA2002).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

}
