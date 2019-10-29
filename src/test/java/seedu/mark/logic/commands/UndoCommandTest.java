package seedu.mark.logic.commands;

import static seedu.mark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mark.logic.commands.CommandTestUtil.deleteFirstBookmark;
import static seedu.mark.testutil.TypicalBookmarks.getTypicalMark;

import org.junit.jupiter.api.Test;

import seedu.mark.model.Model;
import seedu.mark.model.ModelManager;
import seedu.mark.model.UserPrefs;
import seedu.mark.storage.Storage;
import seedu.mark.storage.StorageStub;

public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalMark(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMark(), new UserPrefs());
    private Storage storage = new StorageStub();

    @Test
    public void execute() {
        deleteFirstBookmark(model);
        deleteFirstBookmark(expectedModel);

        deleteFirstBookmark(model);
        deleteFirstBookmark(expectedModel);

        // Two undoable Mark states
        String expectedRecord1 = expectedModel.undoMark(1);
        String expectedMessage1 = String.format(UndoCommand.MESSAGE_SUCCESS, expectedRecord1);
        assertCommandSuccess(new UndoCommand(1), model, storage, expectedMessage1, expectedModel);

        // Single undoable Mark state
        String expectedRecord2 = expectedModel.undoMark(1);
        String expectedMessage2 = String.format(UndoCommand.MESSAGE_SUCCESS, expectedRecord2);
        assertCommandSuccess(new UndoCommand(1), model, storage, expectedMessage2, expectedModel);

        // No undoable Mark state
        assertCommandFailure(new UndoCommand(1), model, storage, UndoCommand.MESSAGE_FAILURE);
    }
}
