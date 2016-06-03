import React from 'react'
import catalogueController from '../controllers/catalogue_controller'
import messages from './messages'

export default class AddManga extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      value: this.props.value,
      editing: false,
      saving: false
    }
    this.style = {}
    this.style.container = {
      cursor: 'pointer',
      width: '100%',
      height: '100%'
    }
    this.edit = this.edit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.save = this.save.bind(this)
    this.discard = this.discard.bind(this)
  }

  edit() {
    if(!this.state.editing) {
      this.setState({
        editing: true
      })
    }
  }

  onChange(event) {
    this.setState({
      value: event.target.value
    })
  }

  save() {
    this.setState({
      saving: true
    })
    catalogueController.editTag(this.props.tag, this.props.language, this.state.value,
    () => {
      this.setState({
        editing: false,
        saving: false,
      })
    }, (error, h, e) => {
      console.log(error)
      console.log(h)
      console.log(e)
      messages.errorMessage('Error saving change: ' + error.responseText)
      this.setState({
        editing: true,
        saving: false,
      })
    })
  }

  discard() {
    this.setState({
      editing: false,
      saving: false,
      value: this.props.value
    })
  }

  render() {
    let loader = <div></div>
    if(this.state.saving) {
      loader = (
        <div className='ui active dimmer'>
          <div className='ui small loader'></div>
        </div>
      )
    }
    let display = <span>{this.state.value}</span>
    if(this.state.editing) {
      display = (
        <div style={{display:'flex'}}>
          <input type='text' value={this.state.value} onChange={this.onChange}></input>
          <button className='ui icon button' onClick={this.save}><i className='ui icon save'></i></button>
          <button className='ui icon button' onClick={this.discard}><i className='ui icon close'></i></button>
        </div>
      )
    }
    return (
      <div className='ui form' style={this.style.container} onClick={this.edit}>
        {display}
        {loader}
      </div>
    )
  }
}
