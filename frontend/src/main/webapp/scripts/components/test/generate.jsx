'use strict';

(function(module) {

  module.exports = function(React, NotificationsClient) {
    var Generate = React.createClass({
      createTestNotification: function() {
        var usernames = ['admin'];
        var payload = {test: 'testing...'};
        NotificationsClient.create(usernames, payload, function(response) {
          console.log(response);
        });
      },
      render: function() {
        return (
          <button type="button"
          className="btn btn-default"
          onClick={this.createTestNotification}>
            <span className="glyphicon glyphicon-bell"></span>
            Generate a new notification
          </button>
        );
      }
    });

    return Generate;
  }

}(module));
