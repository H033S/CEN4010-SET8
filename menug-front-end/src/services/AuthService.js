import CredentialsResponse from "../dto/CredentialsResponse";
import Token2FA from "../models/Token2FA";

class AuthService {
  /**
   * Submit user credential to /api/v1/auth/2fa-code
   * to be validated notifying the user with a
   * 2FA Code sent though email
   *
   * @param {string} username
   * @param {string} password
   *
   * @returns {CredentialsResponse}
   *
   */
  async submitCredentials(username, password) {
    const basicAuthHeader = this.#createBasicAuthenticationHeader(
      username,
      password,
    );

    try {
      console.log("submitCredentials::Making post request");
      const response = await fetch(`/api/v1/auth/2fa-code`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: basicAuthHeader,
        },
      });

      console.log("submitCredentials::Response received");
      return this.#handleSubmitCredentialsResponse(response);
    } catch (error) {
      console.error(`Error happened ${error}`);
      throw error;
    }
  }

  /**
   * Create the header that can be added as authentication
   * using passed username and password
   *
   * @param {string} username
   * @param {string} password
   */
  #createBasicAuthenticationHeader(username, password) {
    const credentials = `${username}:${password}`;
    const encoded = btoa(unescape(encodeURIComponent(credentials)));
    return `Basic ${encoded}`;
  }

  /**
   * Handle response from SubmitCredentials Object.
   *
   * @param {Response} response - Response received from endpoint
   *
   * @returns {CredentialsResponse}
   *
   */
  async #handleSubmitCredentialsResponse(response) {
    console.log(
      `handleSubmitCredentialsResponse::Status Code Received: ${response.status}`,
    );
    switch (response.status) {
      case 200:
        const token = await response.json();
        console.log(
          `handleSubmitCredentialsResponse::Status 200 - Returning Credentials Response with Token ${JSON.stringify(token)}`,
        );
        const credentialsResponse = new CredentialsResponse(
          true,
          new Token2FA(token.id, token.value, token.expiryAt),
        );
        console.log(
          `handleSubmitCredentialsResponse::Object being generated:`,
          credentialsResponse,
        );
        return credentialsResponse;

      case 401:
        console.log(
          "handleSubmitCredentialsResponse::Status 401 - Invalid Credentials",
        );
        return new CredentialsResponse(false, null);

      default:
        const message = `Unexpected code was Received! Status Code: ${response.status}`;
        console.error(message);
        throw new Error(message);
    }
  }

  /**
   * Retrieve JWT form /api/v1/auth/token
   *
   * @param {string} username - Username
   * @param {string} password - Password
   *
   * @returns {string} JWT token
   *
   */
  async getJWTTokenAuthentication(username, password) {
    console.log("getJWTTokenAuthentication::Making request");
    const basicAuthHeader = this.#createBasicAuthenticationHeader(
      username,
      password,
    );

    try {
      const response = await fetch("/api/v1/auth/token", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: basicAuthHeader,
        },
      });
      console.log(
        `getJWTTokenAuthentication::Response received ${response.status}`,
      );

      if (!response.ok) {
        console.error(`Response was not ok. Error happened`);
        throw new Error("Credentials are not valid");
      }

      const jwtCode = await response.text();
      console.log(`getJWTTokenAuthentication::Returning JWT ${jwtCode}`);
      return jwtCode;
    } catch (error) {
      const uError = `Unexpected error: ${error}`;
      console.error(uError);
      throw new Error(uError);
    }
  }
}

export default AuthService;
