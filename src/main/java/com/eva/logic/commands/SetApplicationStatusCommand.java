package com.eva.logic.commands;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static com.eva.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static java.util.Objects.requireNonNull;

import java.util.List;

import com.eva.commons.core.Messages;
import com.eva.commons.core.index.Index;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;

/**
 * Changes the application status of an applicant in the eva database.
 */
public class SetApplicationStatusCommand extends Command {

    public static final String COMMAND_WORD = "setappstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets application status of the specified applicant "
            + "Parameters: "
            + "Index "
            + PREFIX_APPLICATION_STATUS + "APPLICATION STATUS\n"
            + "Application status can only be one of the following: received, processing, accepted, rejected.\n"
            + "Example: "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPLICATION_STATUS + "accepted";
    public static final String MESSAGE_SUCCESS = "Application Status of %1$s changed to %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the eva database";

    private final Index targetIndex;
    private final ApplicationStatus newApplicationStatus;

    /**
     * Creates an SetApplicationStatusCommand to set the application of the specified {@code Applicant}
     */
    public SetApplicationStatusCommand(Index index, ApplicationStatus newApplicationStatus) {
        requireAllNonNull(index, newApplicationStatus);
        this.newApplicationStatus = newApplicationStatus;
        targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Applicant> lastShownList = model.getFilteredApplicantList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Applicant targetApplicant = lastShownList.get(targetIndex.getZeroBased());

        model.setApplicationStatus(targetApplicant, newApplicationStatus);
        model.setApplicant(targetApplicant, targetApplicant);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetApplicant.getName(), newApplicationStatus));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetApplicationStatusCommand // instanceof handles nulls
                && targetIndex.equals(((SetApplicationStatusCommand) other).targetIndex));
    }
}
