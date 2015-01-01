/**
 * 
 */
package org.woym.logic;

import javax.faces.application.FacesMessage;

import org.woym.logic.spec.ICommand;
import org.woym.logic.spec.ICommandHandler;
import org.woym.logic.spec.ILimitedQueue;
import org.woym.logic.spec.IStatus;
import org.woym.logic.util.LimitedQueue;
import org.woym.messages.GenericErrorMessage;

/**
 * Klasse zur umsetzung des CommandHandlers nach dem Singleton Muster.
 * 
 * @author JurSch
 *
 */
public class CommandHandler implements ICommandHandler {

	static final private CommandHandler COMMAND_HANDLER = new CommandHandler();

	final private ILimitedQueue<ICommand> undo = new LimitedQueue<ICommand>();

	final private ILimitedQueue<ICommand> redo = new LimitedQueue<ICommand>();

	/**
	 * Privater Konstruktor
	 */
	private CommandHandler() {

	}

	/**
	 * Ermöglich den zugrif von außen durch eine Getter auf die Instance.
	 * 
	 * @return Instanz des Singleton-CommandHandler
	 */
	static public CommandHandler getInstance() {
		return COMMAND_HANDLER;
	}

	public Integer getUndoSize() {
		return undo.size();
	}

	public Integer getRedoSize() {
		return redo.size();
	}

	@Override
	public IStatus execute(ICommand command) {
		if (command == null) {
			throw new IllegalArgumentException("The Command was null");
		}

		IStatus status = command.execute();

		if (status instanceof SuccessStatus) {
			undo.add(command);
			redo.clear();
		}

		return status;
	}

	@Override
	public IStatus undo() {
		if (undo.size() == 0) {
			return new FailureStatus(GenericErrorMessage.UNDO_EMPTY,
					FacesMessage.SEVERITY_INFO);
		}

		ICommand command = undo.getLast();
		IStatus status = command.undo();

		if (status instanceof SuccessStatus) {
			redo.add(command);
		} else {
			redo.clear();
			undo.clear();
		}

		return status;
	}

	@Override
	public IStatus redo() {
		if (redo.size() == 0) {
			return new FailureStatus(GenericErrorMessage.REDO_EMPTY,
					FacesMessage.SEVERITY_INFO);
		}

		ICommand command = redo.getLast();
		IStatus status = command.redo();

		if (status instanceof SuccessStatus) {
			undo.add(command);
		} else {
			redo.clear();
			undo.clear();
		}

		return status;
	}

}
