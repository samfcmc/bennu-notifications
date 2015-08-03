'use strict';

(function(module) {

  module.exports = function(React) {
    var Notification = React.createClass({
      render: function() {
        return (
          <li className="list-group-item">{this.props.data}</li>
        );
      }
    });

    return Notification;
  };

}(module));
