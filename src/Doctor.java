import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Implementation of Doctor for CS157B Hospital System
 * @author Justin Tieu
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "findDoctorByName", query = "from Doctor where name = :name"),
	@NamedQuery(name = "findDoctorBySpecialty", query = "from Doctor where specialty = :specialty"),
	@NamedQuery(name = "findDoctorByNameAndSpecialty", query = "from Doctor where  name = :name and specialty = :specialty"),
	@NamedQuery(name = "findAllDoctors", query = "from Doctor")
})
public class Doctor extends Person {
	@OneToMany(mappedBy="assignedDoctor", targetEntity = Appointment.class, cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	@OneToMany(mappedBy="assignedDoctor", targetEntity = Prescription.class, cascade = CascadeType.ALL)
	private List<Prescription> prescriptions; 
	
	@ManyToMany
    @JoinTable(name = "Doctor_Patient",
	    joinColumns = {@JoinColumn(name="doctor_id")},
	    inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )
	private List<Patient> patients;
	
	@Column(name = "specialty")
	private String specialty;
	
	/**
	 * Creates new Doctor object
	 */
	public Doctor() {
		this.appointments = new ArrayList<Appointment>();
		this.prescriptions = new ArrayList<Prescription>();
		this.patients = new ArrayList<Patient>();
	}
	
	/**
	 * Gets doctor specialty
	 * @return specialty
	 */
	public String getSpecialty() {
		return specialty;
	}
	
	/**
	 * Set doctor specialty
	 * @param specialty 
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	/**
	 * Gets list of appointments assigned to doctor
	 * @return appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}
	
	/**
	 * Add new appointment assigned to doctor
	 * @param a - appointment 
	 */
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	/**
	 * Sets list of appointments assigned to doctor
	 * @param appointments
	 */
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	/**
	 * Gets list of prescription created by doctor
	 * @return
	 */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	
	/**
	 * Adds new prescription created by doctor
	 * @param p
	 */
	public void addPrescription(Prescription p) {
		prescriptions.add(p);
	}

	/**
	 * Sets list of prescriptions created by doctor
	 * @param prescriptions
	 */
	public void setPrescription(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	/**
	 * Gets list of patients
	 * @return
	 */
	public List<Patient> getPatients() {
		return patients;
	}
	
	/**
	 * Adds a new patient to doctor 
	 * @param p
	 */
	public void addPatient(Patient p) {
		patients.add(p);
	}

	/**
	 * Sets patients list for doctor
	 * @param patients
	 */
	public void setPatient(List<Patient> patients) {
		this.patients = patients;
	}
	
	/**
	 * to string method for admin account type
	 * @return
	 */
	public String adminToString() {
		return "Id: " + getId() + " - " + toString();
	}

	/**
	 * default to string method
	 */
	public String toString() {
		return "Name: " + getName() + " - Specialty: " + getSpecialty();
	}
}
