'use strict';

(function(module) {

  module.exports = function(React, New, List, NotificationsClient) {
    var Notifications = React.createClass({
      get: function(success, error) {
        return NotificationsClient.getLastN(10, success, error);
      },
      render: function() {
        return (
          <div className="row">
            <New></New>
            <List source={this.get}></List>
          </div>
        );
      }
    });

    return Notifications;
  }

}(module));
