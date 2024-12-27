// Noreen Akhtar
package computinganisbn;

import javax.swing.JOptionPane;

public class ComputingAnISBN {

    public static void main(String[] args) {

        boolean keepGoing = true;  // This will control the loop

        while (keepGoing) {
            // Prompt the user for the first 9 digits
            String input = JOptionPane.showInputDialog("Enter the first 9 digits of an ISBN number:");

            // Initialize the ISBN digits and checksum variable
            int isbnDigits = 0;
            int checksum = 0;

            // Try to parse the input as an integer
            try {
                isbnDigits = Integer.parseInt(input); // Parse input as an integer

                // Ensure the input is exactly 9 digits
                if (input.length() != 9) {
                    JOptionPane.showMessageDialog(null, "Please enter exactly 9 digits.");
                    continue;  // Go back to the top of the loop to prompt again
                }

                // Initialize the array for digits extraction
                int[] digits = new int[9];
                for (int n = 8; n >= 0; n--) {
                    digits[n] = isbnDigits % 10;
                    isbnDigits /= 10;
                }

                // Compute the checksum using the provided formula
                checksum = 0;  // Re-initialize before checksum calculation
                for (int n = 0; n < 9; n++) {
                    checksum += (n + 1) * digits[n];
                }
                checksum %= 11;  // Modulus 11 arithmetic

                // Build the complete ISBN-10 number
                StringBuilder isbn10 = new StringBuilder(input);
                if (checksum == 10) {
                    isbn10.append("X");  // If checksum is 10, append 'X'
                } else {
                    isbn10.append(checksum);  // Append the checksum digit
                }

                // Display ISBN-10 number
                JOptionPane.showMessageDialog(null, "The ISBN-10 number is: " + isbn10);

                // Ask if the user wants to enter another number
                int response = JOptionPane.showConfirmDialog(null, "Do you want to enter another?");
                if (response == JOptionPane.NO_OPTION) {
                    keepGoing = false;  // Stop the loop if the user selects "NO"
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter only numerical digits.");
            }
        }
    }
}
