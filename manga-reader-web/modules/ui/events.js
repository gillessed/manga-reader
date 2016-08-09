class Events {
  register(name, value) {
    if(this[name]) {
      this[name].value = value
    } else {
      this[name] = {
        value: value,
        listeners: []
      }
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
    if(!this[name]) {
      this[name] = {
        value: null,
        listeners: []
      }
    }
    this[name].listeners.push(listener)
  }
}

export default new Events()
