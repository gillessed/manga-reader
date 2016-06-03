import Properties from '../properties'
import Client from '../client/client'
import authController from './auth_controller'

class CatalogueController {
  constructor() {
    this.client = new Client(Properties.URL + 'catalogue/', authController)
  }

  getLanguages(callback, error) {
    this.client.get('languages', callback, error)
  }

  getTags(filter, callback, error) {
    this.client.post('tags', {substring: filter}, callback, error)
  }

  createTag(tag, callback, error) {
    this.client.post('add/tag', {languageMap: tag}, callback, error)
  }

  editTag(tagId, language, value, callback, error) {
    this.client.post('edit/tag', {
      tagId: tagId,
      language: language,
      value: value
    }, callback, error)
  }

  deleteTag(tagId, callback, error) {
      this.client.post('delete/tag', {id: tagId}, callback, error)
  }

  getAuthors(filter, callback, error) {
    this.client.post('authors', {substring: filter}, callback, error)
  }

  createAuthor(author, callback, error) {
    this.client.post('add/author', {languageMap: author}, callback, error)
  }

  editAuthor(authorId, language, value, callback, error) {
    this.client.post('edit/author', {
      authorId: authorId,
      language: language,
      value: value
    }, callback, error)
  }

  deleteAuthor(authorId, callback, error) {
      this.client.post('delete/author', {id: authorId}, callback, error)
  }
}

export default new CatalogueController()
