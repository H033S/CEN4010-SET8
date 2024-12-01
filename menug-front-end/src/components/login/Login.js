import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";
import Helper from "../../helpers/Helper";
import AuthService from "../../services/AuthService";
import { useNavigate } from "react-router";

function Login() {
  const authService = new AuthService();
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState({
    username: [],
    password: [],
  });

  async function handleSubmission(e) {
    e.preventDefault(); //Avoid Reloading

    const usernameErrors = isValidUsername();
    const passwordErrors = []; //isValidPassword();

    if (
      (usernameErrors && usernameErrors.length === 0) &
      (passwordErrors && passwordErrors.length === 0)
    ) {
      try {
        const credentialsResponse = await authService.submitCredentials(
          username,
          password,
        );
        console.log(
          `handleSubmission:: Are credentials response valid?`,
          credentialsResponse.isValid,
        );

        if (credentialsResponse.isValid) {
          handleReset();
          console.log(
            `handleSubmission:: Login accepted... Need to redirect user to 2FAPage`,
          );
          navigate("/twofa", {
            state: {
              twoFACode: credentialsResponse.generated2FAToken,
              username: username,
              password: password,
            },
          });
          return;
        } else {
          console.log(
            `handleSubmission:: Invalid credentials need to set error`,
          );
          passwordErrors.push(
            "Credentials are invalid. Please check user and password",
          );
        }
      } catch (error) {
        alert(error);
      }
    }

    setError({
      username: [...usernameErrors],
      password: [...passwordErrors],
    });
  }

  function isValidUsername() {
    return Helper.validateUsername(username);
  }

  function isValidPassword() {
    return Helper.validatePassword(password);
  }

  function handleReset() {
    setUsername("");
    setPassword("");
    setError({
      username: [],
      password: [],
    });
  }

  return (
    <div className="login">
      <form onSubmit={handleSubmission}>
        <h1>Login</h1>

        {error.username && error.username.length > 0 && (
          <div className="alert alert-danger">
            <ul>
              {error.username.map((item, index) => (
                <li key={index}>{item}</li>
              ))}
            </ul>
          </div>
        )}

        <div className="mb-3">
          <label htmlFor="username" className="form-label">
            <i className="fas fa-user"></i> Username
          </label>
          <input
            id="username"
            placeholder="Enter your username"
            value={username}
            className="form-control"
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>

        {error.password && error.password.length > 0 && (
          <div className="alert alert-danger">
            <ul>
              {error.password.map((item, index) => (
                <li key={index}>{item}</li>
              ))}
            </ul>
          </div>
        )}

        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            <i className="fas fa-lock"></i> Password
          </label>
          <input
            id="password"
            placeholder="Enter your password"
            value={password}
            type="password"
            className="form-control"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="d-grid gap-2 col-6 mx-auto">
          <button type="submit" className="bt-submit">
            <span className="text-wrapper">Submit</span>
          </button>
          <button type="button" className="bt-cancel" onClick={handleReset}>
            <span className="text-wrapper">Cancel</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;
