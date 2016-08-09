import catalogueController from '../controllers/catalogue_controller'

class ServerConstants {

  constructor() {
    this.loaded = false
    this.listeners = []
  }

  load() {
    this.loadLanguages()
  }

  loadLanguages() {
    catalogueController.getLanguages(
      (languagesJson) => {
        this.languages = languagesJson
        this.doneLoading()
      },
      (error) => {
        console.log('Error retrieving server constants.')
        console.log(error)
      }
    )
  }

  doneLoading() {
    this.loaded = true
    this.listeners.forEach((listener) => {
      listener.loadedConstants()
    })
  }
}

export default new ServerConstants()
