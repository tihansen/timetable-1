/**
 * 
 */
package org.woym.logic;

import javax.faces.application.FacesMessage;

import org.woym.logic.spec.IStatus;
import org.woym.messages.GenericSuccessMessage;
import org.woym.messages.MessageHelper;
import org.woym.messages.SpecificSuccessMessage;
import org.woym.objects.Entity;

/**
 * Representation eines erfolgreichen {@link IStatus}
 * 
 * @author JurSch, adrian
 *
 */
public class SuccessStatus implements IStatus {

	/**
	 * Die FacesMessage zur erfolgreich ausgeführten Aktion.
	 */
	private final FacesMessage facesMessage;

	/**
	 * Erzeugt ein neues {@linkplain SuccessStatus}-Objekt. Wird für einen der
	 * Parameter {@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen.
	 * 
	 * @param message
	 *            - eine {@linkplain SpecificSuccessMessage}
	 * @param entity
	 *            - das betroffene Objekt
	 */
	public SuccessStatus(SpecificSuccessMessage message, Entity entity) {
		if (message == null || entity == null) {
			throw new IllegalArgumentException();
		}
		facesMessage = MessageHelper.generateMessage(message, entity);
	}

	/**
	 * Erzeugt ein neues {@linkplain SuccessStatus}-Objekt mit der übergebenen
	 * Erfolgsmeldung. Wird {@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen.
	 * 
	 * @param message
	 *            - eine {@linkplain GenericSuccessMessage}
	 */
	public SuccessStatus(GenericSuccessMessage message) {
		if (message == null) {
			throw new IllegalArgumentException();
		}
		facesMessage = MessageHelper.generateMessage(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FacesMessage report() {
		return facesMessage;
	}

}
