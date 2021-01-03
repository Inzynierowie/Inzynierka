import React from "react";

export interface DoctorI {
  id: number;
  name: string;
  surname: string;
  specialist: string;
}
export interface PatientI {
  id: number;
  name: string;
  surname: string;
  insured: boolean;
}
export interface AppointmentI {
  id: number;
  patient: PatientI;
  doctor: DoctorI;
  cost: number;
  appointmentDate: number; // LocalDateTime ??
}
export interface MedicalFacilityI {
  id: number;
  name: string;
  localization: string;
  doctorCount: number;
  patientCount: number;
  contactNumber: number;
}
export interface PatientMedicalDataI {
  id: number;
  patient: PatientI;
  doctor: DoctorI;
  treatmentDate: number; // LocalDateTime ??
  medicalProcedure: string;
  additionalNotes: string;
}
export interface PriceI {
  id: number;
  treatment: string;
  price: number;
}

// TODO: Expand User Interface so we can add it to LoginHook and reuse later on.
