import React, {useRef, useState, useEffect} from 'react';
import {useNavigate} from 'react-router-dom';
import {Col} from "react-bootstrap";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axiosInstance from "../axiosInstance";

interface SignInForm {
    username: string;
    password: string;
}

function SignIn() {
    const userRef = useRef<HTMLInputElement>(null);
    const [signInForm, setSignInForm] = useState<SignInForm>({
        username: '',
        password: ''
    });
    const [errorMessage, setErrorMessage] = useState('');
    const [fieldErrors, setFieldErrors] = useState<any | null>(null);
    const navigate = useNavigate();
    const apiURL = process.env.REACT_APP_API_URL;

    useEffect(() => {
        if (userRef.current) {
            userRef.current.focus();
        }
        // if (errorMessage !== '') {
        //     setErrorMessage('');
        // }
    }, [])

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (!signInForm) return;

        const payload = {
            username: signInForm.username,
            password: signInForm.password
        };


        axiosInstance.post(`${apiURL}/admin/api/auth`, payload)
            .then(response => {
                if (response.data) {
                    axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`
                    localStorage.setItem('token', response.data.token);
                    localStorage.setItem('username', response.data.username);
                    navigate("/admin/home");
                }
                if (response.data.error) {
                    setErrorMessage(response.data.error);
                }
            })
            .catch(error => {
                if (error.response) {
                    if (error.response.status === 400) {
                        const fieldErrors = error.response.data;
                        setFieldErrors(fieldErrors);
                    } else if (error.response.status === 403) {
                        setErrorMessage("Invalid username or password!");
                    } else if (error.response.status === 500) {
                        setErrorMessage("Server error. Please try again later.");
                    }
                } else {
                    setErrorMessage("An unexpected error occurred.");
                }
            })
    }

    const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setErrorMessage("");
        setFieldErrors("");
        const fieldName = event.target.name;
        const fieldValue = event.target.value;

        if (signInForm != null) {
            setSignInForm({
                ...signInForm,
                [fieldName]: fieldValue,
            });
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            {/*<div className="inline-flex-center">*/}
                <div className="border rounded shadow form-box">
                    <h1 className="mb-5">Sign In</h1>
                    <Form onSubmit={handleSubmit}>
                        <Col className="sign-in-form">
                            {errorMessage && <div className="alert alert-danger mb-2 text-center">{errorMessage}</div>}
                            <Form.Group className="mb-3" controlId="formUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="text" placeholder="Enter username"
                                              name="username"
                                              value={signInForm ? signInForm.username : ''}
                                              onChange={handleFieldChange}
                                              ref={userRef}/>
                                {fieldErrors && fieldErrors.username &&
                                    <div className="alert alert-danger" role="alert">{fieldErrors.username}</div>}
                            </Form.Group>
                            <Form.Group className="mb-3 mt-4" controlId="formPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Enter password"
                                              name="password"
                                              value={signInForm ? signInForm.password : ''}
                                              onChange={handleFieldChange}/>
                                {fieldErrors && fieldErrors.password &&
                                    <div className="alert alert-danger" role="alert">{fieldErrors.password}</div>}
                            </Form.Group>
                            <Button className="submit-btn mt-3" variant="primary" type="submit">
                                Submit</Button>
                        </Col>
                    </Form>
                </div>
            {/*</div>*/}
        </div>
    )
}

export default SignIn;