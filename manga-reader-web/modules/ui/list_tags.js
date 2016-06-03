import React from 'react'
import TagCell from './tag_cell'
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
    this.deleteTag = this.deleteTag.bind(this)
    this.load = this.load.bind(this)
    this.filterTags = this.filterTags.bind(this)
    this.onFilterChange = this.onFilterChange.bind(this)
  }

  componentWillMount() {
    this.load()
  }

  load() {
    this.state.tagList = null
    this.setState({deleting: false, loaded: false})
    catalogueController.getTags(
      this.state.filter,
      (json) => {
        this.setState({
          tagList: json,
          loaded: true
        })
      },
      (error) => {
        messages.errorMessage('Error retrieving tags from server.')
        console.log(error)
        this.setState({
          loaded: true
        })
      }
    )
  }

  deleteTag(tagId) {
    return () => {
      this.setState({deleting: true})
      catalogueController.deleteTag(
        tagId,
        () => {
          messages.successMessage('Deleted tag with id ' + tagId)
          this.load()
        },
        (error) => {
          messages.errorMessage('Error deleting tag with id ' + tagId + ': ' + error.reponseText)
          console.log(error)
          this.load()
        }
      )
    }
  }

  filterTags() {
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
    if(this.state.tagList) {
      tableBody = this.state.tagList.map((tag, index) => {
        let entries = serverConstants.languages.map((language, languageIndex) => {
          return (
            <td key={index + ',' + languageIndex}>
              <TagCell value={tag.languageMap[language]} language={language} tag={tag.id}/>
            </td>
          )
        })
        return (
          <tr key={index}>
            {entries}
            <td style={{display: 'flex', justifyContent: 'center'}}>
              <button className='ui icon button' onClick={this.deleteTag(tag.id)}><i className='icon delete'></i></button>
              <button className='ui icon button'><i className='icon search'></i></button>
            </td>
          </tr>
        )
      })
      tableBody = <tbody>{tableBody}</tbody>
    }
    return (
      <div className={'ui segment' + (this.state.loaded || this.state.editing ? '' : ' loading')}>
        <h1>Tags</h1>
        <div className='ui form'>
          <div className='icon field'>
            <div style={{display: 'flex'}}>
              <input ref='filterInput' type='text' placeholder='Filter' value={this.state.displayedFilter} onChange={this.onFilterChange}></input>
              <button className='ui icon button' onClick={this.filterTags}><i className='ui icon search'></i></button>
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
