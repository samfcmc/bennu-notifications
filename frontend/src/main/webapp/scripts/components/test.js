'use strict';

(function(module) {

  module.exports = function(React) {
    var Test = React.createClass({
      render: function() {
        return (
          <div>
            <h2>Test</h2>
          </div>
        );
      }
    });

    return Test;
  }

}(module));
