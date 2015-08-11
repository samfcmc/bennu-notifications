(function(module) {

  module.exports = function() {
    var baseUrl = '/api/notifications';
    var mostRecent = function(notifications) {
      var found = {timestamp: 0};
      for(var i = 0; i < notifications.length; i++) {
        var notification = notifications[i];
        if(notification.timestamp > found.timestamp) {
          found = notification;
        }
      }
      return found;
    }

    var request = function(url, method, data, success, error) {
      var dataRequest = null;
      if(method != 'GET') {
        dataRequest = JSON.stringify(data);
      }
      var request = new XMLHttpRequest();
      request.onload = function () {
        if(success) {
          success(JSON.parse(request.responseText));
        }
      }
      request.onerror = function() {
        if(error) {
          error(request.responseText);
        }
      }
      request.open(method, url, true);
      request.setRequestHeader("Content-type", "application/json");
      request.send(dataRequest);
    }

    return {
      getLastN: function(n, success, error) {
        var url = baseUrl + '/last/' + n;
        request(url, 'GET', {}, function(response) {
          if(response.length > 0) {
            this.last = mostRecent(response);
          }
          if(success) {
            success(response);
          }
        }.bind(this), error);
      },
      create: function(usernames, payload, success, error) {
        var data = {
          usernames: usernames,
          payload: payload
        };
        request(baseUrl, 'POST', data, success, error);
      },
      unread: function(success, error) {
        var url = baseUrl + '/unread';
        request(url, 'GET', {}, function(response) {
          if(response.length > 0) {
            this.last = mostRecent(response);
          }
          if(success) {
            success(response);
          }
        }.bind(this), error);
      },
      after: function(id, success, error) {
        var url = baseUrl + '/after/' + id;
        request(url, 'GET', {}, function(response) {
          if(response.length > 0) {
            this.last = mostRecent(response);
          }
          if(success) {
            success(response);
          }
        }.bind(this), error);
      },
      poll: function(seconds, success, error) {
        var interval = seconds * 1000;
        this.polling = setInterval(function() {
          if(this.last) {
            this.after(this.last.id, success, error);
          }
          else {
            this.unread(success, error);
          }
        }.bind(this), interval);
      },
      stopPolling: function() {
        clearInterval(this.polling);
      },
      read: function(id, success, error) {
        var url = baseUrl + '/' + id + '/read'
        request(url, 'POST', {}, success, error)
      }
    };
  };

}(module));
