import events from './events'

class Messages {
  constructor() {
    this.list = []
    events.listen('screen', this.onScreenChanged)
  }

  onScreenChanged() {
    this.list.clear()
      this.contentScreen.forceUpdate()
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
    this.list.splice(index, 1)
  }

  errorMessage(message) {
    this.newMessage(message, 'error')
  }

  successMessage(message) {
    this.newMessage(message, 'success')
  }
}

export default new Messages()
