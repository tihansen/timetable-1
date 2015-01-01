package org.woym.persistence.spec;

import java.util.List;

import org.woym.exceptions.DatasetException;
import org.woym.objects.Activity;
import org.woym.objects.CompoundLesson;
import org.woym.objects.Employee;
import org.woym.objects.Lesson;
import org.woym.objects.LessonType;
import org.woym.objects.Meeting;
import org.woym.objects.MeetingType;
import org.woym.objects.Room;
import org.woym.objects.Schoolclass;
import org.woym.objects.TimePeriod;
import org.woym.objects.Weekday;

/**
 * Dieses Interface beschreibt Methoden, die von einem {@linkplain Activity}
 * Data-Access-Object zu implementieren wären.
 * 
 * @author adrian
 *
 */
public interface IActivityDAO {

	/**
	 * Wird {@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen. Ansonsten wird in der
	 * Datenbank nach allen Aktivitäten des übergebenen Mitarbeiters gesucht und
	 * diese als Liste zurückgegeben. Tritt dabei ein Fehler auf, wird eine
	 * {@linkplain DatasetException} geworfen.
	 * 
	 * @param employee
	 *            - Mitarbeiter, für den alle Aktivitäten gesucht werden sollen
	 * @return Liste aller Aktivitäten des übergebenen Mitarbeiters, kann auch
	 *         leer sein, wenn der Mitarbeiter an keiner Aktivität teilnimmt
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Employee employee)
			throws DatasetException;

	/**
	 * Wird {@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen. Ansonsten wird in der
	 * Datenbank nach allen Aktivitäten der übergebenen Schulklasse gesucht und
	 * diese als Liste zurückgegeben. Tritt dabei ein Fehler auf, wird eine
	 * {@linkplain DatasetException} geworfen.
	 * 
	 * @param schoolclass
	 *            - Schulklasse, für die alle Aktivitäten gesucht werden sollen
	 * @return Liste aller Aktivitäten der übergebenen Schulklasse, kann auch
	 *         leer sein, wenn die Schulklasse an keiner Aktivität teilnimmt.
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Schoolclass schoolclass)
			throws DatasetException;

	/**
	 * Wird {@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen. Ansonsten wird in der
	 * Datenbank nach allen Aktivitäten gesucht, die im übergebenen Raum
	 * stattfinden und diese als Liste zurückgegeben. Tritt dabei ein Fehler
	 * auf, wird eine {@linkplain DatasetException} geworfen.
	 * 
	 * @param room
	 *            - Raum, für welchen alle Aktivitäten gesucht werden sollen
	 * @return Liste aller Aktivitäten, die im übergebenen Raum stattfinden,
	 *         kann auch leer sein
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Room room) throws DatasetException;

	/**
	 * Wird für einen Parameter{@code null} übergeben, wird eine
	 * {@linkplain IllegalArgumentException} geworfen. Ansonsten wird in der
	 * Datenbank nach allen Aktivitäten gesucht, die am übergebenen Wochentag
	 * stattfinden und an welchen der übergebene Mitarbeiter teilnimmt.
	 * 
	 * @param employee
	 *            - der Mitarbeiter, für welchen nach Aktivitäten gesucht werden
	 *            soll
	 * @param weekday
	 *            - der Tag, an welchem diese Aktivitäten liegen sollen
	 * @return Liste aller Aktivitäten, die am übergebenen Tag mit dem
	 *         übergebenen Mitarbeiter als Teilnehmer stattfinden, kann auch
	 *         leer sein
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Employee employee, Weekday weekday)
			throws DatasetException;

	/**
	 * Gibt eine Liste aller Aktivitäten für den übergebenen Mitarbeiter zurück,
	 * die mit dem übergebenen Zeitraum kollidieren. Wobei es nicht als
	 * Kollision gewertet wird, wenn der Startzeitpunkt einer Aktivität des
	 * Mitarbeiters gleich dem Endzeitpunkt der übergebenen Aktivität oder der
	 * Endzeitpunkt einer Aktivität des Mitarbeiters gleich dem Startzeitpunkt
	 * der übergebenen Aktivität bzw. der Startzeitpunkt der übergebenen
	 * Aktivität gleich dem Endzeitpunkt einer Aktivität des Mitarbeiters oder
	 * der Endzeitpunkt der übergebenen Aktivität gleich dem Startzeitpunkt
	 * einer Aktivität des Mitarbeiters ist.
	 * 
	 * @param employee
	 *            - Mitarbeiter, für welchen kollidierende Aktivitäten gesucht
	 *            werden sollen
	 * @param timePeriod
	 *            - Zeitraum, für welchen kollidierende Aktivitäten gesucht
	 *            werden sollen
	 * @return eine Liste der mit dem übergebenen Zeitraum kollidiernden
	 *         Aktivitäten des übergebenen Mitarbeiters. Eine leere Liste, wenn
	 *         keine Kollisionen vorliegen.
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Employee employee,
			TimePeriod timePeriod) throws DatasetException;

	/**
	 * Gibt eine Liste aller Aktivitäten für die übergebene Schulklasse zurück,
	 * die mit dem übergebenen Zeitraum kollidieren. Wobei es nicht als
	 * Kollision gewertet wird, wenn der Startzeitpunkt einer Aktivität der
	 * Schulklasse gleich dem Endzeitpunkt der übergebenen Aktivität oder der
	 * Endzeitpunkt einer Aktivität der Schulklasse gleich dem Startzeitpunkt
	 * der übergebenen Aktivität bzw. der Startzeitpunkt der übergebenen
	 * Aktivität gleich dem Endzeitpunkt einer Aktivität der Schulklasse oder
	 * der Endzeitpunkt der übergebenen Aktivität gleich dem Startzeitpunkt
	 * einer Aktivität der Schulklasse ist.
	 * 
	 * @param schoolclass
	 *            - Schulklasse, für welchen kollidierende Aktivitäten gesucht
	 *            werden sollen
	 * @param timePeriod
	 *            - Zeitraum, für welchen kollidierende Aktivitäten gesucht
	 *            werden sollen
	 * @return eine Liste der mit dem übergebenen Zeitraum kollidiernden
	 *         Aktivitäten der übergebenen Schulklasse. Eine leere Liste, wenn
	 *         keine Kollisionen vorliegen.
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Schoolclass schoolclass,
			TimePeriod timePeriod) throws DatasetException;

	/**
	 * Gibt eine Liste aller Aktivitäten für den übergebenen Raum zurück, die
	 * mit dem übergebenen Zeitraum kollidieren. Wobei es nicht als Kollision
	 * gewertet wird, wenn der Startzeitpunkt einer Aktivität des Raumes gleich
	 * dem Endzeitpunkt der übergebenen Aktivität oder der Endzeitpunkt einer
	 * Aktivität des Raumes gleich dem Startzeitpunkt der übergebenen Aktivität
	 * bzw. der Startzeitpunkt der übergebenen Aktivität gleich dem Endzeitpunkt
	 * einer Aktivität des Raumes oder der Endzeitpunkt der übergebenen
	 * Aktivität gleich dem Startzeitpunkt einer Aktivität des Raumes ist.
	 * 
	 * @param room
	 *            - Raum, für welchen kollidierende Aktivitäten gesucht werden
	 *            sollen
	 * @param timePeriod
	 *            - Zeitraum, für welchen kollidierende Aktivitäten gesucht
	 *            werden sollen
	 * @return eine Liste der mit dem übergebenen Zeitraum kollidiernden
	 *         Aktivitäten des übergebenen Raumes. Eine leere Liste, wenn keine
	 *         Kollisionen vorliegen.
	 * @throws DatasetException
	 */
	public List<Activity> getAllActivities(Room room, TimePeriod timePeriod)
			throws DatasetException;

	/**
	 * Gibt eine Liste aller in der Datenbank vorhandenen
	 * {@linkplain CompoundLesson}-Objekte zurück, wo das übergebene
	 * {@linkplain LessonType}-Objekt Teil ist. Tritt dabei ein Fehler auf, wird
	 * eine {@linkplain DatasetException} geworfen.
	 * 
	 * @param lessonType
	 *            - {@linkplain LessonType}-Objekt, das Teil der CompoundLessons
	 *            sein soll
	 * @return Liste der {@linkplain CompoundLesson}-Objekte, wo das übergebene
	 *         {@linkplain LessonType}-Objekt Teil ist.
	 * @throws DatasetException
	 */
	public List<CompoundLesson> getAllCompoundLessons(LessonType lessonType)
			throws DatasetException;

	/**
	 * Gibt eine Liste aller in der Datenbank vorhandenen {@linkplain Lesson}
	 * -Objekte zurück, wo das übergebene {@linkplain LessonType}-Objekt Teil
	 * ist. Tritt dabei ein Fehler auf, wird eine {@linkplain DatasetException}
	 * geworfen.
	 * 
	 * @param lessonType
	 *            - {@linkplain LessonType}-Objekt, das Teil der
	 *            {@linkplain Lesson}-Objekte sein soll
	 * @return Liste der {@linkplain Lesson}-Objekte, wo das übergebene
	 *         {@linkplain LessonType}-Objekt Teil ist.
	 * @throws DatasetException
	 */
	public List<Lesson> getAllLessons(LessonType lessonType)
			throws DatasetException;

	/**
	 * Gibt eine Liste aller in der Datenbank vorhandenen {@linkplain Meeting}
	 * -Objekte zurück, wo das übergebene {@linkplain MeetingType}-Objekt Teil
	 * ist. Tritt dabei ein Fehler auf, wird eine {@linkplain DatasetException}
	 * geworfen.
	 * 
	 * @param meetingType
	 *            - {@linkplain MeetingType}-Objekt, das Teil der
	 *            {@linkplain Meeting}-Objekte sein soll
	 * @return Liste der {@linkplain Meeting}-Objekte, wo das übergebene
	 *         {@linkplain MeetingType}-Objekt Teil ist.
	 * @throws DatasetException
	 */
	public List<Meeting> getAllMeetings(MeetingType meetingType)
			throws DatasetException;
}
