'use strict';

(function(module) {

  module.exports = function(React, Navbar, ReactRouter, user) {
    var RouteHandler = ReactRouter.RouteHandler;

    var Main = React.createClass({
      render: function() {
        return (
          <div className="container">
            <Navbar user={user}/>
            <RouteHandler/>
          </div>
        );
      }
    });

    return Main;
  }

}(module));
