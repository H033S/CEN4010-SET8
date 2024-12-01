import CryptoJS from "crypto-js";

class Helper {
  /**
   * Validate password to make sure it meets
   * password requirements for the system
   *
   * @param {string} password - Password submitted by User
   * @returns {string[]} Errors found during validation
   */
  static validatePassword(password) {
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

  /**
   * Validate username to make sure it meets
   * username requirements for the system
   *
   * @param {string} username - Username submitted by User
   * @returns {string[]} - Errors founds during validation
   */
  static validateUsername(username) {
    const usernameErrors = [];

    if (username.length < 3) {
      console.log(`Username too short`);
      usernameErrors.push("Username must be at least 3 characters long");
    }

    return usernameErrors;
  }

  /**
   * Helper that will be used to hash all secrets manage in the system
   *
   * @param {string} secret - Secret to be hashed
   * @returns {string}
   */
  static hashSecretUsingMD5(secret) {
    return CryptoJS.MD5(secret).toString(CryptoJS.enc.Hex);
  }

  /**
   * Compare if two secrets entered are the same
   *
   * @param {string} secret1 - First secret two be used in comparison
   * @param {string} secret2 - Second secret two be used in comparison
   *
   * @return {boolean}
   *
   */
  static hashSecretsAreMatching(secret1, secret2) {
    return secret1 === secret2;
  }

  /**
   * Generate Date out of a datetime value of the form
   * `yyyy-mm-dd'T'HH:mm:ss`
   *
   * @param {string} datetime - Datetime value in string format
   * @returns {Date}
   *
   */
  static getDateFromTokenDatetimeString(datetime) {
    return new Date(datetime);
  }
}

export default Helper;
