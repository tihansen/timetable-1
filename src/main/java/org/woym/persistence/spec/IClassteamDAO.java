package org.woym.persistence.spec;

import java.util.List;

import org.woym.common.exceptions.DatasetException;
import org.woym.common.objects.Classteam;
import org.woym.common.objects.Employee;
import org.woym.common.objects.Schoolclass;

/**
 * Dieses Interface beschreibt Methoden, die von einem {@linkplain Classteam}
 * Data-Access-Object zu implementieren wären.
 * 
 * @author adrian
 *
 */
public interface IClassteamDAO {

	/**
	 * Gibt eine Liste aller in der Datenbank vorhandenen Klassenteams zurück.
	 * Tritt dabei ein Fehler auf, wird eine {@linkplain DatasetException}
	 * geworfen.
	 * 
	 * @return Liste aller in der Datenbank vorhanden Klassenteams
	 * @throws DatasetException
	 */
	public List<Classteam> getAllClassteams() throws DatasetException;

	/**
	 * Gibt eine Liste aller in der Datenbank vorhandenen Klassenteams zurück,
	 * an welchen der übergebene Mitarbeiter beteiligt ist. Tritt dabei ein
	 * Fehler auf, wird eine {@linkplain DatasetException} geworfen.
	 * 
	 * @param employee
	 *            - der Mitarbeiter, für welchen alle Klassenteams gesucht
	 *            werden sollen
	 * @return Liste aller Klassenteams, an welchen der übergebene Mitarbeiter
	 *         beteiligt ist
	 * @throws DatasetException
	 */
	public List<Classteam> getAllClassteams(Employee employee)
			throws DatasetException;

	/**
	 * Gibt das Klassenteam zurück, zu welchem die übergebene Klasse gehört.
	 * Gehört die Klasse zu keinem Klassenteam, wird {@code null} zurückgegeben.
	 * Tritt ein Fehler auf, wird eine {@linkplain DatasetException} geworfen.
	 * 
	 * @param schoolclass
	 *            - die Schulklasse, für welche das Klassenteam gesucht wird
	 * @return das Klassenteam, zu welchem die übergebene Schulklasse gehört
	 *         oder {@code null}, wenn sie zu keinem gehört.
	 * @throws DatasetException
	 */
	public Classteam getOneClassteam(Schoolclass schoolclass)
			throws DatasetException;
}
