export default class Client {
  constructor(url, authController) {
    this.url = url
    if(authController) {
      $.ajaxSetup({
        beforeSend: function(xhr) {
          xhr.setRequestHeader('X-Auth-Token', authController.token);
        }
      });
    }
  }

  post(path, payload, callback, error) {
    $.ajax({
      url: this.url + path,
      type: 'POST',
      dataType: 'json',
      data: JSON.stringify(payload),
      contentType: 'application/json; charset=utf-8'
    }).done((json) => {
      callback(json)
    }).fail((h, x, e) => {
      error(h, x, e)
    })
  }

  get(path, callback, error) {
    $.ajax({
      url: this.url + path,
      type: 'GET'
    }).done((json) => {
      callback(json)
    }).fail((h, x, e) => {
      error(h, x, e)
    })
  }
}
