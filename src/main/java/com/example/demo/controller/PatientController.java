package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private PatientRepo patientRepo;
    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return (List<Patient>) patientRepo.findAll();
    }
    @GetMapping("/patients/{id}")
    public Object getPatientById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if (optionalPatient.isPresent()) {
            return ResponseEntity.ok(optionalPatient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/patients")
    void addPatient(@RequestBody Patient patient) {
        patientRepo.save(patient);
    }
    @DeleteMapping("/patients/{id}")
	public boolean deletePatient(@PathVariable Long id) {
	    patientRepo.deleteById(id); 
	    return true; 
    }
    @PutMapping("/patients/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();
            existingPatient.setNom(updatedPatient.getNom());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setService(updatedPatient.getService());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setPhoto(updatedPatient.getPhoto());
            // Set other properties as needed
            
            patientRepo.save(existingPatient); // Save the updated patient
            
            return ResponseEntity.ok(existingPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
