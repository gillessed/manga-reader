import React from 'react'

export default class AddManga extends React.Component {

  constructor() {
    super()
  }

  render() {
    return (
      <div className='ui segment'>
        <h2>Add Manga to Database</h2>
        <div className='ui form'>
          <div className='field'>
            <label>Title</label>
            <input type='text' placeholder='Title'></input>
          </div>
          <div className='field'>
            <label>Author</label>
            <input type='text' placeholder='Author'></input>
          </div>
        </div>
      </div>
    );
  }
}
