package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Patient;

import com.example.demo.repository.PatientRepo;
@Component
@SpringBootApplication
public abstract class HospitalSpringBootApplication implements CommandLineRunner  {
	private PatientRepo patientRepo;
	public HospitalSpringBootApplication(PatientRepo patientRepo) {
		this.patientRepo=patientRepo;
	}

    public static void main(String[] args) {
        SpringApplication.run(HospitalSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
  
        patientRepo.save(new Patient("Ibrahimi",23,"Onco","ibra@gamil.com","ibra.jpg"));
        patientRepo.save(new Patient("Allaoui",67,"Cardio","allaoui@gamil.com","allaoui.jpg"));
        patientRepo.save(new Patient("barnoussi",53,"Radio","bernoussi@gamil.com","ber.jpg"));
        patientRepo.save(new Patient("saadane",34,"Humato","saad@gamil.com","saad.jpg"));
        patientRepo.findAll().forEach(c->{
        System.out.println(c.getNom());
        		System.out.println(c.getAge());
    });
    }
}