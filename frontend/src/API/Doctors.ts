// TODO: For future use in building endpoints
// import { DoctorI, PatientI, AppointmentI, MedicalFacilityI, PatientMedicalDataI, PriceI } from "./Interfaces";
import { AppointmentI } from "./Interfaces";
import { api_base } from "../config";
// import jwt_decode from "jsonwebtoken";
import useAxios from "axios-hooks";
// import { useCookies } from "react-cookie";
import { useState } from "react";

export const useAppointmentGetter = (appointmentId: AppointmentI["id"]) => {
  const [id, setId] = useState(appointmentId);

  const [{ data, loading, error }, refetch] = useAxios(`${api_base}appointment/select/${id}`);

  return [data, loading, error, refetch];
};

export const useLoginPost = (login?: string, password?: string) => {
  //   const [cookies, setCookie, removeCookie] = useCookies();
  const [user, setUser] = useState({
    email: login,
    password: password,
  });

  // USE COOKIES TO DECODE JWT TOKEN IF IT EXISTS

  //   if (cookies?.loggedUser) {
  //     console.log(cookies?.loggedUser);
  //   }

  const [{ data, loading, error }, refetch] = useAxios({ url: `${api_base}login`, method: "POST", data: user });

  //   if (data) {
  //     setCookie("loggedUser", data);
  //   }

  return [data, loading, error, refetch, setUser];
};

export interface UserDataI {
  name: string;
  surname: string;
  email: string;
  password: string;
  role: string;
}

export const useRegisterPost = (userData?: UserDataI) => {
  const [user, setUser] = useState({
    name: userData?.name,
    surname: userData?.surname,
    email: userData?.email,
    password: userData?.password,
    role: userData?.role,
  });

  const [{ data, loading, error }, refetch] = useAxios({ url: `${api_base}register`, method: "POST", data: user });

  return [data, loading, error, refetch, setUser];
};
