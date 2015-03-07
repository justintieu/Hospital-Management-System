import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
/**
 * MedicialDAO interface for methods to be implemented in concrete method
 * @author justintieu
 *
 */
public interface MedicalDAO {
	void closeSessionFactory();
	
	/* create objects */
	Doctor create(Doctor doctor);
	Patient create(Patient patient);
	Prescription create(Prescription prescription);
	Appointment create(Appointment appointment);

	/* find doctor by x */
	Doctor findDoctorByName(String name);
	List findDoctorBySpecialty(String specialty);
	Doctor findDoctorByNameAndSpecialty(String name, String specialty);
	
	/* find patient by x */
	Patient findPatientByNameAndDOB(String name, Calendar dob);
	Patient findPatientByName(String name);
	
	/* find prescription by x */
	List findPrescriptionsByName(String medicationName);
	List findPrescriptionsByDoctor(String assignedDoctor);
	List findPrescriptionsByPatient(String assignedPatient);
	
	/* find appointment by x */
//	List findAppointmentByDoctor(String name);
//	List findAppointmentByPatient(String name);
//	List findAppointmentByDoctorAndPatient(String doctor, String patient);
//	List findAppointmentByDate(Calendar appointmentDate);
	List findAppointmentByPatientAndDate(String patient, Calendar appointmentDate);
	List findAppointmentByPatient(String patient);
	
	/* list all */
	List findAllDoctors();
	List findAllPatients();
	List findAllPrescriptions();
	List findAllAppointments();
	
	/* delete specific objects */
	boolean delete(Doctor doctor);
	boolean delete(Patient patient);
	boolean delete(Prescription prescription);
	boolean delete(Appointment appointment);
	
	boolean delete(String name);				// delete doctor by name
	boolean delete(String name, Calendar dob);	// delete patient by name and dob
	boolean delete(int appointmentId); // delete appointment
	
	/* delete appointment; if appointments > 1 then  by date for a patient by name and dob*/
	
}
