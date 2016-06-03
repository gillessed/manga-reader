class Messages {
  constructor() {
    this.list = []
  }

  newMessage(message, level) {
    this.list.push({
      message: message,
      level: level
    })
    if(!this.contentScreen) {
      console.log('Message content screen not set.')
    } else {
      this.contentScreen.forceUpdate()
    }
  }

  deleteMessage(message) {
    let index = this.list.indexOf(message)
    //TODO: delete item
  }

  errorMessage(message) {
    this.newMessage(message, 'error')
  }

  successMessage(message) {
    this.newMessage(message, 'success')
  }
}

export default new Messages()
