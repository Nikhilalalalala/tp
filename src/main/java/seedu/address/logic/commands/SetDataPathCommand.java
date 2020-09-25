package seedu.address.logic.commands;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the location where all data is stored
 */
public class SetDataPathCommand extends Command {

    public static final String COMMAND_WORD = "setdatapath";
    public static final String MESSAGE_SUCCESS = "Data path to store all data updated";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a specified path to store all data\n"
            + "Parameters: DATAPATH (new data path where directories are separated by \\) "
            + "\n Example: " + COMMAND_WORD + " C:\\Users\\Username\\Desktop\\data";

    private Path newDataPath;

    /**
     * Creates a SetDataPathCommand to change the path to the specified path
     *
     * @param path
     */
    public SetDataPathCommand(Path path) {
        this.newDataPath = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setAddressBookFilePath(this.newDataPath);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
