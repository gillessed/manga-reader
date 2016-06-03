import React from 'react'
import AuthorCell from './author_cell'
import catalogueController from '../controllers/catalogue_controller'
import serverConstants from '../model/server_constants'
import messages from './messages'

export default class AddManga extends React.Component {

  constructor() {
    super()
    this.state = {
      loaded: false,
      deleting: false,
      filter: '',
      displayedFilter: ''
    }
    this.deleteAuthor = this.deleteAuthor.bind(this)
    this.load = this.load.bind(this)
    this.filterAuthors = this.filterAuthors.bind(this)
    this.onFilterChange = this.onFilterChange.bind(this)
  }

  componentWillMount() {
    this.load()
  }

  load() {
    this.state.authorList = null
    this.setState({deleting: false, loaded: false})
    catalogueController.getAuthors(
      this.state.filter,
      (json) => {
        this.setState({
          authorList: json,
          loaded: true
        })
      },
      (error) => {
        messages.errorMessage('Error retrieving authors from server.')
        console.log(error)
        this.setState({
          loaded: true
        })
      }
    )
  }

  deleteAuthor(authorId) {
    return () => {
      this.setState({deleting: true})
      catalogueController.deleteAuthor(
        authorId,
        () => {
          messages.successMessage('Deleted author with id ' + authorId)
          this.load()
        },
        (error) => {
          messages.errorMessage('Error deleting author with id ' + authorId + ': ' + error.reponseText)
          console.log(error)
          this.load()
        }
      )
    }
  }

  filterAuthors() {
    this.state.filter = this.state.displayedFilter
    this.load()
  }

  onFilterChange(event) {
    this.setState({
      displayedFilter: this.refs.filterInput.value
    })
  }

  render() {
    let tableHeaders = serverConstants.languages.map((language, index) => {
      return <th key={index}>{language}</th>
    })
    tableHeaders = <tr>{tableHeaders} <th></th></tr>

    let tableBody = <tbody></tbody>
    if(this.state.authorList) {
      tableBody = this.state.authorList.map((author, index) => {
        let entries = serverConstants.languages.map((language, languageIndex) => {
          return (
            <td key={index + ',' + languageIndex}>
              <AuthorCell value={author.languageMap[language]} language={language} author={author.id}/>
            </td>
          )
        })
        return (
          <tr key={index}>
            {entries}
            <td style={{display: 'flex', justifyContent: 'center'}}>
              <button className='ui icon button' onClick={this.deleteAuthor(author.id)}><i className='icon delete'></i></button>
              <button className='ui icon button'><i className='icon search'></i></button>
            </td>
          </tr>
        )
      })
      tableBody = <tbody>{tableBody}</tbody>
    }
    return (
      <div className={'ui segment' + (this.state.loaded || this.state.editing ? '' : ' loading')}>
        <h1>Authors</h1>
        <div className='ui form'>
          <div className='icon field'>
            <div style={{display: 'flex'}}>
              <input ref='filterInput' type='text' placeholder='Filter' value={this.state.displayedFilter} onChange={this.onFilterChange}></input>
              <button className='ui icon button' onClick={this.filterAuthors}><i className='ui icon search'></i></button>
            </div>
          </div>
        </div>
        <table className='ui celled striped table'>
          <thead>{tableHeaders}</thead>
          {tableBody}
        </table>
      </div>
    );
  }
}
