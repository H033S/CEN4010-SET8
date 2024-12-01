import React, { useState } from "react";
import { useLocation } from "react-router";
import Helper from "../../helpers/Helper";
import AuthService from "../../services/AuthService";
import "../login/Login.css";

function TwoFaForm() {
  const location = useLocation();
  const [code, setCode] = useState("");
  const [error, setError] = useState("");

  const { twoFACode, username, password } = location.state || {};
  const authService = new AuthService();

  async function handleSubmit(e) {
    e.preventDefault();
    console.log("handleSubmit::Verification code entered:", code);
    const codeEnteredHashed = Helper.hashSecretUsingMD5(code);
    const now = new Date();
    const codeExpiration = Helper.getDateFromTokenDatetimeString(
      twoFACode.expiryAt,
    );

    if (!Helper.hashSecretsAreMatching(codeEnteredHashed, twoFACode.value)) {
      console.log("handleSubmit::Invalid Code was entered");
      setError(`Code ${code} is invalid. Please try again`);
      return;
    }
    if (codeExpiration < now) {
      console.log("handleSubmit::Code already expired");
      setError(`Code ${code} already expired`);
      return;
    }

    handleReset();
    console.log("handleSubmit::Valid Code was entered. Retrieving JWT token");
    const jwt = await authService.getJWTTokenAuthentication(username, password);
    console.log(`handleSubmit::Token received`);
    console.log("Need to redirect the user to Menu List Page");
  }

  function handleReset() {
    setCode("");
    setError("");
  }

  return (
    <div className="login">
      <h1>Enter Verification Code</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="verification-code">6-Digit Code</label>
          <input
            type="text"
            id="verification-code"
            className="form-control"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            minLength={6}
            maxLength={6}
            required
          />
          {error && error.length > 0 && (
            <div className="alert alert-danger">
              <p>{error}</p>
            </div>
          )}
        </div>
        <div className="d-grid gap-2 col-6 mx-auto">
          <button type="submit" className="bt-submit">
            <span className="text-wrapper">Submit</span>
          </button>
          <button
            type="button"
            className="bt-cancel"
            onClick={() => setCode("")}
          >
            <span className="text-wrapper">Cancel</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default TwoFaForm;
