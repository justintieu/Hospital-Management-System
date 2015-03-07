import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementation of Service Layer to connect concrete DAO with Presentation later
 * @author Justin Tieu
 *
 */
public class ServiceLayer {
	private MedicalDAO md;
	
	public ServiceLayer() {
		md = new ConcreteMedicalDAO();
	}
	
	public void createDoctor(String name, String specialty) {
		Doctor doctor = new Doctor();
		doctor.setName(name);
		doctor.setSpecialty(specialty);
		md.create(doctor);
	}
	
	public Doctor retrieveDoctorObjectByName(String name) {
		return md.findDoctorByName(name);
	}
	
	public String retrieveDoctorForAdmin(String name, String specialty) {
		return md.findDoctorByNameAndSpecialty(name, specialty).adminToString();
	}
	
	public String retrieveDoctorForPatient(String name) {
		return md.findDoctorByName(name).toString();
	}
	
	public List retrieveDoctorBySpecialty(String specialty) {
		return md.findDoctorBySpecialty(specialty);
	}
	
	public void deleteDoctor(String name) {
		md.delete(name);
	}
	
	public void createPatient(String name, int month, int dayOfMonth, int year) {
		Patient patient = new Patient();
		patient.setName(name);
		patient.setDOB(new GregorianCalendar(year, month, dayOfMonth));
		md.create(patient);
	}
	
	public String retrievePatientObjectByNameAndDOB(String name, int month, int dayOfMonth, int year) {
		return md.findPatientByNameAndDOB(name, new GregorianCalendar(year, month, dayOfMonth)).toString();
	}
	
	public Patient retrievePatientObjectByName(String name) {
		return md.findPatientByName(name);
	}
	
	public String retrievePatientForAdmin(String name, int month, int dayOfMonth, int year) {
		return md.findPatientByNameAndDOB(name, new GregorianCalendar(year, month, dayOfMonth)).adminToString();
	}
	
	public String retrievePatientForDoctor(String name, int month, int dayOfMonth, int year) {
		return md.findPatientByNameAndDOB(name, new GregorianCalendar(year, month, dayOfMonth)).toString();
	}
	
	public void deletePatient(String name, int month, int dayOfMonth, int year) {
		md.delete(name, new GregorianCalendar(year, month, dayOfMonth));
	}
	
	public void createAppointment(String assignedDoctor, String assignedPatient, int month, int dayOfMonth, int year) {
		Appointment appointment = new Appointment();
		appointment.setAssignedDoctor(retrieveDoctorObjectByName(assignedDoctor));
		appointment.setAssignedPatient(retrievePatientObjectByName(assignedPatient));
		appointment.setAppointmentDate(new GregorianCalendar(year, month, dayOfMonth));
		Patient patient = md.findPatientByName(assignedPatient);
		patient.addAppointment(appointment);
		md.create(appointment);
	}
	
	public void retrieveAppointment() {
		
	}
	
	public void deleteAppointment(String assignedPatient, int month, int dayOfMonth, int year) {
		Patient patient = md.findPatientByName(assignedPatient);
		List<Appointment> appointments = patient.getAppointments();
		
		int appointmentId = 0;
		for(Appointment a: appointments) {
			if(a.getAppointmentDate().equals(new GregorianCalendar(year, month, dayOfMonth))) {
				
				appointmentId = a.getAppointmentId();
			}
		}
		md.delete(appointmentId);
	}
	
	public void createPrescription(String doctorName, String patientName, String medicationName) {
		Prescription prescription = new Prescription();
		Doctor assignedDoctor = md.findDoctorByName(doctorName);
		Patient assignedPatient = md.findPatientByName(patientName);
		assignedDoctor.addPrescription(prescription);
		assignedPatient.addPrescription(prescription);
		prescription.setDoctor(assignedDoctor);
		prescription.setPatient(assignedPatient);
		prescription.setMedicationName(medicationName);
		md.create(prescription);
	}
	
	public void retrievePrescription() {
		
	}
	
	public String retrieveAllAppointmentsByPatient(String patientName) {
		String result = "";
		List<Appointment> appointments = md.findAppointmentByPatient(patientName);
		for(Appointment a: appointments){
			result += a.toString() + "\n";
		}
		return result;
	}
	
	public String retrieveAllPrescriptionsByPatient(String patientName) {
		String result = ""; 
		List<Prescription> prescriptions = md.findPrescriptionsByPatient(patientName);
		for(Prescription p: prescriptions){
			result += p.toString() + "\n";
		}
		return result;
	}
}
