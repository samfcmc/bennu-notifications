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
            self.last = mostRecent(response);
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
          success: function(response) {
            self.last = mostRecent(response);
            if(success) {
              success(response);
            }
          },
          error: error
        });
      },
      after: function(id, success, error) {
        var url = baseUrl + '?after=' + id;
        jQuery.ajax(url, {
          method: 'GET',
          success: success,
          error: error
        });
      },
      poll: function(seconds, success, error) {
        var interval = seconds * 1000;
        var self = this;
        if(!this.last) {
          this.unread(function(response) {
            self.poll(seconds, success, error)
          }, function() {
            throw 'Error getting unread notifications';
          });
        }
        else {
          setInterval(function() {
            self.after(self.last.id, success, error);
          }, interval);
        }
      }
    };
  };

}(module));
