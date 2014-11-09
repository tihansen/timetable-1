package com.woym.controller.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.woym.exceptions.DatasetException;
import com.woym.objects.Teacher;
import com.woym.persistence.TeacherDataHandler;

@SessionScoped
@ManagedBean(name = "teacherController")
public class TeacherController implements Serializable {

	private static final long serialVersionUID = -2341971622906815080L;

	private TeacherDataHandler db = new TeacherDataHandler();

	private Teacher selectedTeacher;

	public List<Teacher> getTeachers() {
		try {
			return db.getTeachers();
		} catch (DatasetException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Fehler beim Laden der Lehrer", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return new ArrayList<Teacher>();
		}
	}

	public void addTeacherDialog() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400);
		options.put("contentWidth", 600);

		RequestContext rc = RequestContext.getCurrentInstance();
		rc.openDialog("manageTeachersDialog", options, null);
	}

	public void editTeacherDialog() {

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400);
		options.put("contentWidth", 600);

		RequestContext rc = RequestContext.getCurrentInstance();
		rc.openDialog("editTeacherDialog", options, null);
	}

	public void onTeacherAdded(SelectEvent event) {

		Teacher teacher = (Teacher) event.getObject();

		try {
			db.persistObject(teacher);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Lehrer hinzugefügt", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (DatasetException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Lehrer existiert bereits", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
	}

	public void deleteTeacher() {
		if (selectedTeacher != null) {
			try {
				db.deleteObject(selectedTeacher);
			} catch (DatasetException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Fehler beim Löschen des Lehrers", "");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public Teacher getSelectedTeacher() {
		return selectedTeacher;
	}

	public void setSelectedTeacher(Teacher selectedTeacher) {
		this.selectedTeacher = selectedTeacher;
	}
}
