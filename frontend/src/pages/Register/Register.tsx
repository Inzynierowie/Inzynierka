import * as yup from "yup";

import { Button, Col, Container, Form, Row, Spinner } from "react-bootstrap";
import { Formik, FormikHelpers } from "formik";
import { UserDataI, useRegisterPost } from "../../API/Doctors";

import React from "react";

interface LoginProps {
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
  isLoggedIn: boolean;
}

const initialValues: UserDataI = {
  email: "",
  password: "",
  name: "",
  surname: "",
  role: "",
};
const Register: React.FC = () => {
  const [data, loading, error, refetch, setUser] = useRegisterPost();
  let validationSchema = yup.object().shape({
    email: yup
      .string()
      .required("* Email is required")
      .email("* Must be a valid email address")
      .min(2, "* Email must have at least 2 characters")
      .max(100, "* Email can't be longer than 100 characters"),
    password: yup
      .string()
      .required("* Password is required")
      .min(2, "* Password must have at least 2 characters")
      .max(100, "* Email can't be longer than 100 characters"),
  });

  const onSubmit = async (values: UserDataI, actions: FormikHelpers<UserDataI>) => {
    console.log(values);
  };

  return (
    <Container className="d-flex align-self-center justify-content-center">
      {!loading && error && (
        <Row>
          <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
            {({ touched, values, errors, isSubmitting, handleSubmit, validateForm, handleChange }) => (
              <Form
                className="shadow rounded p-5"
                noValidate
                onSubmit={async (e) => {
                  e.preventDefault();
                  validateForm()
                    .then(() => handleSubmit())
                    .catch((err) => console.error(err));
                }}>
                <div className="mb-3 p-3">
                  Register to access the applications full version or <a href="/login">login</a> if you already have an
                  account!
                </div>
                <div className="d-flex">
                  <Col className="">
                    <Form.Group>
                      <Form.Label>First name</Form.Label>
                      <Form.Control
                        onChange={handleChange}
                        id="name"
                        name="name"
                        type="text"
                        placeholder="Enter your name"
                        value={values.name}
                        isValid={touched.name && !errors.name}
                      />
                      {errors.email && <Form.Text className="ml-2 text-danger text-small">{errors.email}</Form.Text>}
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>Last name</Form.Label>
                      <Form.Control
                        onChange={handleChange}
                        type="text"
                        id="surname"
                        name="surname"
                        placeholder="Enter your surname"
                        value={values.surname}
                        isValid={touched.surname && !errors.surname}
                      />
                      {errors.password && (
                        <Form.Text className="ml-2 text-danger text-small">{errors.password}</Form.Text>
                      )}
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>Role</Form.Label>
                      <Form.Control
                        onChange={handleChange}
                        type="text"
                        id="role"
                        name="role"
                        className="text-capitalize"
                        placeholder="Select your role"
                        value={values.role}
                        isValid={touched.role && !errors.role}
                        as="select"
                        custom>
                        <option value="ROLE_DOCTOR">doctor</option>
                        <option value="ROLE_PATIENT">patient</option>
                      </Form.Control>
                      {errors.password && (
                        <Form.Text className="ml-2 text-danger text-small">{errors.password}</Form.Text>
                      )}
                    </Form.Group>
                  </Col>
                  <Col className="">
                    <Form.Group>
                      <Form.Label className={errors ? ".text-danger" : ""}>Email address</Form.Label>
                      <Form.Control
                        onChange={handleChange}
                        id="email"
                        name="email"
                        type="email"
                        placeholder="Enter your email address"
                        value={values.email}
                        isValid={touched.email && !errors.email}
                      />
                      {errors.email && <Form.Text className="ml-2 text-danger text-small">{errors.email}</Form.Text>}
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>Password</Form.Label>
                      <Form.Control
                        onChange={handleChange}
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Enter your password"
                        value={values.password}
                        isValid={touched.password && !errors.password}
                      />
                      {errors.password && (
                        <Form.Text className="ml-2 text-danger text-small">{errors.password}</Form.Text>
                      )}
                    </Form.Group>
                    <Button variant="info" type="submit">
                      Register
                    </Button>
                  </Col>
                </div>{" "}
              </Form>
            )}
          </Formik>
        </Row>
      )}
      {loading && !error && (
        <Spinner animation="border" variant="info" role="status">
          <span className="sr-only">Loading...</span>
        </Spinner>
      )}
      {data && !loading && !error && <div style={{ wordBreak: "break-all" }}>{JSON.stringify(data)}</div>}
    </Container>
  );
};

export default Register;
