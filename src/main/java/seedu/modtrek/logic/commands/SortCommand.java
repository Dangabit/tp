package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modtrek.commons.util.AppUtil.checkArgument;

import java.util.Locale;

import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.model.Model;

/**
 * The type Sort command.
 */
public class SortCommand extends Command {

    /**
     * The constant COMMAND_WORD.
     */
    public static final String COMMAND_WORD = "sort";

    /**
     * The constant MESSAGE_SUCCESS.
     */
    public static final String MESSAGE_SUCCESS = "Listed modules by %s";

    /**
     * The constant MESSAGE_FAILURE.
     */
    public static final String MESSAGE_FAILURE = "Sorry but I don't know how to sort modules that way.";

    /**
     * The enum Sort.
     */
    public enum Sort {
        /**
         * Semyear sort.
         */
        SEMYEAR,
        /**
         * Grade sort.
         */
        GRADE,
        /**
         * Credit sort.
         */
        CREDIT,
        /**
         * Code sort.
         */
        CODE,
        /**
         * Tag sort.
         */
        TAG };
    /**
     * The constant DEFAULT_SORT.
     */
    public static final Sort DEFAULT_SORT = Sort.SEMYEAR;

    private String sortOrder;

    /**
     * Instantiates a new Sort command.
     *
     * @param sortOrder the sort order
     */
    public SortCommand(String sortOrder) {
        requireNonNull(sortOrder);
        checkArgument(isValid(sortOrder), MESSAGE_FAILURE);
        this.sortOrder = sortOrder.toUpperCase(Locale.ROOT);
    }

    private Boolean isValid(String sortOrder) {
        try {
            SortCommand.Sort.valueOf(sortOrder.toUpperCase(Locale.ROOT));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortModuleGroups(SortCommand.Sort.valueOf(sortOrder));
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortOrder));
    }
}
