'use strict';

(function(module) {

  module.exports = function(React) {
    var Generate = React.createClass({
      render: function() {
        return (
          <button type="button"
          className="btn btn-default">
            <span className="glyphicon glyphicon-bell"></span>
            Generate a new notification
          </button>
        );
      }
    });

    return Generate;
  }

}(module));
