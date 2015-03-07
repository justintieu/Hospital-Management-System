import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import javax.print.Doc;

/**
 * Implementation of Patient for CS157B Hospital System
 * @author Justin Tieu
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "findPatientByName", query = "from Patient where name = :name"),
	@NamedQuery(name = "findPatientByDOB", query = "from Patient where dob = :dob"),
	@NamedQuery(name = "findPatientByNameAndDOB", query = "from Patient where name = :name and dob = :dob"),
	@NamedQuery(name = "findAllPatients", query = "from Patient")
})
public class Patient extends Person {
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Calendar dob;
	
	@OneToMany(mappedBy="assignedPatient", targetEntity = Appointment.class, cascade = CascadeType.REMOVE)
	private List<Appointment> appointments;
	
	@OneToMany(mappedBy="assignedPatient", targetEntity = Prescription.class, cascade = CascadeType.REMOVE)
	private List<Prescription> prescriptions; 
	
	@ManyToMany
    @JoinTable(name = "Doctor_Patient",
	    joinColumns = {@JoinColumn(name="patient_id")},
	    inverseJoinColumns = {@JoinColumn(name = "doctor_id")}
    )
	private List<Doctor> doctors;
	
	/**
	 * creates patient object
	 */
	public Patient() {
		this.appointments = new ArrayList<Appointment>();
		this.prescriptions = new ArrayList<Prescription>();
		this.doctors = new ArrayList<Doctor>();
	}
	
	/**
	 * get patient's date of birth
	 * @return
	 */
	public Calendar getDOB() {
		return dob;
	}
	
	/**
	 * sets date of birth for patient
	 * @param dob
	 */
	public void setDOB(Calendar dob) {
		this.dob = dob;
	}
	
	/**
	 * get list of appointments 
	 * @return appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}
	
	/**
	 * add patient's new appointment
	 * @param a
	 */
	public void addAppointment(Appointment a) {
		appointments.add(a);
	}
	
	/**
	 * sets list of appointments for patient
	 * @param appointments
	 */
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	/**
	 * gets patient's prescription list
	 * @return
	 */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	
	/**
	 * adds new prescription to patient
	 * @param p
	 */
	public void addPrescription(Prescription p) {
		prescriptions.add(p);
	}

	/**
	 * sets patient's list of prescriptions
	 * @param prescriptions
	 */
	public void setPrescription(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	/**
	 * gets patient's list of doctors
	 * @return doctors
	 */
	public List<Doctor> getDoctors() {
		return doctors;
	}
	
	/**
	 * adds new doctor to patient's doctor list
	 * @param d new doctor
	 */
	public void addDoctor(Doctor d) {
		doctors.add(d);
	}

	/**
	 * sets list of doctors for patient
	 * @param doctors
	 */
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	/**
	 * to string method for admin in presentation layer
	 * @return
	 */
	public String adminToString() {
		return "Id: " + getId() + " - " + toString();
	}
	
	/**
	 * default to string method for patient
	 */
	public String toString() {
		return "Patient: " + getName() + " - DOB: " + (getDOB().get(2)+1) + "/" +  getDOB().get(5) + "/" + getDOB().get(1);
	}
}
