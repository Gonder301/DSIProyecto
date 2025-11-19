package com.mycompany.asiproyecto.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class test extends JFrame {

    public test() {
        setTitle("LocalDate Picker Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // 1. Create a DatePickerSettings object (Optional, for formatting)
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy"); // Format: 2025-11-18
        //dateSettings.setAllowKeyboardEditing(false); // Force use of the calendar popup

        // 2. Create the DatePicker
        DatePicker datePicker = new DatePicker(dateSettings);
        
        // Set a default value (e.g., today)
        datePicker.setDate(LocalDate.now());

        // 3. Create a button to get the value
        JButton saveButton = new JButton("Get Date");
        JLabel resultLabel = new JLabel("Selected: " + LocalDate.now());

        // 4. Add Action Listener to retrieve the LocalDate
        saveButton.addActionListener(e -> {
            // METHOD TO GET THE DATA:
            LocalDate selectedDate = datePicker.getDate();

            if (selectedDate != null) {
                resultLabel.setText("Selected: " + selectedDate.toString());
                System.out.println("Saved to variable: " + selectedDate);
            } else {
                resultLabel.setText("No date selected");
            }
        });

        // Add components to Frame
        add(new JLabel("Pick a Date:"));
        add(datePicker);
        add(saveButton);
        add(resultLabel);
        
        setLocationRelativeTo(null); // Center on screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new test().setVisible(true);
        });
    }
}