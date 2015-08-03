'use strict';

(function(module) {

  module.exports = function(React, Generate, Notifications) {
    var Test = React.createClass({
      render: function() {
        return (
          <div className="row">
            <div className="row">
              <h2>Test</h2>
            </div>
            <div className="row">
              <div className="col-lg-6">
                <Generate></Generate>
              </div>
              <div className="col-lg-6">
                <Notifications></Notifications>
              </div>
            </div>
          </div>
        );
      }
    });

    return Test;
  }

}(module));
