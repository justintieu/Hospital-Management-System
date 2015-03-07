import javax.persistence.*;

/**
 * Implementation of Prescription for CS157B Hospital System
 * @author Justin Tieu
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllPrescriptions", query = "from Prescription"),
	@NamedQuery(name = "findPrescriptionsByName", query = "from Prescription where medicationName = :medicationName"),
	@NamedQuery(name = "findPrescriptionsByDoctor", query = "from Prescription where assignedDoctor = :assignedDoctor"),
	@NamedQuery(name = "findPrescriptionsByPatient", query = "from Prescription where assignedPatient = :assignedPatient"),
})
@Table(name="Prescriptions")
public class Prescription {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int prescriptionId;
	
	@Column(name = "medicationName")
	private String medicationName;
	
	@ManyToOne
	@JoinColumn(name = "assignedDoctor")
	private Doctor assignedDoctor;
	
	@ManyToOne
	@JoinColumn(name = "assignedPatient")
	private Patient assignedPatient;
	
	/**
	 * gets prescription id
	 */
	public int getPrescriptionId() {
		return prescriptionId;
	}
	
	/**
	 * set prescription id
	 * @param prescriptionId
	 */
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	
	/**
	 * gets prescription's medication name
	 * @return
	 */
	public String getMedicationName() {
		return medicationName;
	}
	
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	
	/**
	 * get doctor who assigned prescription
	 * @return
	 */
	public Doctor getDoctor() {
		return assignedDoctor;
	}
	
	/**
	 * set name of doctor for prescription
	 * @param assignedDoctor
	 */
	public void setDoctor(Doctor assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}
	
	/**
	 * get patient prescription is prescribed to
	 * @return
	 */
	public Patient getPatient() {
		return assignedPatient;
	}
	
	/**
	 * set patient to prescribe prescription
	 * @param assignedPatient
	 */
	public void setPatient(Patient assignedPatient) {
		this.assignedPatient = assignedPatient;
	}
	
	/**
	 * default prescription to string
	 */
	public String toString() {
		return "Drug: " + getMedicationName() + " Doctor: " + getDoctor();
	}
}
