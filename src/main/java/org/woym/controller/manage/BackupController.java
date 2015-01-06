package org.woym.controller.manage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.woym.logic.BackupRestoreHandler;
import org.woym.logic.spec.IStatus;
import org.woym.persistence.DataBase;

@ViewScoped
@ManagedBean(name = "backupController")
public class BackupController implements Serializable {

	private static final long serialVersionUID = 1386396038643221783L;

	private File backup;

	private Boolean hasName = true;
	private String backupName;

	public List<File> getAllBackups() {
		return BackupRestoreHandler.getAllBackups();
	}

	public void doBackup() {
		if ("".equals(backupName)) {
			backupName = null;
		}

		IStatus status = BackupRestoreHandler.backup(backupName);
		FacesMessage msg = status.report();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doRestore() {
		IStatus status = BackupRestoreHandler.restore(backup.getAbsolutePath());
		FacesMessage msg = status.report();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Öffnet den Backup-Speicherort im FileExplorer des jeweiligen
	 * Betriebssystems.
	 */
	public void openBackupLocation() {
		if (!Desktop.isDesktopSupported()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Wird nicht unterstützt.",
					"Diese Aktion wird auf Ihrem System nicht unterstützt.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		Desktop desktop = Desktop.getDesktop();
		File file = new File(DataBase.DB_BACKUP_LOCATION);
		if (!file.exists()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Speicherort nicht gefunden.",
					"Der Speicherort wurde nicht gefunden.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			desktop.open(file);
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Fehler beim Öffnen des Speicherortes",
					"Beim Öffnen des Speicherortes ist ein Fehler aufgetreten.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
	}

	public File getBackup() {
		return backup;
	}

	public void setBackup(File backup) {
		this.backup = backup;
	}

	public Boolean getHasName() {
		return hasName;
	}

	public void setHasName(Boolean hasName) {
		if (hasName) {
			backupName = null;
		}

		this.hasName = hasName;
	}

	public String getBackupName() {
		return backupName;
	}

	public void setBackupName(String backupName) {
		this.backupName = backupName;
	}
}