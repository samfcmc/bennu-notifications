'use strict';

(function(module) {

  module.exports = function(React, Navbar, ReactRouter) {
    var RouteHandler = ReactRouter.RouteHandler;

    var Main = React.createClass({
      render: function() {
        return (
          <div className="container">
            <Navbar/>
            <RouteHandler/>
          </div>
        );
      }
    });

    return Main;
  }

}(module));
