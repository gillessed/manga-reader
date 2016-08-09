import React from 'react'
import AddManga from './add_manga'
import AddAuthor from './add_author'
import ListAuthors from './list_authors'
import AddTag from './add_tag'
import ListTags from './list_tags'
import serverConstants from '../model/server_constants'
import catalogueController from '../controllers/catalogue_controller'
import events from './events'
import messages from './messages'

export default class Content extends React.Component {

  constructor() {
    super()
    serverConstants.listeners.push(this)
    events.register('screen', <AddManga/>)
    events.listen('screen', this.onScreenChanged)
    messages.contentScreen = this
    this.state = {
      screen: <AddManga/>
    }
    this.style = {}
    this.style.container = {
      padding: '20px',
      display: 'flex',
      flexDirection: 'column'
    }
    this.style.sidebarButton = {
      border: '1px solid black',
      background: 'rgb(210,210,220)',
      cursor: 'pointer'
    }
    this.style.titleBar = {
      display:'flex',
      alignItems:'center'
    }
    this.style.title = {
      margin: '0px',
      padding: '0px',
      marginLeft: '10px'
    }
    this.toggleMenu = this.toggleMenu.bind(this)
    this.openScreen = this.openScreen.bind(this)
    this.deleteMessage = this.deleteMessage.bind(this)
  }

  componentDidMount() {
    $('#app-sidebar').sidebar({
      context: $('#app-content')
    }).sidebar('setting', {
      dimPage: false,
      transition: 'overlay'
    })
  }

  toggleMenu() {
    $('#app-sidebar').sidebar('show')
  }

  onScreenChanged() {
    //TODO: clear messages
    this.setState({
      screen: events.get('screen')
    })
  }

  openScreen(screen) {
    return () => {
      $('#app-sidebar').sidebar('hide')
      //TODO: clear messages
      this.setState({
        screen: screen
      })
    }
  }

  componentWillMount() {
    if(!serverConstants.loaded) {
      serverConstants.load()
    }
  }

  loadedConstants() {
    this.forceUpdate()
  }

  deleteMessage(message) {
    return () => {
      messages.deleteMessage(message)
      this.forceUpdate()
    }
  }

  render() {
    let loader = <div></div>
    if(!serverConstants.loaded) {
      loader = (
        <div className='ui active dimmer'>
          <div className='ui large text loader'>Loading</div>
        </div>
      )
    }
    let appMessages = messages.list.map((message, index) => {
      return (
        <div key={index} className={'ui ' + message.level + ' message'}>
          <p>{message.message}</p>
          <i className='icon close' onClick={this.deleteMessage(message)}></i>
        </div>
      )
    })
    return (
      <div>
        <div id='app-sidebar' className='ui vertical inverted inline menu sidebar'>
          <div className='item'>
            <b style={{fontSize: '32px'}}>Manga Reader</b>
          </div>
          <div className='item'>
            <div className='ui icon input'>
              <i className='search icon'></i>
              <input type='text' placeholder='Search...'/>
            </div>
          </div>
          <div className='item'>
            <div className='header'>Manga</div>
            <div className='menu'>
              <a className='item' onClick={this.openScreen(<AddManga/>)}>Add</a>
              <a className='item'>List</a>
            </div>
          </div>
          <div className='item'>
            <div className='header'>Authors</div>
            <div className='menu'>
              <a className='item' onClick={this.openScreen(<AddAuthor/>)}>Add</a>
              <a className='item' onClick={this.openScreen(<ListAuthors/>)}>List</a>
            </div>
          </div>
          <div className='item'>
            <div className='header'>Tags</div>
            <div className='menu'>
              <a className='item' onClick={this.openScreen(<AddTag/>)}>Add</a>
              <a className='item' onClick={this.openScreen(<ListTags/>)}>List</a>
            </div>
          </div>
          <div className='item'>
            <div className='header'>User</div>
            <div className='menu'>
              <a className='item'>Logout</a>
            </div>
          </div>
        </div>
        <div id='app-content' style={{height: '100vh'}} className='pusher'>
          <div style={this.style.container}>
            <div style={this.style.titleBar}>
              <div onClick={this.toggleMenu}>
                <i style={this.style.sidebarButton} className='big circular sidebar icon'></i>
              </div>
              <h1 style={this.style.title}>Manga Reader</h1>
            </div>
            {appMessages}
            {this.state.screen}
          </div>
          {loader}
        </div>
      </div>
    );
  }
}
