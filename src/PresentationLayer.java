import java.util.Scanner;

/**
 * Implementation layer that allows user interaction
 * @author Justin Tieu
 *
 */
public class PresentationLayer {
	private String account_type = "";
	private Scanner sc;
	private ServiceLayer sl;
	
	public static void main(String[] args) {
		PresentationLayer pa = new PresentationLayer();
		pa.run();
	}
	
	public PresentationLayer() {
		account_type = "";
		sc = new Scanner(System.in);
		sl = new ServiceLayer();
		
		/* create three doctors and one dummy */
		sl.createDoctor("Eric Tam", "Surgeon");
		sl.createDoctor("Andres Chorro", "ER");
		sl.createDoctor("Anthony Tsang", "Neurology");
//		sl.createDoctor("Doctor", "None");
		
		/* create four patients + one dummy patient*/
		sl.createPatient("Jeffrey Su", 1, 2, 1993);
		sl.createPatient("Kunal Palwankar", 6, 20, 1993);
		sl.createPatient("Byron Custodio", 5, 20, 1993);
		sl.createPatient("Jessica Benedicto", 5, 4, 1990);
//		sl.createPatient("Patient", 1, 2, 1993);
		
		/* 2 patients has one appointment and one prescription */
		sl.createAppointment("Eric Tam", "Jeffrey Su", 4, 4, 2015);
		sl.createPrescription("Eric Tam", "Jeffrey Su", "Viagra");
		
		sl.createAppointment("Anthony Tsang", "Kunal Palwankar", 3, 8, 2015);
		sl.createPrescription("Anthony Tsang", "Kunal Palwankar", "Medical Marihuana");
		
		/* 2 patients has two appointments with different doctors and two prescriptions. */ 
//		sl.createAppointment("Eric Tam", "Byron Custodio", 4, 4, 2015);
//		sl.createPrescription("Eric Tam", "Byron Custodio", "Weed");
//		sl.createAppointment("Andres Chorro", "Byron Custodio", 4, 4, 2015);
//		sl.createPrescription("Andres Chorro", "Byron Custodio", "Viagra");
		
//		sl.createAppointment("Eric Tam", "Jessica Benedicto", 3, 8, 2015);
//		sl.createPrescription("Eric Tam", "Jessica Benedicto", "Nyquil");
//		sl.createAppointment("Anthony Tsang", "Jessica Benedicto", 3, 8, 2015);
//		sl.createPrescription("Anthony Tsang", "Jessica Benedicto", "Dayquil");
		
		/* delete dummy doctor and dummy patient */
//		sl.deleteDoctor("Doctor");
//		sl.deletePatient("Patient", 1, 2, 1993);
	}
	
	public void run() { 
		do {
			System.out.println("Welcome to the Online Hospital Management System");
			System.out.println("------------------------------------------------");
			
			first_menu();
			
			System.out.println("Thank you for using Online Hospital Management System!");
			System.out.println("We hope you have a good day! :)");
			System.out.println();
		} while(!account_type.equalsIgnoreCase("5"));
		sc.close();
	}
	
	public void first_menu() {
		System.out.println("Please select your account type:");
		System.out.println("1. Administrator");
		System.out.println("2. Staff");
		System.out.println("3. Doctor");
		System.out.println("4. Patient");
		System.out.println("5. Turn Off System");
		
		boolean input_is_not_valid = true;
		
		do {	
			System.out.print("Select action: ");
			account_type = sc.nextLine();
			if(account_type.equalsIgnoreCase("1")) {
				System.out.println("You have logged into Administrator.");
				input_is_not_valid = false;
			} else if(account_type.equalsIgnoreCase("2")) {
				System.out.println("You have logged into Staff.");
				input_is_not_valid = false;
			} else if(account_type.equalsIgnoreCase("3")) {
				System.out.println("You have logged into Doctor");
				input_is_not_valid = false;
			} else if(account_type.equalsIgnoreCase("4")) {
				System.out.println("You have logged into Patient");
				input_is_not_valid = false;
			} else if(account_type.equalsIgnoreCase("5")) {
				input_is_not_valid = false;
			} else {
				input_is_not_valid = true;
			}
			
			if(!input_is_not_valid && !account_type.equalsIgnoreCase("5")) {
				System.out.println("Loading your settings...");
			}
		} while(input_is_not_valid);
		
		System.out.println();
		while(Integer.parseInt(account_type) > 0 && !account_type.equalsIgnoreCase("5")) {
			second_menu(account_type);
		}
	}
	
	public void second_menu(String account_type) {
		if(account_type.equalsIgnoreCase("1")) {
			System.out.println("Administrator Control Panel");
			System.out.println("---------------------------");
			System.out.println("0. Quit Admin Session");
			System.out.println("1. Create doctor account");
			System.out.println("2. View doctor account");
			System.out.println("3. Delete doctor account");
			System.out.println("4. Create patient account");
			System.out.println("5. View patient account");
			System.out.println("6. Delete patient account");
			System.out.println();
			adminMenu();
			
		} else if(account_type.equalsIgnoreCase("2")) {
			System.out.println("Staff Control Panel");
			System.out.println("-------------------");
			System.out.println("0. Quit Staff Session");
			System.out.println("1. Create appointment");
			System.out.println("2. Cancel appointment");
			System.out.println();
			staffMenu();
		} else if(account_type.equalsIgnoreCase("3")) {
			System.out.println("Doctor Control Panel");
			System.out.println("--------------------");
			System.out.println("0. Quit Doctor Session");
			System.out.println("1. View patient information");
			System.out.println("2. Create prescription for patient");
			System.out.println();
			doctorMenu();
		} else if(account_type.equalsIgnoreCase("4")) {
			System.out.println("Patient Control Panel");
			System.out.println("---------------------");
			System.out.println("0. Quit Patient Session");
			System.out.println("1. View appointments");
			System.out.println("2. View prescriptions");
			System.out.println("3. View doctor's information");
			System.out.println();
			patientMenu();
		}
	}
	
//	options 0-6
	public void adminMenu() {
		System.out.print("Select action: ");
		String menu_option = sc.nextLine();
		if(menu_option.equalsIgnoreCase("0")) {
			account_type = "0";
		} else if(menu_option.equalsIgnoreCase("1")) {
//			create doctor
			System.out.println("Please give name of doctor");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give specialty of doctor");
			System.out.print(">> ");
			String specialty = sc.nextLine();
			sl.createDoctor(name, specialty);
		} else if(menu_option.equalsIgnoreCase("2")) {
//			view doctor (name & specialty)
			System.out.println("Please give name of doctor");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give specialty of doctor");
			System.out.print(">> ");
			String specialty = sc.nextLine();
			System.out.println(sl.retrieveDoctorForAdmin(name, specialty));
		} else if(menu_option.equalsIgnoreCase("3")) {
//			delete doctor by name
			System.out.println("Please enter name of doctor that you would like to delete");
			System.out.print(">> ");
			String name = sc.nextLine();
			sl.deleteDoctor(name);
		} else if(menu_option.equalsIgnoreCase("4")) {
//			create patient
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give month of birth");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of birth");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of birth");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			sl.createPatient(name, month-1, dayOfMonth, year);
		} else if(menu_option.equalsIgnoreCase("5")) {
//			view patient
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give month of birth");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of birth");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of birth");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			System.out.println(sl.retrievePatientForAdmin(name, month-1, dayOfMonth, year));
		} else if(menu_option.equalsIgnoreCase("6")) {
//			delete patient
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give month of birth");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of birth");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of birth");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			sl.deletePatient(name, month-1, dayOfMonth, year);
		} else {
			System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
			System.out.println();
		}
	}
	
//	options 0-2
	public void staffMenu() {
		System.out.print("Select action: ");
		String menu_option = sc.nextLine();
		if(menu_option.equalsIgnoreCase("0")) {
			account_type = "0";
		} else if(menu_option.equalsIgnoreCase("1")) {
//			create appointment - give doctor patient and date
			System.out.println("Please give name of doctor");
			System.out.print(">> ");
			String assignedDoctor = sc.nextLine();
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String assignedPatient = sc.nextLine();
			System.out.println("Please give month of appointment");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of appointment");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of appointment");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			sl.createAppointment(assignedDoctor, assignedPatient, month-1, dayOfMonth, year);
		} else if(menu_option.equalsIgnoreCase("2")) {
//			cancel appointment
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String assignedPatient = sc.nextLine();
			System.out.println("Please give month of appointment");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of appointment");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of appointment");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			sl.deleteAppointment(assignedPatient, month-1, dayOfMonth, year);
		} else {
			System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
			System.out.println();
		}
	}
	
//	options 0-2
	public void doctorMenu() {
		System.out.print("Select action: ");
		String menu_option = sc.nextLine();
		if(menu_option.equalsIgnoreCase("0")) {
			account_type = "0";
		} else if(menu_option.equalsIgnoreCase("1")) {
//			view patient info by name & dob
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("Please give month of birth");
			System.out.print(">> ");
			int month = Integer.parseInt(sc.nextLine());
			System.out.println("Please give day of month of birth");
			System.out.print(">> ");
			int dayOfMonth = Integer.parseInt(sc.nextLine());
			System.out.println("Please give year of birth");
			System.out.print(">> ");
			int year = Integer.parseInt(sc.nextLine());
			System.out.println(sl.retrievePatientForDoctor(name, month-1, dayOfMonth, year));
		} else if(menu_option.equalsIgnoreCase("2")) {
//			create prescription for patient
			System.out.println("Please give name of doctor");
			System.out.print(">> ");
			String doctorName = sc.nextLine();
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String patientName = sc.nextLine();
			System.out.println("Please give name of medicine");
			System.out.print(">> ");
			String medicationName = sc.nextLine();
			sl.createPrescription(doctorName, patientName, medicationName);
		} else {
			System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
			System.out.println();
		}
	}
	
//	options 0-3
	public void patientMenu() {
		System.out.print("Select action: ");
		String menu_option = sc.nextLine();
		if(menu_option.equalsIgnoreCase("0")) {
			account_type = "0";
		} else if(menu_option.equalsIgnoreCase("1")) {
//			view appointment list given patient name
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String patientName = sc.nextLine();
			System.out.println("Appointments:");
			System.out.println(sl.retrieveAllAppointmentsByPatient(patientName));
		} else if(menu_option.equalsIgnoreCase("2")) {
//			view prescription list given patient name 
			System.out.println("Please give name of patient");
			System.out.print(">> ");
			String patientName = sc.nextLine();
			System.out.println("Prescriptions:");
			System.out.println(sl.retrieveAllPrescriptionsByPatient(patientName));
		} else if(menu_option.equalsIgnoreCase("3")) {
//			view doctor info - ask for doctor name
			System.out.println("Please give name of doctor");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println(sl.retrieveDoctorForPatient(name));
		} else {
			System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
			System.out.println();
		}
	}
}
