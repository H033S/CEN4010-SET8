class Token2FA {
  id;
  value;
  expiryAt;

  /**
   * @param {string} id - Id use to save the token
   * @param {string} value - Hashed version of the token sent by email
   * @param {string} expiryAt - Token expiration date and time
   */
  constructor(id, value, expiryAt) {
    this.id = id;
    this.value = value;
    this.expiryAt = expiryAt;
  }
}

export default Token2FA;
