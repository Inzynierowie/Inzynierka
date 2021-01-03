import * as yup from "yup";

import { Button, Col, Container, Form, Row, Spinner } from "react-bootstrap";
import { Formik, FormikHelpers } from "formik";

import React from "react";
import { Redirect } from "react-router-dom";
import { useLoginPost } from "../../API/Doctors";

interface LoginProps {
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
  isLoggedIn: boolean;
}

interface LoginValuesI {
  email: string;
  password: string;
}

const initialValues: LoginValuesI = { email: "", password: "" };

const Login: React.FC<LoginProps> = ({ setIsLoggedIn, isLoggedIn }) => {
  const [data, loading, error, refetch, setUser] = useLoginPost();

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

  const onSubmit = async (values: LoginValuesI, actions: FormikHelpers<LoginValuesI>) => {
    await setUser({
      email: values.email,
      password: values.password,
    });

    if (data && !loading && !error) setIsLoggedIn(true);
  };

  return (
    <Container className="d-flex align-self-center justify-content-center">
      {!loading && error && (
        <Row>
          <Col className="shadow p-5 rounded">
            <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
              {({ touched, values, errors, isSubmitting, handleSubmit, validateForm, handleChange }) => (
                <Form
                  noValidate
                  onSubmit={async (e) => {
                    e.preventDefault();
                    validateForm()
                      .then(() => handleSubmit())
                      .catch((err) => console.error(err));
                  }}>
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
                    <Form.Text className="ml-1 text-muted text-small">
                      We'll never share your email with anyone else.
                    </Form.Text>
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
                    Log In
                  </Button>
                </Form>
              )}
            </Formik>
          </Col>
          <Col className="p-5">
            <h2>Welcome to the APP!</h2>
            <div className="mt-3">
              <p>
                We offer you the best way to track your appointments and the illness records dashboard. We allow our
                users to control their expenses regarding treatment as well as medicine prescriptions.
              </p>
              <p>
                If you're a new client you can register <a href="/register">here</a>.
              </p>
              <p>
                If you can't remember your password you can use the form <a href="/forgot_password">here</a>.
              </p>
            </div>
          </Col>
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

export default Login;
