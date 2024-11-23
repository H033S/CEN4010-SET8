import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState({
    username: [],
    password: [],
  });

  function handleSubmission(e) {
    e.preventDefault(); //Avoid Reloading

    const usernameErrors = isValidUsername();
    const passwordErrors = isValidPassword();

    if (
      (usernameErrors && usernameErrors.length === 0) &
      (passwordErrors && passwordErrors.length === 0)
    ) {
        //Need to submit request in here
      handleReset();
      return;
    }

    setError({
      username: [...usernameErrors],
      password: [...passwordErrors],
    });
  }

  function isValidUsername() {
    const usernameErrors = [];

    if (username.length < 3) {
      console.log(`Username too short`);
      usernameErrors.push("Username must be at least 3 characters long");
    }

    return usernameErrors;
  }

  function isValidPassword() {
    const passwordErrors = [];

    if (password.length < 8) {
      console.log(`Password too short`);
      passwordErrors.push("Password must be at least 8 characters long");
    }
    if (!/[a-z]/.test(password)) {
      console.log(`Password does not contain lower case`);
      passwordErrors.push(
        "Password must contain at least one lowercase letter",
      );
    }
    if (!/[A-Z]/.test(password)) {
      console.log(`Password does not contain upper case`);
      passwordErrors.push(
        "Password must contain at least one uppercase letter",
      );
    }
    if (!/[0-9]/.test(password)) {
      console.log(`Password does not contain number`);
      passwordErrors.push("Password must contain at least one number");
    }

    return passwordErrors;
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
