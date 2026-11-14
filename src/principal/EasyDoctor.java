package principal;

import java.io.InputStream;
import java.util.Scanner;
import mycollections.LinkedMap;
import mycollections.SinglyLinkedList;

public class EasyDoctor {
    private LinkedMap<String, SinglyLinkedList<String>> associatedIllness;
    private SinglyLinkedList<String> patientSymptoms;

    // Constructor to initialize the attributes and read the file
    public EasyDoctor() {
        associatedIllness = new LinkedMap<>();
        patientSymptoms = new SinglyLinkedList<>();

        // Load the file using getResourceAsStream
        InputStream inputStream = getClass().getResourceAsStream("Data_EN.txt");
        if (inputStream == null) {
            System.err.println("Error: Data_EN.txt file not found in the resources folder.");
            return;
        }

        try (Scanner scanner = new Scanner(inputStream)) {
            readFile(scanner);
        } catch (Exception e) {
            System.err.println("Error initializing AIDoctor: " + e.getMessage());
        }
    }

    // Method to read the data from the file
    public void readFile(Scanner data) {
        while (data.hasNextLine()) {
            String line = data.nextLine().trim();
            // System.out.println("Reading line: " + line); // Debug statement

            // Skip empty or incorrectly formatted lines
            if (line.isEmpty() || !line.contains(":")) {
                System.err.println("Invalid or empty line, skipping: " + line);
                continue;
            }

            // Split the line into parts
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String illness = parts[0].trim();
                String symptomsPart = parts[1].trim();

                if (illness.isEmpty() || symptomsPart.isEmpty()) {
                    System.err.println("Illness or symptoms are empty, skipping line: " + line);
                    continue;
                }

                // Create the list of symptoms
                SinglyLinkedList<String> symptomList = new SinglyLinkedList<>();
                for (String symptom : symptomsPart.split(",")) {
                    String trimmedSymptom = symptom.trim().toLowerCase();
                    if (!symptom.isEmpty()) {
                        symptomList.addLast(trimmedSymptom);
                        // System.out.println("Added symptom: " + trimmedSymptom); // Debug statement
                    }
                }

                // Add to the data structure if symptoms list is valid
                if (!symptomList.isEmpty()) {
                    try {
                        associatedIllness.put(illness, symptomList);
                    } catch (Exception e) {
                        System.err.println("Error adding illness: " + illness + ", error: " + e.getMessage());
                    }
                } else {
                    System.err.println("No valid symptoms found for illness: " + illness);
                }
            } else {
                System.err.println("Line format incorrect, skipping: " + line);
            }
        }
    }

    // Show the list of symptoms
    public void showSymptomList() {
        System.out.println("The possible symptoms are:");
        if (associatedIllness == null || associatedIllness.isEmpty()) {
            System.out.println("No illnesses available.");
            return;
        }

        // List to store unique symptoms
        SinglyLinkedList<String> uniqueSymptoms = new SinglyLinkedList<>();

        for (String illness : associatedIllness.keySet()) {
            SinglyLinkedList<String> symptoms = associatedIllness.get(illness);
            if (symptoms != null && !symptoms.isEmpty()) {
                for (String symptom : symptoms) {
                    boolean isAlreadyAdded = false;

                    // Check if the symptom is already in the unique list
                    for (String uniqueSymptom : uniqueSymptoms) {
                        if (uniqueSymptom.equalsIgnoreCase(symptom)) {
                            isAlreadyAdded = true;
                            break;
                        }
                    }

                    // Add if not already present
                    if (!isAlreadyAdded) {
                        uniqueSymptoms.addLast(symptom.trim());
                    }
                }
            }
        }

        // Display unique symptoms
        if (uniqueSymptoms.isEmpty()) {
            System.out.println("No symptoms found.");
        } else {
            for (String symptom : uniqueSymptoms) {
                System.out.print("{" + symptom + "} ");
            }
            System.out.println();
        }
    }

    // Show the list of patient symptoms that have been entered
    public String patientSymptoms() {
        return patientSymptoms.toString();
    }

    // Remove all symptoms from the patient
    public void clearPatientSymptoms() {
        patientSymptoms = new SinglyLinkedList<>();
    }

    // Show the list of diseases associated with the symptoms
    public void showDiseases(int nSymptoms) {
        if (patientSymptoms.isEmpty()) {
            System.out.println("\nThe patient symptoms are: None!");
            System.out.println("\nThe possible ilness that match any symptom are:");
            System.out.println("\tNone!");
            System.out.println("\nPossible illness that match at least 2 symptoms are:");
            System.out.println("\tNone!");
            System.out.println("\nTake care!");
            return;
        }

        LinkedMap<String, Integer> diseaseCount = new LinkedMap<>();

        for (String symptom : patientSymptoms) {
            for (String illness : associatedIllness.keySet()) {
                SinglyLinkedList<String> symptoms = associatedIllness.get(illness);
                boolean symptomFound = false;

                // Check if the symptom exists for the illness
                for (String s : symptoms) {
                    if (s.equals(symptom)) {
                        symptomFound = true;
                        break;
                    }
                }

                if (symptomFound) {
                    int currentCount = 0;

                    // Check manually if the illness already exists in diseaseCount
                    for (String key : diseaseCount.keySet()) {
                        if (key.equals(illness)) {
                            currentCount = diseaseCount.get(illness);
                            break;
                        }
                    }
                    diseaseCount.put(illness, currentCount + 1);
                }
            }
        }

        // Show the patient's symptoms once they are finished
        System.out.print("\nThe patient symptoms are: ");
        for (String symptom : patientSymptoms) {
            System.out.print(symptom + ", ");
        }

        System.out.println("\nThe possible ilness that match any symptom are:");
        for (String illness : diseaseCount.keySet()) {
            System.out.println("\tDisease: " + illness + " corresponds to " + diseaseCount.get(illness) + " symptom(s)");
        }

        System.out.println("\nPossible illness that match at least " + nSymptoms + " symptoms are:");
        boolean found = false; // Flag to track if any illness matches
        for (String illness : diseaseCount.keySet()) {
            int count = diseaseCount.get(illness);
            //System.out.println("Checking illness: " + illness + ", count: " + count);
            if (count >= nSymptoms) {
                System.out.println("\tDisease: " + illness + " matches " + count + " symptoms");
                found = true; // Set flag to true if condition is met
            }
        }
        if (!found) {
            System.out.println("No illness matches the given threshold of symptoms.");
        }
        System.out.println("\nTake care!");
    }

    // Prompt user for symptoms and validate them
    public void getSymptoms() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.print("Enter a symptom or 'done' if you are finished: ");
        String symptom = scanner.nextLine().trim().toLowerCase();

        if (symptom.equalsIgnoreCase("done")) {
            break;
        }

        // Normalizing the symptom input (remove extra spaces)
        symptom = symptom.trim();

        // Check if the symptom exists in associatedIllness
        boolean symptomExists = false;

        for (String illness : associatedIllness.keySet()) {
            SinglyLinkedList<String> symptoms = associatedIllness.get(illness);
            for (String s : symptoms) {
                if (s.trim().toLowerCase().equals(symptom)) {
                    symptomExists = true;
                    break;
                }
            }
            if (symptomExists) {
                break;
            }
        }

        // If the symptom exists, add it if not already added to the patientSymptoms list
        if (symptomExists) {
            // Check if the symptom is already in the patient's symptoms list using equals()
            boolean alreadyAdded = false;
            for (String s : patientSymptoms) {
                if (s.equals(symptom)) {
                    alreadyAdded = true;
                    break;
                }
            }

            if (!alreadyAdded) {
                patientSymptoms.addFirst(symptom);
                System.out.println("Symptom added: " + symptom + "\n");
            } else {
                System.out.println("Symptom already added: " + symptom + "\n");
            }
        } else {
            System.out.println("Symptom not recognized. Please try again.\n");
        }

        // Show available symptoms after each input
        showSymptomList();
    }

    // Show diseases based on patient symptoms
    showDiseases(patientSymptoms.size());

    // Clear symptoms for a new session
    clearPatientSymptoms();
}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String symptom : patientSymptoms) {
            sb.append(symptom).append(" ");
        }
        return sb.toString().trim();
    }
}