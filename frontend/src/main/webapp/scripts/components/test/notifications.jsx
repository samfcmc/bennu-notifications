'use strict';

(function(module) {

  module.exports = function(React, New, List) {
    var Notifications = React.createClass({
      render: function() {
        return (
          <div className="row">
            <New></New>
            <List type="Unread"></List>
            <List type="Read"></List>
          </div>
        );
      }
    });

    return Notifications;
  }

}(module));
