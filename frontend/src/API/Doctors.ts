import { useState } from "react";
import useAxios from "axios-hooks";
// TODO: For future use in building endpoints
// import { DoctorI, PatientI, AppointmentI, MedicalFacilityI, PatientMedicalDataI, PriceI } from "./Interfaces";
import { AppointmentI } from "./Interfaces";
import { api_base } from "../config";

export const useAppointmentGetter = (appointmentId: AppointmentI["id"]) => {
  const [id, setId] = useState(appointmentId);

  const [{ data, loading, error }, refetch] = useAxios(`${api_base}appointment/select/${id}`);

  return [data, loading, error, refetch];
};
