import React from 'react'
import authController from '../controllers/auth_controller'

export default class App extends React.Component {

  constructor() {
    super()
    this.state = {}
    this.style = {}
    this.style.container = {
      display: 'flex',
      justifyContent: 'center',
      paddingTop: '10px'
    }
    this.style.loginButton = {
      width: '100%'
    }
    this.login = this.login.bind(this)
  }

  login() {
    authController.login(
      this.refs.usernameField.value,
      this.refs.passwordField.value,
      () => {},
      (e) => {
        if(e.status == 401) {
          this.setState({
            errorMessage: e.responseText
          })
        }
      }
    )
  }

  render() {
    let errorMessage = <div></div>;
    if(this.state.errorMessage) {
      errorMessage = (
        <div className='ui error message'>
          <p>{this.state.errorMessage}</p>
        </div>
      )
    }
    return (
      <div style={{display: 'flex', flexDirection: 'column'}}>
        <div style={{display: 'flex', justifyContent: 'center', paddingTop: '10px'}}>
          {errorMessage}
        </div>
        <div style={this.style.container}>
          <div className='ui segment' style={this.style.segment}>
            <h1>Login to Manga Reader</h1>
            <div className='ui form'>
              <div className='field'>
                <label>Username</label>
                <input ref='usernameField' type='text' placeholder='Username'></input>
              </div>
              <div className='field'>
                <label>Password</label>
                <input ref='passwordField' type='password' placeholder='Password'></input>
              </div>
              <div className='ui button' style={this.style.loginButton} onClick={this.login}>Login</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
