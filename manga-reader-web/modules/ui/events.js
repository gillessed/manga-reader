class Events {
  register(name, value) {
    this[name] = {
      value: value,
      listeners: []
    }
  }

  get(name) {
    return this[name].value
  }

  set(name, value) {
    this[name].value = value
    this[name].listeners.forEach((listener) => {
      listener.valueChanged()
    })
  }

  listen(name, listener) {
    this[name].listeners.push(listener)
  }
}

export default new Events()
