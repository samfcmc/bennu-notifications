'use strict';

(function(module) {
  module.exports = function(React) {
    var Admin = React.createClass({
      render: function() {
        return (
          <div className="row">
            <h2>Admin</h2>
          </div>
        );
      }
    });

    return Admin;
  }

}(module))
