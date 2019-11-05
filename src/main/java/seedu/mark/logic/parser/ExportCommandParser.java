package seedu.mark.logic.parser;

import static seedu.mark.commons.core.Messages.MESSAGE_FILE_NAME_INCLUDES_EXTENSION;
import static seedu.mark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

import seedu.mark.logic.commands.ExportCommand;
import seedu.mark.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ExportCommand} object.
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // args must contain exactly one word
        if (trimmedArgs.isEmpty() || trimmedArgs.split(" ").length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.endsWith(".json")) {
            throw new ParseException(MESSAGE_FILE_NAME_INCLUDES_EXTENSION);
        }

        Path destinationFile = Path.of("data", "bookmarks", trimmedArgs + ".json");

        return new ExportCommand(destinationFile);
    }
}
