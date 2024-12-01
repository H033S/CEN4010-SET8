import Token2FA from "../models/Token2FA";

class CredentialsResponse {
  #isValid;
  #token2FA;

  /**
   * @param {boolean} isValid
   * @param {Token2FA} token2FA
   */
  constructor(isValid, token2FA) {
    this.#isValid = isValid;
    this.#token2FA = token2FA;
  }

  get isValid() {
    return this.#isValid;
  }

  get generated2FAToken() {
    return this.#token2FA;
  }
}

export default CredentialsResponse;
