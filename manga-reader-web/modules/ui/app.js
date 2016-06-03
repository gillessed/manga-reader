import React from 'react'
import Content from './content.js'
import Login from './login.js'
import authController from '../controllers/auth_controller'
import catalogueController from '../controllers/catalogue_controller'

export default class App extends React.Component {

  constructor() {
    super()
    this.style = {}
    authController.listeners.push(this)
  }

  loggedIn() {
    this.forceUpdate()
  }
  
  render() {
    let displayedScreen
    if(authController.token) {
      displayedScreen = <Content/>
    } else {
      displayedScreen = <Login/>
    }
    return (
      <div>
        {displayedScreen}
      </div>
    );
  }
}
