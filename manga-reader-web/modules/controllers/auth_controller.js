import Properties from '../properties.js'
import Client from '../client/client.js'

class AuthController {
  constructor() {
    this.client = new Client(Properties.URL + "auth/")
    this.listeners = []

    //TODO: delete this later
    this.token = 'admin'
  }

  login(username, password, callback, error) {
    let payload = {
      username: username,
      password: password
    }
    this.client.post(
      'login',
      payload,
      (json) => {
        this.token = json.token
        callback(json)
        this.listeners.forEach((listener) => {
          listener.loggedIn()
        })
      },
      error
    )
  }
}

export default new AuthController()
