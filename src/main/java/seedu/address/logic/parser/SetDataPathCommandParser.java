package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

import seedu.address.logic.commands.SetDataPathCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the input arguments and creates a new SetDataPath Object
 */
public class SetDataPathCommandParser implements Parser<SetDataPathCommand> {
    /**
     * Parses the given {@code String} of arguments to obtain the new path of data
     * and returns a SetDataPath Object for execution.
     * @throws ParseException if the user input doesn't represent a proper file path
     */
    @Override
    public SetDataPathCommand parse(String userInput) throws ParseException {
        String trimmedArgument = userInput.strip();
        if (trimmedArgument.length() < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDataPathCommand.MESSAGE_USAGE));
        }
        Path newDataPath = ParserUtil.parseDataPath(userInput);
        return new SetDataPathCommand(newDataPath);
    }
}
