import React from 'react'
import serverConstants from '../model/server_constants'
import catalogueController from '../controllers/catalogue_controller'
import messages from './messages'

export default class AddManga extends React.Component {

  constructor() {
    super()
    this.state = {
      creating: false
    }
    this.style = {}
    this.style.submitButton = {
      width: '100%'
    }
    this.createAuthor = this.createAuthor.bind(this)
  }

  createAuthor() {
    this.setState({
      creating: true
    })
    let author = {}
    serverConstants.languages.forEach((language) => {
      author[language] = this.refs['input_' + language].value
    })
    catalogueController.createAuthor(
      author,
      (json) => {
        serverConstants.languages.forEach((language) => {
          author[language] = this.refs['input_' + language].value = 0
        })
        this.setState({
          creating: false
        })
        messages.successMessage('Successfully created author.')
      },
      (error, s, e) => {
        console.log(error)
        messages.errorMessage('There was a problem creating the author: ' + error.responseText)
        this.setState({
          creating: false
        })
      }
    )
  }

  render() {
    let fields = serverConstants.languages.map((language) => {
      return (
        <div key={language} className='field'>
          <label>{language}</label>
          <input ref={'input_' + language}type='text'></input>
        </div>
      )
    })
    return (
      <div className='ui segment'>
        <h2>Add Author to Database</h2>
        <div className='ui form'>
          {fields}
          <button className={'ui button' + (this.state.creating ? ' loading' : '')} style={this.style.submitButton} onClick={this.createAuthor}>
            Create Author
            </button>
        </div>
      </div>
    );
  }
}
