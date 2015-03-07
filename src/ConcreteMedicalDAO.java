import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Implementation of MedicalDAO methods for ServiceLayer
 * @author Justin Tieu
 *
 */
public class ConcreteMedicalDAO implements MedicalDAO {
	private SessionFactory sessionFactory;
	private Session session;
	
	/**
	 * Creates concrete medical dao object with 
	 * implementation of methods for service layer
	 */
	public ConcreteMedicalDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}
	
	/**
	 * Closes sessions
	 */
	public void closeSessionFactory() {
		this.session.close();
		this.sessionFactory.close();
	}
	
	/** 
	 * CREATE METHODS
	 */
	
	/**
	 * Create doctor object
	 */
	@Override
	public Doctor create(Doctor doctor) {
		session.beginTransaction();
		session.save(doctor);
		session.getTransaction().commit();
		return doctor;
	}

	/**
	 * Creates patient object
	 */
	@Override
	public Patient create(Patient patient) {
		session.beginTransaction();
		session.save(patient);
		session.getTransaction().commit();
		return patient;
	}
	
	/**
	 * Creates prescription object
	 */
	@Override
	public Prescription create(Prescription prescription) {
		session.beginTransaction();
		session.save(prescription);
		session.getTransaction().commit();
		return prescription;
	}
	
	/**
	 * Creates appointment object
	 */
	@Override
	public Appointment create(Appointment appointment) {
		session.beginTransaction();
		appointment.getAssignedDoctor().addPatient(appointment.getAssignedPatient());
		session.save(appointment);
		session.getTransaction().commit();
		return appointment;
	}

	/**
	 * DOCTOR METHODS
	 */
	
	/**
	 * find doctor by name 
	 */
	@Override
	public Doctor findDoctorByName(String name) {
		session.beginTransaction();
		Query query = session.getNamedQuery("findDoctorByName");
		query.setString("name", name);
		session.getTransaction().commit();
		Doctor doctor = (Doctor) query.list().get(0);
		return doctor;
	}

	/**
	 * find doctor by specialty
	 */
	@Override
	public List findDoctorBySpecialty(String specialty) {
		session.beginTransaction();
		Query query = session.getNamedQuery("findDoctorBySpecialty");
		query.setString("specialty", specialty);
		session.getTransaction().commit();
		return query.list().size() == 0 ? null : query.list();
	}

	/**
	 * find doctor by name and specialty
	 */
	@Override
	public Doctor findDoctorByNameAndSpecialty(String name, String specialty) {
		session.beginTransaction();
		Query query = session.getNamedQuery("findDoctorByNameAndSpecialty");
		query.setString("name", name);
		query.setString("specialty", specialty);
		session.getTransaction().commit();
		
		return query.list().size() == 0 ? null : (Doctor) query.list().get(0);
	}
	
	/**
	 * PATIENT METHODS
	 */
	
	/**
	 * find patient by name and dob
	 */
	@Override
	public Patient findPatientByNameAndDOB(String name, Calendar dob) {
		session.beginTransaction();
		Query query = session.getNamedQuery("findPatientByNameAndDOB");
		query.setString("name", name);
		query.setCalendar("dob", dob);
		session.getTransaction().commit();
		Patient patient = (Patient) query.list().get(0);
		return patient;
	}
	
	/**
	 * find patient by name
	 */
	@Override
	public Patient findPatientByName(String name) {
		session.beginTransaction();
		Query query = session.getNamedQuery("findPatientByName");
		query.setString("name", name);
		session.getTransaction().commit();

		return query.list().size() == 0 ? null : (Patient) query.list().get(0);
	}
	
	/**
	 * PRESCRIPTION METHODS
	 */
	
	/**
	 * find prescription by name
	 * unimplemented method
	 */
	@Override
	public List findPrescriptionsByName(String medicationName) {
		return null;
	}
	
	/**
	 * find prescription by doctor
	 * unimplemented method
	 */
	@Override
	public List findPrescriptionsByDoctor(String assignedDoctor) {
		return null;
	}
	
	/**
	 * find prescription by patient name
	 */
	@Override
	public List findPrescriptionsByPatient(String assignedPatient) {
		Patient p = findPatientByName(assignedPatient);
		return p.getPrescriptions();
	}
	
	/**
	 * APPOINTMENT METHODS
	 */
	
	/**
	 * find appointment by patient and appointment date 
	 */
	@Override
	public List findAppointmentByPatientAndDate(String patient, Calendar appointmentDate) {
		Patient p = findPatientByName(patient);
		Query query = session.getNamedQuery("findAppointmentByDate").setParameter(0, p.getId()).setParameter(1, appointmentDate);
		return query.list();
	}
	
	/**
	 * find appointment by patient name
	 */
	@Override
	public List findAppointmentByPatient(String patient) {
		Patient p = findPatientByName(patient);
		return p.getAppointments();
	}
	
	/**
	 * FIND ALL METHODS
	 */
	
	/**
	 * find all doctors
	 */
	@Override
	public List findAllDoctors() {
		Query queryResult = session.getNamedQuery("findAllDoctors");
		return queryResult.list();
	}

	/**
	 * find all patients
	 */
	@Override
	public List findAllPatients() {
		Query queryResult = session.getNamedQuery("findAllPatients");
		return queryResult.list();
	}

	/**
	 * find all prescriptions
	 */
	@Override
	public List findAllPrescriptions() {
		Query queryResult = session.getNamedQuery("findAllPrescriptions");
		return queryResult.list();
	}

	/**
	 * find all appointments
	 */
	@Override
	public List findAllAppointments() {
		Query queryResult = session.getNamedQuery("findAllAppointments");
		return queryResult.list();
	}

	
	/**
	 * DELETE METHODS
	 */
	
	/**
	 * delete doctor object
	 */
	@Override
	public boolean delete(Doctor doctor) {
		session.beginTransaction();
		
		// delete any existing appointments for doctor 
//		List appointments = findAppointmentByDoctor(doctor.getName());
//		for(Object o:appointments) {
//			session.delete((Appointment) o);
//		}
		// end of appointment delete
		session.delete(doctor);
		session.getTransaction().commit();
		return true;

	}

	/**
	 * delete patient object
	 */
	@Override
	public boolean delete(Patient patient) {
		session.beginTransaction();
		// delete any existing appointments for patient 
//		List appointments = findAppointmentByPatient(patient.getName());
//		for(Object o:appointments) {
//			session.delete((Appointment) o);
//		}
		// end of appointment delete
		
		session.delete(patient);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * delete prescription object
	 */
	@Override
	public boolean delete(Prescription prescription) {
		session.beginTransaction();

		session.delete(prescription);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * delet appointment object
	 */
	@Override
	public boolean delete(Appointment appointment) {
		session.beginTransaction();
		session.delete(appointment);
		session.getTransaction().commit();
		return true;
	}

	/**
	 * delete doctor by name
	 */
	@Override
	public boolean delete(String name) {
		Doctor doctor = findDoctorByName(name);
		return delete(doctor);
	}

	/**
	 * Delete patient by name and dob
	 */
	@Override
	public boolean delete(String name, Calendar dob) {
		Patient patient = findPatientByNameAndDOB(name, dob);
		return delete(patient);
	}
	
	/**
	 * Delete appointment by id
	 */
	@Override
	public boolean delete(int appointmentId) {
		session.clear();
		session.beginTransaction();
		Appointment appointment = (Appointment) session.get(Appointment.class, appointmentId);
		session.delete(appointment);
		session.getTransaction().commit();
		return true;
	}
}
