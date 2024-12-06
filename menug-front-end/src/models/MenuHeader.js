class MenuHeader {
  id;
  name;
  creationDate;

  /**
   * Constructor
   *
   * @param {string} id - Menu Id
   * @param {string} name - Menu Name
   * @param {string} creationDate - Menu Creation Date
   */
  constructor(id, name, creationDate) {
    this.id = id;
    this.name = name;
    this.creationDate = creationDate;
  }
}

export default MenuHeader;
