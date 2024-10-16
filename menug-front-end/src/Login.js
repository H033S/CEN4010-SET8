import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function handleSubmission() {
        console.log('submitted')
    }

  return (
    <div className="login">
      <form onSubmit={handleSubmission}>
        <h1>Login</h1>
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
          />
        </div>
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
          />
        </div>
        <div className="d-grid gap-2 col-6 mx-auto">
          <button type="submit" className="bt-submit">
            <span className="text-wrapper">Submit</span>
          </button>
          <button type="reset" className="bt-cancel">
            <span className="text-wrapper">Cancel</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;
