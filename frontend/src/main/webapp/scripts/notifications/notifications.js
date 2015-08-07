(function(module) {

  module.exports = function(jQuery) {
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

    return {
      getLastN: function(n, success, error) {
        var url = baseUrl + '?lastN=' + n;
        var self = this;
        jQuery.ajax(url, {
          method: 'GET',
          success: function(response) {
            if(response.length > 0) {
              self.last = mostRecent(response);
            }
            if(success) {
              success(response);
            }
          },
          error: error
        });
      },
      create: function(usernames, payload, success, error) {
        var data = {
          usernames: usernames,
          payload: payload
        };
        jQuery.ajax(baseUrl, {
          data: JSON.stringify(data),
          contentType: "application/json; charset=utf-8",
          method: 'POST',
          success: success,
          error: error
        });
      },
      unread: function(success, error) {
        var url = baseUrl + '/unread';
        var self = this;
        jQuery.ajax(url, {
          method: 'GET',
          success: success,
          error: error
        });
      },
      after: function(id, success, error) {
        var url = baseUrl + '?after=' + id;
        var self = this;
        jQuery.ajax(url, {
          method: 'GET',
          success: function(response) {
            if(response.length > 0) {
              self.last = mostRecent(response);
            }
            if(success) {
              success(response);
            }
          },
          error: error
        });
      },
      poll: function(seconds, success, error) {
        var interval = seconds * 1000;
        var self = this;
        setInterval(function() {
          if(self.last) {
            self.after(self.last.id, success, error);
          }
        }, interval);
      },
      read: function(id, success, error) {
        var url = baseUrl + '/' + id + '/read'
        jQuery.ajax(url, {
          contentType: "application/json; charset=utf-8",
          method: 'POST',
          success: success,
          error: error
        });
      }
    };
  };

}(module));
