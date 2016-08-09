import React from 'react'

export default class AddManga extends React.Component {

  constructor() {
    super()
  }

  componentDidMount() {
    $(this.refs.authorDropdown).dropdown()
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
            <div className='fields'>
              <div className='fifteen wide field'>
                <div ref='authorDropdown' className='ui search selection dropdown'>
                  <input type='hidden'></input>
                  <i className = 'dropdown icon'></i>
                  <div className='default text'>Author</div>
                  <div className='menu'>
                    <div className='item'>Gobou</div>
                  </div>
                </div>
              </div>
              <div className='one wide field'>
                <button className='ui icon button' style={{width: '100%'}}><i className='add icon'></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
